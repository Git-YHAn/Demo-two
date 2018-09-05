package com.bee.devops.admin.core.vo.response;

/**
 * 服务器的状态及相关应用版本的包装类
 * @author Administrator
 *
 */
public class ServerDepAppStatusVo {
	private String appName;//应用名
	private String versionCode;//版本号
	private String assetsName;//服务器名
	private int appStatus;//服务器运行状态
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public int getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}
}
