package com.bee.devops.admin.core.vo.response;

public class SaveSerAppEnvParamVo {
	private Long envId;
	private Long appId;
	private Long[] serviceId;
	
	public Long getEnvId() {
		return envId;
	}
	public void setEnvId(Long envId) {
		this.envId = envId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long[] getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long[] serviceId) {
		this.serviceId = serviceId;
	}
}
