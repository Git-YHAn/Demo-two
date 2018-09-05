package com.bee.devops.admin.core.common.entity.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdminUser implements Serializable {

	private static final long serialVersionUID = 3936792243856894587L;
	public static final int ADMIN_STATUS_NORMAL = 1;// 正常用户
	public static final int ADMIN_SUPPER_NORMAL = 0;// 是否为超级管理员 0为普通用户，1为超级用户

	private Long adminUserId;

	private String realName;

	private String username;

	private String password;

	private String email;

	private String mobile;

	private Integer isSupper;

	private Integer isActive;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date updateTime;

	private Integer loginNum;

	private List<AdminRole> adminRole = new ArrayList<>();

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
		this.realName = realName == null ? null : realName.trim();
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
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer getIsSupper() {
		return isSupper;
	}

	public void setIsSupper(Integer isSupper) {
		this.isSupper = isSupper;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<AdminRole> getRoleInfo() {
		return adminRole;
	}

	public void setRoleInfo(List<AdminRole> adminRole) {
		this.adminRole = adminRole;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

}