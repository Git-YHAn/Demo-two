package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

public class OpsVersionAppDep {
    private Long depAppVerId;

    private Long appEnvId;

    private Long appVersionId;

    private Long configVersionId;

    private String versionCode;

    private String commitId;

    private Integer status;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Long operateUserId;
    
    private String tagUrl;

    private Integer used;

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public String getTagUrl() {
		return tagUrl;
	}

	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
	}

	public Long getDepAppVerId() {
        return depAppVerId;
    }

    public void setDepAppVerId(Long depAppVerId) {
        this.depAppVerId = depAppVerId;
    }

    public Long getAppEnvId() {
        return appEnvId;
    }

    public void setAppEnvId(Long appEnvId) {
        this.appEnvId = appEnvId;
    }

    public Long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
    }

    public Long getConfigVersionId() {
        return configVersionId;
    }

    public void setConfigVersionId(Long configVersionId) {
        this.configVersionId = configVersionId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId == null ? null : commitId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }
}