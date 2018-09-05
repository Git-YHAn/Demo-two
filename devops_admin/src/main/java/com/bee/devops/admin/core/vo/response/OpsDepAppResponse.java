package com.bee.devops.admin.core.vo.response;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDep;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *
 * @author Roc created on 2018/8/14
 */
public class OpsDepAppResponse implements Serializable {
	private static final long serialVersionUID = -4984598924914723032L;

	private Long appEnvId;
	private Long appId;// 应用ID
	private String appName;// 应用名称
	private List<OpsBaseServer> servers;// 服务器
	private List<OpsVersionAppDep> versions;// 所有发布版本
	
	
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
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public List<OpsBaseServer> getServers() {
		return servers;
	}
	public void setServers(List<OpsBaseServer> servers) {
		this.servers = servers;
	}
	public List<OpsVersionAppDep> getVersions() {
		return versions;
	}
	public void setVersions(List<OpsVersionAppDep> versions) {
		this.versions = versions;
	}

	
}
