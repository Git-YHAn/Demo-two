package com.bee.devops.admin.core.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DeployAssetsExecHisVo {
    private String sshAddress;

    private String operateUserName;

    private String execTplName;

    private String execContent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date execTime;

    private String execResult;

    public String getSshAddress() {
        return sshAddress;
    }

    public void setSshAddress(String sshAddress) {
        this.sshAddress = sshAddress;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getExecTplName() {
        return execTplName;
    }

    public void setExecTplName(String execTplName) {
        this.execTplName = execTplName;
    }

    public String getExecContent() {
        return execContent;
    }

    public void setExecContent(String execContent) {
        this.execContent = execContent;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }
}
