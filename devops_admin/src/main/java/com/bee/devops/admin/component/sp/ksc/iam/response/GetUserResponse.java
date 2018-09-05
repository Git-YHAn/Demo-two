package com.bee.devops.admin.component.sp.ksc.iam.response;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.iam.transform.GetUserResult;

/**
 * @description GetUserResult
 * @author TurnerXi
 * @date 2018年7月17日
 */
public class GetUserResponse {
	String RequestId;
	GetUserResult getUserResult;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public GetUserResult getGetUserResult() {
		return getUserResult;
	}

	public void setGetUserResult(GetUserResult getUserResult) {
		this.getUserResult = getUserResult;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
