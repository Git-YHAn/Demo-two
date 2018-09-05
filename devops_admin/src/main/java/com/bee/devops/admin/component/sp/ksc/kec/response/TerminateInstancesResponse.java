package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.InstanceStateChange;

public class TerminateInstancesResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;
	/**
	 * 销毁实例集合
	 * 类型: 实例（Instance） 列表
	 */
	List<InstanceStateChange> instancesSet;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<InstanceStateChange> getInstancesSet() {
		return instancesSet;
	}

	public void setInstancesSet(List<InstanceStateChange> instancesSet) {
		this.instancesSet = instancesSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
