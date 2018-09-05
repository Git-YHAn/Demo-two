package com.bee.devops.admin.core.common.entity.ops;

public class OpsBaseAppType {

	public static final int APP_TYPE_JAVA = 100;// JAVA应用类型
	public static final int APP_TYPE_WEB = 200;// web应用类型
	public static final int APP_TYPE_MS = 300;// 微服务应用类型
	public static final int APP_TYPE_STATIC = 400;// STATIC应用类型

    private Long appTypeId;

    private String appTypeName;

    private String scriptPath;

    private String deployPath;

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
        this.appTypeName = appTypeName == null ? null : appTypeName.trim();
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath == null ? null : scriptPath.trim();
    }

    public String getDeployPath() {
        return deployPath;
    }

    public void setDeployPath(String deployPath) {
        this.deployPath = deployPath == null ? null : deployPath.trim();
    }
    
    
}