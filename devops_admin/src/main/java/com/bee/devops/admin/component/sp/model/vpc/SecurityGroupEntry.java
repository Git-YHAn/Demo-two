package com.bee.devops.admin.component.sp.model.vpc;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 安全组规则信息
 * 
 * @description SecurityGroupEntry
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class SecurityGroupEntry {
	/**
	 * 安全组规则的描述
	 */
	String description;

	/**
	 * 安全组的ID
	 */
	String securityGroupEntryId;

	/**
	 * 安全组规则的网段
	 */
	String cidrBlock;

	/**
	 * 安全组规则方向，in为入站规则，out为出站规则
	 * 有效值:in | out
	 */
	String direction;

	/**
	 * 协议，IP代表所有协议
	 * 有效值: ip|tcp|udp|icmp
	 */
	String protocol;

	/**
	 * ICMP协议，ICMP类型，只有协议为ICMP类型，才必填。
	 */
	Integer icmpType;

	/**
	 * ICMP协议，ICMP代码，只有协议为ICMP类型，才必填。
	 */
	Integer icmpCode;

	/**
	 * TCP或UDP协议的端口规则起始端口，只有协议为TCP\UDP类型，才必填。范围1-65535。
	 */
	Integer portRangeFrom;

	/**
	 * TCP或UDP协议的端口规则结束端口，只有协议为TCP\UDP类型，才必填。范围1-65535。
	 */
	Integer portRangeTo;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecurityGroupEntryId() {
		return securityGroupEntryId;
	}

	public void setSecurityGroupEntryId(String securityGroupEntryId) {
		this.securityGroupEntryId = securityGroupEntryId;
	}

	public String getCidrBlock() {
		return cidrBlock;
	}

	public void setCidrBlock(String cidrBlock) {
		this.cidrBlock = cidrBlock;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getIcmpType() {
		return icmpType;
	}

	public void setIcmpType(Integer icmpType) {
		this.icmpType = icmpType;
	}

	public Integer getIcmpCode() {
		return icmpCode;
	}

	public void setIcmpCode(Integer icmpCode) {
		this.icmpCode = icmpCode;
	}

	public Integer getPortRangeFrom() {
		return portRangeFrom;
	}

	public void setPortRangeFrom(Integer portRangeFrom) {
		this.portRangeFrom = portRangeFrom;
	}

	public Integer getPortRangeTo() {
		return portRangeTo;
	}

	public void setPortRangeTo(Integer portRangeTo) {
		this.portRangeTo = portRangeTo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
