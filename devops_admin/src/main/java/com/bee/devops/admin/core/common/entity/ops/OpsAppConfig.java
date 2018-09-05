package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

/**
 * 应用审核
 */
public class OpsAppConfig {

    public final static Integer RECORD_STATUS_COMMIT = 1;// 已提交审核
    public final static Integer RECORD_STATUS_AUDIT_SUCCESS = 2;// 审核通过
    public final static Integer RECORD_STATUS_AUDIT_FAIL = -1;// 审核不通过

    private Long recordId;
    private Integer recordStatus;
    private Long appId;
    private Long proId;
    private String appName;
    private String proName;
    private String branchName;
    private Long editorId;
    private Date commitDate;
    private Long auditorId;
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

    public OpsAppConfig() {
    }

    public OpsAppConfig(Long appId, Long proId, String appName, String proName, String branchName, Long editorId, Date commitDate, String beginSha, String editorName) {
        this.appId = appId;
        this.proId = proId;
        this.appName = appName;
        this.proName = proName;
        this.branchName = branchName;
        this.editorId = editorId;
        this.editorName = editorName;
        this.commitDate = commitDate;
        this.beginSha = beginSha;
        this.endSha = beginSha;
        this.recordStatus = 0;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
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