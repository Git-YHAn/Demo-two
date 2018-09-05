package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class PublishCancelRequest implements Serializable {
	private static final long serialVersionUID = -595517555114259196L;
	private Long publishId; // 应用发布ID

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

}
