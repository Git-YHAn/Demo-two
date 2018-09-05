package com.bee.devops.admin.core.controller.ops.appconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.DateUtils;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.common.utils.VersionCodeUtils;
import com.bee.devops.admin.component.git.Repo;
import com.bee.devops.admin.component.git.RepoFactory;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.ops.*;
import com.bee.devops.admin.core.common.service.common.AppVersionControlService;
import com.bee.devops.admin.core.common.service.ops.*;
import com.bee.devops.admin.core.vo.request.ConfigRecordAuditRequest;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.ConfigRecordDiffsResponse;
import com.bee.devops.admin.core.vo.response.AppConfigRecordsResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jgit.diff.DiffEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 应用审核controller
 */
@RestController
@RequestMapping(value = "/appconfig/record")
public class OpsAppConfigController extends BaseController {
    final static Logger log = Logger.getLogger(OpsAppConfigController.class);
    private static final Object lock = new Object();

    @Autowired
    OpsVersionAppService opsVersionAppService;
    @Autowired
    OpsAppConfigService opsAppConfigService;
    @Autowired
    OpsAssembleAppService opsAssembleAppService;
    @Autowired
    AppVersionControlService appVersionControlService;
    @Autowired
    OpsBaseProService opsBaseProService;
    @Autowired
    OpsBaseEnvService opsBaseEnvService;
    @Autowired
    Environment environment;
    @Autowired
    OpsBaseAppService opsBaseAppService;

    /**
     * 查询当前登陆用户的应用审核记录
     */
    @RequestMapping(value = {"/search/user/{pageNum}/{pageSize}/{proId}/{appId}",
            "/search/user/{pageNum}/{pageSize}/{proId}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<AppConfigRecordsResponse>> queryAppConfigRecordsByUser(@PathVariable(required = false) Long appId, @PathVariable Long proId,
                                                                                         @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageBean<OpsAppConfig> list = opsAppConfigService.queryAppConfigRecordsByUser(getAdminUser().getAdminUserId(), proId, appId, pageNum, pageSize);
        List<AppConfigRecordsResponse> transList = AppConfigRecordsResponse.transList(list.getResults());
        return ResultHandler.success(new PageBean<>(list.getMeta(), transList));
    }

    /**
     * 查询待审应用,即为已经提交审核的应用
     */
    @RequestMapping(value = {"/search/commit/{pageNum}/{pageSize}/{proId}/{appId}",
            "/search/commit/{pageNum}/{pageSize}/{proId}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<AppConfigRecordsResponse>> queryCommitConfigRecords(@PathVariable(required = false) Long appId, @PathVariable Long proId,
                                                                                      @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageBean<OpsAppConfig> list = opsAppConfigService.queryCommitAppConfigRecords(proId, appId, pageNum, pageSize);
        List<AppConfigRecordsResponse> transList = AppConfigRecordsResponse.transList(list.getResults());
        return ResultHandler.success(new PageBean<>(list.getMeta(), transList));
    }

    /**
     * 审核应用
     */
    @RequestMapping(value = {"/commit/audit"}, method = RequestMethod.POST)
    public ResultHandler<String> commitAudit(@RequestBody ConfigRecordAuditRequest request) {
        OpsAppConfig opsConfig = opsAppConfigService.getAppConfigRecord(request.getRecordId());
        // 对应用进行审核,不同的应用可以同时进行,同一个应用只能审核一次
        synchronized (lock) {
            int auditStatus = opsConfig.getAuditStatus();
            if (auditStatus == 1) {
                return ResultHandler.error("应用【" + opsConfig.getAppName() + "】已经进行审核操作...");
            }
            opsConfig.setAuditStatus(1);
            opsAppConfigService.updateAppConfigRecord(opsConfig);
        }

        Long appId = opsConfig.getAppId();
        Long proId = opsConfig.getProId();
        AppEnvProCodeVo codeVo = opsVersionAppService.getProCodeAndAppCode(appId, proId);
        // 获取应用
        OpsBaseApp app = opsBaseAppService.getAppById(appId);
        AdminUser adminUser = getAdminUser();
        Long adminUserId = adminUser.getAdminUserId();
        String username = adminUser.getUsername();

        if (request.getStatus().equals(OpsAppConfig.RECORD_STATUS_AUDIT_SUCCESS)) { // 审核通过
            if (opsConfig.getBeginSha().equals(opsConfig.getEndSha())) {
                return ResultHandler.error("分支【" + opsConfig.getBranchName() + "】两次commitId相同,内容无改动,不能合并到主分支");
            }
            if (appVersionControlService.mergeBranchToMain(app, codeVo, opsConfig.getBranchName())) {
                appVersionControlService.dropRemoteBranch(app, codeVo, opsConfig.getBranchName());
                opsAppConfigService.updateAppConfigRecordStatus(opsConfig.getRecordId(), OpsAppConfig.RECORD_STATUS_COMMIT, OpsAppConfig.RECORD_STATUS_AUDIT_SUCCESS);
                opsConfig.setAuditorId(adminUserId);
                opsConfig.setAuditorName(username);
                opsConfig.setAuditDate(new Date());
                opsAppConfigService.updateAppConfigRecord(opsConfig);
                opsAppConfigService.updateAuditInfo(opsConfig);
                try {
                    // 获取当日的最大版本号
                    String oldVersionCode;
                    // 根据当日最大版本号生成新版本号,递增
                    String versionCode;
                    // 获取当前最新的版本号
                    String newVersionCode;
                    OpsVersionApp opsVersionApp;
                    synchronized (this) {
                        // 获取最新提交id
                        String headCommitId = appVersionControlService.getHeadCommitId(app, codeVo);
                        // 获取当日的最大版本号
                        oldVersionCode = opsVersionAppService.getMaxVersionCode(appId, DateUtils.dateToStr(new Date(), "yyyyMMdd"));
                        // 根据当日最大版本号生成新版本号,递增
                        versionCode = VersionCodeUtils.getVersionCode(oldVersionCode); // V_20180704_04
                        // 获取当前最新的版本号
                        newVersionCode = opsVersionAppService.getNewestVersion(appId);

                        // 在本地仓库创建tag
                        appVersionControlService.createTagFromMainBranch(app, codeVo, versionCode);
                        log.info("创建tag成功");
                        // 打标签
                        if (appVersionControlService.createTagFromMainBranch(app, codeVo, versionCode)) {
                            appVersionControlService.pushLocalBranch(app, codeVo, OpsSysParameterUtil.getGitBranch());
                            appVersionControlService.dropLocalBranch(app, codeVo, opsConfig.getBranchName());
                            // 审核通过之后直接生成应用版本
                            opsVersionApp = new OpsVersionApp(app.getAppCode(), appId, headCommitId, versionCode, newVersionCode, adminUser.getAdminUserId(), adminUser.getUsername());
                            opsVersionApp.setDescription(opsConfig.getCommitMessage());
                            opsVersionAppService.addAppVersion(opsVersionApp);
                        }
                    }
                } catch (Exception e) {
                    String description = "Ex:" + e.getMessage();
                    return ResultHandler.error(description);
                }
                return ResultHandler.success("应用审核通过");
            }
        } else if (request.getStatus().equals(OpsConfig.RECORD_STATUS_AUDIT_FAIL)) { // 审核不通过
            appVersionControlService.checkoutLocalBranch(app, codeVo, opsConfig.getBranchName());
            appVersionControlService.dropRemoteBranch(app, codeVo, opsConfig.getBranchName());
            opsAppConfigService.updateAppConfigRecordStatus(opsConfig.getRecordId(), OpsAppConfig.RECORD_STATUS_COMMIT, OpsAppConfig.RECORD_STATUS_AUDIT_FAIL);

            opsConfig.setAuditorId(adminUserId);
            opsConfig.setAuditorName(username);
            opsConfig.setAuditDate(new Date());
            opsAppConfigService.updateAppConfigRecord(opsConfig);
            opsAppConfigService.updateAuditInfo(opsConfig);

            return ResultHandler.success("应用审核不通过");
        }
        return ResultHandler.error("应用审核状态错误【" + request.getStatus() + "】");
    }

    /**
     * 查看文件变动,查看当前分支与master分支的对比
     */
    @RequestMapping(value = {"/query/diffs/{recordId}"}, method = RequestMethod.GET)
    public ResultHandler<List<ConfigRecordDiffsResponse>> queryAppConfigRecordsDiff(@PathVariable Long recordId) {
        OpsAppConfig opsConfig = opsAppConfigService.getAppConfigRecord(recordId);
        Long appId = opsConfig.getAppId();
        AppEnvProCodeVo codeVo = opsVersionAppService.getProCodeAndAppCode(appId, opsConfig.getProId());
        // 获取应用
        OpsBaseApp app = opsBaseAppService.getAppById(appId);
        List<DiffEntry> diffs = appVersionControlService.showAppVersionFileDiffs(app, codeVo, opsConfig.getBeginSha(), opsConfig.getEndSha());
        List<ConfigRecordDiffsResponse> list = new ArrayList<>();
        for (DiffEntry diffEntry : diffs) {
            String diffFile;
            if (diffEntry.getChangeType() == DiffEntry.ChangeType.DELETE) {
                diffFile = diffEntry.getOldPath();
            } else {
                diffFile = diffEntry.getNewPath();
            }
            list.add(new ConfigRecordDiffsResponse(diffEntry.getChangeType().name(), diffFile, ""));
        }
        return ResultHandler.success(list);
    }

    /**
     * gitlab钩子触发生成待审应用,不再直接生成应用版本
     *
     * @return
     */
    @RequestMapping(value = "/gitlab/save", method = RequestMethod.POST)
    @LogAnnotation(methods = "钩子触发生成待审应用")
    public ResultHandler<String> createAppVersion() {
        try {
            String info = IOUtils.toString(getRequest().getInputStream(), "utf-8");
            JSONObject jo = JSON.parseObject(info);
            String ref = jo.getString("ref");
            // 获取推送分支(推送到哪条分支触发了钩子)
            String branch = StringUtils.substringAfterLast(ref, "/");
            log.info("推送到分支【" + branch + "】触发了钩子");
            // 睡眠一秒
            TimeUnit.SECONDS.sleep(1);
            String sha = jo.getString("checkout_sha");
            if (StringUtils.isEmpty(sha)) {
                return ResultHandler.success("success");
            }

            // 判断分支,master分支触发的不做任何处理
            if (!StringUtils.equals(branch, "master")) {
                String before = jo.getString("before");
                String after = jo.getString("after");
                JSONObject repository = jo.getJSONObject("repository");
                // 获取git仓库地址
                String gitUrl = repository.getString("git_http_url");

                // 通过git路径去应用表中获取对应的应用
                OpsBaseApp baseApp = opsBaseAppService.getAppByGitUrl(gitUrl);
                Long appId = baseApp.getAppId();
                String appName = baseApp.getAppName();
                Long proId = baseApp.getProId();
                String proName = baseApp.getProName();

                // 去应用配置表中查询对应的记录是否存在
                List<OpsAppConfig> records = opsAppConfigService.getRecord(proId, appId, branch);
                int size = records.size();
                for (OpsAppConfig record : records) {
                    if (record.getEndSha().equals(after)) {
                        return ResultHandler.error("无变动信息");
                    }
                }

                // 获取初始commitId
                if (StringUtils.contains(before, "0000000000000000")) {
                    AppEnvProCodeVo codeVo = opsVersionAppService.getProCodeAndAppCode(appId, proId);
                    String repoPath = appVersionControlService.getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
                    Repo repo = RepoFactory.getInstance().get(repoPath, gitUrl);
                    before = repo.getMergeBase(branch, "master");
                }

                // 获取最新的提交信息
                JSONArray commits = jo.getJSONArray("commits");
                String message = "初始提交";
                if (commits.size() > 0) {
                    JSONObject commit = commits.getJSONObject(commits.size() - 1);
                    message = commit.getString("message");
                }

                if (size == 0) {
                    // 生成应用待审记录
                    generateRecord(before, after, branch, message, appId, appName, proId, proName);
                } else {
                    // 记录已经存在,判断其状态
                    AtomicInteger count = new AtomicInteger(0);
                    OpsAppConfig updated = null;
                    for (OpsAppConfig config : records) {
                        if (config.getRecordStatus() == 2 || config.getRecordStatus() == -1) {
                            count.incrementAndGet();
                        } else {
                            updated = config;
                        }
                    }
                    // 全部都是已审核,新生成应用待审记录
                    if (size == count.get()) {
                        generateRecord(before, after, branch, message, appId, appName, proId, proName);
                    } else {
                        // 更新commit
                        if (updated != null) {
                            updated.setEndSha(after);
                            opsAppConfigService.updateAppConfigRecord(updated);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("触发钩子生成应用待审记录异常", e);
            return ResultHandler.error("触发钩子生成应用待审记录异常" + e.getMessage());
        }
        return ResultHandler.success("success");
    }

    private void generateRecord(String before, String after, String branch, String message, Long appId, String appName, Long proId, String proName) {
        Date date = new Date();
        AdminUser adminUser = getAdminUser();
        OpsAppConfig appConfig = new OpsAppConfig(appId, proId, appName, proName, branch, adminUser.getAdminUserId(), date, before, adminUser.getUsername());
        appConfig.setEndSha(after);
        appConfig.setCommitMessage(message);
        appConfig.setAuditorId(adminUser.getAdminUserId());
        appConfig.setAuditDate(date);
        appConfig.setRecordStatus(OpsAppConfig.RECORD_STATUS_COMMIT); // 状态为已提交审核
        opsAppConfigService.addAppConfigRecord(appConfig);
    }
}
