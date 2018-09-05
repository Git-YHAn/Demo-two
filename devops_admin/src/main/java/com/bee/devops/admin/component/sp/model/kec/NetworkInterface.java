package com.bee.devops.admin.component.sp.model.kec;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.eip.Address;
import com.bee.devops.admin.component.sp.model.vpc.Subnet;
import com.bee.devops.admin.component.sp.model.vpc.Vpc;

/**
 * 网络接口类型
 * 
 * @description NetworkInterface
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class NetworkInterface {
	/**
	 * 网络接口ID
	 * 类型: String
	 * 是否可缺省: 否
	 */
	String networkInterfaceId;

	/**
	 * 网卡的类型
	 * 有效值:primary|extension
	 * 类型:String
	 * 是否可缺省: 否
	 */
	String networkInterfaceType;

	/**
	 * 网卡的MAC地址
	 * 类型:String
	 * 是否可缺省: 否
	 */
	String macAddress;

	/**
	 * 
	 * 子网ID
	 * 类型: String
	 * 是否可缺省: 否
	 */
	String subnetId;

	/**
	 * 私有IP地址
	 * 类型: String
	 * 是否可缺省: 否
	 */
	String privateIpAddress;

	/**
	 * 实例绑定的安全组集合
	 * 类型: 安全组标识（GroupIdentifier） 列表
	 * 是否可缺省: 否
	 */
	List<GroupIdentifier> securityGroupSet;

	/**
	 * Vpc的ID
	 * 类型: String
	 */
	String vpcId;

	/**
	 * Vpc
	 */
	Vpc vpc;

	/**
	 * subNet
	 */
	Subnet subNet;

	/**
	 * 弹性IP
	 */
	Address address;

	public String getNetworkInterfaceId() {
		return networkInterfaceId;
	}

	public void setNetworkInterfaceId(String networkInterfaceId) {
		this.networkInterfaceId = networkInterfaceId;
	}

	public String getNetworkInterfaceType() {
		return networkInterfaceType;
	}

	public void setNetworkInterfaceType(String networkInterfaceType) {
		this.networkInterfaceType = networkInterfaceType;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}

	public String getPrivateIpAddress() {
		return privateIpAddress;
	}

	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}

	public List<GroupIdentifier> getSecurityGroupSet() {
		return securityGroupSet;
	}

	public void setSecurityGroupSet(List<GroupIdentifier> securityGroupSet) {
		this.securityGroupSet = securityGroupSet;
	}

	public String getVpcId() {
		return vpcId;
	}

	public void setVpcId(String vpcId) {
		this.vpcId = vpcId;
	}

	public Vpc getVpc() {
		return vpc;
	}

	public void setVpc(Vpc vpc) {
		this.vpc = vpc;
	}

	public Subnet getSubNet() {
		return subNet;
	}

	public void setSubNet(Subnet subNet) {
		this.subNet = subNet;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
