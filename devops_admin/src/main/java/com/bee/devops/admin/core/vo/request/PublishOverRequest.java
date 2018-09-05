package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class PublishOverRequest implements Serializable {
	private static final long serialVersionUID = -142055753589997042L;
	private Long publishId; // 应用发布ID

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

}
