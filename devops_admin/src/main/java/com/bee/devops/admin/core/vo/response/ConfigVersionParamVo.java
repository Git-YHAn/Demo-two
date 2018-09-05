package com.bee.devops.admin.core.vo.response;

public class ConfigVersionParamVo {
	private Long envId;
	private Long proId;
	private Long appId;
	private String conVerName;
	private String conVerCode;
	private String description;
//	private String identify;
//	public String getIdentify() {
//		return identify;
//	}
//	public void setIdentify(String identify) {
//		this.identify = identify;
//	}
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
	public String getConVerName() {
		return conVerName;
	}
	public void setConVerName(String conVerName) {
		this.conVerName = conVerName;
	}
	public String getConVerCode() {
		return conVerCode;
	}
	public void setConVerCode(String conVerCode) {
		this.conVerCode = conVerCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}



