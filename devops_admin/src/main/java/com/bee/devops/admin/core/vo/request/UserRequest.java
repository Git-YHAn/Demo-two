/**
 * @author heping
 * @date2018年6月11日
 */
package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class UserRequest extends RestRequest {
	private static final long serialVersionUID = -1301273102547286225L;

	private Long adminUserId;

	@NotBlank(message = "{admin.user.realName.notnull}")
	@Pattern(regexp = "^([\u4e00-\u9fa5]+|[a-zA-Z\\s]+)$",message = "{admin.user.realName.format}")
	private String realName;

	@NotBlank(message = "{admin.user.username.notnull}")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,12}$",message = "{admin.user.username.format}")
	private String username;

	@NotBlank(message = "{admin.user.password.notnull}")
	@Pattern(regexp = "^.*(?=.{6,})[A-Za-z](?=.*[0-9])(?=.*[~!@#$%^&*_=;:,./<>?]).*$", message = "{admin.user.password.format}")
	private String password;

	@Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$|^\\s*$", message = "{admin.user.email.format}")
	private String email;

	@Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$|^\\s*$", message = "{admin.user.mobile.format}")
	private String mobile;

	private Integer isActive;

	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

}
