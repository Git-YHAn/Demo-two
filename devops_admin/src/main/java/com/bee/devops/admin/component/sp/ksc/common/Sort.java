package com.bee.devops.admin.component.sp.ksc.common;

import java.io.Serializable;

public class Sort implements Serializable, Cloneable {

	private static final long serialVersionUID = 3073197893388762070L;

	/**
	 * 筛选名称，大小写敏感
	 * 类型: String
	 */
	private String name;

	/**
	 * 筛选值
	 * 有效值：ACS(正序排列) | DESC(倒序排列)
	 */
	private String value;

	public Sort(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Sort() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}