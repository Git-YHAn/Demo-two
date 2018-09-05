package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 实例配置类型
 * 
 * @description InstanceConfigure
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class InstanceConfigure {
	/**
	 * 实例VCPU的个数
	 * 类型: Integer
	 * 是否可缺省: 否
	 */
	Integer VCPU;
	/**
	 * 实例内存的大小，单位GB
	 * 类型: Integer
	 * 是否可缺省: 否
	 */
	Integer memoryGb;
	/**
	 * 数据磁盘类型
	 * 类型: String
	 * 有效值: SSD | SATA
	 * 是否可缺省: 否
	 */
	String dataDiskType;
	/**
	 * 数据磁盘大小，单位GB
	 * 类型: Integer
	 * 是否可缺省: 否
	 */
	Integer dataDiskGb;
	/**
	 * 异构计算（GPU或FPGA）规格
	 * 类型: String
	 */
	String spec;

	/**
	 * 磁盘大小，单位GB
	 * 类型: Integer
	 * 是否可缺省: 否
	 */
	Integer rootDiskGb;
	/**
	 * 实例GPU的个数
	 * 类型: Integer
	 * 是否可缺省: 否
	 */
	Integer GPU;

	public Integer getRootDiskGb() {
		return rootDiskGb;
	}

	public void setRootDiskGb(Integer rootDiskGb) {
		this.rootDiskGb = rootDiskGb;
	}

	public Integer getGPU() {
		return GPU;
	}

	public void setGPU(Integer gPU) {
		GPU = gPU;
	}

	public Integer getVCPU() {
		return VCPU;
	}

	public void setVCPU(Integer vCPU) {
		VCPU = vCPU;
	}

	public Integer getMemoryGb() {
		return memoryGb;
	}

	public void setMemoryGb(Integer memoryGb) {
		this.memoryGb = memoryGb;
	}

	public String getDataDiskType() {
		return dataDiskType;
	}

	public void setDataDiskType(String dataDiskType) {
		this.dataDiskType = dataDiskType;
	}

	public Integer getDataDiskGb() {
		return dataDiskGb;
	}

	public void setDataDiskGb(Integer dataDiskGb) {
		this.dataDiskGb = dataDiskGb;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
