package com.bee.devops.admin.core.vo.response;

/**
 * @author Roc created on 2018/8/12
 */
public class DashBoardProDeployAppVo {

    private Long proId;
    private String proName;
    private int serverNum;
    private AppReleaseInfo deployAppInfo;
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
	public AppReleaseInfo getDeployAppInfo() {
		return deployAppInfo;
	}
	public void setDeployAppInfo(AppReleaseInfo deployAppInfo) {
		this.deployAppInfo = deployAppInfo;
	}
	public int getServerNum() {
		return serverNum;
	}
	public void setServerNum(int serverNum) {
		this.serverNum = serverNum;
	}
}
