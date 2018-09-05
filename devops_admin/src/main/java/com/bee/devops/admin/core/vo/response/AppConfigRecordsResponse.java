package com.bee.devops.admin.core.vo.response;

import com.bee.devops.admin.core.common.entity.ops.OpsAppConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppConfigRecordsResponse {
    private Long recordId;
    private Integer recordStatus;
    private Long appId;
    private Long proId;
    private String appName;
    private String proName;
    private String branchName;
    private Long editorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commitDate;
    private Long auditorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditDate;
    private String beginSha;
    private String endSha;
    private String editorName;
    private String auditorName;
    private String commitMessage;
    /**
     * 审核状态,只有0和1两种状态,1表示 进行了审核操作
     */
    private int auditStatus;

    public AppConfigRecordsResponse() {
        super();
    }

    public static List<AppConfigRecordsResponse> transList(List<OpsAppConfig> list) {
        List<AppConfigRecordsResponse> result = new ArrayList<>();
        for (OpsAppConfig record : list) {
            result.add(new AppConfigRecordsResponse(record));
        }
        return result;
    }

    public AppConfigRecordsResponse(OpsAppConfig record) {
        super();
        this.recordId = record.getRecordId();
        this.recordStatus = record.getRecordStatus();
        this.appId = record.getAppId();
        this.proId = record.getProId();
        this.appName = record.getAppName();
        this.proName = record.getProName();
        this.branchName = record.getBranchName();
        this.editorId = record.getEditorId();
        this.commitDate = record.getCommitDate();
        this.auditorId = record.getAuditorId();
        this.auditDate = record.getAuditDate();
        this.beginSha = record.getBeginSha();
        this.endSha = record.getEndSha();
        this.editorName = record.getEditorName();
        this.auditorName = record.getAuditorName();
        this.commitMessage = record.getCommitMessage();
        this.auditStatus = record.getAuditStatus();
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
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
