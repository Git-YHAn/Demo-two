package com.bee.devops.admin.core.vo.request;

public class GitlabMessageRequest {
	private String object_kind;
	private String event_name;
	private String message;
	private String user_name;
	private String user_email;
	private Integer total_commits_count;
	private GitlabProjectRequest project;
	private GitlabCommitsRequest[] commits;
	private GitlabRepositoryRequest repository;
	public String getObject_kind() {
		return object_kind;
	}
	public void setObject_kind(String object_kind) {
		this.object_kind = object_kind;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Integer getTotal_commits_count() {
		return total_commits_count;
	}
	public void setTotal_commits_count(Integer total_commits_count) {
		this.total_commits_count = total_commits_count;
	}
	public GitlabProjectRequest getProject() {
		return project;
	}
	public void setProject(GitlabProjectRequest project) {
		this.project = project;
	}
	public GitlabCommitsRequest[] getCommits() {
		return commits;
	}
	public void setCommits(GitlabCommitsRequest[] commits) {
		this.commits = commits;
	}
	public GitlabRepositoryRequest getRepository() {
		return repository;
	}
	public void setRepository(GitlabRepositoryRequest repository) {
		this.repository = repository;
	}
	
}
