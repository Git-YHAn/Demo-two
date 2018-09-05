package com.bee.devops.admin.core.vo.response;
/**
 * 服务器配置保存接口的参数包装类
 * @author Administrator
 *
 */
public class SaveAssetsHostParamVo {
	private Long envId;
	private Long appId;
	private Long[] hostId;
	private Long appEnvId;
	public Long getAppEnvId() {
		return appEnvId;
	}
	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}
	public Long getEnvId() {
		return envId;
	}
	public void setEnvId(Long envId) {
		this.envId = envId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long[] getHostId() {
		return hostId;
	}
	public void setHostId(Long[] hostId) {
		this.hostId = hostId;
	}
}
