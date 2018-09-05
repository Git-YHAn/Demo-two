package com.bee.devops.admin.core.common.entity.ops;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OpsBaseServerExecHis {
    private Long execHisId;

    private String execContent;

    private String execResult;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date execTime;

    private Long execTplId;

    private Long operateUserId;

    private Long assetsId;

    public Long getExecHisId() {
        return execHisId;
    }

    public void setExecHisId(Long execHisId) {
        this.execHisId = execHisId;
    }

    public String getExecContent() {
        return execContent;
    }

    public void setExecContent(String execContent) {
        this.execContent = execContent;
    }

    public Long getExecTplId() {
        return execTplId;
    }

    public void setExecTplId(Long execTplId) {
        this.execTplId = execTplId;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Long getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Long assetsId) {
        this.assetsId = assetsId;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }
}