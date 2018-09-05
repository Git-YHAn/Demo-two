/**
 * @author heping
 * @date2018年5月30日
 */
package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsVersionAppImg {
	private Long appImagesId;
	private Long appId;
	private Long envId;
	private Long proId;
	private Long appVersionId;
	private Long configVersionId;
	private String versionCode;
	private String appImagesName;
	private String appImagesTag;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;
	private Long operateUserId;
	private String gitUrl;
	public Long getAppImagesId() {
		return appImagesId;
	}
	public void setAppImagesId(Long appImagesId) {
		this.appImagesId = appImagesId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long getEnvId() {
		return envId;
	}
	public void setEnvId(Long envId) {
		this.envId = envId;
	}
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
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
		this.versionCode = versionCode;
	}
	public String getAppImagesName() {
		return appImagesName;
	}
	public void setAppImagesName(String appImagesName) {
		this.appImagesName = appImagesName;
	}
	public String getAppImagesTag() {
		return appImagesTag;
	}
	public void setAppImagesTag(String appImagesTag) {
		this.appImagesTag = appImagesTag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getGitUrl() {
		return gitUrl;
	}
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}
	
	
}
