package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 安全组标识类型
 * 
 * @description GroupIdentifier
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class GroupIdentifier {
	/**
	 * 安全组ID
	 * 类型: String
	 * 是否可缺省: 否
	 */
	String securityGroupId;

	public String getSecurityGroupId() {
		return securityGroupId;
	}

	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
