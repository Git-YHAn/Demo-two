package com.bee.devops.admin.component.sp.model.epc;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 镜像类型
 * 
 * @description Image
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class EPCImage {
	/**
	 * 创建时间
	 * 类型:String
	 */
	String createTime;

	/**
	 * 镜像ID
	 * 类型：String
	 */
	String imageId;

	/**
	 * 镜像名称
	 * 类型：String
	 */
	String imageName;

	/**
	 * 镜像类别，枚举为：Base,标准镜像；默认为标准镜像
	 * 类型：String
	 * 有效值：base|private
	 */
	String imageType;

	/**
	 * 操作系统名称
	 * 类型：String
	 */
	String osName;

	/**
	 * 操作系统类型
	 * 类型：String
	 */
	String osType;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
