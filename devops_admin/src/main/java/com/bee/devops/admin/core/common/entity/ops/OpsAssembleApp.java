package com.bee.devops.admin.core.common.entity.ops;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OpsAssembleApp {
    /**
     * 上传中
     */
    public static final int UPLOADING = 1;
    /**
     * 上传完成
     */
    public static final int UPLOAD_SUCCESS = 2;
    /**
     * 上传失败
     */
    public static final int UPLOAD_FAILURE = -1;

    private Long appEnvId;

    private Long appId;

    private Long envId;

    private Long proId;

    private String appName;

    private String envName;

    private String proName;

    private String namespace;

    private String labels;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    private String currentVersion;

    private String currentContain;

    private String configGitUrl;

    private String deployAppGitUrl;

    private String deployAppImagesGitUrl;

    private Integer msPort;

    private String msEurekaAddress;

    private Long msProfileTypeId;

    private Long msZoneTypeId;

    private Long msRegionTypeId;

    private String msConfigUrl;

    private Long appTypeId;

    /**
     * 应用上传状态
     */
    private int uploadStatus;

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getDeployAppImagesGitUrl() {
        return deployAppImagesGitUrl;
    }

    public void setDeployAppImagesGitUrl(String deployAppImagesGitUrl) {
        this.deployAppImagesGitUrl = deployAppImagesGitUrl;
    }

    public String getAppName() {
        return appName;
    }

    public Long getAppEnvId() {
        return appEnvId;
    }

    public void setAppEnvId(Long appEnvId) {
        this.appEnvId = appEnvId;
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

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
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

    public String getCurrentContain() {
        return currentContain;
    }

    public void setCurrentContain(String currentContain) {
        this.currentContain = currentContain;
    }

    public String getConfigGitUrl() {
        return configGitUrl;
    }

    public void setConfigGitUrl(String configGitUrl) {
        this.configGitUrl = configGitUrl;
    }

    public String getDeployAppGitUrl() {
        return deployAppGitUrl;
    }

    public void setDeployAppGitUrl(String deployAppGitUrl) {
        this.deployAppGitUrl = deployAppGitUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getMsPort() {
        return msPort;
    }

    public void setMsPort(Integer msPort) {
        this.msPort = msPort;
    }

    public String getMsEurekaAddress() {
        return msEurekaAddress;
    }

    public void setMsEurekaAddress(String msEurekaAddress) {
        this.msEurekaAddress = msEurekaAddress;
    }

    public Long getMsProfileTypeId() {
        return msProfileTypeId;
    }

    public void setMsProfileTypeId(Long msProfileTypeId) {
        this.msProfileTypeId = msProfileTypeId;
    }

    public Long getMsZoneTypeId() {
        return msZoneTypeId;
    }

    public void setMsZoneTypeId(Long msZoneTypeId) {
        this.msZoneTypeId = msZoneTypeId;
    }

    public Long getMsRegionTypeId() {
        return msRegionTypeId;
    }

    public void setMsRegionTypeId(Long msRegionTypeId) {
        this.msRegionTypeId = msRegionTypeId;
    }

    public String getMsConfigUrl() {
        return msConfigUrl;
    }

    public void setMsConfigUrl(String msConfigUrl) {
        this.msConfigUrl = msConfigUrl;
    }

    public Long getAppTypeId() {
        return appTypeId;
    }

    public void setAppTypeId(Long appTypeId) {
        this.appTypeId = appTypeId;
    }
}