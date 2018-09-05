package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.InstanceFamily;

/**
 * 查询机型配置信息
 * 
 * @description DescribeInstanceFamilysResponse
 * @author TurnerXi
 * @date 2018年8月21日
 */
public class DescribeInstanceFamilysResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String RequestId;
	/**
	 * 机型配置信息集合
	 * 类型：String列表
	 */
	List<InstanceFamily> InstanceFamilySet = new ArrayList<>();

	public DescribeInstanceFamilysResponse withInstanceFamilysSet(InstanceFamily... instanceFamilys) {
		for (InstanceFamily instanceFamily : instanceFamilys) {
			this.InstanceFamilySet.add(instanceFamily);
		}
		return this;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public List<InstanceFamily> getInstanceFamilySet() {
		return InstanceFamilySet;
	}

	public void setInstanceFamilySet(List<InstanceFamily> instanceFamilySet) {
		InstanceFamilySet = instanceFamilySet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
