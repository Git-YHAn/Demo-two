package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsVersionAppSync {
	private Long syncId;
	
	private Long oldEnvId;

	private String oldEnvName;
	
	private Long syncEnvId;
	
	private String syncEnvName;
	
	private Long syncProId;
	
	private String syncProName;
	
	private Long syncAppId;
	
	private String syncAppName;
	
	private String syncVersionCode;
	
	private Long operateUserId;
	
	private String operateUserName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date syncTime;
	
	private String syncFolderName;

	public OpsVersionAppSync(){}
	
	public OpsVersionAppSync(Long oldEnvId,Long syncEnvId,Long syncAppId,String syncVersionCode, Long operateUserId, Date syncTime,String syncFolderName) {
		this.oldEnvId = oldEnvId;
		this.syncEnvId = syncEnvId;
		this.syncAppId = syncAppId;
		this.syncVersionCode = syncVersionCode;
		this.operateUserId = operateUserId;
		this.syncTime = syncTime;
		this.syncFolderName = syncFolderName;
	}

	public Long getSyncId() {
		return syncId;
	}

	public void setSyncId(Long syncId) {
		this.syncId = syncId;
	}

	public Long getOldEnvId() {
		return oldEnvId;
	}

	public void setOldEnvId(Long oldEnvId) {
		this.oldEnvId = oldEnvId;
	}

	public String getOldEnvName() {
		return oldEnvName;
	}

	public void setOldEnvName(String oldEnvName) {
		this.oldEnvName = oldEnvName;
	}

	public Long getSyncEnvId() {
		return syncEnvId;
	}

	public void setSyncEnvId(Long syncEnvId) {
		this.syncEnvId = syncEnvId;
	}

	public String getSyncEnvName() {
		return syncEnvName;
	}

	public void setSyncEnvName(String syncEnvName) {
		this.syncEnvName = syncEnvName;
	}

	public Long getSyncProId() {
		return syncProId;
	}

	public void setSyncProId(Long syncProId) {
		this.syncProId = syncProId;
	}

	public String getSyncProName() {
		return syncProName;
	}

	public void setSyncProName(String syncProName) {
		this.syncProName = syncProName;
	}

	public Long getSyncAppId() {
		return syncAppId;
	}

	public void setSyncAppId(Long syncAppId) {
		this.syncAppId = syncAppId;
	}

	public String getSyncAppName() {
		return syncAppName;
	}

	public void setSyncAppName(String syncAppName) {
		this.syncAppName = syncAppName;
	}

	public String getSyncVersionCode() {
		return syncVersionCode;
	}

	public void setSyncVersionCode(String syncVersionCode) {
		this.syncVersionCode = syncVersionCode;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getSyncFolderName() {
		return syncFolderName;
	}

	public void setSyncFolderName(String syncFolderName) {
		this.syncFolderName = syncFolderName;
	}
}
