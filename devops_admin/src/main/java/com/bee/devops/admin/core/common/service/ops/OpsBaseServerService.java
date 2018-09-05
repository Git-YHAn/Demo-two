package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.utils.ExecutorUtils;
import com.bee.devops.admin.common.utils.MapUtils;
import com.bee.devops.admin.common.utils.Shell;
import com.bee.devops.admin.core.asynctask.InitServerTask;
import com.bee.devops.admin.core.common.dao.ops.OpsAssembleAppDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerType;
import com.bee.devops.admin.core.vo.request.DeployHostInitialRequest;
import com.bee.devops.admin.core.vo.request.DeployHostRequest;
import com.bee.devops.admin.core.vo.response.DeployAssetsHostVo;
import com.bee.devops.admin.core.vo.response.HostAppEnvVo;
import com.bee.devops.admin.core.vo.response.ServerDepAppStatusVo;
import com.bee.devops.admin.core.vo.response.ServerStatusVo;
import com.bee.devops.admin.core.vo.response.ServerUseStatusResponse;
import com.github.pagehelper.PageHelper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static com.bee.devops.admin.common.utils.MapUtils.MAP;

/**
 * 服务器操作相关服务
 *
 * @author Administrator
 *
 */
@Service
@Transactional
public class OpsBaseServerService {
	private final static Logger logger = Logger.getLogger(OpsBaseServerService.class);

	@Autowired
	OpsBaseServerDao opsBaseServerDao;
	@Autowired
	OpsAssembleAppDao opsAssembleAppDao;

	/**
	 * 验证服务器是否能够连通
	 *
	 * @param address
	 * @param port
	 * @return
	 */
	public boolean verifyAddress(Shell shell) {
		Session initSession = shell.initSession();
		if(initSession != null) {
			initSession.disconnect();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据服务器ID查询服务器信息
	 *
	 * @param hostId
	 * @return
	 */
	public DeployAssetsHostVo selectById(long hostId) {
		return opsBaseServerDao.selectById(hostId);
	}

	/**
	 * 根据服务器ID查询服务器信息
	 *
	 * @param hostId
	 * @return
	 */
	public OpsBaseServer getById(long hostId) {
		return opsBaseServerDao.getById(hostId);
	}

	/**
	 * 根据名称查询服务器信息
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param assetsName
	 * @return
	 */
	public PageBean<DeployAssetsHostVo> queryEnvsByName(int pageNum, int pageSize, String assetsName) {
		PageHelper.startPage(pageNum, pageSize);
		if (assetsName != null) {
			assetsName = assetsName.trim();
		}

		List<DeployAssetsHostVo> lists = opsBaseServerDao.selectAllByName(assetsName);
		return new PageBean<>(lists);
	}

	/**
	 * 根据SSH地址查询服务器信息
	 *
	 * @param sshAddress
	 * @return
	 */
	public OpsBaseServer selectByAddress(String sshAddress) {
		return opsBaseServerDao.selectByAddress(sshAddress);
	}

	/**
	 * 添加服务器信息
	 *
	 * @return
	 */
	public int insertAssetsHost(DeployHostRequest deployHostRequest) {
		return opsBaseServerDao.insertAssetsHost(deployHostRequest);
	}

	/**
	 * 修改服务器信息
	 *
	 * @return
	 */
	public int updateById(DeployHostRequest deployHostRequest) {
		return opsBaseServerDao.updateById(deployHostRequest);
	}

	/**
	 * 根据服务器类型查询
	 *
	 * @return
	 */
	public List<OpsBaseServer> queryServerByType(Integer type) {
		return opsBaseServerDao.queryServerByType(type);
	}

	/**
	 * 查询SSH地址
	 *
	 * @param hostId
	 * @return
	 */
	public String selectSSH(Long hostId) {
		return opsBaseServerDao.selectSSH(hostId);
	}

	/**
	 * 根据ID删除服务器信息
	 *
	 * @param hostId
	 * @return
	 */
	public int deleteById(Long hostId) {
		return opsBaseServerDao.deleteById(hostId);
	}

	/**
	 * 查询服务器类型
	 *
	 * @return
	 */
	public List<OpsBaseServerType> selectType() {
		return opsBaseServerDao.showType();
	}

	/**
	 * 根据环境ID和应用ID查询服务器
	 *
	 * @param envId
	 * @param appId
	 * @return
	 */
	public PageBean<DeployAssetsHostVo> queryEnvsByName(int pageNum, int pageSize, Long envId, Long appId) {
		PageHelper.startPage(pageNum, pageSize);
		List<DeployAssetsHostVo> lists = opsBaseServerDao.selectByAppEnv(envId, appId);
		return new PageBean<>(lists);
	}

	/**
	 * 查询已配置的服务器
	 *
	 * @return
	 */
	public List<Long> selectEmploy(Long appEnvId) {
		return opsBaseServerDao.selectEmploy(appEnvId);
	}

	/**
	 * 保存服务器配置信息
	 *
	 * @param hostId
	 * @return
	 */
	public int insertAssetsConfig(Long appEnvId, Long hostId) {
		return opsBaseServerDao.insertAssetsConfig(appEnvId, hostId);
	}

	/**
	 * 查询服务器是否配置
	 *
	 * @param hostId
	 * @return
	 */
	public int selectAssetsRel(Long hostId) {
		return opsBaseServerDao.selectAssetsRel(hostId);
	}

	/**
	 * 查询未配置的服务器
	 *
	 * @return
	 */
	public List<OpsBaseServer> selectUnused(Long appEnvId) {
		return opsBaseServerDao.selectUnused(appEnvId);
	}

	/**
	 * 查询是否有应用环境
	 *
	 * @param appEnvId
	 * @return
	 */
	public int selectByAppEnvCount(Long appEnvId) {
		return opsBaseServerDao.selectByAppEnvCount(appEnvId);
	}

	/**
	 * 查询所有服务器
	 *
	 * @return
	 */
	public List<OpsBaseServer> selectAll() {
		return opsBaseServerDao.selectAll();
	}

	public List<OpsBaseServer> selectHostByaddress(String sshAddress) {
		return opsBaseServerDao.selectHostByaddress(sshAddress);
	}

	public int queryHostEnv(String envName) {
		return opsBaseServerDao.queryHostEnv(envName);
	}

	public int deleteAll(Long appEnvId) {
		return opsBaseServerDao.deleteByAppEnv(appEnvId);
	}

	public HostAppEnvVo selectRelation(Long hostId, Long appEnvId) {
		return opsBaseServerDao.selectRelation(hostId, appEnvId);
	}

	public int updateCurrentVersion(Long hostAppEnvId, Long operateUserId, String versionCode) {
		return opsBaseServerDao.updateCurrentVersion(hostAppEnvId, operateUserId, versionCode);
	}

	public OpsBaseServer getAccountById(Long hostId) {
		return opsBaseServerDao.getAccountById(hostId);
	}

	public void uploadAssets(List<DeployHostRequest> list) {
		for (DeployHostRequest aHostRequest : list) {
			opsBaseServerDao.insertAssetsHost(aHostRequest);
		}
	}

	/**
	 * 查询可以初始化的服务器,需要满足两个条件,1-> 初始化状态为0  2-> 已经关联了应用
	 *
	 * @param assetsName 服务器名称或者IP
	 * @param pageNum
	 * @param pageSize
	 * @param envName 环境
	 * @param appName 应用
	 * @param proName 项目
	 * @return
	 */
	public PageBean<DeployAssetsHostVo> initialableHosts(int pageNum, int pageSize, String assetsName, String envName, String appName, String proName) {
		PageHelper.startPage(pageNum, pageSize);
		List<DeployAssetsHostVo> list = opsBaseServerDao.initialableHosts(assetsName, envName, appName, proName);
		return new PageBean<>(list);
	}

	/**
	 * 初始化服务器,调用shell脚本
	 *
	 * @param list
	 */
	@Deprecated
	public void initHosts(DeployHostInitialRequest[] list) {
		ExecutorService pool = null;
		try {
			// 获取项目中的shell脚本
			ClassPathResource pathResource = new ClassPathResource("shell/lemi_init.sh");
			int length = list.length;
			// 固定大小的线程池
			pool = ExecutorUtils.getFixedThreadPool(length);
			for (DeployHostInitialRequest request : list) {
				Long assetsId = request.getAssetsId();
				String appName = request.getAppName();
				if (MAP.get(assetsId) == null) {
					synchronized (MAP) {
						if (MAP.get(assetsId) == null) {
							OpsBaseServer server = opsBaseServerDao.getById(assetsId);
							MAP.put(assetsId, server);
							pool.execute(new InitServerTask(opsBaseServerDao, server, pathResource, appName));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			// 关闭线程池
			if (pool != null) {
				pool.shutdown();
			}
		}
	}
	
	/**
	 * 初始化服务器,调用shell脚本
	 *
	 * @param list
	 */
	public void initHost(DeployHostInitialRequest request) {
		// 获取项目中的shell脚本
		ClassPathResource pathResource = new ClassPathResource("shell/lemi_init.sh");
		
		Long assetsId = request.getAssetsId();
		String appName = request.getAppName();
		OpsBaseServer server = opsBaseServerDao.getById(assetsId);
		
		 // 连接到对应的服务器
        Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
        Session session = shell.initSession();
        
        // 将脚本文件复制到服务器目录下
        String shellPath = "/usr/local/server/" + assetsId + "/lemi_init.sh";
        try {
			shell.scp(pathResource.getInputStream(), shellPath);
			  // 执行脚本的时候需要传入对应的应用名
            if (StringUtils.isNotEmpty(appName)) {
                String[] apps = StringUtils.split(appName, ",");
                for (String app : apps) {
                    String cmd = "sh " + shellPath + " " + app;
                    logger.info("执行远程脚本,cmd = " + cmd);
                    Channel channel = shell.exec(cmd);
                    List<String> result = shell.getExecResult(channel);
                    if (result != null) {
                        logger.info("执行完毕,关闭通道,result = " + result.size());
                    }
                }
                logger.info("服务器【" + server.getAssetsName() + "】脚本执行应用【" + appName + "】成功,已初始化");
                // 更新服务器初始化状态为 已初始化
                server.setInitialStatus(1);
                opsBaseServerDao.updateHostInitStatus(server);
                MapUtils.MAP.remove(assetsId);
            }
		} catch (Exception e) {
            logger.error("服务器【" + server.getAssetsName() + "】脚本执行应用【" + appName + "】异常,初始化失败", e);
			e.printStackTrace();
            throw new RuntimeException("服务器【" + server.getAssetsName() + "】脚本执行应用【" + appName + "】异常,初始化失败");
		} finally {
			session.disconnect();
		}
	}
	
	/**
	 * 改变服务器初始化状态
	 * 
	 * @param request
	 */
	public void updateInitStatus(DeployHostInitialRequest[] request) {
		for (DeployHostInitialRequest deployHostInitialRequest : request) {
			OpsBaseServer opsBaseServer = new OpsBaseServer();
			opsBaseServer.setAssetsId(deployHostInitialRequest.getAssetsId());
			opsBaseServer.setInitialStatus(2);
			opsBaseServerDao.updateHostInitStatus(opsBaseServer);
		}
	}
	
	/**
	 * 改变服务器初始化状态
	 * 
	 * @param request
	 */
	public void updateInitStatus(DeployHostInitialRequest request, Integer status) {
		OpsBaseServer opsBaseServer = new OpsBaseServer();
		opsBaseServer.setAssetsId(request.getAssetsId());
		opsBaseServer.setInitialStatus(status);
		opsBaseServerDao.updateHostInitStatus(opsBaseServer);
	}

	public int getServerUseInfo(Long envId, Long proId) {
		return opsBaseServerDao.getServerUseInfo(envId, proId);
	}
	
	/**
	 * 获取服务器应用的状态
	 * @author Yang Chunhai	 
	 * @return
	 */
	public List<ServerStatusVo> getServerStatus(Long proId, Long envId) {
		List<ServerDepAppStatusVo> serverDepAppStatus = opsBaseServerDao.getServerDepAppStatus(proId, envId);
		List<ServerDepAppStatusVo> stopLists = new ArrayList<>();
		List<ServerDepAppStatusVo> deployLists = new ArrayList<>();
		List<ServerDepAppStatusVo> runLists = new ArrayList<>();
		for (ServerDepAppStatusVo serverDepAppStatusVo : serverDepAppStatus) {
			if(serverDepAppStatusVo.getAppStatus() == -1){
				stopLists.add(serverDepAppStatusVo);
			} else if(serverDepAppStatusVo.getAppStatus() == 0){
				runLists.add(serverDepAppStatusVo);
			} else {
				deployLists.add(serverDepAppStatusVo);
			}
		}
		ServerStatusVo stopVo = new ServerStatusVo();
		ServerStatusVo deployVo = new ServerStatusVo();
		ServerStatusVo runVo = new ServerStatusVo();
		
		List<ServerStatusVo> serverStatusVos = new ArrayList<>();
		stopVo.setStatus(-1);
		stopVo.setLists(stopLists);
		serverStatusVos.add(stopVo);
		
		deployVo.setStatus(1);
		deployVo.setLists(deployLists);
		serverStatusVos.add(deployVo);
		
		runVo.setStatus(0);
		runVo.setLists(runLists);
		serverStatusVos.add(runVo);
		return serverStatusVos;
	}

	public ServerUseStatusResponse getServerUseStatus() {
		return opsBaseServerDao.getServerUseStatus();
	}
}
