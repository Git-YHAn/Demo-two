package com.bee.devops.admin.core.vo.response;

/**
 * 最新发布版本
 *
 * @author wanghuajie
 * @date 2018/8/13 16:41
 */
public class LatestPublishVersionVo {
    private String depAppVerId;
    private String tagUrl;
    private String description;
    private String appVersionInfo;
    private String configVersionInfo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepAppVerId() {
        return depAppVerId;
    }

    public void setDepAppVerId(String depAppVerId) {
        this.depAppVerId = depAppVerId;
    }

    public String getTagUrl() {
        return tagUrl;
    }

    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
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
}
