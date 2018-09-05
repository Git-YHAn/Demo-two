package com.bee.devops.admin.core.controller.ops.version;

import java.util.Date;
import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.DateUtils;
import com.bee.devops.admin.common.utils.DingTalkUtil;
import com.bee.devops.admin.common.utils.LoggerUtil;
import com.bee.devops.admin.common.utils.VersionCodeUtils;
import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.bee.devops.admin.core.common.service.common.AppVersionControlService;
import com.bee.devops.admin.core.common.service.common.ConfigVersionControlService;
import com.bee.devops.admin.core.common.service.common.DepAppVersionControlService;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppDepService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionConfigService;
import com.bee.devops.admin.core.vo.request.DeployAppVersionRequest;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.CodeVo;
import com.bee.devops.admin.core.vo.response.DeployAppVersionVo;
import com.bee.devops.admin.core.vo.response.PublishVersionResponse;

import io.swagger.annotations.ApiOperation;

/**
 * 应用发布版本对应控制层
 *
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/release")
@LogAnnotation(module="版本管理")
public class OpsVersionAppDepController extends BaseController {
	final static Logger log = Logger.getLogger(OpsVersionAppDepController.class);
	@Autowired
	OpsVersionAppDepService opsVersionAppDepService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	OpsVersionAppService opsVersionAppService;
	@Autowired
	Environment environment;
	@Autowired
	OpsBaseAppService opsBaseAppService;
	@Autowired
	OpsVersionConfigService opsVersionConfigService;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	AppVersionControlService appVersionControlService;
	@Autowired
	ConfigVersionControlService configVersionControlService;
	@Autowired
	DepAppVersionControlService depAppVersionControlService;
	@Autowired
	OpsBaseProService opsBaseProServer;
	@ApiOperation(value = "获取应用发布镜像版本列表", notes = "获取应用发布镜像版本列表")
	@RequestMapping(value = "/query_all_images", method = RequestMethod.GET)
	public ResultHandler<PageBean<OpsVersionAppImg>> queryAllImagesByPageData(@RequestParam(name = "envId", required = false) Long envId,
	        @RequestParam(name = "proId", required = false) Long proId, @RequestParam(name = "appId", required = false) Long appId) {
		PageBean<OpsVersionAppImg> pageBean = opsVersionAppDepService.queryAllImagesByPageData(getPageNum(), getPageSize(), envId, proId, appId);
		return ResultHandler.success(pageBean);
	}

	@ApiOperation(value = "根据项目ID和环境ID查询所有版本", notes = "根据项目ID和环境ID查询所有版本")
	@RequestMapping(value = "/apps/wait/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<PublishVersionResponse>> queryWaitEnactApps(@PathVariable Long proId, @PathVariable Long envId) {
		List<PublishVersionResponse> apps = opsVersionAppDepService.queryWaitEnactApps(proId, envId);
		return ResultHandler.success(apps);
	}

	/**
	 * 查询指定环境，应用的发布版本
	 *
	 * @author
	 * @return
	 */
	@ApiOperation(value = "获取应用发布版本列表", notes = "获取应用发布版本列表")
	@RequestMapping(value = { "/search/{pageNum}/{pageSize}/{proId}/{envId}",
	        "/search/{pageNum}/{pageSize}/{proId}/{envId}/{appName}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<DeployAppVersionVo>> queryAllByPageData(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
	        @PathVariable Long proId, @PathVariable Long envId, @PathVariable(required = false) String appName) {
		PageBean<DeployAppVersionVo> pageBean = opsVersionAppDepService.searchAppVersion(pageNum, pageSize, envId, proId, appName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 通过版本号获取发布版本
	 * 
	 * @author Yang Chunhai
	 * @param versionCode
	 * @return
	 */
	@ApiOperation(value = "通过版本号获取发布版本", notes = "通过版本号获取发布版本")
	@RequestMapping(value = "/query_data/{versionCode}", method = RequestMethod.GET)
	public ResultHandler<DeployAppVersionVo> queryDataByVersionCode(@PathVariable(name = "versionCode") String versionCode) {
		DeployAppVersionVo deployAppVersionVo = opsVersionAppDepService.queryDataByVersionCode(versionCode);
		return ResultHandler.success(deployAppVersionVo);
	}
	
	/**
	 * 查询正在制作的发布版本
	 * 
	 * @author Yang Chunhai
	 * @return
	 */
	@ApiOperation(value = "通过版本号获取发布版本", notes = "通过版本号获取发布版本")
	@RequestMapping(value = "/query/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsVersionAppDepHis>> queryMakingVersion(@PathVariable Long proId, @PathVariable Long envId) {
		List<OpsVersionAppDepHis> opsVersionAppDepHis = opsVersionAppDepService.queryMakingVersion(proId, envId);
		return ResultHandler.success(opsVersionAppDepHis);
	}
	
	/**
	 * 更改制作状态为完成
	 * 
	 * @author Yang Chunhai
	 * @return
	 */
	@ApiOperation(value = "通过版本号获取发布版本", notes = "通过版本号获取发布版本")
	@RequestMapping(value = "/change/{appDepVerHisId}", method = RequestMethod.GET)
	public ResultHandler<String> changeVersionStatus(@PathVariable Long appDepVerHisId) {
		if(opsVersionAppDepService.changeVersionStatus(appDepVerHisId) == 1) {
			return ResultHandler.success("修改成功");
		} else {
			return ResultHandler.error("修改失败");
		}
	}

	/**
	 * 根据环境与应用版本及配置创建发布版本
	 *
	 * @author
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "创建发布版本", notes = "创建发布版本")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@LogAnnotation(methods="制作发布版本")
	public ResultHandler<String> createDeployAppVersion(@RequestBody DeployAppVersionRequest davr) {
		OpsVersionAppDep opsVersionAppDep = new OpsVersionAppDep();
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(davr.getEnvId(), davr.getAppId());
		OpsBaseApp opsBaseApp = opsBaseAppService.getAppById(davr.getAppId());
		// 获取钉钉对象
		String webHook = opsBaseProServer.getWebHookByProId(davr.getProId());
		DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
		if (opsAssembleApp != null) {
			// 获取应用环境ID
			Long appEnvId = opsAssembleApp.getAppEnvId();
			// 获取环境编码
			String envCode = opsBaseEnvService.getEnvById(opsAssembleApp.getEnvId()).getEnvCode();
			
			String versionCode = null;
			String newVersionCode = null;
			Long depVerHisId = null;
			synchronized(this) {
				//根据ID和状态获取是否当前制作版本的应用正在进行
				if(opsVersionAppDepService.getVerHisByStatusId(appEnvId) != null) {
					return ResultHandler.error("正在制作版本");
				}
				// 获取当前应用的历史最新发布版本
				newVersionCode = opsVersionAppDepService.getNewestVersionHis(appEnvId);
				// 获取当前应用今日的最新发布版本
				String oldVersionCode = opsVersionAppDepService.getMaxDeployVersionHis(appEnvId, DateUtils.dateToStr(new Date(), "yyyyMMdd"));
				// 自动生成版本
				versionCode = VersionCodeUtils.getVersionCode(oldVersionCode);
				
				opsVersionAppDep.setAppVersionId(davr.getAppVerId());
				opsVersionAppDep.setAppEnvId(opsAssembleApp.getAppEnvId());
				opsVersionAppDep.setConfigVersionId(davr.getConfigVerId());
				opsVersionAppDep.setVersionCode(versionCode);
				opsVersionAppDep.setDescription(davr.getDescription());
				opsVersionAppDep.setOperateUserId(getAdminUser().getAdminUserId());
				opsVersionAppDep.setTagUrl(newVersionCode);
				depVerHisId = opsVersionAppDepService.insertDepAppVerHis(opsVersionAppDep, davr.getAppId());
			}
			//拿取应用的gitUrl
			String url = opsAssembleApp.getDeployAppGitUrl();
			url = url.substring(0,url.lastIndexOf("."));
			String compareUrl = url + "/compare/" + newVersionCode + "..." + versionCode;
			
			// 获取请求路径
			String header = getHeader();
			header = header + "?env_code=" + envCode;

			AppEnvProCodeVo appEnvProCodeVo = opsVersionAppService.getCodes(davr.getEnvId(), davr.getAppId(), davr.getProId());
			CodeVo codeVo = opsVersionConfigService.getCode(davr.getEnvId(), davr.getAppId(), davr.getProId());
			String configVersionCode = null;
			if (davr.getAppTypeId() != OpsBaseAppType.APP_TYPE_STATIC) {
				OpsVersionConfig configVersion = opsVersionConfigService.queryDataByConfigVerId(davr.getConfigVerId());
				configVersionCode = configVersion.getVersionCode();
			}
			OpsVersionApp appVersion = opsVersionAppService.queryDataByAppVerId(davr.getAppVerId());
			if (appEnvProCodeVo != null) {
				try {
					depAppVersionControlService.createDepAppVersion(opsAssembleApp, opsBaseApp, codeVo, appVersion.getVersionCode(), configVersionCode, versionCode, davr.getDescription());
				} catch (Exception e) {
					log.error(e.getMessage());
					opsVersionAppDepService.updateDeeAppVerHis(depVerHisId, false); 
					// 记录操作日志
					String description =  "Ex:" + e.getMessage();
					LoggerUtil.setDescription(description);
					// 发送钉钉通知
					dingTalkUtil.depVersionDingTalkSend(new DingTalkMessage().
							getVersionMessage(opsBaseApp.getAppName(), opsAssembleApp.getEnvName(), e.getMessage(), "点击重新制作", header, getAdminUser().getRealName(), DingTalkMessage.VER_FAIL));
					return ResultHandler.error(e.getMessage());
				}
				// 创建发布版本时 还未使用
				opsVersionAppDep.setUsed(0);
				if (opsVersionAppDepService.insertSelective(opsVersionAppDep) == 1) {
					OpsVersionApp opsVersionApp = new OpsVersionApp();
					opsVersionApp.setAppVerId(davr.getAppVerId());
					opsVersionApp.setUsed(1);
					opsVersionAppService.updateAppVersionUsed(opsVersionApp);

					OpsVersionConfig opsVersionConfig = new OpsVersionConfig();
					opsVersionConfig.setConfigVerId(davr.getConfigVerId());
					opsVersionConfig.setUsed(1);
					opsVersionConfigService.updateConfigVersionUsed(opsVersionConfig);
					opsVersionAppDepService.updateDeeAppVerHis(depVerHisId, true);
					//记录操作日志
					String description =  "在【"+opsAssembleApp.getEnvName()+"】环境下制作了【"+"["+opsBaseApp.getAppId()+"]"+opsBaseApp.getAppName()+"】应用的【"+versionCode+"】版本";
					LoggerUtil.setDescription(description);
					//发送钉钉通知
					dingTalkUtil.depVersionDingTalkSend(new DingTalkMessage().
							getVersionMessage(opsBaseApp.getAppName(), opsAssembleApp.getEnvName(), null, versionCode, compareUrl, getAdminUser().getRealName(), DingTalkMessage.VER_SUCCESS));
					return ResultHandler.success("版本：" + versionCode + "创建成功！");
				}
			}
		} else {
			return ResultHandler.error("创建失败，所选环境对应的应用不存在！");
		}
		return ResultHandler.error("创建失败！");
	}
}
