package com.bee.devops.admin.core.vo.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.OpsConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AllConfigRecordsResponse {
	private Long recordId;
	private Long appEnvId;
	private Long editorId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date commitDate;
	private Long auditorId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date auditDate;
	private Integer recordStatus;
	private String editorName;
	private String auditorName;

	private String appName;
	private String proName;
	private String envName;

	public AllConfigRecordsResponse() {
		super();
	}

	public static List<AllConfigRecordsResponse> transList(List<OpsConfig> list) {
		List<AllConfigRecordsResponse> result = new ArrayList<>();
		for (OpsConfig record : list) {
			result.add(new AllConfigRecordsResponse(record));
		}
		return result;
	}

	public AllConfigRecordsResponse(OpsConfig record) {
		super();
		this.recordId = record.getRecordId();
		this.appEnvId = record.getAppEnvId();
		this.editorId = record.getEditorId();
		this.commitDate = record.getCommitDate();
		this.auditorId = record.getAuditorId();
		this.auditDate = record.getAuditDate();
		this.recordStatus = record.getRecordStatus();
		this.editorName = record.getEditorName();
		this.auditorName = record.getAuditorName();
		this.appName = record.getAppName();
		this.proName = record.getProName();
		this.envName = record.getEnvName();
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
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

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
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

}
