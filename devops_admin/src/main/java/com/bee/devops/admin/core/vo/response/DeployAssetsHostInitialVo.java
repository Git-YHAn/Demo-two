package com.bee.devops.admin.core.vo.response;

public class DeployAssetsHostInitialVo {
    private String assetsName;
    private String sshAddress;
    private Integer initialStatus;

    private String envName;
    private String proName;
    private String appName;

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getSshAddress() {
        return sshAddress;
    }

    public void setSshAddress(String sshAddress) {
        this.sshAddress = sshAddress;
    }

    public Integer getInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(Integer initialStatus) {
        this.initialStatus = initialStatus;
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
}
