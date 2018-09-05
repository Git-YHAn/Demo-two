package com.bee.devops.admin.core.vo.response;

import java.util.Date;

public class HostAppEnvVo {
	private Long hostAppEnvId;
	private Long hostId;
	private Long appEnvId;
	private String versionCode;
	private Date updateTime;
	private Long operateUserId;
	public Long getHostAppEnvId() {
		return hostAppEnvId;
	}
	public void setHostAppEnvId(Long hostAppEnvId) {
		this.hostAppEnvId = hostAppEnvId;
	}
	
	public Long getHostId() {
		return hostId;
	}
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	public Long getAppEnvId() {
		return appEnvId;
	}
	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}
}
