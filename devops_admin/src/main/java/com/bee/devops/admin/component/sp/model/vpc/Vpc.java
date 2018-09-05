package com.bee.devops.admin.component.sp.model.vpc;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 虚拟私有网络信息
 * 
 * @description Vpc
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class Vpc {
	/**
	 * VPC创建时间
	 */
	String createTime;
	/**
	 * Vpc的名称
	 */
	String vpcName;
	/**
	 * Vpc的ID
	 */
	String vpcId;
	/**
	 * Vpc的网段信息
	 */
	String cidrBlock;
	/**
	 * 是否为默认Vpc
	 */
	Boolean isDefault;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getVpcName() {
		return vpcName;
	}

	public void setVpcName(String vpcName) {
		this.vpcName = vpcName;
	}

	public String getVpcId() {
		return vpcId;
	}

	public void setVpcId(String vpcId) {
		this.vpcId = vpcId;
	}

	public String getCidrBlock() {
		return cidrBlock;
	}

	public void setCidrBlock(String cidrBlock) {
		this.cidrBlock = cidrBlock;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
