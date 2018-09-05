package com.bee.devops.admin.core.vo.response;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 应用实例管理
 *
 * @author Roc created on 2018/8/14
 */
public class OpsDepAppInstanceVo {
    private Long appEnvId;

    private String appName;
    
    private Long appId;

    private Long appTypeId;

    private int publishSuccessNum;

    private int publishingNum;

    private int publishErrorNum;

    private int runningNum;

    private int stopingNum;

    private List<JSONObject> versions;

    private List<OpsDepAppOrderInfoVo> orderInfoVoList;

    public Long getAppEnvId() {
        return appEnvId;
    }

    public void setAppEnvId(Long appEnvId) {
        this.appEnvId = appEnvId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getPublishSuccessNum() {
        return publishSuccessNum;
    }

    public void setPublishSuccessNum(int publishSuccessNum) {
        this.publishSuccessNum = publishSuccessNum;
    }

    public int getPublishingNum() {
        return publishingNum;
    }

    public void setPublishingNum(int publishingNum) {
        this.publishingNum = publishingNum;
    }

    public int getPublishErrorNum() {
        return publishErrorNum;
    }

    public void setPublishErrorNum(int publishErrorNum) {
        this.publishErrorNum = publishErrorNum;
    }

    public int getRunningNum() {
        return runningNum;
    }

    public void setRunningNum(int runningNum) {
        this.runningNum = runningNum;
    }

    public int getStopingNum() {
        return stopingNum;
    }

    public void setStopingNum(int stopingNum) {
        this.stopingNum = stopingNum;
    }

    public List<JSONObject> getVersions() {
        return versions;
    }

    public void setVersions(List<JSONObject> versions) {
        this.versions = versions;
    }

    public List<OpsDepAppOrderInfoVo> getOrderInfoVoList() {
        return orderInfoVoList;
    }

    public void setOrderInfoVoList(List<OpsDepAppOrderInfoVo> orderInfoVoList) {
        this.orderInfoVoList = orderInfoVoList;
    }

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

    public Long getAppTypeId() {
        return appTypeId;
    }

    public void setAppTypeId(Long appTypeId) {
        this.appTypeId = appTypeId;
    }
}
