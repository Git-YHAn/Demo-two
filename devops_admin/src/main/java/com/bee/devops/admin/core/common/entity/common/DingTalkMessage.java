package com.bee.devops.admin.core.common.entity.common;

public class DingTalkMessage {
	
	public static final String DEP_SUCCESS = "DEP_SUCCESS";// 应用发布完毕
	public static final String DEP_ING = "DEP_ING"; // 应用部署中
	public static final String DEP_FAIL = "DEP_FAIL"; // 应用部署失败
	public static final String RE_SUCCESS = "RE_SUCCESS"; // 应用重启完毕
	public static final String RE_ING = "RE_ING";// 应用重启中
	public static final String RE_FAIL = "RE_FAIL";// 应用重启失败
	public static final String VER_SUCCESS = "VER_SUCCESS";// 版本制作成功
	public static final String VER_FAIL = "VER_FAIL";// 版本制作失败
	public static final String CONFIG_SUSSESS = "配置审核通过！";// 版本制作失败
	public static final String CONFIG_FAIL = "配置审核不通过！";// 版本制作失败
	
	private String appName;//应用名
	private String envName;//环境名
	private String userName;//操作人
	private String branchName;//分支名
	private String serverName;//操作
	private String versionCode;//版本号
	private String motif;//主题
	private String url;//链接
	private String operation;//操作
	private String failMessage;//失败信息
	
	/**
	 * 配置管理消息方法
	 * @author Yang Chunhai	 
	 * @return
	 */
	public DingTalkMessage getConfigMessage(String appName, String envName, String branchName,String motif, String url, String userName, String operation){
		DingTalkMessage dingTalkMessage = new DingTalkMessage();
		dingTalkMessage.setAppName(appName);
		dingTalkMessage.setEnvName(envName);
		dingTalkMessage.setBranchName(branchName);
		dingTalkMessage.setOperation(operation);
		dingTalkMessage.setMotif(motif);
		dingTalkMessage.setUrl(url);
		dingTalkMessage.setUserName(userName);
		return dingTalkMessage;
	}
	
	/**
	 * 版本管理消息方法
	 * @author Yang Chunhai	 
	 * @return
	 */
	public DingTalkMessage getVersionMessage(String appName, String envName, String failMessage,String motif, String url, String userName, String operation){
		DingTalkMessage dingTalkMessage = new DingTalkMessage();
		dingTalkMessage.setAppName(appName);
		dingTalkMessage.setEnvName(envName);
		dingTalkMessage.setFailMessage(failMessage);
		dingTalkMessage.setMotif(motif);
		dingTalkMessage.setOperation(operation);
		dingTalkMessage.setUrl(url);
		dingTalkMessage.setUserName(userName);
		return dingTalkMessage;
	}
	
	/**
	 * 应用发布消息方法
	 * @author Yang Chunhai	 
	 * @return
	 */
	public DingTalkMessage getDepAppMessage(String appName, String envName, String serverNmae, String versionCode, String motif, String url, String operation, String failMessage){
		DingTalkMessage dingTalkMessage = new DingTalkMessage();
		dingTalkMessage.setAppName(appName);
		dingTalkMessage.setEnvName(envName);
		dingTalkMessage.setServerName(serverNmae);
		dingTalkMessage.setVersionCode(versionCode);
		dingTalkMessage.setMotif(motif);
		dingTalkMessage.setOperation(operation);
		dingTalkMessage.setUrl(url);
		dingTalkMessage.setFailMessage(failMessage);
		System.out.println("============"+dingTalkMessage.getUrl());
		return dingTalkMessage;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
}
