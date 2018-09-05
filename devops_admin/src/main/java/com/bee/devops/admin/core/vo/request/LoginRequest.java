package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest implements Serializable{

	private static final long serialVersionUID = 2494580380375060556L;
	@NotBlank(message="用户名不能为空")
	private String username;
	@NotBlank(message="密码不能为空")
	private String pwd;
	private boolean rember; //是否记住密码
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isRember() {
		return rember;
	}
	public void setRember(boolean rember) {
		this.rember = rember;
	}
}
