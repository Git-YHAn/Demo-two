package com.bee.devops.admin.component.sp.ksc.vpc.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DescribeSubnetAvailableAddressesResponse {
	/**
	 * 请求ID
	 */
	String requestId;
	/**
	 * 子网内可用IP信息
	 * 类型: String
	 */
	List<String> availableIpAddresses = new ArrayList<>();

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<String> getAvailableIpAddresses() {
		return availableIpAddresses;
	}

	public void setAvailableIpAddresses(List<String> availableIpAddresses) {
		this.availableIpAddresses = availableIpAddresses;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

}