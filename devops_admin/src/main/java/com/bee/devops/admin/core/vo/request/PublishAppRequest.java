package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class PublishAppRequest  implements Serializable {
	private static final long serialVersionUID = -494838657496914701L;
	private Long envAppId;
	private Long envId;
	private Long proId;
	private Long appId;
	private String versionCode;
	private String[] assetsHost;
	private Long jobId;
	private Integer operateType;
	private String deployAppGitUrl;
	private String deploytype;
	private String labels;
	private String namespace;
	
	public Long getEnvAppId() {
		return envAppId;
	}
	public void setEnvAppId(Long envAppId) {
		this.envAppId = envAppId;
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
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String[] getAssetsHost() {
		return assetsHost;
	}
	public void setAssetsHost(String[] assetsHost) {
		this.assetsHost = assetsHost;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getDeployAppGitUrl() {
		return deployAppGitUrl;
	}
	public void setDeployAppGitUrl(String deployAppGitUrl) {
		this.deployAppGitUrl = deployAppGitUrl;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public String getDeploytype() {
		return deploytype;
	}
	public void setDeploytype(String deploytype) {
		this.deploytype = deploytype;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	
}
