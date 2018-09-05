package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 描述可用区信息
 * 
 * @description AvailabilityZoneInfo
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class AvailabilityZoneInfo {
	/**
	 * 可用区的名称
	 * 类型:String
	 */
	private String AzCode;

	public String getAzCode() {
		return AzCode;
	}

	public void setAzCode(String azCode) {
		AzCode = azCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
