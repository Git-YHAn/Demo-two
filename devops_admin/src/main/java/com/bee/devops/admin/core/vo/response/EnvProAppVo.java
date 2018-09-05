package com.bee.devops.admin.core.vo.response;

public class EnvProAppVo {
	private Long sourceEnvId;
	private Long sourcepProId;
	private Long sourceAppId;
	private Long envId;
	private Long proId;
	private Long appId;
	private String appVerCode;
	public Long getSourceEnvId() {
		return sourceEnvId;
	}
	public void setSourceEnvId(Long sourceEnvId) {
		this.sourceEnvId = sourceEnvId;
	}
	public Long getSourcepProId() {
		return sourcepProId;
	}
	public void setSourcepProId(Long sourcepProId) {
		this.sourcepProId = sourcepProId;
	}
	public Long getSourceAppId() {
		return sourceAppId;
	}
	public void setSourceAppId(Long sourceAppId) {
		this.sourceAppId = sourceAppId;
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
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppVerCode() {
		return appVerCode;
	}
	public void setAppVerCode(String appVerCode) {
		this.appVerCode = appVerCode;
	}
}
