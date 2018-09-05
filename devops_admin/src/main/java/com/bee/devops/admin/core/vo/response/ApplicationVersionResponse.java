package com.bee.devops.admin.core.vo.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.OpsVersionApp;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplicationVersionResponse {
	private Long appVerId;

	private String envName;

	private String proName;

	private String appName;

	private String versionCode;

	private Integer status;

	private String description;

	private String operateUserName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date updateTime;

	private String tagUrl;

	private String appGitUrl;

	/**
	 * 标识该应用版本是否已经使用
	 */
	private Integer used;

	public ApplicationVersionResponse() {
		super();
	}

	public static List<ApplicationVersionResponse> transList(List<OpsVersionApp> list) {
		List<ApplicationVersionResponse> result = new ArrayList<>();
		for (OpsVersionApp application : list) {
			result.add(new ApplicationVersionResponse(application));
		}
		return result;
	}

	public ApplicationVersionResponse(OpsVersionApp application) {
		super();
		this.appVerId = application.getAppVerId();
		this.envName = application.getEnvName();
		this.proName = application.getProName();
		this.appName = application.getAppName();
		this.versionCode = application.getVersionCode();
		this.status = application.getStatus();
		this.description = application.getDescription();
		this.createTime = application.getCreateTime();
		this.updateTime = application.getUpdateTime();
		this.tagUrl = application.getTagUrl();
		this.appGitUrl = application.getAppGitUrl();
		this.operateUserName = application.getOperateUserName();
		this.used = application.getUsed();
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Long getAppVerId() {
		return appVerId;
	}

	public void setAppVerId(Long appVerId) {
		this.appVerId = appVerId;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
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
		this.description = description;
	}

	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
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

	public String getTagUrl() {
		return tagUrl;
	}

	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
	}

	public String getAppGitUrl() {
		return appGitUrl;
	}

	public void setAppGitUrl(String appGitUrl) {
		this.appGitUrl = appGitUrl;
	}

}
