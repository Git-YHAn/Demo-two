package com.bee.devops.admin.core.vo.response;

/**
 * 应用环境查询的包装类
 * @author Yang Chunhai
 */
public class AppEnvVo {
	private Long proId;
	
	private Long[] appId;
	
	private Long envId;

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long[] getAppId() {
		return appId;
	}

	public void setAppId(Long[] appId) {
		this.appId = appId;
	}

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}
}
