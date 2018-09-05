package com.bee.devops.admin.core.controller.ops.assemble.response;

import java.util.List;

public class AppLogsResponse {
	private String resourceName;
	private String logPath;
	private int type;
	private String lastModified;
	private long size;
	private List<AppLogsResponse> children;

	public AppLogsResponse() {
		super();
	}

	public AppLogsResponse(String resourceName, String logPath, int type, String lastModified, long size, List<AppLogsResponse> children) {
		super();
		this.resourceName = resourceName;
		this.logPath = logPath;
		this.type = type;
		this.lastModified = lastModified;
		this.size = size;
		this.children = children;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<AppLogsResponse> getChildren() {
		return children;
	}

	public void setChildren(List<AppLogsResponse> children) {
		this.children = children;
	}

}
