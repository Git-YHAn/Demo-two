package com.bee.devops.admin.core.common.entity.ops;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OpsDepApp implements Serializable {

	private static final long serialVersionUID = -4984598924914723032L;

	public static final Integer PUBLISH_STATUS_DEFAULT = 0;// 默认状态
	public static final Integer PUBLISH_STATUS_DEPLOY_ING = 100; // 应用部署中
	public static final Integer PUBLISH_STATUS_RESTART_ING = 200; // 应用重启中
	public static final Integer PUBLISH_STATUS_FINISH = 300; // 发布完毕
	public static final Integer PUBLISH_STATUS_DEPLOY_FAIL = -100;// 应用部署失败
	public static final Integer PUBLISH_STATUS_RESTART_FAIL = -200;// 应用重启失败
	public static final Integer PUBLISH_STATUS_PUBLISH_STOP = -300;// 取消发布
	
	private Long publishId; // 应用发布ID
	private Long appEnvId; // 应用环境ID
	private Long serverId; // 服务器ID
	private Long deployVersionId;// 发布版本ID
	private Integer publishStatus;// 发布状态
	private Integer deployType;// 发布方式
	private Integer autoRestart;// 是否自动重启
	private Date publishDate;// 发布时间
	
	private String deployVersionCode;// 发布版本编码
	private Long proId; // 项目ID
	private String proName; // 项目名称
	private Long envId;// 环境ID
	private String envName;// 环境名称
	private Long appId;// 应用ID
	private String appName;// 应用名称
	private String serverName; // 服务器名称
	private List<OpsBaseServer> assetsHosts;// 服务器
	private List<OpsVersionAppDep> opsVersionAppDeps;// 所有发布版本

	private String description;
	private String publishInfo; //发布信息
	private String appVersionInfo;

	private String configVersionInfo;

	/**
	 * 返回发布版本的上一版本和git地址,用于做版本比对
	 */
	private String tagUrl;
	private String deployAppGitUrl;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppVersionInfo() {
		return appVersionInfo;
	}

	public void setAppVersionInfo(String appVersionInfo) {
		this.appVersionInfo = appVersionInfo;
	}

	public String getConfigVersionInfo() {
		return configVersionInfo;
	}

	public void setConfigVersionInfo(String configVersionInfo) {
		this.configVersionInfo = configVersionInfo;
	}

	public String getTagUrl() {
		return tagUrl;
	}

	public void setTagUrl(String tagUrl) {
		this.tagUrl = tagUrl;
	}

	public String getDeployAppGitUrl() {
		return deployAppGitUrl;
	}

	public void setDeployAppGitUrl(String deployAppGitUrl) {
		this.deployAppGitUrl = deployAppGitUrl;
	}

	private Long operateUserId;//操作用户ID

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getDeployVersionId() {
		return deployVersionId;
	}

	public void setDeployVersionId(Long deployVersionId) {
		this.deployVersionId = deployVersionId;
	}

	public String getDeployVersionCode() {
		return deployVersionCode;
	}

	public void setDeployVersionCode(String deployVersionCode) {
		this.deployVersionCode = deployVersionCode;
	}

	public Integer getDeployType() {
		return deployType;
	}

	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getAutoRestart() {
		return autoRestart;
	}

	public void setAutoRestart(Integer autoRestart) {
		this.autoRestart = autoRestart;
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

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<OpsBaseServer> getAssetsHosts() {
		return assetsHosts;
	}

	public void setAssetsHosts(List<OpsBaseServer> assetsHosts) {
		this.assetsHosts = assetsHosts;
	}

	public List<OpsVersionAppDep> getDeployAppVersions() {
		return opsVersionAppDeps;
	}

	public void setDeployAppVersions(List<OpsVersionAppDep> opsVersionAppDeps) {
		this.opsVersionAppDeps = opsVersionAppDeps;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}
}
