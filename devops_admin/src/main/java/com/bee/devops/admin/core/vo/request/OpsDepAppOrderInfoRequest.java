package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;
import java.util.Date;

import com.bee.devops.admin.common.enums.DeployStatusEnums;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrder;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrderInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsDepAppOrderInfoRequest implements Serializable {
	
	private static final long serialVersionUID = -5760291373791376017L;
    private Long appEnvId;

    private String appName;

    private Long serverId;

    private String serverIp;

    private String serverName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createDate = new Date();
    
    private Long depAppVerId;

	private String versionCode;

    private Integer deployStatus = DeployStatusEnums.DEPLOY_NOT_START.getValue();
    
    private Integer deployType;

	private StringBuilder deployLog;

    public OpsDepAppOrderInfo transEntity() {
    	OpsDepAppOrderInfo opsdepapporderinfo = new OpsDepAppOrderInfo();
    	opsdepapporderinfo.setAppEnvId(appEnvId);
    	opsdepapporderinfo.setAppName(appName);
    	opsdepapporderinfo.setCreateDate(createDate);
    	opsdepapporderinfo.setDeployStatus(deployStatus);
    	opsdepapporderinfo.setDeployType(deployType);
    	opsdepapporderinfo.setDepAppVerId(depAppVerId);
    	opsdepapporderinfo.setServerId(serverId);
    	opsdepapporderinfo.setServerIp(serverIp);
    	opsdepapporderinfo.setServerName(serverName);
        return opsdepapporderinfo;
    }
    
	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Integer getDeployStatus() {
		return deployStatus;
	}

	public void setDeployStatus(Integer deployStatus) {
		this.deployStatus = deployStatus;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getDepAppVerId() {
		return depAppVerId;
	}

	public void setDepAppVerId(Long depAppVerId) {
		this.depAppVerId = depAppVerId;
	}


	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public StringBuilder getDeployLog() {
		return deployLog;
	}

	public void setDeployLog(StringBuilder deployLog) {
		this.deployLog = deployLog;
	}
}
