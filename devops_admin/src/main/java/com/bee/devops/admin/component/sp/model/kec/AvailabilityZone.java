package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 可用区类型
 * 
 * @description AvailabilityZone
 * @author TurnerXi
 * @date 2018年8月28日
 */
public class AvailabilityZone {
	/**
	 * 可用区名
	 * 类型：String
	 * 是否可缺省：是
	 */
	String availabilityZone;
	/**
	 * 可用区所在的地域
	 * 类型：String
	 * 是否可缺省：是
	 */
	String region;

	public String getAvailabilityZone() {
		return availabilityZone;
	}

	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
