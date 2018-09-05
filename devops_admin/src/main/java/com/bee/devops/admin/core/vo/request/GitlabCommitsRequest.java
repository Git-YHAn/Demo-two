package com.bee.devops.admin.core.vo.request;

public class GitlabCommitsRequest {
	private Long id;
	private String message;
	private String timestamp;
	private String[] modified;
	private String[] added;
	private String[] removed;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String[] getModified() {
		return modified;
	}
	public void setModified(String[] modified) {
		this.modified = modified;
	}
	public String[] getAdded() {
		return added;
	}
	public void setAdded(String[] added) {
		this.added = added;
	}
	public String[] getRemoved() {
		return removed;
	}
	public void setRemoved(String[] removed) {
		this.removed = removed;
	}
	
}
