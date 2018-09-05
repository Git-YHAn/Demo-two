package com.bee.devops.admin.component.sp.ksc.eip.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.eip.Address;

public class DescribeAddressesResponse {
	/**
	 * 请求ID
	 */
	String requestId;
	/**
	 * 安全组信息
	 */
	List<Address> AddressesSet;

	/**
	 * 获取另一页返回结果的 token.
	 * 类型: String
	 */
	String nextToken;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<Address> getAddressesSet() {
		return AddressesSet;
	}

	public void setAddressesSet(List<Address> addressesSet) {
		AddressesSet = addressesSet;
	}

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
