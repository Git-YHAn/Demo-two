package com.bee.devops.admin.core.vo.request;

public class DeployHostInitialRequest {
    private Integer pageSize;

    private Integer pageNum;

    private String assetsNameOrSsh;

    private String envName;

    private String proName;

    private String appName;

    private Long assetsId;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getAssetsNameOrSsh() {
        return assetsNameOrSsh;
    }

    public void setAssetsNameOrSsh(String assetsNameOrSsh) {
        this.assetsNameOrSsh = assetsNameOrSsh;
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

    public Long getAssetsId() {
        return assetsId;
    }

    public void setAssetsIds(Long assetsId) {
        this.assetsId = assetsId;
    }
}
