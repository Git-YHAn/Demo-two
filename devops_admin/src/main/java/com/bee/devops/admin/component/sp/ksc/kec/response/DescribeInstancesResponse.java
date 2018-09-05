package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.Instance;

public class DescribeInstancesResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;
	/**
	 * 分页标识，如果调用未返回全部实例，标记下次调用的返回值的起点，如果已返回全部实例，则其值为0
	 */
	Integer marker;
	/**
	 * 实例总数
	 */
	Integer instanceCount;
	/**
	 * 销毁实例集合
	 * 类型: 实例（Instance） 列表
	 */
	List<Instance> InstancesSet;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getMarker() {
		return marker;
	}

	public void setMarker(Integer marker) {
		this.marker = marker;
	}

	public Integer getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(Integer instanceCount) {
		this.instanceCount = instanceCount;
	}

	public List<Instance> getInstancesSet() {
		return InstancesSet;
	}

	public void setInstancesSet(List<Instance> instancesSet) {
		InstancesSet = instancesSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
