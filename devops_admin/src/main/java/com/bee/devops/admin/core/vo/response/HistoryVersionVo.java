package com.bee.devops.admin.core.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HistoryVersionVo {
    private String envName;

    private String proName;

    private String appName;

    private String deployVersionCode;
    
    private Integer assetsType;

    private Long assetsId;

    private String assetsName;

    private String sshAddress;
    
    private Integer deployType;

    private Integer autoRestart;

    private Integer publishStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String operationUserName;

    private String description;
    
    private String publishInfo;

    private String appVersionInfo;

    private String configVersionInfo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    /**
     * 以下两项用于在发布历史列表页做发布版本版本比对
     */
    private String tagUrl;

    private String deployAppGitUrl;

    public String getDeployAppGitUrl() {
        return deployAppGitUrl;
    }

    public void setDeployAppGitUrl(String deployAppGitUrl) {
        this.deployAppGitUrl = deployAppGitUrl;
    }

    public String getTagUrl() {
        return tagUrl;
    }

    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
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

    public String getDeployVersionCode() {
        return deployVersionCode;
    }

    public void setDeployVersionCode(String deployVersionCode) {
        this.deployVersionCode = deployVersionCode;
    }

    public Integer getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(Integer assetsType) {
		this.assetsType = assetsType;
	}

	public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public Integer getAutoRestart() {
        return autoRestart;
    }

    public void setAutoRestart(Integer autoRestart) {
        this.autoRestart = autoRestart;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSshAddress() {
        return sshAddress;
    }

    public void setSshAddress(String sshAddress) {
        this.sshAddress = sshAddress;
    }

    public Long getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Long assetsId) {
        this.assetsId = assetsId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}
}
