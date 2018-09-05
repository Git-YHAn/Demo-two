/**
 * @author heping
 * @date2018年6月7日
 */
package com.bee.devops.admin.core.vo.request;

import java.util.List;

public class RoleResource {
	private Long roleId;
	private List<Long> resourceId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public List<Long> getResourceId() {
		return resourceId;
	}
	public void setResourceId(List<Long> resourceId) {
		this.resourceId = resourceId;
	}
	
}
