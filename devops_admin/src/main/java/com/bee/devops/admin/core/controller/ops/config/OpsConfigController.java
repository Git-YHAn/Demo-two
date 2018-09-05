package com.bee.devops.admin.core.controller.ops.config;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.*;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsConfig;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionConfig;
import com.bee.devops.admin.core.common.service.common.ConfigVersionControlService;
import com.bee.devops.admin.core.common.service.ops.*;
import com.bee.devops.admin.core.vo.request.ConfigRecordAuditRequest;
import com.bee.devops.admin.core.vo.request.ConfigRecordWithdrawnRequest;
import com.bee.devops.admin.core.vo.response.AllConfigRecordsResponse;
import com.bee.devops.admin.core.vo.response.CodeVo;
import com.bee.devops.admin.core.vo.response.ConfigRecordDiffsResponse;
import com.bee.devops.admin.core.vo.response.UserConfigRecordsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Api(value = "配置文件修改审核记录")
@RestController
@RequestMapping(value = "/config/record")
@LogAnnotation(module="配置管理")
public class OpsConfigController extends BaseController {
	final static Logger log = Logger.getLogger(OpsConfigController.class);

	@Autowired
	OpsVersionConfigService opsVersionConfigService;
	@Autowired
	OpsConfigService opsConfigService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	ConfigVersionControlService configVersionControlService;
	@Autowired
	OpsBaseProService opsBaseProService;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	Environment environment;
	//根据应用环境ID保存锁
	private volatile static Map<Long, Lock> appEnvIdLockMap = new ConcurrentHashMap<>();

	@ApiOperation(value = "根据用户查询配置文件修改记录", notes = "根据用户查询配置文件修改记录")
	@RequestMapping(value = { "/search/user/{pageNum}/{pageSize}/{proId}/{envId}/{appId}",
	        "/search/user/{pageNum}/{pageSize}/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<UserConfigRecordsResponse>> queryConfigRecordsByUser(@PathVariable(required = false) Long appId, @PathVariable Long proId,
	        @PathVariable Long envId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
		PageBean<OpsConfig> list = opsConfigService.queryConfigRecordsByUser(getAdminUser().getAdminUserId(), proId, envId, appId, pageNum, pageSize);
		List<UserConfigRecordsResponse> transList = UserConfigRecordsResponse.transList(list.getResults());
		return ResultHandler.success(new PageBean<>(list.getMeta(), transList));
	}

	@ApiOperation(value = "审核配置提交记录", notes = "审核配置提交记录")
	@RequestMapping(value = { "/commit/audit" }, method = RequestMethod.POST)
	@LogAnnotation(methods="审核配置")
	public ResultHandler<String> commitAudit(@RequestBody ConfigRecordAuditRequest request) throws Exception {
		OpsConfig opsConfig = opsConfigService.getConfigRecord(request.getRecordId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByappEnvId(opsConfig.getAppEnvId());
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		Long appEnvId = opsAssembleApp.getAppEnvId();
		// 获取环境编码
		String envCode = opsBaseEnvService.getEnvById(opsAssembleApp.getEnvId()).getEnvCode();
		// 获取钉钉git仓库地址
		String url = opsAssembleApp.getConfigGitUrl();
		// 拼接到比较地址
		url = url.substring(0, url.lastIndexOf("."));
		// 获取请求头
		String header = getHeader();
		header = header.replaceAll("audit", "edit");
		header = header + "?env_code=" + envCode;
		// 获取钉钉对象
		String webHook = opsBaseProService.getWebHookByProId(opsAssembleApp.getProId());
		DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
		AdminUser adminUser = getAdminUser();
		Long adminUserId = adminUser.getAdminUserId();
		String username = adminUser.getUsername();
		Lock lock = appEnvIdLockMap.get(appEnvId);
		if (lock == null) {
			synchronized (OpsConfigController.class) {
				lock = appEnvIdLockMap.get(appEnvId);
				if (lock == null) {
					lock = new ReentrantLock();
					appEnvIdLockMap.put(appEnvId, lock);
				}
			}
		}
		lock.lock();
		try {
			if (request.getStatus().equals(OpsConfig.RECORD_STATUS_AUDIT_SUCCESS)) { // 审核成功
				if (configVersionControlService.mergeBranchToMain(opsAssembleApp, codeVo, opsConfig.getBranchName())) {
					configVersionControlService.dropRemoteBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
					opsConfigService.updateConfigRecordStatus(opsConfig.getRecordId(), OpsConfig.RECORD_STATUS_COMMIT, OpsConfig.RECORD_STATUS_AUDIT_SUCCESS);
					opsConfig.setAuditorId(adminUserId);
					opsConfig.setAuditorName(username);
					opsConfig.setAuditDate(new Date());
					opsConfigService.updateConfigRecord(opsConfig);
					opsConfigService.updateAuditInfo(opsConfig);
					// 获取当前应用的历史最新配置版本
					String newVersionCode = opsVersionConfigService.getNewestVersion(appEnvId);
					// 获取当前应用的今日最新配置版本
					String oldVersionCode = opsVersionConfigService.getMaxConfigVersion(appEnvId, DateUtils.dateToStr(new Date(), "yyyyMMdd"));
					// 自动生成版本
					String versionCode = VersionCodeUtils.getVersionCode(oldVersionCode);

					// 打标签
					if (configVersionControlService.createTagFromMainBranch(opsAssembleApp, codeVo, versionCode)) {
						configVersionControlService.pushLocalBranch(opsAssembleApp, codeVo, OpsSysParameterUtil.getGitBranch());
						configVersionControlService.dropLocalBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
						opsVersionConfigService.addConfigVersion(new OpsVersionConfig(appEnvId, versionCode, getAdminUser().getAdminUserId(), newVersionCode, opsConfig.getCommitMessage()));
					}
					// 记录操作日志
					String description = "将【" + opsAssembleApp.getEnvName() + "】环境下【" + "[" + opsAssembleApp.getAppId() + "]" + opsAssembleApp.getAppName() + "】应用的【" + opsConfig.getBranchName() + "】分支【审核通过】";
					LoggerUtil.setDescription(description);
					// 拼接比较地址
					String compareUrl = url + "/compare/" + newVersionCode + "..." + versionCode;
					// 发送钉钉通知
					dingTalkUtil.auditConfigDingTalkSend(new DingTalkMessage().
							getConfigMessage(opsAssembleApp.getAppName(), opsAssembleApp.getEnvName(), opsConfig.getBranchName(), versionCode, compareUrl, getAdminUser().getRealName(), DingTalkMessage.CONFIG_SUSSESS));
					return ResultHandler.success("配置审核通过");
				}
			} else if (request.getStatus().equals(OpsConfig.RECORD_STATUS_AUDIT_FAIL)) { // 审核不通过
				configVersionControlService.checkoutLocalBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
				configVersionControlService.dropRemoteBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
				opsConfigService.updateConfigRecordStatus(opsConfig.getRecordId(), OpsConfig.RECORD_STATUS_COMMIT, OpsConfig.RECORD_STATUS_AUDIT_FAIL);
				opsConfig.setAuditorId(adminUserId);
				opsConfig.setAuditorName(username);
				opsConfig.setAuditDate(new Date());
				opsConfigService.updateConfigRecord(opsConfig);
				opsConfigService.updateAuditInfo(opsConfig);
				// 记录操作日志
				String description = "将【"+opsAssembleApp.getEnvName()+"】环境下【"+"["+opsAssembleApp.getAppId()+"]"+opsAssembleApp.getAppName()+"】应用的【"+opsConfig.getBranchName()+"】分支【审核不通过】";
				LoggerUtil.setDescription(description);
				// 发送钉钉通知
				dingTalkUtil.auditConfigDingTalkSend(new DingTalkMessage().
						getConfigMessage(opsAssembleApp.getAppName(), opsAssembleApp.getEnvName(), opsConfig.getBranchName(), "点击重新修改", header, getAdminUser().getRealName(), DingTalkMessage.CONFIG_FAIL));

				return ResultHandler.success("审核不通过");
			}
		} catch (Exception e) {
			String description = "Ex:" + e.getMessage();
			LoggerUtil.setDescription(description);
			return ResultHandler.error(description);
		} finally {
			lock.unlock();
			appEnvIdLockMap.remove(appEnvId);
		}
		return ResultHandler.error("审核状态错误【" + request.getStatus() + "】");
	}

	@ApiOperation(value = "根据配置文件修改记录查询变更文件", notes = "根据配置文件修改记录查询变更文件")
	@RequestMapping(value = { "/query/diffs/{recordId}" }, method = RequestMethod.GET)
	public ResultHandler<List<ConfigRecordDiffsResponse>> queryConfigRecordsByUser(@PathVariable Long recordId) {
		OpsConfig opsConfig = opsConfigService.getConfigRecord(recordId);
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByappEnvId(opsConfig.getAppEnvId());
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		List<DiffEntry> diffs = configVersionControlService.showDiffsWithCommits(opsAssembleApp, codeVo, opsConfig.getBeginSha(), opsConfig.getEndSha());
		List<ConfigRecordDiffsResponse> list = new ArrayList<>();
		for (DiffEntry diffEntry : diffs) {
			String diffFile;
			if (diffEntry.getChangeType() == ChangeType.DELETE) {
				diffFile = diffEntry.getOldPath();
			} else {
				diffFile = diffEntry.getNewPath();
			}
			String diffContent = configVersionControlService.showDiffContent(opsAssembleApp, codeVo, diffEntry);
			list.add(new ConfigRecordDiffsResponse(diffEntry.getChangeType().name(), diffFile, diffContent));
		}
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "配置修改人员撤回已提交审核的分支", notes = "配置修改人员撤回已提交审核的分支")
	@RequestMapping(value = { "/commit/withdrawn" }, method = RequestMethod.POST)
	public ResultHandler<String> commitWithdrawn(@RequestBody ConfigRecordWithdrawnRequest request) {
		OpsConfig opsConfig = opsConfigService.getConfigRecord(request.getRecordId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByappEnvId(opsConfig.getAppEnvId());
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		configVersionControlService.checkoutLocalBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
		configVersionControlService.dropRemoteBranch(opsAssembleApp, codeVo, opsConfig.getBranchName());
		opsConfigService.updateConfigRecordStatus(request.getRecordId(), opsConfig.getRecordStatus(), OpsConfig.RECORD_STATUS_DEFAULT);
		return ResultHandler.success("");
	}

	@ApiOperation(value = "配置审核人员查询所有已提交的记录", notes = "根据用户查询配置文件修改记录")
	@RequestMapping(value = { "/search/{pageNum}/{pageSize}/{proId}/{envId}/{appId}",
	        "/search/{pageNum}/{pageSize}/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<AllConfigRecordsResponse>> queryConfigRecords(@PathVariable(required = false) Long appId, @PathVariable Long proId,
	        @PathVariable Long envId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
		PageBean<OpsConfig> list = opsConfigService.queryConfigRecords(proId, envId, appId, pageNum, pageSize);
		List<AllConfigRecordsResponse> transList = AllConfigRecordsResponse.transList(list.getResults());
		return ResultHandler.success(new PageBean<>(list.getMeta(), transList));
	}
	
	@ApiOperation(value = "配置审核人员查询所有已提交的记录", notes = "根据用户查询配置文件修改记录")
	@RequestMapping(value = { "/search/commit/{pageNum}/{pageSize}/{proId}/{envId}/{appId}",
	        "/search/commit/{pageNum}/{pageSize}/{proId}/{envId}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<AllConfigRecordsResponse>> queryCommitConfigRecords(@PathVariable(required = false) Long appId, @PathVariable Long proId,
	        @PathVariable Long envId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
		PageBean<OpsConfig> list = opsConfigService.queryCommitConfigRecords(proId, envId, appId, pageNum, pageSize);
		List<AllConfigRecordsResponse> transList = AllConfigRecordsResponse.transList(list.getResults());
		return ResultHandler.success(new PageBean<>(list.getMeta(), transList));
	}

}
