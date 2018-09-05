package com.bee.devops.admin.core.common.entity.ops;

public class OpsBaseServerType {
	private Integer assetsType;
	private String assetsTypeName;
	
	public OpsBaseServerType(){}

	public OpsBaseServerType(Integer assetsType, String assetsTypeName) {
		super();
		this.assetsType = assetsType;
		this.assetsTypeName = assetsTypeName;
	}

	public Integer getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(Integer assetsType) {
		this.assetsType = assetsType;
	}

	public String getAssetsTypeName() {
		return assetsTypeName;
	}

	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
	}

	@Override
	public String toString() {
		return "OpsBaseServerType [assetsType=" + assetsType + ", assetsTypeName=" + assetsTypeName + "]";
	}
}
