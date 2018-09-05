package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.AvailabilityZone;

/**
 * 查询可用区列表
 * 
 * @description DescribeAvailabilityZonesResponse
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class DescribeAvailabilityZonesResponse {
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
	List<AvailabilityZone> availabilityZoneSet = new ArrayList<>();

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public List<AvailabilityZone> getAvailabilityZoneSet() {
		return availabilityZoneSet;
	}

	public void setAvailabilityZoneSet(List<AvailabilityZone> availabilityZoneSet) {
		this.availabilityZoneSet = availabilityZoneSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
