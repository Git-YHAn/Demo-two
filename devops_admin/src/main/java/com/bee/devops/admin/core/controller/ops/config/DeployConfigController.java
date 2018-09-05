package com.bee.devops.admin.core.controller.ops.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.DateUtils;
import com.bee.devops.admin.common.utils.DingTalkUtil;
import com.bee.devops.admin.common.utils.LoggerUtil;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsConfig;
import com.bee.devops.admin.core.common.service.common.ConfigVersionControlService;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.common.service.ops.OpsConfigService;
import com.bee.devops.admin.core.common.service.ops.OpsVersionConfigService;
import com.bee.devops.admin.core.controller.ops.config.response.OpsConfigFileResponse;
import com.bee.devops.admin.core.vo.request.DeployConfigFileBodyRequest;
import com.bee.devops.admin.core.vo.request.DeployConfigSaveFileRequest;
import com.bee.devops.admin.core.vo.response.CodeVo;

@RestController
@RequestMapping(value = "/deployconfig_version")
@LogAnnotation(module="配置管理")
public class DeployConfigController extends BaseController {
	final static Logger log = Logger.getLogger(DeployConfigController.class);

	@Autowired
	OpsVersionConfigService opsVersionConfigService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	OpsConfigService opsConfigService;
	@Autowired
	ConfigVersionControlService configVersionControlService;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	OpsBaseProService opsBaseProService;
	/**
	 * 查询分支
	 *
	 * @param envId
	 * @param appId
	 * @param proId
	 * @return
	 * @throws GitAPIException
	 */
	@RequestMapping(value = "/query/app/branches/{proId}/{envId}/{appId}", method = RequestMethod.GET)
	public ResultHandler<List<String>> queryAppBranches(@PathVariable Long envId, @PathVariable Long appId, @PathVariable Long proId) throws GitAPIException {
		CodeVo codeVo = opsVersionConfigService.getCode(envId, appId, proId);
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		List<String> branchNames = configVersionControlService.queryLocalBranches(opsAssembleApp, codeVo);
		List<String> result = new ArrayList<>();
		for (String branchName : branchNames) {
			if (branchName != null && !"master".equals(branchName) && branchName.contains(getAdminUser().getUsername())
			        && result.indexOf(branchName) < 0) {
				result.add(branchName);
			}
		}
		return ResultHandler.success(result);
	}

	/**
	 * 创建分支
	 *
	 * @param envId
	 * @param appId
	 * @param proId
	 * @return
	 * @throws IOException
	 * @throws GitAPIException
	 */
	@RequestMapping(value = "/checkout/app/branch/{proId}/{envId}/{appId}", method = RequestMethod.GET)
	public ResultHandler<String> checkoutNewBranch(@PathVariable Long envId, @PathVariable Long appId, @PathVariable Long proId) throws IOException {
		CodeVo codeVo = opsVersionConfigService.getCode(envId, appId, proId);
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		String branchName = getAdminUser().getUsername() + DateUtils.dateToStr(new Date(), "yyyyMMddHHmmss");
		String newBranchName = configVersionControlService.createLocalBranchFromMaster(opsAssembleApp, codeVo, branchName);
		return ResultHandler.success(newBranchName);
	}

	/**
	 * 删除本地分支,同时删除对应的配置审核记录
	 *
	 * @param envId
	 * @param appId
	 * @param proId
	 * @param branchName
	 * @return
	 * @throws GitAPIException
	 * @throws IOException
	 */
	@RequestMapping(value = "/drop/branch/{proId}/{envId}/{appId}/{branchName}", method = RequestMethod.GET)
	public ResultHandler<String> dropBranch(@PathVariable Long envId, @PathVariable Long appId, @PathVariable Long proId, @PathVariable String branchName) {
		CodeVo codeVo = opsVersionConfigService.getCode(envId, appId, proId);
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		// 删除对应的配置审核记录
		opsConfigService.deleteRecord(branchName);
		configVersionControlService.dropLocalBranch(opsAssembleApp, codeVo, branchName);
		return ResultHandler.success("success");
	}

	/**
	 * 推送并删除本地分支
	 *
	 * @param envId
	 * @param appId
	 * @param proId
	 * @param branchName
	 * @return
	 * @throws GitAPIException
	 * @throws IOException
	 */
	@RequestMapping(value = "/push/branch", method = RequestMethod.POST)
	@LogAnnotation(methods="修改配置")
	public synchronized ResultHandler<String> pushBranch(@RequestBody DeployConfigSaveFileRequest branchInfo){
		CodeVo codeVo = opsVersionConfigService.getCode(branchInfo.getEnvId(), branchInfo.getAppId(), branchInfo.getProId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(branchInfo.getEnvId(), branchInfo.getAppId());
		// 获取环境编码
		String envCode = opsBaseEnvService.getEnvById(opsAssembleApp.getEnvId()).getEnvCode();
		// 获取钉钉对象
		String webHook = opsBaseProService.getWebHookByProId(branchInfo.getProId());
		DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
		configVersionControlService.pushLocalBranch(opsAssembleApp, codeVo, branchInfo.getBranchName());
		configVersionControlService.dropLocalBranch(opsAssembleApp, codeVo, branchInfo.getBranchName());
		// 记录操作日志
		String description = "将【"+opsAssembleApp.getEnvName()+"】环境下【"+"["+opsAssembleApp.getAppId()+"]"+opsAssembleApp.getAppName()+"】应用的【"+branchInfo.getBranchName()+"】分支提交审核";
		LoggerUtil.setDescription(description);
		// 获取请求头
		String header = getHeader();
		header = header.replaceAll("edit", "audit");
		header = header + "?env_code=" + envCode;
		dingTalkUtil.editConfigDingTalkSend(new DingTalkMessage().
				getConfigMessage(opsAssembleApp.getAppName(), opsAssembleApp.getEnvName(), branchInfo.getBranchName(),null ,header, getAdminUser().getRealName(), "修改配置文件 ,请求审核！"));
		// 修改配置记录状态
		OpsConfig opsConfig = opsConfigService.getConfigRecordByAppBranch(opsAssembleApp.getAppEnvId(), branchInfo.getBranchName());
		opsConfigService.updateConfigRecordStatus(opsConfig.getRecordId(), opsConfig.getRecordStatus(), OpsConfig.RECORD_STATUS_COMMIT);
		opsConfigService.updateConfigCommitMessage(opsConfig.getRecordId(), branchInfo.getCommitMessage());
		return ResultHandler.success("success");
	}

	/**
	 * 查询分支文件列表
	 *
	 * @param envId
	 * @param appId
	 * @param proId
	 * @param branchName
	 * @return
	 * @throws IOException
	 * @throws GitAPIException
	 */
	@RequestMapping(value = "/query/branch/files/{proId}/{envId}/{appId}/{branchName}", method = RequestMethod.GET)
	public ResultHandler<List<OpsConfigFileResponse>> queryBranchFiles(@PathVariable Long envId, @PathVariable Long appId, @PathVariable Long proId,
	        @PathVariable String branchName) throws IOException, GitAPIException {
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		List<Path> repoFiles = configVersionControlService.queryRepoFiles(opsAssembleApp, codeVo, branchName);
		Path repoPath = configVersionControlService.getRepoPath(codeVo);
		// 文件信息
		List<OpsConfigFileResponse> res = new ArrayList<>();
		for (Path filePath : repoFiles) {
			String fileType = Files.probeContentType(filePath);
			res.add(new OpsConfigFileResponse(repoPath.relativize(filePath).toString(), filePath.toString(), fileType));
		}
		return ResultHandler.success(res);
	}

	/**
	 * 获取配置文件内容
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/content", method = RequestMethod.POST)
	public ResultHandler<String> getFileContent(@RequestBody DeployConfigFileBodyRequest request) throws IOException {
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(request.getEnvId(), request.getAppId());
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		String res = configVersionControlService.getRepoFileContent(opsAssembleApp, codeVo, request.getFilePath(), request.getBranchName());
		if (res == null) {
			return ResultHandler.error("未找到文件!");
		}
		return ResultHandler.success(res);
	}

	/**
	 * 获取主分支配置文件内容
	 *
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/content/origin", method = RequestMethod.POST)
	public ResultHandler<String> getOriginFileContent(@RequestBody DeployConfigFileBodyRequest request) throws IOException {
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(request.getEnvId(), request.getAppId());
		CodeVo codeVo = opsVersionConfigService.getCode(opsAssembleApp.getEnvId(), opsAssembleApp.getAppId(), opsAssembleApp.getProId());
		String res = configVersionControlService.getRepoFileContent(opsAssembleApp, codeVo, request.getFilePath(), null);
		if (res == null) {
			return ResultHandler.error("未找到文件!");
		}
		return ResultHandler.success(res);
	}

	/**
	 * 新增配置文件
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/add", method = RequestMethod.POST)
	public ResultHandler<String> addFile(@RequestBody DeployConfigSaveFileRequest request) throws IOException {
		CodeVo codeVo = opsVersionConfigService.getCode(request.getEnvId(), request.getAppId(), request.getProId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(request.getEnvId(), request.getAppId());
		String branchName = request.getBranchName();
		Long appEnvId = opsAssembleApp.getAppEnvId();
		AdminUser adminUser = getAdminUser();

		String commitId = configVersionControlService.addRepoFile(opsAssembleApp, codeVo, adminUser.getUsername(), branchName, request.getFilePath(),
		        request.getFileContent());

		opsConfigService.addOrUpdateOpsConfig(branchName, appEnvId, adminUser, commitId);
		return ResultHandler.success("success");
	}

	/**
	 * 删除配置文件
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/delete", method = RequestMethod.POST)
	public ResultHandler<String> deleteFile(@RequestBody DeployConfigFileBodyRequest request) throws IOException {
		CodeVo codeVo = opsVersionConfigService.getCode(request.getEnvId(), request.getAppId(), request.getProId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(request.getEnvId(), request.getAppId());
		String branchName = request.getBranchName();
		Long appEnvId = opsAssembleApp.getAppEnvId();
		AdminUser adminUser = getAdminUser();
		String commitId = configVersionControlService.deleteRepoFile(opsAssembleApp, codeVo, adminUser.getUsername(), branchName, request.getFilePath());

		opsConfigService.addOrUpdateOpsConfig(branchName, appEnvId, adminUser, commitId);
		return ResultHandler.success("success");
	}

	/**
	 * 修改配置文件
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/content/save", method = RequestMethod.POST)
	public ResultHandler<String> saveFileContent(@RequestBody DeployConfigSaveFileRequest request) throws IOException {
		CodeVo codeVo = opsVersionConfigService.getCode(request.getEnvId(), request.getAppId(), request.getProId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(request.getEnvId(), request.getAppId());

		AdminUser adminUser = getAdminUser();
		String branchName = request.getBranchName();
		String commitId = configVersionControlService.modifyRepoFile(opsAssembleApp, codeVo, adminUser.getUsername(), branchName, request.getFilePath(),
		        request.getFileContent());

		Long appEnvId = opsAssembleApp.getAppEnvId();

		opsConfigService.addOrUpdateOpsConfig(branchName, appEnvId, adminUser, commitId);
		return ResultHandler.success("success");
	}

	/**
	 * 查询当前分支未推送文件变动
	 *
	 * @param branchName
	 * @return
	 * @throws IOException
	 * @throws GitAPIException
	 */
	@RequestMapping(value = "/show/staged/diff/{proId}/{envId}/{appId}/{branchName}", method = RequestMethod.GET)
	public ResultHandler<List<DiffEntry>> showStagedDiffFiles(@PathVariable Long envId, @PathVariable Long appId, @PathVariable Long proId,
	        @PathVariable String branchName) {
		CodeVo codeVo = opsVersionConfigService.getCode(envId, appId, proId);
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByEnvIdAppId(envId, appId);
		List<DiffEntry> showStagedDiff = configVersionControlService.showDiffsWithMainBranch(opsAssembleApp, codeVo, branchName);
		return ResultHandler.success(showStagedDiff);
	}

}
