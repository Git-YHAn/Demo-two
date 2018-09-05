package com.bee.devops.admin.core.vo.response;

public class AppEnvProCodeVo {
	private String appCode;
	private String envCode;
	private String proCode;

	public AppEnvProCodeVo(String appCode, String envCode, String proCode) {
		super();
		this.appCode = appCode;
		this.envCode = envCode;
		this.proCode = proCode;
	}

	public AppEnvProCodeVo() {
		super();
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getEnvCode() {
		return envCode;
	}

	public void setEnvCode(String envCode) {
		this.envCode = envCode;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	@Override
	public String toString() {
		return "AppEnvProCodeVo [appCode=" + appCode + ", envCode=" + envCode + ", proCode=" + proCode + "]";
	}
}
