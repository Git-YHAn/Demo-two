package com.bee.devops.admin.core.controller.ops.ksyun.request;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.common.request.RestRequest;

public class KecModifyInstanceImageRequest extends RestRequest {

	private static final long serialVersionUID = -2597452921336974605L;

	List<String> instanceIds;

	String imageId;

	String instancePassword;

	Boolean keepImageLogin;

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getInstancePassword() {
		return instancePassword;
	}

	public void setInstancePassword(String instancePassword) {
		this.instancePassword = instancePassword;
	}

	public Boolean getKeepImageLogin() {
		return keepImageLogin;
	}

	public void setKeepImageLogin(Boolean keepImageLogin) {
		this.keepImageLogin = keepImageLogin;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
