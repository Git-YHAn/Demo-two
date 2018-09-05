/**
 * @author heping
 * @date2018年6月8日
 */
package com.bee.devops.admin.core.vo.request;

import java.util.List;

public class UserRoleRequest {
	private Long userId;
	private List<Long> roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Long> getRoleId() {
		return roleId;
	}
	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}
	
}
