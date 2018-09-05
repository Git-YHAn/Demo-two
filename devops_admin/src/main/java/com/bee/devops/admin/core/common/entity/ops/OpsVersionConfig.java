package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

public class OpsVersionConfig {
	public final static Integer VERSION_STATUS_DEFAULT = 0;
	public final static Integer VERSION_USED_STATUS_DEFAULT = 0;

	private Long configVerId;

	private Long appEnvId;

	private String versionName;

	private String versionCode;

	private String commitId;

	private Integer status = VERSION_STATUS_DEFAULT;

	private String description;

	private Date createTime;

	private Date updateTime;

	private Long operateUserId;

	private String tagUrl;

	private Integer used = VERSION_USED_STATUS_DEFAULT;

	public OpsVersionConfig() {
		super();
	}

	public OpsVersionConfig(Long appEnvId, String versionCode, Long operateUserId, String tagUrl) {
		super();
		this.appEnvId = appEnvId;
		this.versionCode = versionCode;
		this.operateUserId = operateUserId;
		this.tagUrl = tagUrl;
	}
	
	public OpsVersionConfig(Long appEnvId, String versionCode, Long operateUserId, String tagUrl, String description) {
		super();
		this.appEnvId = appEnvId;
		this.versionCode = versionCode;
		this.operateUserId = operateUserId;
		this.description = description;
		this.tagUrl = tagUrl;
	}

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

	public Long getConfigVerId() {
		return configVerId;
	}

	public void setConfigVerId(Long configVerId) {
		this.configVerId = configVerId;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName == null ? null : versionName.trim();
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