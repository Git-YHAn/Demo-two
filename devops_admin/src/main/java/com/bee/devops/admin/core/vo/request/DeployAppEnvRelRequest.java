package com.bee.devops.admin.core.vo.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 应用展示表
 * @author Administrator
 *
 */
public class DeployAppEnvRelRequest {
	private Long appEnvId;	//编号
	private String appName;	//应用名称
	private String envName;	//环境名称
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;	//创建时间
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date updateTime;	//修改时间
	private String currentVersion;	//当前版本
	private String configGitUrl;	//配置版本库地址
	private String appGitUrl;	//应用配置版本库地址
	private String deployAppGitUrl;	//发布版本库地址
	public DeployAppEnvRelRequest() {
		super();
	}

	public DeployAppEnvRelRequest(Long appEnvId, String appName, String envName, Date createTime, Date updateTime,
			String currentVersion, String configGitUrl, String appGitUrl, String deployAppGitUrl) {
		super();
		this.appEnvId = appEnvId;
		this.appName = appName;
		this.envName = envName;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.currentVersion = currentVersion;
		this.configGitUrl = configGitUrl;
		this.appGitUrl = appGitUrl;
		this.deployAppGitUrl = deployAppGitUrl;
	}

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
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
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
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getConfigGitUrl() {
		return configGitUrl;
	}
	public void setConfigGitUrl(String configGitUrl) {
		this.configGitUrl = configGitUrl;
	}
	public String getAppGitUrl() {
		return appGitUrl;
	}
	public void setAppGitUrl(String appGitUrl) {
		this.appGitUrl = appGitUrl;
	}
	public String getDeployAppGitUrl() {
		return deployAppGitUrl;
	}
	public void setDeployAppGitUrl(String deployAppGitUrl) {
		this.deployAppGitUrl = deployAppGitUrl;
	}
	
	@Override
	public String toString() {
		return "DeployAppEnvRel [appEnvId=" + appEnvId + ", appName=" + appName + ", envName=" + envName
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", currentVersion=" + currentVersion
				+ ", configGitUrl=" + configGitUrl + ", appGitUrl=" + appGitUrl + ", deployAppGitUrl=" + deployAppGitUrl
				+ "]";
	}
}
