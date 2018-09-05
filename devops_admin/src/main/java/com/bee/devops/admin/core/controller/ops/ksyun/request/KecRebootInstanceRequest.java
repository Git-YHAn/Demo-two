package com.bee.devops.admin.core.controller.ops.ksyun.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.common.request.RestRequest;

public class KecRebootInstanceRequest extends RestRequest {

	private static final long serialVersionUID = -6795825989318126169L;

	List<String> instanceIds = new ArrayList<>();

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
