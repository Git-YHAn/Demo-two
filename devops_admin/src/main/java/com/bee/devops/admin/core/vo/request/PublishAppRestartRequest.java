package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class PublishAppRestartRequest implements Serializable {
	private static final long serialVersionUID = 957623962459367393L;
	private Long appEnvId;
	private Integer deployType;
	private Long[] serverIds;

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public Long[] getServerIds() {
		return serverIds;
	}

	public void setServerIds(Long[] serverIds) {
		this.serverIds = serverIds;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	
}
