package com.bee.devops.admin.core.vo.response;


/**
 * 面板数据的返回类
 * @author Administrator
 *
 */
public class DashboardResponse {
	private DeployAppVo appList;
	private ServerStatusVo serverList;
	public DeployAppVo getAppList() {
		return appList;
	}
	public void setAppList(DeployAppVo appList) {
		this.appList = appList;
	}
	public ServerStatusVo getServerList() {
		return serverList;
	}
	public void setServerList(ServerStatusVo serverList) {
		this.serverList = serverList;
	}
	
}
