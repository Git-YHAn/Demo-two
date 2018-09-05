package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

/**
 * 服务器管理封装类
 * @author Administrator
 *
 */
public class DeployHostRequest extends RestRequest{

	private static final long serialVersionUID = 1996924158939120244L;

	private Long assetsId;
	
	@NotBlank(message="{base.assetsHost.assetsName.notNull}")
	@Pattern(regexp = "^[\\w\\#-＿\u4e00-\u9fa5]{1,20}$",message = "{base.assetsHost.assetsName.format}")
	private String assetsName;	//服务器名称

	private String assetsTypeName;	//服务器类型
	
	@NotNull(message="{base.assetsHost.assetsType.notNull}")
	private Integer assetsType;	//服务器类型id
	
	@NotBlank(message="{base.assetsHost.sshAddress.notNull}")
	@Pattern(regexp="^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$",message = "{base.assetsHost.sshAddress.format}")
	private String sshAddress;	//ssh连接地址
	
	@NotBlank(message="{base.assetsHost.sshPort.notNull}")
	@Pattern(regexp="^[0-9]*$", message = "{base.assetsHost.sshPort.format}")
	private String sshPort;	//ssh端口
	
	@Pattern(regexp="^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$",message = "{base.assetsHost.outerIp.format}")
	private String outerIp;	//外网ip

	@Pattern(regexp="^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$", message = "{base.assetsHost.innerIp.format}")
	private String innerIp;	//内网ip
	
	private Integer assetsStatus;	//服务器状态
    private String operateSystem;	//操作系统 
    private String memory;	//存储器
    private String cpu;	//cpu型号
    private String disk;//磁盘信息 
    private Long operateUserId;	//操作员ID  
    
    @NotBlank(message="{base.assetsHost.hostAccount.notNull}")
	private String hostAccount;	//服务器连接账号
    
    @NotBlank(message="{base.assetsHost.hostPassword.notNull}")
	private String hostPassword;	//服务器连接密码

	private Integer initialStatus;

	public DeployHostRequest() {}
	
	public DeployHostRequest(Long assetsId, String assetsName, String assetsTypeName, Integer assetsType,
			String sshAddress, String sshPort, String outerIp, String innerIp, Integer assetsStatus,
			String operateSystem, String memory, String cpu, String disk, Long operateUserId, String hostAccount,
			String hostPassword) {
		this.assetsId = assetsId;
		this.assetsName = assetsName;
		this.assetsTypeName = assetsTypeName;
		this.assetsType = assetsType;
		this.sshAddress = sshAddress;
		this.sshPort = sshPort;
		this.outerIp = outerIp;
		this.innerIp = innerIp;
		this.assetsStatus = assetsStatus;
		this.operateSystem = operateSystem;
		this.memory = memory;
		this.cpu = cpu;
		this.disk = disk;
		this.operateUserId = operateUserId;
		this.hostAccount = hostAccount;
		this.hostPassword = hostPassword;
	}

	public Integer getInitialStatus() {
		return initialStatus;
	}

	public void setInitialStatus(Integer initialStatus) {
		this.initialStatus = initialStatus;
	}

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
	public String getAssetsTypeName() {
		return assetsTypeName;
	}
	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
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
	public Integer getAssetsStatus() {
		return assetsStatus;
	}
	public void setAssetsStatus(Integer assetsStatus) {
		this.assetsStatus = assetsStatus;
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
	public Long getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getHostAccount() {
		return hostAccount;
	}
	public void setHostAccount(String hostAccount) {
		this.hostAccount = hostAccount;
	}
	public String getHostPassword() {
		return hostPassword;
	}
	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}	
}