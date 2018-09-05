package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class DeployJobRequest extends RestRequest{
	
	private static final long serialVersionUID = 3204225517002900590L;

	private Long depJobId;

	@NotBlank(message="{base.job.depJobName.notNull}")
	private String depJobName;

	@NotBlank(message="{base.job.folderName.notNull}")
	private String folderName;

	@NotNull(message="{base.job.operateType.notNull}")
	private Integer operateType;

	private Integer enabled;

	@Pattern(regexp = "^.{0,50}$",message = "{base.job.description.format}")
	private String description;

	public Long getDepJobId() {
		return depJobId;
	}

	public void setDepJobId(Long depJobId) {
		this.depJobId = depJobId;
	}

	public String getDepJobName() {
		return depJobName;
	}

	public void setDepJobName(String depJobName) {
		this.depJobName = depJobName;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
