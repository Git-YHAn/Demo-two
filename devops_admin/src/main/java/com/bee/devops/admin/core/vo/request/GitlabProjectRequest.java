package com.bee.devops.admin.core.vo.request;

public class GitlabProjectRequest {
	private String name;
	private String description;
	private String web_url;
	private String git_ssh_url;
	private String git_http_url;
	private String namespace;
	private String default_branch;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getGit_ssh_url() {
		return git_ssh_url;
	}
	public void setGit_ssh_url(String git_ssh_url) {
		this.git_ssh_url = git_ssh_url;
	}
	public String getGit_http_url() {
		return git_http_url;
	}
	public void setGit_http_url(String git_http_url) {
		this.git_http_url = git_http_url;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getDefault_branch() {
		return default_branch;
	}
	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}
	
}
