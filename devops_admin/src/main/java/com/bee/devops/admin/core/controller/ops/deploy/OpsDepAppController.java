package com.bee.devops.admin.core.controller.ops.deploy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bee.devops.admin.core.vo.response.OpsDepAppResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.LoggerUtil;
import com.bee.devops.admin.core.asynctask.AsyncPublishTask;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDep;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppHisService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppDepService;
import com.bee.devops.admin.core.controller.ops.base.OpsBaseAppController;
import com.bee.devops.admin.core.vo.request.PublishAppSaveRequest;
import com.bee.devops.admin.core.vo.request.PublishCancelRequest;
import com.bee.devops.admin.core.vo.request.PublishOverRequest;
import com.bee.devops.admin.core.vo.request.RePublishAppRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/appenv/publish")
@LogAnnotation(module="应用发布")
public class OpsDepAppController extends BaseController {
	final static Logger log = Logger.getLogger(OpsBaseAppController.class);

	@Autowired
	OpsDepAppService opsDepAppService;
	@Autowired
	OpsVersionAppDepService opsVersionAppDepService;
	@Autowired
	AsyncPublishTask asyncPublishTask;
	@Autowired
	OpsDepAppHisService opsDepAppHisService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	
	@ApiOperation(value = "根据项目ID和环境ID查询正在发布的应用", notes = "根据项目ID和环境ID查询正在发布的应用")
	@RequestMapping(value = "/apps/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepApp>> queryApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsDepApp> apps = opsDepAppService.queryPublishingApps(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "根据项目ID和环境ID查询常规发布的应用", notes = "根据项目ID和环境ID查询正在发布的应用")
	@RequestMapping(value = "/apps/phy/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepApp>> queryPhyApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsDepApp> apps = opsDepAppService.queryPhyPublishingApps(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "根据项目ID和环境ID查询容器发布的应用", notes = "根据项目ID和环境ID查询正在发布的应用")
	@RequestMapping(value = "/apps/con/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepApp>> queryConApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsDepApp> apps = opsDepAppService.queryConPublishingApps(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "根据项目ID和环境ID查询所有待发布的应用", notes = "根据项目ID和环境ID查询所有待发布的应用")
	@RequestMapping(value = "/apps/wait/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepAppResponse>> queryWaitPublishApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsDepAppResponse> apps = opsDepAppService.queryPublishApps(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "根据项目ID和环境ID查询所有待发布的应用", notes = "根据项目ID和环境ID查询所有待发布的应用")
	@RequestMapping(value = "/apps/waits/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsDepApp>> queryWaitPublishAppsByCon(@PathVariable Long proId,
			@PathVariable Long envId) {
		List<OpsDepApp> apps = opsDepAppService.queryWaitPublishAppsByCon(proId, envId);
		return ResultHandler.success(apps);
	}

	@ApiOperation(value = "保存发布应用", notes = "保存发布应用")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@LogAnnotation(methods="发布")
	public ResultHandler<List<OpsDepApp>> saveApp(@RequestBody PublishAppSaveRequest request) throws IOException {
		OpsDepApp opsDepApp = request.transEntity();
		List<OpsDepApp> apps = opsDepAppService.queryPublishingApps(request.getProId(), request.getEnvId());
		List<Long> publishingServerIds = new ArrayList<>();
		OpsVersionAppDep opsVersionAppDep = opsVersionAppDepService.get(request.getDeployVersionId());
		
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByappEnvId(request.getAppEnvId());
		for (OpsDepApp app : apps) {
			if (app.getAppEnvId().longValue() == opsDepApp.getAppEnvId().longValue()) {
				publishingServerIds.add(app.getServerId());
			}
		}
		//操作用户ID
		opsDepApp.setOperateUserId(getAdminUser().getAdminUserId());
		// 服务器ID
		List<OpsBaseServer> assetsHosts = request.getAssetsHosts();

		// 获取发布版本
		for (OpsBaseServer opsBaseServer : assetsHosts) {
			if (publishingServerIds.indexOf(opsBaseServer.getAssetsId()) > -1) {
				continue;
			}
			if (opsBaseServer.getAssetsType() == 2) {
				opsDepApp.setServerId(opsBaseServer.getAssetsId());
				opsDepApp.setPublishStatus(OpsDepApp.PUBLISH_STATUS_DEFAULT);
				opsDepApp.setAutoRestart(1);
				opsDepApp.setDeployType(2);
				opsDepAppService.saveApp(opsDepApp);
				// 记录操作日志
				String description = "在【"+opsAssembleApp.getEnvName()+"】环境下将【"+"["+opsAssembleApp.getAppId()+"]"+opsAssembleApp.getAppName()+"】应用的【"+opsVersionAppDep.getVersionCode()+"】版本【容器发布】到服务器【"+opsBaseServer.getAssetsName()+"】";
				LoggerUtil.setDescription(description);
			} else if (opsBaseServer.getAssetsType() == 0) {
				String versionCode = opsVersionAppDep.getVersionCode();
				opsDepApp.setServerId(opsBaseServer.getAssetsId());
				opsDepApp.setPublishStatus(OpsDepApp.PUBLISH_STATUS_DEFAULT);
				opsDepApp.setDeployType(0);
				opsDepApp.setDeployVersionCode(versionCode);
				opsDepAppService.saveApp(opsDepApp);
				// 记录操作日志
				String description = "在【"+opsAssembleApp.getEnvName()+"】环境下将【"+"["+opsAssembleApp.getAppId()+"]"+opsAssembleApp.getAppName()+"】应用的【"+opsVersionAppDep.getVersionCode()+"】版本【常规发布】到服务器【"+opsBaseServer.getAssetsName()+"】";
				LoggerUtil.setDescription(description);
				// 设置发布版本的状态为已经使用,不管其发布成功还是失败
				opsVersionAppDep.setUsed(1);
				opsVersionAppDepService.updateReleaseVersionUsed(opsVersionAppDep);
			}
		}
		//拿到请求头
		String header = getRequest().getHeader("referer");
		asyncPublishTask.multiDeployApp(opsDepApp.getAppEnvId(), assetsHosts, header);
		return ResultHandler.success(null);
	}

	@ApiOperation(value = "重新发布应用", notes = "重新发布应用")
	@RequestMapping(value = "/republish", method = RequestMethod.POST)
	@LogAnnotation(methods = "重新发布")
	public ResultHandler<List<OpsDepApp>> republishApp(@RequestBody RePublishAppRequest request) throws IOException {
		String header = getHeader();
		OpsDepApp opsDepApp = opsDepAppService.get(request.getPublishId());
		opsDepAppService.updateStatus(opsDepApp.getPublishStatus(), OpsDepApp.PUBLISH_STATUS_DEFAULT,
				opsDepApp.getPublishId());
		if (request.getDeployType() == 0) {
			// 记录操作日志
			String description = "在【"+opsDepApp.getEnvName()+"】环境下将【"+"["+opsDepApp.getAppId()+"]"+opsDepApp.getAppName()+"】应用的【"+opsDepApp.getDeployVersionCode()+"】版本【常规发布】到服务器【"+opsDepApp.getServerName()+"】";
			LoggerUtil.setDescription(description);
			asyncPublishTask.deployApplication(opsDepApp.getAppEnvId(), opsDepApp.getServerId(), header);
			return ResultHandler.success(null);
		} else {
			asyncPublishTask.deployContainerApplication(opsDepApp.getAppEnvId(), opsDepApp.getServerId());
			return ResultHandler.success(null);
		}
	}

	@ApiOperation(value = "应用归档", notes = "应用归档")
	@RequestMapping(value = "/archive", method = RequestMethod.POST)
	public ResultHandler<String> publishOver(@RequestBody PublishOverRequest request) {
		opsDepAppService.moveToHistory(request.getPublishId());
		return ResultHandler.success("应用归档成功");
	}

	@ApiOperation(value = "取消应用发布", notes = "取消应用发布")
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public ResultHandler<String> publishCancel(@RequestBody PublishCancelRequest request) {
		opsDepAppService.cancelPublish(request.getPublishId());
		return ResultHandler.success("取消发布成功");
	}
}
