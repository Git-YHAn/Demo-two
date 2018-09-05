package com.bee.devops.admin.core.vo.response;

/**
 * 应用实例管理应用详情
 * @author Roc created on 2018/8/14
 */
public class OpsDepAppOrderInfoVersionVo {
	
    private Long versionId;
    private String versionCode;
    private String versionDesc;
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

    
}
