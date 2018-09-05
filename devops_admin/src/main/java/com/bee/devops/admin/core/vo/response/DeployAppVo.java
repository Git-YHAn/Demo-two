package com.bee.devops.admin.core.vo.response;

/**
 * 发布应用包装类
 * @author Administrator
 *
 */
public class DeployAppVo {
	private String appName; //应用名
	private int runSuccess; //发布成功数
	private int runFaile; //发布失败数
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getRunSuccess() {
		return runSuccess;
	}
	public void setRunSuccess(int runSuccess) {
		this.runSuccess = runSuccess;
	}
	public int getRunFaile() {
		return runFaile;
	}
	public void setRunFaile(int runFaile) {
		this.runFaile = runFaile;
	}
	
}
