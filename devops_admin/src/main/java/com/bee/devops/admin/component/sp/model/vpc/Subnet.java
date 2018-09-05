package com.bee.devops.admin.component.sp.model.vpc;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 子网信息
 * 
 * @description Subnet
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class Subnet {
	/**
	 * 子网创建时间
	 */
	String createTime;

	/**
	 * Vpc的ID
	 */
	String vpcId;

	/**
	 * 子网的ID
	 */
	String subnetId;

	/**
	 * 子网的类型，终端子网（Reserve）、云服务器子网（Normal）、云物理主机子网（Physical）
	 * 有效值: Reserve|Normal|Physical
	 */
	String subnetType;

	/**
	 * 子网的名称
	 */
	String subnetName;

	/**
	 * 子网的网络范围，例如：10.0.0.0/24
	 */
	String cidrBlock;

	/**
	 * DHCP起始IP，Reserve类型时可以缺省。
	 * 类型: ip address
	 */
	String dhcpIpFrom;

	/**
	 * DHCP结束IP，Reserve类型时可以缺省。
	 * 类型: ip address
	 */
	String dhcpIpTo;

	/**
	 * 网关的IP，Reserve类型时可以缺省。
	 * 类型: ip address
	 */
	String gatewayIp;

	/**
	 * 子网的Dns1，Reserve类型时可以缺省。
	 */
	String dns1;

	/**
	 * 子网的Dns2，Reserve类型时可以缺省。
	 */
	String dns2;

	/**
	 * NetworkAcl的ID，Reserve类型时可以缺省。
	 */
	String networkAclId;

	/**
	 * NAT的ID，Reserve类型时可以缺省。
	 */
	String natId;

	/**
	 * 子网的可用IP数量
	 */
	String availbleIPNumber;

	/**
	 * 可用区的名称
	 */
	String availabilityZoneName;

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

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}

	public String getSubnetType() {
		return subnetType;
	}

	public void setSubnetType(String subnetType) {
		this.subnetType = subnetType;
	}

	public String getSubnetName() {
		return subnetName;
	}

	public void setSubnetName(String subnetName) {
		this.subnetName = subnetName;
	}

	public String getCidrBlock() {
		return cidrBlock;
	}

	public void setCidrBlock(String cidrBlock) {
		this.cidrBlock = cidrBlock;
	}

	public String getDhcpIpFrom() {
		return dhcpIpFrom;
	}

	public void setDhcpIpFrom(String dhcpIpFrom) {
		this.dhcpIpFrom = dhcpIpFrom;
	}

	public String getDhcpIpTo() {
		return dhcpIpTo;
	}

	public void setDhcpIpTo(String dhcpIpTo) {
		this.dhcpIpTo = dhcpIpTo;
	}

	public String getGatewayIp() {
		return gatewayIp;
	}

	public void setGatewayIp(String gatewayIp) {
		this.gatewayIp = gatewayIp;
	}

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	public String getNetworkAclId() {
		return networkAclId;
	}

	public void setNetworkAclId(String networkAclId) {
		this.networkAclId = networkAclId;
	}

	public String getNatId() {
		return natId;
	}

	public void setNatId(String natId) {
		this.natId = natId;
	}

	public String getAvailbleIPNumber() {
		return availbleIPNumber;
	}

	public void setAvailbleIPNumber(String availbleIPNumber) {
		this.availbleIPNumber = availbleIPNumber;
	}

	public String getAvailabilityZoneName() {
		return availabilityZoneName;
	}

	public void setAvailabilityZoneName(String availabilityZoneName) {
		this.availabilityZoneName = availabilityZoneName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
