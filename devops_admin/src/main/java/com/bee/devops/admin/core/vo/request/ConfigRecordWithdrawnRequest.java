package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;

import com.bee.devops.admin.common.request.RestRequest;

public class ConfigRecordWithdrawnRequest extends RestRequest {

	private static final long serialVersionUID = 2992291521016951494L;

	@NotNull(message="{config.record.recordId.notnull}")
	Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

}
