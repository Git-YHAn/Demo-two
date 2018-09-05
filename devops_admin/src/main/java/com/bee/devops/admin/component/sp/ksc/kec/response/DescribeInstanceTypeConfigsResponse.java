package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.InstanceTypeConfig;

/**
 * 查询机型套餐配置信息
 * 
 * @description DescribeInstanceTypeConfigsResponse
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class DescribeInstanceTypeConfigsResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String RequestId;
	/**
	 * 机型套餐配置信息集合
	 * 类型：List
	 * 是否可缺省：是
	 */
	List<InstanceTypeConfig> instanceTypeConfigSet = new ArrayList<>();

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public List<InstanceTypeConfig> getInstanceTypeConfigSet() {
		return instanceTypeConfigSet;
	}

	public void setInstanceTypeConfigSet(List<InstanceTypeConfig> instanceTypeConfigSet) {
		this.instanceTypeConfigSet = instanceTypeConfigSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
