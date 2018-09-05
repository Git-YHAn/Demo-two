package com.bee.devops.admin.core.common.entity.ops;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.beans.Transient;
import java.util.Date;

/**
 * 应用版本entity
 */
public class OpsVersionApp {

	public final static Integer VERSION_STATUS_DEFAULT = 0;
	public final static Integer VERSION_USED_STATUS_DEFAULT = 0;

	private Long appVerId;

	private String appCode;

	private Long appId;

	private String versionCode;

	private String commitId;

	private Integer status = VERSION_STATUS_DEFAULT;

	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date updateTime;

	private Long operateUserId;

	private String tagUrl;

	/**
	 * 2018.06.27新增,用于标识该应用版本是否已经使用,即是否已经组装成发布版本; 1 表示已经使用 0表示未使用
	 */
	private Integer used = VERSION_USED_STATUS_DEFAULT;

	/******* Transient *******/

	private String envName;

	private String proName;

	private String appName;

	private String appGitUrl;

	private String operateUserName;

	public OpsVersionApp(String appCode, Long appId,String commitId, String versionCode, String tagUrl, Long operateUserId, String operateUserName) {
		super();
		this.appCode = appCode;
		this.appId = appId;
		this.commitId = commitId;
		this.versionCode = versionCode;
		this.operateUserId = operateUserId;
		this.tagUrl = tagUrl;
		this.operateUserName = operateUserName;
	}

	public OpsVersionApp() {
		super();
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

	public Long getAppVerId() {
		return appVerId;
	}

	public void setAppVerId(Long appVerId) {
		this.appVerId = appVerId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	@Transient
	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	@Transient
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Transient
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Transient
	public String getAppGitUrl() {
		return appGitUrl;
	}

	public void setAppGitUrl(String appGitUrl) {
		this.appGitUrl = appGitUrl;
	}

	@Transient
	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}