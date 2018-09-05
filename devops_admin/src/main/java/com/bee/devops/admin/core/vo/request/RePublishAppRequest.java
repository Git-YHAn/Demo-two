package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class RePublishAppRequest implements Serializable {
	private static final long serialVersionUID = 7722416463931075406L;
	private Long publishId; // 应用发布ID
	private Integer deployType;
	
	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}
}
