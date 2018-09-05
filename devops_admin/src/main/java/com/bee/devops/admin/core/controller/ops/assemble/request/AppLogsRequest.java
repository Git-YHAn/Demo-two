package com.bee.devops.admin.core.controller.ops.assemble.request;

import javax.validation.constraints.NotNull;

import com.bee.devops.admin.common.request.RestRequest;

public class AppLogsRequest extends RestRequest {
	private static final long serialVersionUID = -889342014810951933L;

	@NotNull(message = "{app.instance.instanceId.notnull}")
	Long appInstanceId;
	Long appInstanceDetailId;

	public Long getAppInstanceId() {
		return appInstanceId;
	}

	public void setAppInstanceId(Long appInstanceId) {
		this.appInstanceId = appInstanceId;
	}

	public Long getAppInstanceDetailId() {
		return appInstanceDetailId;
	}

	public void setAppInstanceDetailId(Long appInstanceDetailId) {
		this.appInstanceDetailId = appInstanceDetailId;
	}

}
