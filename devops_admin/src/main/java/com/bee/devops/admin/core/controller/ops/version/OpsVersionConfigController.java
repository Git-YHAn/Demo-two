package com.bee.devops.admin.core.controller.ops.version;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionConfig;
import com.bee.devops.admin.core.common.service.admin.AdminUserService;
import com.bee.devops.admin.core.common.service.common.ConfigVersionControlService;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppDepService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionAppService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionConfigService;
import com.bee.devops.admin.core.vo.response.CodeVo;
import com.bee.devops.admin.core.vo.response.ConfigVersionParamVo;
import com.bee.devops.admin.core.vo.response.ConfigVersionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "配置版本模块Controller")
@RestController
@RequestMapping(value = "/config_version")
@LogAnnotation(module="版本管理")
public class OpsVersionConfigController extends BaseController {
	final static Logger log = Logger.getLogger(OpsVersionConfigController.class);

	@Autowired
	OpsVersionConfigService opsVersionConfigService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	Environment environment;
	@Autowired
	AdminUserService adminUserService;
	@Autowired
	OpsVersionAppService opsVersionAppService;
	@Autowired
	OpsVersionAppDepService opsVersionAppDepService;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	OpsBaseProService opsBaseProServer;
	@Autowired
	ConfigVersionControlService configVersionControlService;

	/**
	 * 查询指定环境，配置的版本
	 *
	 * @return
	 * @author
	 */
	@ApiOperation(value = "查询指定环境，配置版本", notes = "查询指定环境，配置版本")
	@RequestMapping(value = { "/search/{pageNum}/{pageSize}/{proId}/{envId}/{appName}",
	        "/search/{pageNum}/{pageSize}/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<ConfigVersionVo>> queryConfigVersionByPageData(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
	        @PathVariable Long envId, @PathVariable Long proId, @PathVariable(required = false) String appName) {

		PageBean<ConfigVersionVo> pageBean = opsVersionConfigService.queryConfigVersionByPageData(pageNum, pageSize, envId, proId, appName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 通过Id查询配置版本
	 *
	 * @return
	 * @author
	 */
	@ApiOperation(value = "通过Id查询配置版本", notes = "通过Id查询配置版本")
	@RequestMapping(value = { "/search/byid/{proId}/{envId}/{appId}", "/search/byid" }, method = RequestMethod.GET)
	public ResultHandler<List<ConfigVersionVo>> queryConfigVersionByid(@PathVariable(name = "envId", required = false) Long envId,
	        @PathVariable(name = "proId", required = false) Long proId, @PathVariable(name = "appId", required = false) Long appId) {
		List<ConfigVersionVo> lists = opsVersionConfigService.queryConfigVersionByid(envId, proId, appId);
		return ResultHandler.success(lists);
	}

	/**
	 * 根据环境与应用创建配置版本
	 *
	 * @return
	 * @author
	 */
	@ApiOperation(value = "创建配置版本", notes = "根据环境创建配置版本")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@LogAnnotation(methods="制作配置版本")
	public ResultHandler<String> createConfigVersion(@RequestBody ConfigVersionParamVo configVersionParamVo) {
		// 获取应用环境
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(configVersionParamVo.getEnvId(), configVersionParamVo.getAppId());
		// 获取钉钉对象
		String webHook = opsBaseProServer.getWebHookByProId(configVersionParamVo.getProId());
		DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
		
		// 判断应用环境是否存在，不存在则返回应用不存在
		if (opsAssembleApp != null) {
			// 获取应用环境ID
			Long appEnvId = opsAssembleApp.getAppEnvId();
			// 获取环境编码
			String envCode = opsBaseEnvService.getEnvById(opsAssembleApp.getEnvId()).getEnvCode();
		
			CodeVo codeVo = opsVersionConfigService.getCode(configVersionParamVo.getEnvId(), configVersionParamVo.getAppId(), configVersionParamVo.getProId());
			// 获取钉钉git仓库地址
			String url = opsAssembleApp.getConfigGitUrl();

			// 获取请求头
			String header = getHeader();
			header = header + "?env_code=" + envCode;
			try {
				// 获取当前应用的历史最新配置版本
				String newVersionCode = null;
				// 获取当前应用的今日最新配置版本
				String oldVersionCode = null;
				// 自动生成版本
				String versionCode = null;
				synchronized (this) {
					newVersionCode = opsVersionConfigService.getNewestVersion(appEnvId);
					oldVersionCode = opsVersionConfigService.getMaxConfigVersion(appEnvId, DateUtils.dateToStr(new Date(), "yyyyMMdd"));
					versionCode = VersionCodeUtils.getVersionCode(oldVersionCode);
					// 打标签
					if (configVersionControlService.createTagFromMainBranch(opsAssembleApp, codeVo, versionCode)) {
						opsVersionConfigService.addConfigVersion(new OpsVersionConfig(appEnvId, versionCode, getAdminUser().getAdminUserId(), newVersionCode, configVersionParamVo.getDescription()));
					}
				}
				// 拼接到比较地址
				url = url.substring(0, url.lastIndexOf("."));
				String compareUrl = url + "/compare/" + newVersionCode + "..." + versionCode;
				//记录操作日志
				String description =  "在【"+opsAssembleApp.getEnvName()+"】环境下制作了【"+"["+opsAssembleApp.getAppId()+"]"+opsAssembleApp.getAppName()+"】应用的【"+versionCode+"】版本";
				LoggerUtil.setDescription(description);
				//发送钉钉通知
				dingTalkUtil.configVersionDingTalkSend(new DingTalkMessage().
						getVersionMessage(opsAssembleApp.getAppName(), opsAssembleApp.getEnvName(), null, versionCode, compareUrl, getAdminUser().getRealName(), DingTalkMessage.VER_SUCCESS));
			} catch (Exception e) {
				log.error(e.getMessage());
				// 记录操作日志
				String description =  "Ex:" + e.getMessage();
				LoggerUtil.setDescription(description);
				// 发送钉钉通知
				dingTalkUtil.configVersionDingTalkSend(new DingTalkMessage().
						getVersionMessage(opsAssembleApp.getAppName(), opsAssembleApp.getEnvName(), e.getMessage(), "点击重新制作", header, getAdminUser().getRealName(), DingTalkMessage.VER_FAIL));
				return ResultHandler.error("创建失败！");
			}
		} else {
			return ResultHandler.error("创建失败，所选环境对应的应用不存在！");
		}
		return ResultHandler.success("配置版本创建成功！");
	}

	/**
	 * 通过版本号获取配置版本
	 *
	 * @param versionCode
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "通过版本号获取应用发布版本", notes = "通过版本号获取应用发布版本")
	@RequestMapping(value = "/query/data/{versionCode}", method = RequestMethod.GET)
	public ResultHandler<ConfigVersionVo> queryDataByVersionCode(@PathVariable(name = "versionCode") String versionCode) {
		ConfigVersionVo configVersionVo = opsVersionConfigService.queryDataByVersionCode(versionCode);
		return ResultHandler.success(configVersionVo);
	}

	/**
	 * 通过版本ID获取配置版本
	 *
	 * @return
	 * @author Yang Chunhai
	 */
	@ApiOperation(value = "通过版本ID获取配置版本", notes = "通过版本ID获取配置版本")
	@RequestMapping(value = "/query/configver/{configVerId}", method = RequestMethod.GET)
	public ResultHandler<OpsVersionConfig> queryDataByVersionCode(@PathVariable(name = "configVerId") Long configVerId) {
		OpsVersionConfig opsVersionConfig = opsVersionConfigService.queryDataByConfigVerId(configVerId);
		return ResultHandler.success(opsVersionConfig);
	}
}
