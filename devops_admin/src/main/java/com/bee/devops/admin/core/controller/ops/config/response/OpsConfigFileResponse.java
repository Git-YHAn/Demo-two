package com.bee.devops.admin.core.controller.ops.config.response;

public class OpsConfigFileResponse {
	private String configFileName;
	private String fileUrl;
	private String fileType;

	public OpsConfigFileResponse(String configFileName, String fileUrl, String fileType) {
		super();
		this.configFileName = configFileName;
		this.fileUrl = fileUrl;
		this.fileType = fileType;
	}

	public OpsConfigFileResponse() {
		super();
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName == null ? null : configFileName.trim();
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

}