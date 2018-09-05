package com.bee.devops.admin.component.sp.model.kec;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 机型（实例族）配置信息类型
 * 
 * @description InstanceFamily
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class InstanceFamily {
	/**
	 * 该类型支持的可用区
	 * 类型：String列表
	 */
	private List<AvailabilityZoneInfo> AvailabilityZoneSet;
	/**
	 * 机型（实例族）名称的中文全称
	 * 类型：String
	 */
	private String InstanceFamilyName;
	/**
	 * 机型（实例族）名称的英文全称
	 * 类型：String
	 */
	private String InstanceFamily;

	public List<AvailabilityZoneInfo> getAvailabilityZoneSet() {
		return AvailabilityZoneSet;
	}

	public void setAvailabilityZoneSet(List<AvailabilityZoneInfo> availabilityZoneSet) {
		AvailabilityZoneSet = availabilityZoneSet;
	}

	public String getInstanceFamily() {
		return InstanceFamily;
	}

	public void setInstanceFamily(String instanceFamily) {
		InstanceFamily = instanceFamily;
	}

	public String getInstanceFamilyName() {
		return InstanceFamilyName;
	}

	public void setInstanceFamilyName(String instanceFamilyName) {
		InstanceFamilyName = instanceFamilyName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
