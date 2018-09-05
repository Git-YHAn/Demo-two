package com.bee.devops.admin.component.sp.ksc.iam.response;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.iam.transform.ListProjectResult;

/**
 * @description GetAccountAllProjectListResponse
 * @author TurnerXi
 * @date 2018年7月17日
 */
public class GetAccountAllProjectListResponse {
	String RequestId;
	ListProjectResult listProjectResult;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public ListProjectResult getListProjectResult() {
		return listProjectResult;
	}

	public void setListProjectResult(ListProjectResult listProjectResult) {
		this.listProjectResult = listProjectResult;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
