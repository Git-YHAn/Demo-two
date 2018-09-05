package com.bee.devops.admin.core.vo.response;

import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.OpsVersionApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionConfig;

public class PublishVersionResponse {
	private Long proId; // 项目ID
	private String proName; // 项目名称
	private Long envId;// 环境ID
	private String envName;// 环境名称
	private Long appId;// 应用ID
	private String appName;// 应用名称
	
	private Long appVerId;//应用版本ID
	private String appVersionCode;//应用版本号
	private Long configVerId;//配置版本ID
	private String configVersionCode;//配置版本号
	private String publishVersionCode;//最新的发布版本
	
	private List<OpsVersionApp> opsVersionApps;//应用版本
	private List<OpsVersionConfig> opsVersionConfigs;//配置版本

	/**
	 *  新增以下几种属性,用于版本比对与组成该发布版本的应用版本、配置版本的版本信息
	 */
	private String deployGitUrl; // 发布版本git路径

	private LatestPublishVersionVo latestVo; // 最新发布版本vo

	private Long appTypeId;

	public LatestPublishVersionVo getLatestVo() {
		return latestVo;
	}

	public void setLatestVo(LatestPublishVersionVo latestVo) {
		this.latestVo = latestVo;
	}

	public String getDeployGitUrl() {
		return deployGitUrl;
	}

	public void setDeployGitUrl(String deployGitUrl) {
		this.deployGitUrl = deployGitUrl;
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
	public Long getAppVerId() {
		return appVerId;
	}
	public void setAppVerId(Long appVerId) {
		this.appVerId = appVerId;
	}
	public String getAppVersionCode() {
		return appVersionCode;
	}
	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}
	public Long getConfigVerId() {
		return configVerId;
	}
	public void setConfigVerId(Long configVerId) {
		this.configVerId = configVerId;
	}
	public String getConfigVersionCode() {
		return configVersionCode;
	}
	public void setConfigVersionCode(String configVersionCode) {
		this.configVersionCode = configVersionCode;
	}
	public String getPublishVersionCode() {
		return publishVersionCode;
	}
	public void setPublishVersionCode(String publishVersionCode) {
		this.publishVersionCode = publishVersionCode;
	}
	public List<OpsVersionApp> getApplicationVersions() {
		return opsVersionApps;
	}
	public void setApplicationVersions(List<OpsVersionApp> opsVersionApps) {
		this.opsVersionApps = opsVersionApps;
	}
	public List<OpsVersionConfig> getConfigVersions() {
		return opsVersionConfigs;
	}
	public void setConfigVersions(List<OpsVersionConfig> opsVersionConfigs) {
		this.opsVersionConfigs = opsVersionConfigs;
	}

	public Long getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Long appTypeId) {
		this.appTypeId = appTypeId;
	}
}
