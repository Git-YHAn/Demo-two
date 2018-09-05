package com.bee.devops.admin.core.vo.request;

public class GitlabRepositoryRequest {
	private String name;
	private String url;
	private String description;
	private String git_http_url;
	private String git_ssh_url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGit_http_url() {
		return git_http_url;
	}
	public void setGit_http_url(String git_http_url) {
		this.git_http_url = git_http_url;
	}
	public String getGit_ssh_url() {
		return git_ssh_url;
	}
	public void setGit_ssh_url(String git_ssh_url) {
		this.git_ssh_url = git_ssh_url;
	}
	
}
