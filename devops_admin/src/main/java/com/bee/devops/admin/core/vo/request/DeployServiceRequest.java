package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class DeployServiceRequest extends RestRequest{

	private static final long serialVersionUID = 2391953648695167704L;

	private Long serviceId;

	@NotNull(message="{base.serviceComponent.envId.notNull}")
	private Long envId;

	private String envName;

	@NotBlank(message="{base.serviceComponent.serverName.notNull}")
	@Pattern(regexp = "^[\\w\\#-＿\u4e00-\u9fa5]{1,20}$",message = "{base.serviceComponent.serverName.format}")
	private String serviceName;

	@NotBlank(message="{base.serviceComponent.identify.notNull}")
	private String identify;

	@Pattern(regexp="^http://((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$",message = "{base.serviceComponent.serviceUrl.format}")
	private String serviceUrl;

	@NotBlank(message="{base.serviceComponent.servicePort.notNull}")
	@Pattern(regexp="^[0-9]*$", message = "{base.serviceComponent.servicePort.format}")
	private String servicePort;

	@NotBlank(message="{base.serviceComponent.serviceUsername.notNull}")
	@Pattern(regexp="^[\\w]+|([a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+))+$",message = "{base.serviceComponent.serviceUsername.format}")
	private String serviceUsername;

	@NotBlank(message="{base.serviceComponent.servicePassword.notNull}")
	private String servicePassword;

	@Pattern(regexp="^.{0,50}$", message = "{base.serviceComponent.description.format}")
	private String description;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	public String getServiceUsername() {
		return serviceUsername;
	}

	public void setServiceUsername(String serviceUsername) {
		this.serviceUsername = serviceUsername;
	}

	public String getServicePassword() {
		return servicePassword;
	}

	public void setServicePassword(String servicePassword) {
		this.servicePassword = servicePassword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
