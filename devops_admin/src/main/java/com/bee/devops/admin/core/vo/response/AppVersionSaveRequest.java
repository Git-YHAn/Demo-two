package com.bee.devops.admin.core.vo.response;

import javax.validation.constraints.NotNull;

public class AppVersionSaveRequest {
	@NotNull(message="{app.version.envId.notnull}")
	private Long envId;
	@NotNull(message="{app.version.proId.notnull}")
	private Long proId;
	@NotNull(message="{app.version.appId.notnull}")
	private Long appId;
	private String description;

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

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
