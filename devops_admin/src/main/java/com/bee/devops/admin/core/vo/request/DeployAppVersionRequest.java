package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;

import com.bee.devops.admin.common.request.RestRequest;

public class DeployAppVersionRequest  extends RestRequest {
	private static final long serialVersionUID = -494838657496914701L;

	private Long envId;
	
	private Long proId;

    private Long appId;

    @NotNull(message="{publish.version.appVerId.notNull}")
    private Long appVerId;
    
    private String appVerCode;

    //@NotNull(message="{publish.version.configVerId.notNull}")
    private Long configVerId;
    
    private String configVerCode;

    private String versionCode;

    private Integer status;

    private String description;

    private Long appTypeId;

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	public Long getProId() {
		return proId;
	}

	public String getAppVerCode() {
		return appVerCode;
	}

	public void setAppVerCode(String appVerCode) {
		this.appVerCode = appVerCode;
	}

	public String getConfigVerCode() {
		return configVerCode;
	}

	public void setConfigVerCode(String configVerCode) {
		this.configVerCode = configVerCode;
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

	

	public Long getAppVerId() {
		return appVerId;
	}

	public void setAppVerId(Long appVerId) {
		this.appVerId = appVerId;
	}

	public Long getConfigVerId() {
		return configVerId;
	}

	public void setConfigVerId(Long configVerId) {
		this.configVerId = configVerId;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Long getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Long appTypeId) {
		this.appTypeId = appTypeId;
	}
}
