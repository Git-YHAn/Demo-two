package com.bee.devops.admin.core.controller.ops.version;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.*;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppSync;
import com.bee.devops.admin.core.common.service.common.AppVersionControlService;
import com.bee.devops.admin.core.common.service.ops.*;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.ApplicationVersionResponse;
import com.bee.devops.admin.core.vo.response.ConfigVersionParamVo;

import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/app_version")
@LogAnnotation(module = "版本管理")
public class OpsVersionAppController extends BaseController {
    final static Logger log = Logger.getLogger(OpsVersionAppController.class);

    @Autowired
    OpsVersionAppService opsVersionAppService;
    @Autowired
    OpsVersionAppSyncService opsVersionAppSyncService;
    @Autowired
    OpsAssembleAppService opsAssembleAppService;
    @Autowired
    OpsBaseAppService deployAPPService;
    @Autowired
    Environment environment;
    @Autowired
    OpsVersionConfigService opsVersionConfigService;
    @Autowired
    OpsVersionAppDepService opsVersionAppDepService;
    @Autowired
    OpsBaseEnvService opsBaseEnvService;
    @Autowired
    OpsBaseProService opsBaseProServer;
    @Autowired
    AppVersionControlService appVersionControlService;
    @Autowired
    OpsAppConfigService opsAppConfigService;

    /**
     * 查询指定环境，应用的版本
     *
     * @return
     * @author
     */
    @ApiOperation(value = "查询指定环境，应用的版本", notes = "查询指定环境，应用的版本")
    @RequestMapping(value = {"/search/{pageNum}/{pageSize}/{proId}/{envId}/{appName}",
            "/search/{pageNum}/{pageSize}/{proId}/{envId}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<ApplicationVersionResponse>> searchAppVersion(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
                                                                                @PathVariable Long proId, @PathVariable Long envId, @PathVariable(required = false) String appName) {

        PageBean<OpsVersionApp> pageBean = opsVersionAppService.searchAppVersion(pageNum, pageSize, null, proId, appName);
        List<ApplicationVersionResponse> list = ApplicationVersionResponse.transList(pageBean.getResults());
        PageBean<ApplicationVersionResponse> data = new PageBean<>(pageBean.getMeta(), list);
        return ResultHandler.success(data);
    }

    /**
     * 通过Id查询应用版本
     *
     * @return
     * @author
     */
    @ApiOperation(value = "通过Id查询应用版本", notes = "通过Id查询应用版本")
    @RequestMapping(value = {"/search/byid/{proId}/{appId}", "/search/byid"}, method = RequestMethod.GET)
    public ResultHandler<List<OpsVersionApp>> searchAppVersionByid(@PathVariable(name = "proId", required = false) Long proId,
                                                                   @PathVariable(name = "appId", required = false) Long appId) {
        List<OpsVersionApp> lists = opsVersionAppService.searchAppVersionByid(null, proId, appId);
        return ResultHandler.success(lists);
    }

    /**
     * gitlab钩子触发应用版本的自动创建
     *
     * @param proId
     *            项目id
     * @param appId
     *            应用id
     * @return
     */
    @ApiOperation(value = "gitlab钩子自动触发创建应用版本", notes = "gitlab钩子自动触发创建应用版本")
    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    @LogAnnotation(methods="制作应用版本")
    public ResultHandler<String> createAppVersion(@RequestBody ConfigVersionParamVo versionInfo) {
        log.info("appId:" + versionInfo.getAppId() + "versionInfo:" + versionInfo + "proId:" + versionInfo.getProId());
        // 获取项目编码及应用编码,需要保证单个值
        AppEnvProCodeVo appEnvProCodeVo = opsVersionAppService.getProCodeAndAppCode(versionInfo.getAppId(), versionInfo.getProId());

        // 获取钉钉对象
        String webHook = opsBaseProServer.getWebHookByProId(versionInfo.getProId());
        DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
        // 判断路径是否存在
        if (appEnvProCodeVo != null) {
            // 查询应用
            OpsBaseApp opsBaseApp = deployAPPService.getAppById(versionInfo.getAppId());
            if (opsBaseApp != null) {

                //拿取应用的gitUrl
                String url = opsBaseApp.getAppGitUrl();
                url = url.substring(0,url.lastIndexOf("."));
                // 获取当前操作员
                AdminUser adminUser = getAdminUser();
                try {
                    // 获取当日的最大版本号
                    String oldVersionCode;
                    // 根据当日最大版本号生成新版本号,递增
                    String versionCode;
                    // 获取当前最新的版本号
                    String newVersionCode;
                    // 获取最近一次提交的信息
//					String info = null;
                    OpsVersionApp opsVersionApp;

                    synchronized (this) {
                        // 获取最新提交id
                        String headCommitId = appVersionControlService.getHeadCommitId(opsBaseApp, appEnvProCodeVo);
                        // 根据最新提交id从数据库获取应用版本
                        List<OpsVersionApp> list = opsVersionAppService.queryVersionByCommitId(headCommitId);
                        if (list.size() > 0) { // 若数据库中已经存在则抛出异常
                            OpsVersionApp app = list.get(0);
                            throw new ApplicationContextException("应用ID[" + app.getAppId() + "]版本[" + app.getVersionCode() + "]提交记录ID[" + app.getCommitId() + "]已存在");
                        }
                        // 获取当日的最大版本号
                        oldVersionCode = opsVersionAppService.getMaxVersionCode(versionInfo.getAppId(), DateUtils.dateToStr(new Date(), "yyyyMMdd"));
                        // 根据当日最大版本号生成新版本号,递增
                        versionCode = VersionCodeUtils.getVersionCode(oldVersionCode); // V_20180704_04
                        // 获取当前最新的版本号
                        newVersionCode = opsVersionAppService.getNewestVersion(versionInfo.getAppId());
                        // 在打标记之前先在数据库新增一条应用版本,此时还未设置description信息
                        opsVersionApp = new OpsVersionApp(opsBaseApp.getAppCode(), versionInfo.getAppId(), headCommitId, versionCode, newVersionCode, adminUser.getAdminUserId(),
                                adminUser.getUsername());
                        opsVersionApp.setDescription(versionInfo.getDescription());
                        opsVersionAppService.addAppVersion(opsVersionApp);
                        // 在本地仓库创建tag
                        appVersionControlService.createTagFromMainBranch(opsBaseApp, appEnvProCodeVo, versionCode);
                        log.info("创建tag成功");
//						info = appVersionControlService.getHeadCommitMsg(opsBaseApp, appEnvProCodeVo);
                    }

                    // 更新对应的应用版本的description信息
//					opsVersionApp.setDescription(StringUtils.isNotEmpty(info) ? info : "无文件改动");
//					opsVersionAppService.updateAppInfo(opsVersionApp);
                    //记录操作日志
                    String description =  "制作了【"+"["+opsBaseApp.getAppId()+"]"+opsBaseApp.getAppName()+"】应用的【"+versionCode+"】版本";
                    LoggerUtil.setDescription(description);
                    // 拼接比较地址
                    String compareUrl = url + "/compare/" + newVersionCode + "..." + versionCode;
                    // 发送钉钉通知
                    dingTalkUtil.appVersionDingTalkSend(new DingTalkMessage().
                            getVersionMessage(opsBaseApp.getAppName(), null, null, versionCode, compareUrl, getAdminUser().getRealName(), DingTalkMessage.VER_SUCCESS));
                    return ResultHandler.success("创建成功");
                } catch (ApplicationContextException e) {
                    log.error("制定应用版本异常:" + e.getMessage());
//					dingTalkUtil.appVersionDingTalkSend(new DingTalkMessage().
//							getVersionMessage(opsBaseApp.getAppName(), null, e.getMessage(), "点击重新制作", header, getAdminUser().getRealName(), DingTalkMessage.VER_FAIL));
                    // 记录操作日志
                    String description =  "Ex:" + e.getMessage();
                    LoggerUtil.setDescription(description);
                    return ResultHandler.error("本次应用版本制定与上次版本无变化");
                } catch (Exception e) {
                    log.error(e);
                    return ResultHandler.error("创建应用版本失败");
                }
            } else {
                return ResultHandler.error("创建失败,没有获取到GIT账号！");
            }
        } else {
            return ResultHandler.error("创建失败，所选环境对应的应用不存在！");
        }
    }

    @ApiOperation(value = "查询同步记录", notes = "按条件条件查询同步记录")
    @RequestMapping(value = {"/select_sync/{pageNum}/{pageSize}/{proId}/{envId}/{appName}",
            "/select_sync/{pageNum}/{pageSize}/{proId}/{envId}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<OpsVersionAppSync>> selectBySelective(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Long envId,
                                                                        @PathVariable Long proId, @PathVariable(required = false) String appName) {
        PageBean<OpsVersionAppSync> selectAppVersionSync = opsVersionAppSyncService.selectAppVersionSync(pageNum, pageSize, envId, proId, appName);
        return ResultHandler.success(selectAppVersionSync);
    }

    /**
     * 查询某条同步记录的修改信息
     *
     * @param syncId
     * @return
     * @author Yang Chunhai
     */
    @Deprecated
    @ApiOperation(value = "查询修改记录", notes = "查询某条同步记录的修改信息")
    @GetMapping("/select_sync_modify/{syncId}")
    public ResultHandler<String> selectByModifyRecord(@PathVariable("syncId") Long syncId) {
        OpsVersionAppSync selectByKeyId = opsVersionAppSyncService.selectByKeyId(syncId);
        String syncFolderName = selectByKeyId.getSyncFolderName();
        log.info(syncFolderName);
        String syncFolderInfo = FolderFunction.readString(syncFolderName);
        return ResultHandler.success(syncFolderInfo);
    }

    @ApiOperation(value = "查询应用", notes = "根据环境id查询应用")
    @RequestMapping(value = "/select_by_envid/{envId}", method = RequestMethod.GET)
    public ResultHandler<List<OpsAssembleApp>> selectByEnvId(@PathVariable("envId") Long envId) {
        List<OpsAssembleApp> list = opsVersionAppService.selectAppByEnvId(envId);
        return ResultHandler.success(list);
    }

    /**
     * 通过版本号获取应用版本
     *
     * @param versionCode
     * @return
     * @author Yang Chunhai
     */
    @ApiOperation(value = "通过版本号获取应用发布版本", notes = "通过版本号获取应用发布版本")
    @RequestMapping(value = "/query/data/{versionCode}", method = RequestMethod.GET)
    public ResultHandler<ApplicationVersionResponse> queryDataByVersionCode(@PathVariable(name = "versionCode") String versionCode) {
        ApplicationVersionResponse applicationVersionVo = opsVersionAppService.queryDataByVersionCode(versionCode);
        return ResultHandler.success(applicationVersionVo);
    }

    /**
     * 通过版本ID获取应用版本
     *
     * @return
     * @author Yang Chunhai
     */
    @ApiOperation(value = "通过版本ID获取应用版本", notes = "通过版本ID获取应用版本")
    @RequestMapping(value = "/query/appver/{appVerId}", method = RequestMethod.GET)
    public ResultHandler<OpsVersionApp> queryDataByVersionCode(@PathVariable(name = "appVerId") Long appVerId) {
        OpsVersionApp opsVersionApp = opsVersionAppService.queryDataByAppVerId(appVerId);
        return ResultHandler.success(opsVersionApp);
    }
}
