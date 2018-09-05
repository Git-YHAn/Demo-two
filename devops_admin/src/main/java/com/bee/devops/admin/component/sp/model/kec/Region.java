package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 地域类型
 * 
 * @description Region
 * @author TurnerXi
 * @date 2018年8月28日
 */
public class Region {
	/**
	 * 地域的英文名称
	 * 类型：String
	 * 是否可缺省：否
	 */
	String region;

	/**
	 * 地域的中文名称
	 * 类型：String
	 * 是否可缺省:否
	 */
	String regionName;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
