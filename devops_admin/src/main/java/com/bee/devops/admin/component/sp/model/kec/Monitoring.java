package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 监控状态
 * 
 * @description Monitoring
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class Monitoring {
	/**
	 * 监控状态
	 * 类型: String
	 * 有效值: disabled | enabled
	 * 是否可缺省: 否
	 */
	String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
