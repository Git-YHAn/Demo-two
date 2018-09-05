package com.bee.devops.admin.core.vo.response;

public class ConfigRecordDiffsResponse {
	private String diffType;
	private String diffFile;
	private String diffContent;

	public ConfigRecordDiffsResponse(String diffType, String diffFile, String diffContent) {
		super();
		this.diffType = diffType;
		this.diffFile = diffFile;
		this.diffContent = diffContent;
	}

	public ConfigRecordDiffsResponse() {
		super();
	}

	public String getDiffType() {
		return diffType;
	}

	public void setDiffType(String diffType) {
		this.diffType = diffType;
	}

	public String getDiffFile() {
		return diffFile;
	}

	public void setDiffFile(String diffFile) {
		this.diffFile = diffFile;
	}

	public String getDiffContent() {
		return diffContent;
	}

	public void setDiffContent(String diffContent) {
		this.diffContent = diffContent;
	}

}
