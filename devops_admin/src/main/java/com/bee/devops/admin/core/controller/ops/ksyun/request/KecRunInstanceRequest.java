package com.bee.devops.admin.core.controller.ops.ksyun.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.component.sp.ksc.kec.request.RunInstancesRequest;
import com.bee.devops.admin.component.sp.model.kec.DataDisk;

public class KecRunInstanceRequest extends KecBaseRequest {

	private static final long serialVersionUID = -2803547990102412517L;
	/**
	 * 镜像ID
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 */
	@NotBlank(message = "{ksyun.kec.imageId.notnull}")
	String imageId;
	/**
	 * 计费类型，调用时需要明确指定，无默认值
	 * 有效值：Monthly(包年包月）、Daily（按日月结)、 PostPaidByHour（按小时月结）
	 */
	@NotBlank(message = "{ksyun.kec.chargeType.notnull}")
	@Pattern(regexp = "Monthly|Daily|PostPaidByHour|PostPaidByHour", message = "{ksyun.kec.chargeType.notexist}")
	String chargeType;
	/**
	 * 购买时长，单位月
	 * 有效值：当计费类型为Monthly（包年包月）时，有效值1-36；其他计费类型时，强制要求参数值为0
	 */
	Integer purchaseTime = 0;
	/**
	 * 实例绑定的安全组，目前仅支持绑定一个安全组
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 */
	@NotBlank(message = "{ksyun.kec.securityGroupId.notnull}")
	String securityGroupId;
	/**
	 * 最大实例数，如果指定的实例数大于金山云在本人Region所能创建的最大实例数，则会创建大于MinCount数量的最大允许实例数。
	 * 有效值: 最小值1，最大值为MIN(当前用户实例剩余配额，50），即用户剩余配额和50比较的小者
	 */
	Integer maxCount = 1;
	/**
	 * 最小实例数，如果指定的实例数大于金山云在本Region所能创建的最大实例数，则不会创建任何实例。
	 * 有效值: 最小值1，最大值为MIN(当前用户实例剩余配额，50），即用户剩余配额和50比较的小者
	 */
	Integer minCount = 1;
	/**
	 * VPC环境下的子网ID，绑定到主网卡
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 */
	@NotBlank(message = "{ksyun.kec.subnetId.notnull}")
	String subnetId;

	/**
	 * 实例套餐类型，如果调用时未指定实例套餐类型，默认值为I1.1A
	 * 有效值:
	 * 
	 * IO优化型I1：I1.1A | I1.1B| I1.1C| I1.2A| I1.2B| I1.2C| I1.4A| I1.4B| I1.4C| I1.8A| I1.8B| I1.8C| I1.16A| I1.16B| I1.16C| I1.24A| I1.24B| I1.24C| I1.24D|
	 * I1.32A| I1.32B；
	 * 计算优化型C1：C1.1A | C1.1B| C1.1C| C1.2A| C1.2B| C1.2C| C1.4A| C1.4B| C1.4C|C1.8A| C1.8B| C1.8C| C1.16A| C1.16B| C1.16C| C1.24A| C1.24B| C1.24C| C1.24D|
	 * C1.32A| C1.32B
	 * IO优化型I2：I2.1A| I2.1B | I2.1C | I2.2B | I2.2C | I2.4B | I2.4C | I2.8B | I2.8C | I2.16B | I2.16C | I2.24B | I2.24C | I2.32B | I2.32C
	 * 具体套餐信息参考 实例套餐类型定义
	 * 
	 */
	@NotBlank(message = "{ksyun.kec.instanceType.notnull}")
	String instanceType;
	/**
	 * 数据卷容量，单位GB，容量限制依据 实例套餐类型定义 变化，如果调用时未指定，则为相应实例套餐类型最小值。InstanceType为通用型主机时，此参数不生效。
	 */
	Integer dataDiskGb;
	/**
	 * 数据盘（云盘）的类型，数据盘n的类型，n 的取值范围为 [1, 3]。只支持I2、I2联网增强、N1和N2。DataDisk.n.Type与DataDisk.n.Size必须都填写才有效。
	 * 有效值： N1,I2：SSD2.0，SATA2.0/ N2：SSD3.0
	 * 
	 * 第 n 个数据盘（云盘）的容量大小，n 的取值范围为 [1, 3]。只支持I2、I2联网增强、N1和N2。DataDisk.n.Type与DataDisk.n.Size必须都填写才有效。
	 * 有效值： SATA2.0和SSD2.0：[10, 16000],步长为10/ SSD3.0：[10，64000]
	 */
	List<DataDisk> ebsDataDisks = new ArrayList<>(3); // type:size
	/**
	 * 实例开机密码
	 * 有效值：最短8字符，最长32字符，必须包含大小写英文字符和数字，支持其他可见字符
	 */
	@NotBlank(message = "{ksyun.kec.instancePassword.notnull}")
	String instancePassword;
	/**
	 * 保留镜像设置登录。该参数只对使用自定义镜像有效。当该值填写为true，默认InstancePassword参数无效。该参数与InstancePassword必须填写一个。
	 * 有效值：true | false
	 */
	Boolean keepImageLogin;
	/**
	 * 私有IP地址，指定子网IP地址范围内的任意有效值，代表实例的主IP地址，只能选择一个，绑定到主网卡；如果未指定该参数，系统自动从有效地址池中随机选取一个
	 * 有效值：标准IP地址格式
	 */
	String privateIpAddress;
	/**
	 * 实例名称，如果未指定，则自动生成，形如KSC-IN-[A-Z0-9]{10}
	 * 有效值：最短2字符，最长64字符，支持中英文
	 */
	String instanceName;
	/**
	 * 实例名称后缀，InstanceName参数如果缺省，此参数不生效；当大于1台的批量创建主机，后缀编号自动+1，例如后缀输入5，主机名输入"host"，批量3台，则生成的三台主机名分别为："host-5"、"host-6"、"host-7"；
	 * 有效值：0到9999
	 */
	String instanceNameSuffix;
	/**
	 * 该参数需要满足以下两个条件：
	 * IO优化型I1，计算优化型C1，IO优化型I2的8C以上套餐
	 * 使用的是金山云提高的标准镜像或者通过金山云标准镜像开机的实例再制作的自定义镜像
	 */
	Boolean sriovNetSupport;
	/**
	 * 实例所属项目ID
	 * 有效值：账户有权限的项目ID，0为默认项目
	 */
	Long projectId;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Integer purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getSecurityGroupId() {
		return securityGroupId;
	}

	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public Integer getDataDiskGb() {
		return dataDiskGb;
	}

	public void setDataDiskGb(Integer dataDiskGb) {
		this.dataDiskGb = dataDiskGb;
	}

	public List<DataDisk> getEbsDataDisks() {
		return ebsDataDisks;
	}

	public void setEbsDataDisks(List<DataDisk> ebsDataDisks) {
		this.ebsDataDisks = ebsDataDisks;
	}

	public String getInstancePassword() {
		return instancePassword;
	}

	public void setInstancePassword(String instancePassword) {
		this.instancePassword = instancePassword;
	}

	public Boolean getKeepImageLogin() {
		return keepImageLogin;
	}

	public void setKeepImageLogin(Boolean keepImageLogin) {
		this.keepImageLogin = keepImageLogin;
	}

	public String getPrivateIpAddress() {
		return privateIpAddress;
	}

	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getInstanceNameSuffix() {
		return instanceNameSuffix;
	}

	public void setInstanceNameSuffix(String instanceNameSuffix) {
		this.instanceNameSuffix = instanceNameSuffix;
	}

	public Boolean getSriovNetSupport() {
		return sriovNetSupport;
	}

	public void setSriovNetSupport(Boolean sriovNetSupport) {
		this.sriovNetSupport = sriovNetSupport;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public RunInstancesRequest transRequest() {
		return new RunInstancesRequest(this.imageId, this.chargeType, this.purchaseTime, this.securityGroupId, this.maxCount, this.minCount, this.subnetId,
		        this.instanceType, this.dataDiskGb, this.ebsDataDisks, this.instancePassword, this.keepImageLogin, this.privateIpAddress, this.instanceName,
		        this.instanceNameSuffix, this.sriovNetSupport, this.projectId);
	}

}
