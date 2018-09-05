package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class DeployEnvironmentRequest extends RestRequest{

	private static final long serialVersionUID = 5155449242756148369L;

	private Long envId;

	@NotBlank(message="{base.environment.envName.notNull}")
	@Pattern(regexp = "^[\\w\\#-＿\u4e00-\u9fa5]{1,20}$",message = "{base.environment.envName.format}")
    private String envName;

	@NotBlank(message="{base.environment.envCode.notNull}")
	@Pattern(regexp = "^[A-Za-z0-9]+$",message = "{base.environment.envCode.format}")
    private String envCode;
    
	@NotBlank(message="{base.environment.priority.notNull}")
	@Pattern(regexp = "^[0-9]*$",message = "{base.environment.priority.format}")
    private String priority;

	@Pattern(regexp = "^.{0,50}$",message = "{base.environment.description.format}")
    private String description;

    public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvCode() {
		return envCode;
	}

	public void setEnvCode(String envCode) {
		this.envCode = envCode;
	}

	public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
