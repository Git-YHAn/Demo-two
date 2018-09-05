package com.bee.devops.admin.core.vo.request;

import com.bee.devops.admin.common.request.RestRequest;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 请求参数对象
 */
public class OpsBaseAppRequest extends RestRequest {

    private static final long serialVersionUID = -5034322744608385212L;

    private Long appId;

    @NotNull(message = "{base.application.proId.notNull}")
    private Long proId;

    private String proName;

    @NotBlank(message = "{base.application.appName.notNull}")
    @Pattern(regexp = "^[\\w\\#-＿\u4e00-\u9fa5]{1,20}$", message = "{base.application.appName.format}")
    private String appName;

    @NotBlank(message = "{base.application.appCode.notNull}")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "{base.application.appCode.format}")
    private String appCode;

    @NotNull(message = "{base.application.appType.notNull}")
    private Integer appType;

    private String currentVersion;

    @Pattern(regexp = "^.{0,50}$", message = "{base.application.description.format}")
    private String description;

    private String logPath;
    
    private Long appTypeId;
    
    private String appTypeName;
    
    @NotBlank(message = "{base.application.appGitUrl.notNull}")
    private String appGitUrl;

    public String getAppGitUrl() {
        return appGitUrl;
    }

    public void setAppGitUrl(String appGitUrl) {
        this.appGitUrl = appGitUrl;
    }

    public OpsBaseApp transEntity() {
        OpsBaseApp applicaion = new OpsBaseApp();
        applicaion.setProId(proId);
        applicaion.setAppName(appName);
        applicaion.setAppType(appType);
        applicaion.setLogPath(logPath);
        applicaion.setAppCode(appCode);
        applicaion.setDescription(description);
        applicaion.setAppGitUrl(appGitUrl);
        applicaion.setAppId(appId);
        applicaion.setAppTypeId(appTypeId);
        applicaion.setAppTypeName(appTypeName);
        return applicaion;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

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
}
