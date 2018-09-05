package com.bee.devops.admin.core.controller.ops.ksyun.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class KecBaseRequest extends RestRequest {

	private static final long serialVersionUID = 8883965080020416820L;
	@NotBlank(message = "{ksyun.kec.region.notnull}")
	String region;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
