package com.bee.devops.admin.component.sp.ksc.vpc.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.vpc.Subnet;

public class DescribeSubnetsResponse {
	/**
	 * 请求ID
	 */
	String requestId;
	/**
	 * Subnet的信息
	 */
	List<Subnet> subnetSet;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<Subnet> getSubnetSet() {
		return subnetSet;
	}

	public void setSubnetSet(List<Subnet> subnetSet) {
		this.subnetSet = subnetSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

}