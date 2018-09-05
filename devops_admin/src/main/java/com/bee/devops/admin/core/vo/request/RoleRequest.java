/**
 * @author heping
 * @date2018年6月11日
 */
package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class RoleRequest extends RestRequest{
	
	private static final long serialVersionUID = 798437008409918484L;

	private Long roleId;
	
	@NotBlank(message = "{admin.role.roleName.notnull}")
	@Pattern(regexp = "^[\\w#-＿\u4e00-\u9fa5]{3,20}$",message = "{admin.role.roleName.format}")
	private String roleName;
	
	@Pattern(regexp = "^.{0,50}$",message = "{admin.role.description.format}")
	private String description;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
