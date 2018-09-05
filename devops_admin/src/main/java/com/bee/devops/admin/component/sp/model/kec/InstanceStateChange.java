package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 实例状态改变类型
 * 
 * @description InstanceStateChange
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class InstanceStateChange {
	/**
	 * 实例ID
	 * 类型: String
	 */
	String instanceId;

	/**
	 * 实例状态改变成功与否
	 * 类型: String
	 * 有效值：true（成功）| false（失败）
	 */
	String Return;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getReturn() {
		return Return;
	}

	public void setReturn(String return1) {
		Return = return1;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
