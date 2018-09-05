package com.bee.devops.admin.core.vo.request;

import java.util.Arrays;

import com.bee.devops.admin.common.request.RestRequest;

public class AddAppEnvRequest extends RestRequest{
	private static final long serialVersionUID = -4871817847056953524L;
	private Long hostId;
	private Long[] appEnvId;
	
	public AddAppEnvRequest() {
		super();
	}
	public AddAppEnvRequest(Long hostId, Long[] appEnvId) {
		super();
		this.hostId = hostId;
		this.appEnvId = appEnvId;
	}
	public Long getHostId() {
		return hostId;
	}
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	public Long[] getAppEnvId() {
		return appEnvId;
	}
	public void setAppEnvId(Long[] appEnvId) {
		this.appEnvId = appEnvId;
	}
	@Override
	public String toString() {
		return "AddAppEnvRequest [hostId=" + hostId + ", appEnvId=" + Arrays.toString(appEnvId) + "]";
	}
	
}
