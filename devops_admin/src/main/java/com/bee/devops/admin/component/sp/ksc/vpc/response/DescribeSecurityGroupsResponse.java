package com.bee.devops.admin.component.sp.ksc.vpc.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.vpc.SecurityGroup;

public class DescribeSecurityGroupsResponse {
	/**
	 * 请求ID
	 */
	String requestId;
	/**
	 * 安全组信息
	 */
	List<SecurityGroup> securityGroupSet;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<SecurityGroup> getSecurityGroupSet() {
		return securityGroupSet;
	}

	public void setSecurityGroupSet(List<SecurityGroup> securityGroupSet) {
		this.securityGroupSet = securityGroupSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
