package com.bee.devops.admin.component.sp.ksc.vpc.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.vpc.Vpc;

public class DescribeVpcsResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;
	/**
	 * Vpc的信息
	 * 类型: Vpc List
	 */
	List<Vpc> vpcSet;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<Vpc> getVpcSet() {
		return vpcSet;
	}

	public void setVpcSet(List<Vpc> vpcSet) {
		this.vpcSet = vpcSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
