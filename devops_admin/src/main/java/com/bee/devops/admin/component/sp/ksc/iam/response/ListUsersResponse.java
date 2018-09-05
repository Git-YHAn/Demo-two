package com.bee.devops.admin.component.sp.ksc.iam.response;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.iam.transform.ListUserResult;
import com.bee.devops.admin.component.sp.ksc.iam.transform.ResponseMetadata;

public class ListUsersResponse {
	ListUserResult listUserResult;
	ResponseMetadata responseMetadata;

	public ListUserResult getListUserResult() {
		return listUserResult;
	}

	public void setListUserResult(ListUserResult listUserResult) {
		this.listUserResult = listUserResult;
	}

	public ResponseMetadata getResponseMetadata() {
		return responseMetadata;
	}

	public void setResponseMetadata(ResponseMetadata responseMetadata) {
		this.responseMetadata = responseMetadata;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
