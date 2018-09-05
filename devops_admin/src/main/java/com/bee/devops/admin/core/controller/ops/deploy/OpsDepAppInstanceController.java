package com.bee.devops.admin.core.controller.ops.deploy;

import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.enums.ApplicationStatusEnums;
import com.bee.devops.admin.common.enums.DeployStatusEnums;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvServerService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppOrderInfoService;
import com.bee.devops.admin.core.vo.response.AppServiceManagerVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppInstanceVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoAppEnvVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roc created on 2018/8/14
 */
@Api(value = "应用实例管理Controller")
@RestController
@RequestMapping("/app/instance")
public class OpsDepAppInstanceController {
    private static final Logger logger = Logger.getLogger(OpsDepAppInstanceController.class);
    @Autowired
    private OpsAssembleAppService assembleAppService;
    @Autowired
    private OpsDepAppOrderInfoService orderInfoService;
    @Autowired
    private OpsBaseEnvServerService envServerService;

    @ApiOperation(value = "根据项目和环境查询应用实例", notes = "根据项目和环境查询应用实例")
    @RequestMapping(value = {"/search/{pageNum}/{pageSize}/{proId}/{envId}",
            "/search/{pageNum}/{pageSize}/{proId}/{envId}/{appName}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<OpsDepAppInstanceVo>> queryAllPublishAppsByPageData
            (@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Long proId, @PathVariable Long envId, @PathVariable(required = false) String appName) {
        if (validateNumberInvalid(pageNum) || validateNumberInvalid(pageSize) || validateNumberInvalid(proId) || validateNumberInvalid(envId)) {
            logger.error("Invalid Arguments: Not be null or 0!");
            return ResultHandler.error("Not be null or 0!");
        }

        PageBean<AppServiceManagerVo> appPageBean = assembleAppService.searchAppEnvs(pageNum, pageSize, envId, proId, appName);
        List<OpsDepAppInstanceVo> appInstanceVoList = new ArrayList<>(appPageBean.getMeta().getSize());
        for (OpsAssembleApp appEnv : appPageBean.getResults()) {
            Long appEnvId = appEnv.getAppEnvId();

            OpsDepAppInstanceVo instanceVo = new OpsDepAppInstanceVo();
            instanceVo.setAppId(appEnv.getAppId());
            instanceVo.setAppName(appEnv.getAppName());
            instanceVo.setAppEnvId(appEnv.getAppEnvId());
            instanceVo.setAppTypeId(appEnv.getAppTypeId());

            List<OpsDepAppOrderInfoVo> envServers = envServerService.getEnvServersByAppEnvId(appEnvId);
            List<JSONObject> versions = new ArrayList<>();
            //记录版本数量、运行状态数量
            int publishSuccessNum = 0;
            int publishErrorNum = 0;
            int runningNum = 0;
            int publishingNum = 0;
            int stopingNum = 0;
            for (OpsDepAppOrderInfoVo envServer : envServers) {
                Long serverId = envServer.getServerId();
                OpsDepAppOrderInfoVo orderInfo = orderInfoService.searchOrderInfoByAppEnvId(appEnvId, serverId);
                if (orderInfo != null) {
                    int publishStatus = orderInfo.getPublishStatus();
                    if (publishStatus == DeployStatusEnums.DEPLOY_SUCCESS.getValue()) {
                        publishSuccessNum++;
                    } else if (publishStatus < DeployStatusEnums.DEPLOY_NOT_START.getValue()) {
                        publishErrorNum++;
                    }
                    envServer.setStartDate(orderInfo.getStartDate());
                    envServer.setEndDate(orderInfo.getEndDate());
                    envServer.setDetailId(orderInfo.getDetailId());
                    envServer.setDeployLog(orderInfo.getDeployLog());
                    envServer.setCreateTime(orderInfo.getCreateTime());
                    envServer.setDepAppVerId(orderInfo.getDepAppVerId());
                    envServer.setPublishStatus(orderInfo.getPublishStatus());
                    envServer.setVersionCode(orderInfo.getVersionCode());
                    envServer.setVersionDesc(orderInfo.getVersionDesc());
                    putAndIncreaseCountMap(versions, envServer);
                }

                Integer runStatus = envServer.getRunStatus();
                if (runStatus == ApplicationStatusEnums.APPLICATION_RUNNING.getValue()) {
                    runningNum++;
                } else if (runStatus == ApplicationStatusEnums.APPLICATION_DEPLOYING.getValue()) {
                    publishingNum++;
                } else {
                    stopingNum++;
                }
            }

            instanceVo.setPublishSuccessNum(publishSuccessNum);
            instanceVo.setPublishErrorNum(publishErrorNum);
            instanceVo.setPublishingNum(publishingNum);
            instanceVo.setRunningNum(runningNum);
            instanceVo.setStopingNum(stopingNum);
            instanceVo.setOrderInfoVoList(envServers);
            instanceVo.setVersions(versions);

            appInstanceVoList.add(instanceVo);
        }

        PageBean<OpsDepAppInstanceVo> result = new PageBean<>(appPageBean.getMeta(), appInstanceVoList);
        return ResultHandler.success(result);
    }

    @ApiOperation(value = "通过工单信息Id查询应、服务器和发布版本")
    @RequestMapping(value = {"/search/appEnv/{detailId}"}, method = RequestMethod.GET)
    public ResultHandler<OpsDepAppOrderInfoAppEnvVo> searchAppEnvAndServerAndDepAppVer(@PathVariable Long detailId) {
        OpsDepAppOrderInfoAppEnvVo result = orderInfoService.getAppEnvAndDepAppVerAndServer(detailId);
        return ResultHandler.success(result);
    }

    private void putAndIncreaseCountMap(List<JSONObject> list, OpsDepAppOrderInfoVo infoVo) {
        boolean existed = false;
        Long versionId = infoVo.getDepAppVerId();
        String versionCode = infoVo.getVersionCode();
        if (versionId == null || versionCode == null) {
            return;
        }

        for (JSONObject obj : list) {
            if (obj.getLongValue("versionId") == versionId) {
                int count = obj.getInteger("num");
                count++;
                obj.put("num", count);
                existed = true;
                break;
            }
        }

        if (!existed) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("versionId", infoVo.getDepAppVerId());
            jsonObject.put("versionCode", versionCode);
            jsonObject.put("versionDesc", infoVo.getVersionDesc());
            jsonObject.put("num", 1);
            list.add(jsonObject);
        }
    }

    /**
     * 校验数值的有效性，有效返回true ，无效返回false
     * number = null or number = 0 return false
     */
    private boolean validateNumberInvalid(Number number) {
        return !(number != null && number.intValue() != 0);
    }
}
