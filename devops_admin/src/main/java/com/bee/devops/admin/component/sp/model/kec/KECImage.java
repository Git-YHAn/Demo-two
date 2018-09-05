package com.bee.devops.admin.component.sp.model.kec;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 镜像类型
 * 
 * @description Image
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class KECImage {

	/**
	 * 镜像名称
	 * 类型: String
	 */
	String name;
	/**
	 * 镜像ID
	 * 类型: String
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 */
	String imageId;
	/**
	 * 创建时间
	 * 类型: String
	 * 有效值：按照ISO8601标准，使用UTC时间，格式为"YYYY-MM-DDThh:mm:ssZ"例如，2014-05-26T12:00:00Z
	 */
	String creationDate;
	/**
	 * 是否为公共镜像
	 * 类型: String
	 * 有效值: true（公共镜像）| false（私有镜像）
	 */
	String isPublic;
	/**
	 * 镜像操作系统平台
	 * 类型: String
	 */
	String platform;
	/**
	 * 镜像状态
	 * 类型: String
	 * 有效值: queued | saving | active | deactived | killed | deleted | pending_delete
	 */
	String imageState;
	/**
	 * 关联实例ID
	 * 类型: String
	 */
	String instanceId;

	private Boolean isNpe;
	private String userCategory;
	private String sysDisk;
	private String progress;
	private String imageSource;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getImageState() {
		return imageState;
	}

	public void setImageState(String imageState) {
		this.imageState = imageState;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public Boolean getIsNpe() {
		return isNpe;
	}

	public String getUserCategory() {
		return userCategory;
	}

	public String getSysDisk() {
		return sysDisk;
	}

	public String getProgress() {
		return progress;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setIsNpe(Boolean isNpe) {
		this.isNpe = isNpe;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public void setSysDisk(String sysDisk) {
		this.sysDisk = sysDisk;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
