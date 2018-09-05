package com.bee.devops.admin.component.sp.model.kec;

public class DataDisk {
	String type;
	Integer size;
	Boolean deleteWithInstance;

	public DataDisk() {
		super();
	}

	public DataDisk(String type, Integer size, Boolean deleteWithInstance) {
		super();
		this.type = type;
		this.size = size;
		this.deleteWithInstance = deleteWithInstance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Boolean getDeleteWithInstance() {
		return deleteWithInstance;
	}

	public void setDeleteWithInstance(Boolean deleteWithInstance) {
		this.deleteWithInstance = deleteWithInstance;
	}

}