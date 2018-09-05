package com.bee.devops.admin.core.vo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeployAppVersionVo {
	private String envName;

    private String proName;

    private String appName;

    private String versionCode;

    private String appVersionCode;
    
    private String configVersionCode;

    private String status;

    private String description;

    private String operateUserName;
    
    private String deployAppImagesGitUrl;
    
    private String deployAppGitUrl;
    
    private Long envId;
    
    private Long proId;
    
    private Long appId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    private String tagUrl;

    private Integer used;

	/**
	 * 以下4个属性用于在发布版本列表页面进行各版本比对,2018.07.27
	 */
    private String configGitUrl;

    private String appGitUrl;

    private String configTagUrl;

    private String appTagUrl;

	/**
	 * 返回发布版本对应的应用版本和配置版本的版本信息
	 */
	private String appVersionInfo;

	private String configVersionInfo;

	public String getAppVersionInfo() {
		return appVersionInfo;
	}

	public void setAppVersionInfo(String appVersionInfo) {
		this.appVersionInfo = appVersionInfo;
	}

	public String getConfigVersionInfo() {
		return configVersionInfo;
	}

	public void setConfigVersionInfo(String configVersionInfo) {
		this.configVersionInfo = configVersionInfo;
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

	public String getConfigTagUrl() {
		return configTagUrl;
	}

	public void setConfigTagUrl(String configTagUrl) {
		this.configTagUrl = configTagUrl;
	}

	public String getAppTagUrl() {
		return appTagUrl;
	}

	public void setAppTagUrl(String appTagUrl) {
		this.appTagUrl = appTagUrl;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}


	public String getDeployAppImagesGitUrl() {
		return deployAppImagesGitUrl;
	}

	public void setDeployAppImagesGitUrl(String deployAppImagesGitUrl) {
		this.deployAppImagesGitUrl = deployAppImagesGitUrl;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	public String getDeployAppGitUrl() {
		return deployAppGitUrl;
	}

	public void setDeployAppGitUrl(String deployAppGitUrl) {
		this.deployAppGitUrl = deployAppGitUrl;
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

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getConfigVersionCode() {
		return configVersionCode;
	}

	public void setConfigVersionCode(String configVersionCode) {
		this.configVersionCode = configVersionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
}
