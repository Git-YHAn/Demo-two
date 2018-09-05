package com.bee.devops.admin.core.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 应用实例管理应用详情
 * @author Roc created on 2018/8/14
 */
public class OpsDepAppOrderInfoVo {
    private Long detailId;

    private Long serverId;

    private String serverName;

    private String sshAddress;

    private Long depAppVerId;

    private String versionCode;

    private String lastSuccessVersion;

    private String versionDesc;

    private Integer publishStatus;

    private Integer runStatus;

    private String deployLog;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSshAddress() {
        return sshAddress;
    }

    public void setSshAddress(String sshAddress) {
        this.sshAddress = sshAddress;
    }

    public Long getDepAppVerId() {
        return depAppVerId;
    }

    public void setDepAppVerId(Long depAppVerId) {
        this.depAppVerId = depAppVerId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getDeployLog() {
        return deployLog;
    }

    public void setDeployLog(String deployLog) {
        this.deployLog = deployLog;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLastSuccessVersion() {
        return lastSuccessVersion;
    }

    public void setLastSuccessVersion(String lastSuccessVersion) {
        this.lastSuccessVersion = lastSuccessVersion;
    }
}
