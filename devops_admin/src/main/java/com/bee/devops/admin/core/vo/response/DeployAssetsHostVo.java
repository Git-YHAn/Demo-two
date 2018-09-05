package com.bee.devops.admin.core.vo.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeployAssetsHostVo {
	private Long assetsId;	//ID
    private String assetsName;	//服务器名称

    private Integer assetsType;	//服务器类型
    private String sshAddress;	
    private String sshPort;
    private Integer assetsStatus;	//服务器状态
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;	//创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;	//修改时间
    
    private String operateSystem;	//操作系统
    private String memory;	//存储器
    private String cpu;	//cpu型号
    private String disk;	//磁盘信息
    private String outerIp;	//外网ip
    private String innerIp;	//内网ip   
    private Long operateUserId;	//操作员id
    private String operateUserName;	//操作员名字
    
    private String versionCode;
    
	private String hostAccount; // 服务器账号

	/**
	 * 初始化状态 1 已经初始化  0 未初始化
	 */
	private int initialStatus;

	/**
	 * 该服务器是否已经使用 0 未使用 >0 则已经使用,代表已经关联了应用
	 */
	private int used;

	/**
	 * 项目,多个项目以,分隔
	 */
	private String project = "无";

	/**
	 * 环境
	 */
	private String env = "无";

	/**
	 * 应用,多个应用以,分隔
	 */
	private String application = "无";

	public int getInitialStatus() {
		return initialStatus;
	}

	public void setInitialStatus(int initialStatus) {
		this.initialStatus = initialStatus;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Long getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}
	
	public String getOperateUserName() {
		return operateUserName;
	}

	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	private String assetsTypeName;

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public Integer getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(Integer assetsType) {
		this.assetsType = assetsType;
	}

	public String getSshAddress() {
		return sshAddress;
	}

	public void setSshAddress(String sshAddress) {
		this.sshAddress = sshAddress;
	}

	public String getSshPort() {
		return sshPort;
	}

	public void setSshPort(String sshPort) {
		this.sshPort = sshPort;
	}

	public Integer getAssetsStatus() {
		return assetsStatus;
	}

	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getOuterIp() {
		return outerIp;
	}

	public void setOuterIp(String outerIp) {
		this.outerIp = outerIp;
	}

	public String getInnerIp() {
		return innerIp;
	}

	public void setInnerIp(String innerIp) {
		this.innerIp = innerIp;
	}

	public String getAssetsTypeName() {
		return assetsTypeName;
	}

	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getHostAccount() {
		return hostAccount;
	}

	public void setHostAccount(String hostAccount) {
		this.hostAccount = hostAccount;
	}
}
