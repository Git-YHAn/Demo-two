package com.bee.devops.admin.core.controller.ops.assemble;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.DateUtils;
import com.bee.devops.admin.common.utils.ExecutorUtils;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.git.Repo;
import com.bee.devops.admin.core.asynctask.AsyncRestartTask;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseProfileType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseRegionType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseZoneType;
import com.bee.devops.admin.core.common.service.common.AppVersionControlService;
import com.bee.devops.admin.core.common.service.ops.OpsAppConfigService;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProfileTypeService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseRegionTypeService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseZoneTypeService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppOrderInfoService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppService;
import com.bee.devops.admin.core.controller.ops.assemble.request.AppLogsRequest;
import com.bee.devops.admin.core.controller.ops.assemble.response.AppLogsResponse;
import com.bee.devops.admin.core.vo.request.PublishAppRestartRequest;
import com.bee.devops.admin.core.vo.request.PublishAppStopRequest;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.AppServiceManagerVo;
import com.bee.devops.admin.core.vo.response.MicroServiceTypeVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoAppEnvVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "应用环境模块Controller")
@RestController
@RequestMapping(value = "/appenv")
public class OpsAssembleAppController extends BaseController {
	final static Logger log = Logger.getLogger(OpsAssembleAppController.class);
	private static final Object lock = new Object();

	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	OpsBaseProService opsBaseProService;
	@Autowired
	AsyncRestartTask asyncRestartTask;
	@Autowired
	OpsBaseProfileTypeService profileTypeService;
	@Autowired
	OpsBaseRegionTypeService regionTypeService;
	@Autowired
	OpsBaseZoneTypeService zoneTypeService;
	@Autowired
	AppVersionControlService appVersionControlService;
	@Autowired
	OpsVersionAppService opsVersionAppService;
	@Autowired
	OpsBaseAppService opsBaseAppService;
	@Autowired
	OpsAppConfigService opsAppConfigService;
	@Autowired
	OpsDepAppOrderInfoService opsDepAppOrderInfoService;

	/**
	 * 上传应用压缩包
	 *
	 * @param file
	 *            上传的zip压缩包
	 * @param comments
	 *            备注信息
	 * @return
	 */
	@RequestMapping(value = "/zip/upload", method = RequestMethod.POST)
	public ResultHandler<String> uploadZip(@RequestParam("file") MultipartFile file, @RequestParam("comments") String comments,
	        @RequestParam("appEnvId") Long appEnvId, @RequestParam("appId") Long appId, @RequestParam("proId") Long proId) {
		try {
			String filename = file.getOriginalFilename();
			log.info("filename = " + filename);
			// comments即为提交审核的信息
			log.info("comments = " + comments);
			ExecutorService threadPool = ExecutorUtils.getCachedThreadPool();
			String realPath = getRequest().getSession().getServletContext().getRealPath("/zips");
			String username = getAdminUser().getUsername();
			File zips = new File(realPath, filename);
			if (!zips.getParentFile().exists()) {
				zips.getParentFile().mkdirs();
				zips.createNewFile();
			}
			// 上传zip压缩包到服务器上,这里就放到项目路径下
			file.transferTo(zips);
			threadPool.execute(new UploadTask(zips, comments, appEnvId, appId, proId, username));
		} catch (IOException e) {
			log.error(e);
		}
		return ResultHandler.success("应用处理中...");
	}

	/**
	 * 应用重启
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author
	 */
	@ApiOperation(value = "应用重启", notes = "应用重启")
	@RequestMapping(value = "/restart", method = RequestMethod.POST)
	public ResultHandler<String> restart(@RequestBody PublishAppRestartRequest request) throws UnsupportedEncodingException {
		opsAssembleAppService.restartApplication(request.getServerIds(), request.getAppEnvId(), request.getDeployType());
		return ResultHandler.success("重启成功", "重启成功");
	}

	/**
	 * 停止应用
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author
	 */
	@ApiOperation(value = "应用停止", notes = "应用停止")
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public ResultHandler<String> stop(@RequestBody PublishAppStopRequest request) throws UnsupportedEncodingException {
		opsAssembleAppService.stopApplication(request.getServerIds(), request.getAppEnvId(), request.getDeployType());
		return ResultHandler.success("停止成功", "停止成功");
	}

	/**
	 * 查询所有应用环境
	 *
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "查询所有应用环境", notes = "查询所有应用环境")
	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	public ResultHandler<PageBean<OpsAssembleApp>> getAllAppEnvs() {
		PageBean<OpsAssembleApp> pageBean = opsAssembleAppService.queryAppEnvByPageData(getPageNum(), getPageSize());
		return ResultHandler.success(pageBean);
	}

	/**
	 * 按条件查询应用环境
	 *
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "按条件查询", notes = "按环境，项目，应用名查询应用环境")
	@RequestMapping(value = { "/search/appenvs/{pageNum}/{pageSize}/{proId}/{envId}",
	        "/search/appenvs/{pageNum}/{pageSize}/{proId}/{envId}/{appName}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<AppServiceManagerVo>> searchAppEnvs(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Long envId,
	        @PathVariable Long proId, @PathVariable(required = false) String appName) {
		PageBean<AppServiceManagerVo> pageBean = opsAssembleAppService.searchAppEnvs(pageNum, pageSize, envId, proId, appName);
		return ResultHandler.success(pageBean);
	}

	@ApiOperation(value = "根据项目环境查询所有应用", notes = "根据项目环境查询所有应用")
	@RequestMapping(value = { "/query/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<List<AppServiceManagerVo>> queryAppEnvs(@PathVariable Long envId, @PathVariable Long proId) {
		List<AppServiceManagerVo> list = opsAssembleAppService.queryAppEnvs(envId, proId, null);
		return ResultHandler.success(list);
	}

	/**
	 * 更新应用环境
	 *
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "更新", notes = "修改应用环境")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updateAppEnv(@RequestBody OpsAssembleApp appenvTemp) {
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.selectAppEnvById(appenvTemp.getAppEnvId());
		if (opsAssembleApp != null) {
			opsAssembleApp.setConfigGitUrl(appenvTemp.getConfigGitUrl());
			opsAssembleApp.setDeployAppGitUrl(appenvTemp.getDeployAppGitUrl());
			opsAssembleApp.setLabels(appenvTemp.getLabels());
			opsAssembleApp.setNamespace(appenvTemp.getNamespace());
			opsAssembleApp.setUpdateTime(new Date());
			opsAssembleApp.setDeployAppImagesGitUrl(appenvTemp.getDeployAppImagesGitUrl());
			Long appTypeId = appenvTemp.getAppTypeId();
			if (appTypeId == OpsBaseAppType.APP_TYPE_MS) {
				opsAssembleApp.setMsPort(appenvTemp.getMsPort());
				opsAssembleApp.setMsConfigUrl(appenvTemp.getMsConfigUrl());
				opsAssembleApp.setMsEurekaAddress(appenvTemp.getMsEurekaAddress());
				opsAssembleApp.setMsProfileTypeId(appenvTemp.getMsProfileTypeId());
				opsAssembleApp.setMsRegionTypeId(appenvTemp.getMsRegionTypeId());
				opsAssembleApp.setMsZoneTypeId(appenvTemp.getMsZoneTypeId());
			} else if (appTypeId == OpsBaseAppType.APP_TYPE_WEB || appTypeId == OpsBaseAppType.APP_TYPE_STATIC) {
				opsAssembleApp.setMsPort(appenvTemp.getMsPort());
			}
			if (opsAssembleAppService.updateAppEnv(opsAssembleApp) == 1) {
				return ResultHandler.success("更新成功");
			}
		}
		return ResultHandler.error("更新失败");
	}

	/**
	 * 根据应用环境ID查询应用环境
	 *
	 * @param appEnvId
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "根据应用环境ID查询应用环境", notes = "根据应用环境ID查询应用环境")
	@GetMapping(value = "/query/byid/{appEnvId}")
	public ResultHandler<AppServiceManagerVo> queryAppEnvById(@PathVariable("appEnvId") Long appEnvId) {
		AppServiceManagerVo appServiceManagerVo = opsAssembleAppService.selectAppEnvById(appEnvId);
		return ResultHandler.success(appServiceManagerVo);
	}

	/**
	 * 查询所有应用的数量
	 *
	 * @return
	 */
	@ApiOperation(value = "查询所有应用类型", notes = "查询所有应用类型")
	@GetMapping(value = "/query/ms/types")
	public ResultHandler<MicroServiceTypeVo> queryMicroServiceTypes() {
		List<OpsBaseProfileType> profileTypes = profileTypeService.getAllProfileTypes();
		List<OpsBaseZoneType> zoneTypes = zoneTypeService.getAllZoneTypes();
		List<OpsBaseRegionType> regionTypes = regionTypeService.getAllRegionTypes();

		MicroServiceTypeVo microServiceTypeVo = new MicroServiceTypeVo();
		microServiceTypeVo.setProfileTypes(profileTypes);
		microServiceTypeVo.setRegionTypes(regionTypes);
		microServiceTypeVo.setZoneTypes(zoneTypes);

		return ResultHandler.success(microServiceTypeVo);
	}

	@GetMapping(value = "/query/envent/{appId}/{envId}")
	public ResultHandler<OpsAssembleApp> queryAppEnvEnvent(@PathVariable Long appId, @PathVariable Long envId) {
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		return ResultHandler.success(opsAssembleApp);
	}

	private class UploadTask implements Runnable {
		private File zips;
		private String comments;
		private Long appEnvId;
		private Long appId;
		private Long proId;
		private String username;

		UploadTask(File zips, String comments, Long appEnvId, Long appId, Long proId, String username) {
			this.zips = zips;
			this.comments = comments;
			this.appEnvId = appEnvId;
			this.appId = appId;
			this.proId = proId;
			this.username = username;
		}

		@Override
		public void run() {
			OpsBaseApp app = opsBaseAppService.getAppById(appId);
			OpsAssembleApp assembleApp = null;
			try {
				synchronized (lock) {
					// 上传之前先查询该应用的上传状态,上传中的不允许再上传
					assembleApp = opsAssembleAppService.getByappEnvId(appEnvId);
					int uploadStatus = assembleApp.getUploadStatus();
					if (uploadStatus == OpsAssembleApp.UPLOADING) {
						throw new UploadingException("应用【" + app.getAppName() + "】正在上传中...");
					}

					// 更新当前应用的上传状态为上传中
					assembleApp.setUploadStatus(OpsAssembleApp.UPLOADING);
					opsAssembleAppService.updateAppEnv(assembleApp);
				}

				AppEnvProCodeVo codeVo = opsVersionAppService.getProCodeAndAppCode(appId, proId);
				String branchName = username + DateUtils.dateToStr(new Date(), "yyyyMMddHHmmss");
				// 从主分支新建本地分支
				String newBranchName = appVersionControlService.createLocalBranchFromMaster(app, codeVo, branchName);
				log.info("newBranchName = " + newBranchName);

				// 本地仓库地址
				String repoPath = appVersionControlService.getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
				File directory = new File(repoPath);
				// 先切换到本地分支,获取当前最新的提交id
				Repo repo = appVersionControlService.checkoutLocalBranch(app, codeVo, newBranchName);
				String beginCommitId = repo.getCurrentRepoHeadCommitId();
				log.info("beginCommitId = " + beginCommitId);
				// 删除本地分支除开.git文件夹的所有文件
				appVersionControlService.deleteRepoFile(repo, getPaths(directory));

				// 解压上传到服务器的zip压缩包到本地代码目录下
				Project project = new Project();
				Expand expand = new Expand();
				expand.setProject(project);
				expand.setSrc(zips);
				expand.setDest(directory);
				expand.execute();

				// 删除上传到服务器的压缩包文件
				FileUtils.deleteQuietly(zips);

				// 切换到本地分支,添加解压后的所有文件,提交本地分支,获取当前分支的最新提交id
				appVersionControlService.addLocalBranch(repo, getPaths(directory));
				String endCommitId = appVersionControlService.commitLocalBranch(repo, username, comments);
				log.info("endCommitId = " + endCommitId);
				// 推送本地分支到远程
				appVersionControlService.pushLocalBranch(repo, newBranchName);
				// 删除本地分支,删除本地分支时先切回master分支,不然本地分支无法删除(因为当前处于本地分支)
				repo.checkoutBranch("master");
				appVersionControlService.dropLocalBranch(repo, newBranchName);

				// 更新上传状态为上传完成
				log.info("更新应用【" + app.getAppName() + "】上传状态为上传完成..");
				assembleApp.setUploadStatus(OpsAssembleApp.UPLOAD_SUCCESS);
				opsAssembleAppService.updateAppEnv(assembleApp);
			} catch (Exception e) {
				if (e instanceof UploadingException) {
					log.error(e.getMessage());
					return;
				}
				log.error("提交审核异常,更新应用【" + app.getAppName() + "】上传状态为上传失败..", e);
				if (assembleApp != null) {
					assembleApp.setUploadStatus(OpsAssembleApp.UPLOAD_FAILURE);
					opsAssembleAppService.updateAppEnv(assembleApp);
				}
			}
		}
	}

	private class UploadingException extends RuntimeException {
		UploadingException(String message) {
			super(message);
		}

		UploadingException(String message, Throwable cause) {
			super(message, cause);
		}

		UploadingException(Throwable cause) {
			super(cause);
		}
	}

	private ArrayList<Path> getPaths(File directory) {
		ArrayList<Path> paths = new ArrayList<>();
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File fi : files) {
					if (!".git".equals(fi.getName())) {
						paths.add(fi.toPath());
					}
				}
			}
		}
		return paths;
	}

	@ApiOperation(value = "查询应用日志列表", notes = "查询应用日志列表")
	@PostMapping(value = "/query/app/logs")
	public ResultHandler<List<AppLogsResponse>> queryAppLogs(@RequestBody AppLogsRequest request) {
		String rootPath = OpsSysParameterUtil.getBackLogPath();
		StringBuilder directory = new StringBuilder(rootPath);
		OpsAssembleApp appInstance = opsAssembleAppService.getByappEnvId(request.getAppInstanceId());
		OpsBaseEnv opsBaseEnv = opsBaseEnvService.getEnvById(appInstance.getEnvId());
		OpsBasePro opsBaseProject = opsBaseProService.getProjectById(appInstance.getProId());
		directory.append("/" + opsBaseEnv.getEnvCode() + "/" + opsBaseProject.getProCode() + "/" + appInstance.getAppName());
		if (request.getAppInstanceDetailId() != null) {
			OpsDepAppOrderInfoAppEnvVo detail = opsDepAppOrderInfoService.getAppEnvAndDepAppVerAndServer(request.getAppInstanceDetailId());
			if (detail != null && detail.getServer() != null) {
				directory.append("/" + detail.getServer().getSshAddress());
			}
		}
		List<AppLogsResponse> fileTree = new ArrayList<>();
		List<AppLogsResponse> recursiveQueryFiles = recursiveQueryFiles(fileTree, new File(directory.toString()), new File(rootPath));
		return ResultHandler.success(recursiveQueryFiles);
	}

	public static List<AppLogsResponse> recursiveQueryFiles(List<AppLogsResponse> fileTree, File directory, File rootDir) {
		if (!directory.exists()) {
			log.error("目录不存在:" + directory.getAbsolutePath());
			return fileTree;
		}
		File[] listFiles = directory.listFiles();
		for (File f : listFiles) {
			List<AppLogsResponse> children = new ArrayList<>();
			String path = f.getAbsolutePath().replace(rootDir.getAbsolutePath(), "");
			fileTree.add(new AppLogsResponse(f.getName(), Base64Utils.encodeToUrlSafeString(path.getBytes()), (f.isDirectory() ? 0 : 1),
			        DateUtils.dateToStr(new Date(f.lastModified()), "yyyy-MM-dd HH:mm:ss"), FileUtils.sizeOf(f), children));
			if (f.isDirectory()) {
				recursiveQueryFiles(children, f, rootDir);
			}
		}
		return fileTree;
	}

}
