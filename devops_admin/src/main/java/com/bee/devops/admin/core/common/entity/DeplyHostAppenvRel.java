package com.bee.devops.admin.core.common.entity;

/**
 * DeplyHostAppenvRel表对应bean对象
 * @author Administrator
 *
 */
public class DeplyHostAppenvRel 
{
	private Long hostAppenvId;	//主键id
	private Long hostId;	//服务器id
	private Long appEnvId;	//关联表id
	
	public DeplyHostAppenvRel(){}

	public DeplyHostAppenvRel(Long hostId, Long appEnvId) {
		super();
		this.hostId = hostId;
		this.appEnvId = appEnvId;
	}

	public DeplyHostAppenvRel(Long hostAppenvId, Long hostId, Long appEnvId) {
		super();
		this.hostAppenvId = hostAppenvId;
		this.hostId = hostId;
		this.appEnvId = appEnvId;
	}

	public Long getHostAppenvId() {
		return hostAppenvId;
	}

	public void setHostAppenvId(Long hostAppenvId) {
		this.hostAppenvId = hostAppenvId;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public Long getAppEnvId() {
		return appEnvId;
	}

	public void setAppEnvId(Long appEnvId) {
		this.appEnvId = appEnvId;
	}

	@Override
	public String toString() {
		return "DeplyHostAppenvRel [hostAppenvId=" + hostAppenvId + ", hostId=" + hostId + ", appEnvId=" + appEnvId
				+ "]";
	}
	
}
