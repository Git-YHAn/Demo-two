package com.bee.devops.admin.core.vo.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsBaseAppSearchResponse {
	private Long appId;

	private Long proId;

	private String proName;

	private String appName;

	private String appCode;

	private Integer appType;

	private String currentVersion;

	private String description;

	private String logPath;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date updateTime;
	
	private Long appTypeId;
	
	private String appTypeName;

	public static List<OpsBaseAppSearchResponse> transList(List<OpsBaseApp> list) {
		List<OpsBaseAppSearchResponse> result = new ArrayList<>();
		for (OpsBaseApp application : list) {
			result.add(new OpsBaseAppSearchResponse(application));
		}
		return result;
	}

	public OpsBaseAppSearchResponse(OpsBaseApp application) {
		super();
		this.appId = application.getAppId();
		this.proId = application.getProId();
		this.proName = application.getProName();
		this.appName = application.getAppName();
		this.appCode = application.getAppCode();
		this.appType = application.getAppType();
		this.currentVersion = application.getCurrentVersion();
		this.description = application.getDescription();
		this.logPath = application.getLogPath();
		this.createTime = application.getCreateTime();
		this.updateTime = application.getUpdateTime();
		this.appTypeId = application.getAppTypeId();
		this.appTypeName = application.getAppTypeName();
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

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Long appTypeId) {
		this.appTypeId = appTypeId;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
}
