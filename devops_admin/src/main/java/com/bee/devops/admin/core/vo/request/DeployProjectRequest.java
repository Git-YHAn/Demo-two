package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class DeployProjectRequest extends RestRequest{

	private static final long serialVersionUID = -4423000818544784652L;

	private Long proId;

	@NotBlank(message="{base.project.proName.notNull}")
	@Pattern(regexp = "^[\\w\\#-＿\u4e00-\u9fa5]{1,20}$",message = "{base.project.proName.format}")
	private String proName;

	@NotBlank(message="{base.project.proCode.notNull}")
	@Pattern(regexp = "^[A-Za-z0-9]+$",message = "{base.project.proCode.format}")
	private String proCode;

	@Pattern(regexp = "^.{0,50}$",message = "{base.project.description.format}")
	private String description;

	private String webHook;
	
	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebHook() {
		return webHook;
	}

	public void setWebHook(String webHook) {
		this.webHook = webHook;
	}
}
