package com.bee.devops.admin.component.sp.model.kec;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 套餐信息类型
 * 
 * @description InstanceTypeConfig
 * @author TurnerXi
 * @date 2018年8月28日
 */
public class InstanceTypeConfig {
	/**
	 * 可用区
	 * 类型：List
	 * 是否可缺省：是
	 */
	List<AvailabilityZoneInfo> availabilityZoneSet;

	/**
	 * 实例套餐类型
	 * 类型：String
	 * 是否可缺省：是
	 */
	String instanceType;

	/**
	 * 实例类型
	 * 类型：String
	 * 是否可缺省：是
	 */
	String instanceFamily;

	/**
	 * 是否支持联网增强
	 * 类型：Boolean
	 * 是否可缺省：是
	 */
	Boolean sriovNetSupport;

	/**
	 * GPU颗数
	 * 类型：Integer
	 * 是否可缺省：是
	 */
	Integer gpu;

	/**
	 * CPU核数
	 * 类型：Integer
	 * 是否可缺省：是
	 */
	Integer cpu;

	/**
	 * 内存容量
	 * 类型：Integer
	 * 是否可缺省:是
	 */
	Integer memory;

	/**
	 * 该套餐数据盘支持的最大容量
	 * 类型：Integer
	 * 是否可缺省：是
	 */
	Integer dataDiskMax;

	/**
	 * 该套餐数据盘支持的最小容量
	 * 类型：Integer
	 * 是否可缺省：是
	 */
	Integer dataDiskMin;

	public List<AvailabilityZoneInfo> getAvailabilityZoneSet() {
		return availabilityZoneSet;
	}

	public void setAvailabilityZoneSet(List<AvailabilityZoneInfo> availabilityZoneSet) {
		this.availabilityZoneSet = availabilityZoneSet;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getInstanceFamily() {
		return instanceFamily;
	}

	public void setInstanceFamily(String instanceFamily) {
		this.instanceFamily = instanceFamily;
	}

	public Boolean getSriovNetSupport() {
		return sriovNetSupport;
	}

	public void setSriovNetSupport(Boolean sriovNetSupport) {
		this.sriovNetSupport = sriovNetSupport;
	}

	public Integer getGpu() {
		return gpu;
	}

	public void setGpu(Integer gpu) {
		this.gpu = gpu;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getDataDiskMax() {
		return dataDiskMax;
	}

	public void setDataDiskMax(Integer dataDiskMax) {
		this.dataDiskMax = dataDiskMax;
	}

	public Integer getDataDiskMin() {
		return dataDiskMin;
	}

	public void setDataDiskMin(Integer dataDiskMin) {
		this.dataDiskMin = dataDiskMin;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
