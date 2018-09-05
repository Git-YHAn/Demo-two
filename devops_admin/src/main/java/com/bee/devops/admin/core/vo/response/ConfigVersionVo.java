package com.bee.devops.admin.core.vo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConfigVersionVo {
	private Long configVerId;
	
	private String envName;

    private String proName;

    private String appName;

    private String versionName;
    
	private String versionCode;

    private String status;

    private String description;

    private String operateUserName;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;
    
    private String tagUrl;
    
    private String configGitUrl;

	/**
	 * 用于标识该配置版本是否可用
	 */
    private Integer used;

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Long getConfigVerId() {
		return configVerId;
	}

	public void setConfigVerId(Long configVerId) {
		this.configVerId = configVerId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
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

	public String getConfigverName() {
		return versionName;
	}

	public void setConfigverName(String configverName) {
		this.versionName = configverName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
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

	public String getConfigGitUrl() {
		return configGitUrl;
	}

	public void setConfigGitUrl(String configGitUrl) {
		this.configGitUrl = configGitUrl;
	}
}
