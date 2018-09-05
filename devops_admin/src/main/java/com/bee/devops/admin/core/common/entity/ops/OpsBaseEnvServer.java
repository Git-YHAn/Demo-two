package com.bee.devops.admin.core.common.entity.ops;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 环境应用服务器实体类
 * @author Roc created on 2018/8/13
 */
public class OpsBaseEnvServer {
    private Long hostAppEnvId;

    private Long hostId;

    private Long appEnvId;

    private String deployVersionCode;

    private Integer appRunningStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    public Long getHostAppEnvId() {
        return hostAppEnvId;
    }

    public void setHostAppEnvId(Long hostAppEnvId) {
        this.hostAppEnvId = hostAppEnvId;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getAppEnvId() {
        return appEnvId;
    }

    public void setAppEnvId(Long appEnvId) {
        this.appEnvId = appEnvId;
    }

    public String getDeployVersionCode() {
        return deployVersionCode;
    }

    public void setDeployVersionCode(String deployVersionCode) {
        this.deployVersionCode = deployVersionCode;
    }

    public Integer getAppRunningStatus() {
        return appRunningStatus;
    }

    public void setAppRunningStatus(Integer appRunningStatus) {
        this.appRunningStatus = appRunningStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}