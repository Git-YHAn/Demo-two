package com.bee.devops.admin.core.controller.ops.base;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.*;
import com.bee.devops.admin.core.common.entity.ops.*;
import com.bee.devops.admin.core.common.service.ops.*;
import com.bee.devops.admin.core.vo.request.DeployHostInitialRequest;
import com.bee.devops.admin.core.vo.request.DeployHostRequest;
import com.bee.devops.admin.core.vo.request.ExecuteRequest;
import com.bee.devops.admin.core.vo.response.DeployAssetsHostVo;
import com.bee.devops.admin.core.vo.response.SaveAssetsHostParamVo;
import com.bee.devops.admin.core.vo.response.ServerStatusVo;
import com.bee.devops.admin.core.vo.response.ServerUseStatusResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 服务器信息控制层 
 * 
 * @author Administrator
 */
@Api(value = "服务器信息Controller")
@RestController
@RequestMapping(value = "/assets")
public class OpsBaseServerController extends BaseController {
	private final static Logger log = Logger.getLogger(OpsBaseServerController.class);

	@Autowired
	OpsBaseServerService opsBaseServerService;

	@Autowired
	OpsAssembleAppService opsAssembleAppService;

	@Autowired
	OpsBaseEnvService opsBaseEnvService;

	@Autowired
	OpsBaseServerTempService tempService;

	@Autowired
	OpsBaseServerExecHisService execHisService;

	@ApiOperation(value = "执行脚本命令", notes = "执行脚本命令")
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public ResultHandler<JSONArray> execute(@RequestBody ExecuteRequest request) {
		List<Long> assetsIds = request.getAssetsIds();
		String order = request.getOrder();
		Long moduleId = request.getModuleId();
		// 命令和模板不共存
		if (StringUtils.isEmpty(order)) {
			// 根据模板id查询模板对应的命令
			OpsBaseServerTemp serverTemp = tempService.getById(moduleId);
			order = serverTemp.getTplContent();
		}

		Long operateUserId = getAdminUser().getAdminUserId();
		JSONArray ja = new JSONArray();
		for (Long assetsId : assetsIds) {
			OpsBaseServer server = opsBaseServerService.getById(assetsId);
			String sshAddress = server.getSshAddress();
			Integer sshPort = server.getSshPort();
			String account = server.getHostAccount();
			String password = server.getHostPassword();

			long currentTime = System.currentTimeMillis();
			String key = assetsId + "_" + currentTime;
			// key应该立刻返回给前端,异步去处理服务器返回的结果
			JSONObject jo = new JSONObject();
			jo.put("ssh", sshAddress);
			jo.put("key", key);
			jo.put("result", "");
			jo.put("disabled", false);
			ja.add(jo);

			ExecutorService cachedThreadPool = ExecutorUtils.getCachedThreadPool();
			try {
				Connection connection = SshUtils.getConnection(sshAddress, account, password, sshPort);
				Session session = connection.openSession();

				// 选择了模板
				if (moduleId != null) {
					try {
						// 复制脚本文件到服务器
						SCPClient scpClient = connection.createSCPClient();
						String remoteFileName = moduleId + ".sh";
						String remotePath = "/tmp/" + remoteFileName;
						scpClient.put(order.getBytes("utf-8"), remoteFileName, "/tmp/");
						// 执行拷贝到服务器的脚本
						session.execCommand("sh " + remotePath + " 2>&1");
						// 移除脚本文件
						SFTPv3Client client = new SFTPv3Client(connection);
						client.rm(remotePath);
					} catch (Exception e) {
						log.error("执行模板脚本异常", e);
					}
				} else {
					session.execCommand(order); // 执行命令
				}

				// 异步获取执行结果
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SshUtils.getResult(key, session); // 获取执行结果
						} catch (Exception e) {
							log.error(e);
							GlobalContainer.MAP.put(key, "exception: " + e.getMessage());
						} finally {
							if (session != null) {
								session.close();
							}
							connection.close();
						}
					}
				});
			} catch (Exception e) {
				log.error(e);
				// 记录服务器的出错状态
				GlobalContainer.MAP.put(key, "exception: " + e.getMessage());
			} finally {
				//保存执行记录
				execHisService.insertExecHis(order, assetsId, currentTime, operateUserId, moduleId);
			}
		}

		return ResultHandler.success(ja);
	}


	/**
	 * 根据key获取服务器返回结果
	 *
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/getResult/{key}", method = RequestMethod.GET)
	public ResultHandler<String> getResult(@PathVariable String key) {
		Map<String, String> map = GlobalContainer.MAP;
		String result = "";
		if (map.containsKey(key)) {
			result = map.get(key);
		}
		return ResultHandler.success(result);
	}

	/**
	 * 根据key取消获取结果
	 *
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/cancel/{key}", method = RequestMethod.GET)
	public ResultHandler<String> cancel(@PathVariable String key) {
		Map<String, Session> sessionMap = GlobalContainer.SESSION_MAP;
		Session session = sessionMap.get(key);
		if (session != null) {
			session.close();
			sessionMap.remove(key);
			GlobalContainer.MAP.remove(key);
		}
		return ResultHandler.success("已取消");
	}

	/**
	 * 验证服务器是否存在
	 * 
	 * @return
	 */
	@ApiOperation(value = "验证服务器是否存在", notes = "验证服务器是否存在")
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ResultHandler<String> verifyAddress(@RequestBody DeployHostRequest deployHostRequest) {
		Shell shell = new Shell(deployHostRequest.getSshAddress(), deployHostRequest.getHostAccount(),
				deployHostRequest.getHostPassword(), Integer.parseInt(deployHostRequest.getSshPort()));
		if (opsBaseServerService.verifyAddress(shell)) {
			return ResultHandler.success("连接成功");
		}
		return ResultHandler.error("连接失败");
	}

	/**
	 * 根据名称或者SSH地址查询服务器信息
	 * 
	 * @author Yang bin
	 * @param assetsName
	 * @return
	 */
	@ApiOperation(value = "根据名称查询服务器信息", notes = "根据名称或者SSH地址查询服务器信息")
	@GetMapping(value = {"/query/{getPageNum}/{getPageSize}/{assetsName}", "/query/{getPageNum}/{getPageSize}"})
	public ResultHandler<PageBean<DeployAssetsHostVo>> queryByNamePageData(
			@PathVariable(name = "assetsName", required = false) String assetsName,
			@PathVariable(name = "getPageNum", required = false) Integer getPageNum,
			@PathVariable(name = "getPageSize", required = false) Integer getPageSize) {
		PageBean<DeployAssetsHostVo> pageBean = opsBaseServerService.queryEnvsByName(getPageNum, getPageSize, assetsName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询可以初始化的服务器,需要满足两个条件,1-> 初始化状态为0  2-> 已经关联了应用
	 *
	 * @return
	 */
	@ApiOperation(value = "查询可以初始化的服务器", notes = "查询可以初始化的服务器")
	@PostMapping(value = "/query/uninitialized/")
	public ResultHandler<PageBean<DeployAssetsHostVo>> initialalblePageData(@RequestBody DeployHostInitialRequest request) {
		Integer pageNum = request.getPageNum();
		Integer pageSize = request.getPageSize();
		String nameOrSsh = request.getAssetsNameOrSsh();
		String envName = request.getEnvName();
		String appName = request.getAppName();
		String proName = request.getProName();
		PageBean<DeployAssetsHostVo> pageBean = opsBaseServerService.initialableHosts(pageNum, pageSize, nameOrSsh, envName, appName, proName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 初始化服务器
	 *
	 * @return
	 */
	/*@ApiOperation(value = "初始化服务器", notes = "初始化服务器")
	@PostMapping(value = "/init/")
	@Deprecated
	public ResultHandler<String> init(@RequestBody DeployHostInitialRequest[] request) {
		if (request != null && request.length > 0) {
			opsBaseServerService.updateInitStatus(request);
			opsBaseServerService.initHosts(request);
		}
		return ResultHandler.success("服务器正在初始化");
	}*/
	
	/**
	 * 初始化服务器
	 *
	 * @return
	 */
	@ApiOperation(value = "初始化服务器", notes = "初始化服务器")
	@PostMapping(value = "/init")
	public ResultHandler<String> inits(@RequestBody DeployHostInitialRequest request) {
		OpsBaseServer opsBaseServer = null;
		synchronized(this){
			opsBaseServer = opsBaseServerService.getAccountById(request.getAssetsId());
			// 判断服务器是否已经正在初始化或初始化完成
			if(opsBaseServer.getInitialStatus() != 0) {
				return ResultHandler.error("服务器【"+opsBaseServer.getAssetsName()+"】正在初始化或已初始化");
			}
			// 将要初始化的服务器的状态改为初始化中
			opsBaseServerService.updateInitStatus(request, 2);
		}
		try {
			//执行初始化
			opsBaseServerService.initHost(request);
		} catch (Exception e) {
			// 将要初始化的服务器的状态改为未初始化
			opsBaseServerService.updateInitStatus(request, 0);
			return ResultHandler.error("服务器【"+opsBaseServer.getAssetsName()+"】初始化失败");
		}
		return ResultHandler.success("服务器【"+opsBaseServer.getAssetsName()+"】初始化完成", null);
	}

	/**
	 * 添加服务器信息
	 *
	 * @return
	 */
	@ApiOperation(value = "添加服务器", notes = "添加服务器信息")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultHandler<String> addAssetsHost(@RequestBody DeployHostRequest deployHostRequest) {
		// 获取添加服务器的操作员并保存
		deployHostRequest.setOperateUserId(getAdminUser().getAdminUserId());
		if (opsBaseServerService.selectByAddress(deployHostRequest.getSshAddress()) != null) {
			return ResultHandler.error("地址为" + deployHostRequest.getSshAddress() + "的IP重复");
		}
		// 新增的服务器 均是 未使用状态
		deployHostRequest.setInitialStatus(0);
		if (opsBaseServerService.insertAssetsHost(deployHostRequest) == 1) {
			return ResultHandler.success("添加成功!");
		}
		return ResultHandler.error("添加失败!");
	}

	/**
	 * 标准模板下载
	 * @return
	 */
	@RequestMapping(value = "/download")
	public ResultHandler<String> downloadAssets() {
		DownloadUtils.download("download/serverModule.xlsx", "服务器标准模板.xlsx", getResponse());
		return ResultHandler.success("文件下载成功");
	}

	/**
	 * 服务器批量导入，模板需要和以下设置属性顺序一致
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResultHandler<String> uploadAssetsHost(@RequestParam("file") MultipartFile file) {
        StringBuilder info = new StringBuilder();
        AtomicInteger count = new AtomicInteger(0);
        int size;
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            Workbook book;
            if (StringUtils.equals(extension, "xlsx") || StringUtils.equals(extension, "xls")) {
                book = new XSSFWorkbook(inputStream);
            } else {
                return ResultHandler.error("请按标准模板上传");
            }
            List<DeployHostRequest> list = ExcelUtils.transition(book, getAdminUser().getAdminUserId());
            size = list.size();
            for (DeployHostRequest hostRequest : list) {
                // 如果ip已经重复了 则过滤,不再添加
                String address = hostRequest.getSshAddress();
                if (opsBaseServerService.selectByAddress(address) != null) {
                    info.append("【" + address + "】的服务器已存在").append("\n");
                    count.incrementAndGet();
                    continue;
                }
                opsBaseServerService.insertAssetsHost(hostRequest);
            }
        } catch (Exception e) {
            log.error("模板导入异常", e);
            return ResultHandler.error("模板导入异常:" + e.getMessage());
        }

        // 说明有导入成功的,那总体就算成功
        String tips = info.toString();
        if (size > count.get()) {
            return ResultHandler.success("成功导入【" + (size - count.get()) + "】条记录~" + tips);
        } else {
            return ResultHandler.error(tips);
        }
    }
	
	

	/**
	 * 根据id查询服务器信息
	 * 
	 * @param hostId
	 * @return
	 */
	@ApiOperation(value = "查询服务器", notes = "根据id查询服务器的基本信息")
	@RequestMapping(value = "/select/byid/{hostId}", method = RequestMethod.GET)
	public ResultHandler<DeployAssetsHostVo> queryById(@PathVariable("hostId") Long hostId) {
		DeployAssetsHostVo deployAssetsHostvo = opsBaseServerService.selectById(hostId);
		return ResultHandler.success(deployAssetsHostvo);
	}

	/**
	 * 根据id删除服务器
	 * 
	 * @param hostId
	 * @return
	 */
	@ApiOperation(value = "删除服务器", notes = "根据删除服务器的信息")
	@RequestMapping(value = "/delete/{hostId}", method = RequestMethod.DELETE)
	public ResultHandler<String> deleteById(@PathVariable("hostId") Long hostId) {
		if (opsBaseServerService.selectAssetsRel(hostId) > 0) {
			return ResultHandler.error("删除失败!该服务器尚有关联资源或用户");
		}
		if (opsBaseServerService.deleteById(hostId) == 1) {
			return ResultHandler.success("删除成功");
		}
		return ResultHandler.error("删除失败");
	}

	/**
	 * 根据id修改服务器
	 * 
	 * @return
	 */
	@ApiOperation(value = "修改服务器信息", notes = "修改服务器的基本信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updateById(@RequestBody DeployHostRequest deployHostRequest) {
		DeployAssetsHostVo selectById = opsBaseServerService.selectById(deployHostRequest.getAssetsId());
		if (selectById != null && selectById.getSshAddress().equals(deployHostRequest.getSshAddress())
				|| opsBaseServerService.selectByAddress(deployHostRequest.getSshAddress()) == null) {
			if (opsBaseServerService.updateById(deployHostRequest) == 1) {
				return ResultHandler.success("修改成功");
			}
		}
		return ResultHandler.error("修改失败");
	}

	/**
	 * 查询服务器账号密码
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询服务器账号密码", notes = "查询服务器账号密码")
	@RequestMapping(value = "/get_account", method = RequestMethod.GET)
	public ResultHandler<OpsBaseServer> getAccountById(@RequestParam("hostId") Long hostId) {
		OpsBaseServer host = opsBaseServerService.getAccountById(hostId);
		return ResultHandler.success(host);
	}

	/**
	 * 查询服务器类型 及名称
	 * 
	 * @return
	 */
	@ApiOperation(value = "显示所有服务器类型名称", notes = "用于在前段展示服务器类型下拉栏")
	@RequestMapping(value = "/select_type", method = RequestMethod.POST)
	public ResultHandler<List<OpsBaseServerType>> queryType() {
		List<OpsBaseServerType> list = opsBaseServerService.selectType();
		return ResultHandler.success(list);
	}

	/**
	 * 根据环境ID和应用ID查询服务器
	 * 
	 * @param envId
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "根据环境ID和应用ID查询服务器", notes = "根据环境ID和应用ID查询服务器")
	@RequestMapping(value = "/select_byenvapp", method = RequestMethod.GET)
	public ResultHandler<PageBean<DeployAssetsHostVo>> queryByAppEnv(@RequestParam("envId") Long envId,
			@RequestParam("appId") Long appId) {
		PageBean<DeployAssetsHostVo> pageBean = opsBaseServerService.queryEnvsByName(getPageNum(), getPageSize(), envId,
				appId);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询未使用的服务器
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询未使用的服务器", notes = "查询未使用的服务器")
	@RequestMapping(value = "/select_unused", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseServer>> queryUnused(@RequestParam("envId") Long envId,
			@RequestParam("appId") Long appId) {

		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		List<OpsBaseServer> list = opsBaseServerService.selectUnused(opsAssembleApp.getAppEnvId());
		return ResultHandler.success(list);
	}

	/**
	 * 保存服务器配置信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "保存服务器配置信息", notes = "保存服务器配置信息")
	@RequestMapping(value = "/set/appenv", method = RequestMethod.POST)
	public ResultHandler<String> saveAssetsHost(@RequestBody SaveAssetsHostParamVo saveAssetsHostParamVo) {
		// 临时删除数据
		opsBaseServerService.deleteAll(saveAssetsHostParamVo.getAppEnvId());
		int successCount = 0;
		int errorCount = 0;
		for (int i = 0; i < saveAssetsHostParamVo.getHostId().length; i++) {
			if (opsBaseServerService.insertAssetsConfig(saveAssetsHostParamVo.getAppEnvId(),
					saveAssetsHostParamVo.getHostId()[i]) == 1) {
				successCount++;
			} else {
				errorCount++;
			}
		}
		if (errorCount == 0) {
			return ResultHandler.success("全部成功");
		}
		return ResultHandler.success("成功数为：" + successCount + "，失败数为：" + errorCount);
	}

	/**
	 * 查询已配置的服务器
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询已配置的服务器", notes = "查询已配置的服务器")
	@RequestMapping(value = "/query/servers/{appEnvId}", method = RequestMethod.GET)
	public ResultHandler<List<Long>> queryEmploy(@PathVariable("appEnvId")Long appEnvId) {
		List<Long> list = opsBaseServerService.selectEmploy(appEnvId);
		return ResultHandler.success(list);
	}

	/**
	 * 不同环境下的主机数量显示
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询不同环境下主机的数量", notes = "查询不同环境下主机的数量")
	@RequestMapping(value = "/select_host_env", method = RequestMethod.GET)
	public ResultHandler<Map<String, Integer>> queryHostEnv() {
		List<OpsBaseEnv> list = opsBaseEnvService.getAllEnvs();
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++) {
			int count = opsBaseServerService.queryHostEnv(list.get(i).getEnvName());
			map.put(list.get(i).getEnvName(), count);
		}
		return ResultHandler.success(map);
	}

	/**
	 * 查询所有可用服务器
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有", notes = "查询所有")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseServer>> queryAll() {
		return ResultHandler.success(opsBaseServerService.selectAll()) ;
	}

	/**
	 * 按类型查询服务器
	 *
	 * @return
	 */
	@ApiOperation(value = "查询所有", notes = "查询所有")
	@RequestMapping(value = "/query/bytype/{type}", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseServer>> queryServerByType(@PathVariable("type")Integer type) {
		return ResultHandler.success(opsBaseServerService.queryServerByType(type));
	}

	/**
     * 获取服务器运行状态
     * @author Yang Chunhai	 
     * @return
     */
    @RequestMapping(value = "/status/search/{proId}/{envId}", method = RequestMethod.GET)
    public ResultHandler<List<ServerStatusVo>> getServerStatusData(@PathVariable Long proId, @PathVariable Long envId) {
    	List<ServerStatusVo> serverStatus = opsBaseServerService.getServerStatus(proId, envId);
    	return ResultHandler.success(serverStatus);
    }
    
    /**
     * 查询服务器的使用状态
     * @author Yang Chunhai	 
     * @return
     */
    @RequestMapping(value = "/search/use/status", method = RequestMethod.GET)
    public ResultHandler<ServerUseStatusResponse> getServerUseStatus() {
    	ServerUseStatusResponse serverStatus = opsBaseServerService.getServerUseStatus();
    	return ResultHandler.success(serverStatus == null ? new ServerUseStatusResponse() : serverStatus);
    }
}

