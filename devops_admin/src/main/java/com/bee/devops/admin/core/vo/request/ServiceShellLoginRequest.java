package com.bee.devops.admin.core.vo.request;

/**
 * 连接服务器保存账号密码端口号临时bean对象
 * @author Administrator
 *
 */
public class ServiceShellLoginRequest {
	private String username;	//连接服务器所需要的账号
	private String password;	//连接服务器所需要的密码
	private String address;	//服务器地址
	private Integer port;	//服务器端口号
	
	public ServiceShellLoginRequest(){}
	
	public ServiceShellLoginRequest(String username, String password, String address, Integer port) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.port = port;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "ServiceShellLoginTemp [username=" + username + ", password=" + password + ", address=" + address
				+ ", port=" + port + "]";
	}
}
