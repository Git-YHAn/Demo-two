package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.Region;

/**
 * 查询地域列表
 * 
 * @description DescribeRegionsResponse
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class DescribeRegionsResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;
	/**
	 * 返回有权限的地域集合
	 * 类型：地域（Region）
	 * 是否可缺省：否
	 */
	List<Region> regionSet = new ArrayList<>();

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<Region> getRegionSet() {
		return regionSet;
	}

	public void setRegionSet(List<Region> regionSet) {
		this.regionSet = regionSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
