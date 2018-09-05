package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

public class OpsConfig {
	
	public final static Integer RECORD_STATUS_DEFAULT = 0;// 默认
	public final static Integer RECORD_STATUS_COMMIT = 1;// 已提交审核
	public final static Integer RECORD_STATUS_AUDIT_SUCCESS = 2;// 审核成功
	public final static Integer RECORD_STATUS_AUDIT_FAIL = -1;// 审核失败
	
	private Long recordId;
	private Long appEnvId;
	private String branchName;
	private Long editorId;
	private Date commitDate;
	private Long auditorId;
	private Date auditDate;
	private String beginSha;
	private String endSha;
	private Integer recordStatus;
	private String editorName;
	private String auditorName;
	private String commitMessage;
	
	private String appName;
	private String proName;
	private String envName;
	
	public OpsConfig() {
		super();
	}

	public OpsConfig(Long appEnvId, String branchName, Long editorId, Date commitDate, String beginSha, String editorName) {
		super();
		this.appEnvId = appEnvId;
		this.branchName = branchName;
		this.editorId = editorId;
		this.editorName = editorName;
		this.commitDate = commitDate;
		this.beginSha = beginSha;
		this.endSha = beginSha;
		this.recordStatus = 0;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Long getEditorId() {
		return editorId;
	}

	public void setEditorId(Long editorId) {
		this.editorId = editorId;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getBeginSha() {
		return beginSha;
	}

	public void setBeginSha(String beginSha) {
		this.beginSha = beginSha;
	}

	public String getEndSha() {
		return endSha;
	}

	public void setEndSha(String endSha) {
		this.endSha = endSha;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
	
}