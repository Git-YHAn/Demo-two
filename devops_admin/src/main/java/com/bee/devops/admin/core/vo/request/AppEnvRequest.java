package com.bee.devops.admin.core.vo.request;

/**
 * 查询DEPLOY_APP_ENV_REL临时对象
 * @author Administrator
 *
 */
public class AppEnvRequest {
	private String appName;	//应用名称
	private String envName;	//环境名称
	
	public AppEnvRequest(){}
	
	public AppEnvRequest(String appName, String envName) {
		super();
		this.appName = appName;
		this.envName = envName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	@Override
	public String toString() {
		return "AppEnvTemp [appName=" + appName + ", envName=" + envName + "]";
	}
}
