package com.bee.devops.admin.component.sp.model.vpc;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 安全组信息
 * 
 * @description SecurityGroup
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class SecurityGroup {
	/**
	 * 安全组创建时间
	 */
	String createTime;

	/**
	 * Vpc的ID
	 */
	String vpcId;

	/**
	 * 安全组的名称
	 */
	String securityGroupName;

	/**
	 * 安全组的ID
	 */
	String securityGroupId;

	/**
	 * 安全组的类型
	 * 有效值:default|other
	 */
	String securityGroupType;

	/**
	 * 安全组规则的信息
	 */
	List<SecurityGroupEntry> securityGroupEntrySet;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getVpcId() {
		return vpcId;
	}

	public void setVpcId(String vpcId) {
		this.vpcId = vpcId;
	}

	public String getSecurityGroupName() {
		return securityGroupName;
	}

	public void setSecurityGroupName(String securityGroupName) {
		this.securityGroupName = securityGroupName;
	}

	public String getSecurityGroupId() {
		return securityGroupId;
	}

	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}

	public String getSecurityGroupType() {
		return securityGroupType;
	}

	public void setSecurityGroupType(String securityGroupType) {
		this.securityGroupType = securityGroupType;
	}

	public List<SecurityGroupEntry> getSecurityGroupEntrySet() {
		return securityGroupEntrySet;
	}

	public void setSecurityGroupEntrySet(List<SecurityGroupEntry> securityGroupEntrySet) {
		this.securityGroupEntrySet = securityGroupEntrySet;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
