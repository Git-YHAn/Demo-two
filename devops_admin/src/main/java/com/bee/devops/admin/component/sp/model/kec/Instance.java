package com.bee.devops.admin.component.sp.model.kec;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * KEC实例
 * 
 * @description KecInstance
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class Instance {
	/**
	 * 实例ID
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 * 是否可缺省: 否
	 */
	String instanceId;
	/**
	 * 实例名称 是否可缺省: 否
	 */
	String instanceName;
	/**
	 * 实例配置，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 是否可缺省: 是
	 */
	InstanceConfigure instanceConfigure;
	/**
	 * 镜像ID，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 * 是否可缺省: 是
	 */
	String imageId;
	/**
	 * Image
	 */
	KECImage image;
	/**
	 * 实例套餐类型，“创建实例”时不返回该信息，“描述实例”时返回该信息 类型: String 有效值: IO优化型I1：I1.1A | I1.1B|
	 * I1.1C| I1.2A| I1.2B| I1.2C| I1.4A| I1.4B| I1.4C| I1.8A| I1.8B| I1.8C| I1.16A|
	 * I1.16B| I1.16C| I1.24A| I1.24B| I1.24C| I1.24D| I1.32A| I1.32B； 计算优化型C1：C1.1A
	 * | C1.1B| C1.1C| C1.2A| C1.2B| C1.2C| C1.4A| C1.4B| C1.4C|C1.8A| C1.8B| C1.8C|
	 * C1.16A| C1.16B| C1.16C| C1.24A| C1.24B| C1.24C| C1.24D| C1.32A| C1.32B
	 * IO优化型I2：I2.1A| I2.1B | I2.1C | I2.2B | I2.2C | I2.4B | I2.4C | I2.8B | I2.8C
	 * | I2.16B | I2.16C | I2.24B | I2.24C | I2.32B | I2.32C | 具体套餐信息参考 实例套餐类型定义
	 * 是否可缺省: 是
	 */

	String instanceType;
	/**
	 * 实例状态，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 类型: 实例状态（InstanceState） 类型
	 * 是否可缺省: 是
	 */
	InstanceState instanceState;
	/**
	 * 主网卡子网ID，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 类型: String
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 * 是否可缺省: 是
	 */
	String subnetId;
	// /**
	// * 主网卡VPCID，“创建实例”时不返回该信息，“描述实例”时返回该信息 类型: String
	// * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	// * 是否可缺省: 是
	// */
	// String vpcId;
	/**
	 * 主网卡私有IP地址
	 * 类型: String
	 * 有效值：标准IP地址格式
	 * 是否可缺省: 是
	 */
	String privateIpAddress;

	/**
	 * 监控状态，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 类型: 监控状态 （Monitoring） 类型
	 * 是否可缺省: 是
	 */
	Monitoring monitoring;

	/**
	 * 开启增强联网 类型：Boolean 是否可缺省：是
	 */
	Boolean sriovNetSupport;

	/**
	 * 创建时间，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 类型: String
	 * 有效值：按照ISO8601标准，使用UTC时间，格式为"YYYY-MM-DDThh:mm:ssZ"例如，2014-05-26T12:00:00Z （为北京时间2014年5月26日20点0分0秒）
	 * 是否可缺省: 否
	 */
	String creationDate;
	/**
	 * 网络接口集合，“创建实例”时不返回该信息，“描述实例”时返回该信息
	 * 类型: 网络接口（NetworkInterface）列表
	 * 是否可缺省: 是
	 */

	List<NetworkInterface> networkInterfaceSet;

	/**
	 * 实例所属项目ID，“创建实例”不返回该信息，“描述实例”返回该信息
	 * 有效值：账户有权限的项目ID，0为默认项目
	 * 类型: long
	 * 是否可缺省: 是
	 */
	long projectId;

	/**
	 * 计费类型
	 * 类型：String
	 * 有效值：Monthly（包年包月）、Daily（按日月结）
	 * 是否可缺省：否
	 */
	String chargeType;

	/**
	 * 可用区名称
	 */
	String AvailabilityZoneName;
	/**
	 * 产品类型
	 */
	String ProductType;
	/**
	 * 产品描述
	 */
	String ProductWhat;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public InstanceConfigure getInstanceConfigure() {
		return instanceConfigure;
	}

	public void setInstanceConfigure(InstanceConfigure instanceConfigure) {
		this.instanceConfigure = instanceConfigure;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public InstanceState getInstanceState() {
		return instanceState;
	}

	public void setInstanceState(InstanceState instanceState) {
		this.instanceState = instanceState;
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

	public Monitoring getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(Monitoring monitoring) {
		this.monitoring = monitoring;
	}

	public Boolean getSriovNetSupport() {
		return sriovNetSupport;
	}

	public void setSriovNetSupport(Boolean sriovNetSupport) {
		this.sriovNetSupport = sriovNetSupport;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public List<NetworkInterface> getNetworkInterfaceSet() {
		return networkInterfaceSet;
	}

	public void setNetworkInterfaceSet(List<NetworkInterface> networkInterfaceSet) {
		this.networkInterfaceSet = networkInterfaceSet;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getAvailabilityZoneName() {
		return AvailabilityZoneName;
	}

	public void setAvailabilityZoneName(String availabilityZoneName) {
		AvailabilityZoneName = availabilityZoneName;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public String getProductWhat() {
		return ProductWhat;
	}

	public void setProductWhat(String productWhat) {
		ProductWhat = productWhat;
	}

	public KECImage getImage() {
		return image;
	}

	public void setImage(KECImage image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
