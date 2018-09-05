package com.bee.devops.admin.component.sp.model.eip;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Address {

	/**
	 * 弹性IP创建时间
	 * 类型:String
	 */
	String createTime;

	/**
	 * 弹性IP
	 * 类型:String
	 */
	String publicIp;

	/**
	 * 弹性IP的ID
	 * 类型: String
	 */
	String allocationId;

	/**
	 * 弹性IP的状态，已绑定(associate)，未绑定(disassociate)
	 * 类型: String
	 * 有效值: associate|disassociate
	 */
	String state;

	/**
	 * 弹性IP的线路类型的ID
	 * 类型: String
	 * 
	 */
	String lineId;

	/**
	 * 弹性IP的带宽
	 * 类型: Integer
	 */
	Integer bandWidth;

	/**
	 * 绑定弹性IP的实例类型，IP映射(Ipfwd)，负载均衡（Slb）
	 * 类型: String
	 * 有效值：Ipfwd|Slb
	 */
	String instanceType;

	/**
	 * 弹性IP绑定的实例信息。绑定类型为Ipfwd时，InstanceId为云服务器的ID。绑定类型为Slb时，InstanceId为弹性IP的ID。
	 * 类型: String
	 */
	String instanceId;

	/**
	 * 网络接口的标识，当InstanceType为Ipfwd时，不可缺省。
	 * 类型: String
	 */
	String networkInterfaceId;

	/**
	 * 互联网网关的ID
	 * 类型: String
	 */
	String internetGatewayId;

	/**
	 * 共享带宽的ID
	 * 类型: String
	 */
	String BandWidthShareId;

	/**
	 * 是否属于共享带宽
	 * 类型: Boolean
	 */
	Boolean isBandWidthShare;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public Integer getBandWidth() {
		return bandWidth;
	}

	public void setBandWidth(Integer bandWidth) {
		this.bandWidth = bandWidth;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getNetworkInterfaceId() {
		return networkInterfaceId;
	}

	public void setNetworkInterfaceId(String networkInterfaceId) {
		this.networkInterfaceId = networkInterfaceId;
	}

	public String getInternetGatewayId() {
		return internetGatewayId;
	}

	public void setInternetGatewayId(String internetGatewayId) {
		this.internetGatewayId = internetGatewayId;
	}

	public String getBandWidthShareId() {
		return BandWidthShareId;
	}

	public void setBandWidthShareId(String bandWidthShareId) {
		BandWidthShareId = bandWidthShareId;
	}

	public Boolean getIsBandWidthShare() {
		return isBandWidthShare;
	}

	public void setIsBandWidthShare(Boolean isBandWidthShare) {
		this.isBandWidthShare = isBandWidthShare;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
