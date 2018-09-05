package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;

import com.bee.devops.admin.common.request.RestRequest;

public class ConfigRecordAuditRequest extends RestRequest {
	private static final long serialVersionUID = 7042163510599335310L;
	@NotNull(message = "{config.record.recordId.notnull}")
	Long recordId;
	@NotNull(message = "{config.record.recordStatus.notnull}")
	Integer status;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
