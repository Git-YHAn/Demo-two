package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.InstanceStateChange;

/**
 * 启动实例
 * 启动一个或者多个处于“停止状态（stopped）”的实例。
 * 
 * @description StartInstancesResponse
 * @author TurnerXi
 * @date 2018年8月22日
 */
public class StopInstancesResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;
	/**
	 * 启动实例集合
	 * 类型: 实例状态改变（InstanceStateChange）
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
