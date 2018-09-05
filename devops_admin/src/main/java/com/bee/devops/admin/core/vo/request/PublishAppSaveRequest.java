package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;

public class PublishAppSaveRequest implements Serializable {
	private static final long serialVersionUID = -5760291373791376016L;
	private Long appEnvId; // 应用环境ID
	private Long proId;// 项目ID
	private Long envId;// 环境ID
	private Long deployVersionId;// 发布版本ID
	private List<OpsBaseServer> assetsHosts;// 服务器
	private Integer autoRestart;// 是否自动重启
	private String publishInfo;// 发布信息

	public OpsDepApp transEntity() {
		OpsDepApp opsDepApp = new OpsDepApp();
		opsDepApp.setAppEnvId(appEnvId);
		opsDepApp.setDeployVersionId(deployVersionId);
		opsDepApp.setAutoRestart(autoRestart);
		opsDepApp.setPublishInfo(publishInfo);
		return opsDepApp;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	public Long getDeployVersionId() {
		return deployVersionId;
	}

	public void setDeployVersionId(Long deployVersionId) {
		this.deployVersionId = deployVersionId;
	}

	public List<OpsBaseServer> getAssetsHosts() {
		return assetsHosts;
	}

	public void setAssetsHosts(List<OpsBaseServer> assetsHosts) {
		this.assetsHosts = assetsHosts;
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

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}

}
