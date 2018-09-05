package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

public class OpsVersionAppDepHis {
	private Long depVerHisId;

    private Long appEnvId;
    
    private Long appId;
    
    private Long appVersionId;

    private Long configVersionId;

    private String versionCode;

    private Integer status;

    private String description;

    private Date createTime;

    private Long operateUserId;
    
    private Integer productionStatus;

    private String statusMessages;


	public Long getDepVerHisId() {
		return depVerHisId;
	}

	public void setDepVerHisId(Long depVerHisId) {
		this.depVerHisId = depVerHisId;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getAppVersionId() {
		return appVersionId;
	}

	public void setAppVersionId(Long appVersionId) {
		this.appVersionId = appVersionId;
	}

	public Long getConfigVersionId() {
		return configVersionId;
	}

	public void setConfigVersionId(Long configVersionId) {
		this.configVersionId = configVersionId;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	public Integer getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(Integer productionStatus) {
		this.productionStatus = productionStatus;
	}

	public String getStatusMessages() {
		return statusMessages;
	}

	public void setStatusMessages(String statusMessages) {
		this.statusMessages = statusMessages;
	}
}