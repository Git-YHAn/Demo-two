package com.bee.devops.admin.core.vo.response;

import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;

public class AppServiceManagerVo extends OpsAssembleApp {

    private Long appTypeId;

    private String appTypeName;

    private String msProfileTypeName;

    private String msZoneTypeName;

    private String msRegionTypeName;

    public Long getAppTypeId() {
        return appTypeId;
    }

    public void setAppTypeId(Long appTypeId) {
        this.appTypeId = appTypeId;
    }

    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        this.appTypeName = appTypeName;
    }

    public String getMsProfileTypeName() {
        return msProfileTypeName;
    }

    public void setMsProfileTypeName(String msProfileTypeName) {
        this.msProfileTypeName = msProfileTypeName;
    }

    public String getMsZoneTypeName() {
        return msZoneTypeName;
    }

    public void setMsZoneTypeName(String msZoneTypeName) {
        this.msZoneTypeName = msZoneTypeName;
    }

    public String getMsRegionTypeName() {
        return msRegionTypeName;
    }

    public void setMsRegionTypeName(String msRegionTypeName) {
        this.msRegionTypeName = msRegionTypeName;
    }
}