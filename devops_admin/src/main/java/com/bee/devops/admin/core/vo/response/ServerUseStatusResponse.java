package com.bee.devops.admin.core.vo.response;

/**
 * 服务器使用状态返回类
 * @author Administrator
 */
public class ServerUseStatusResponse {
	private int total; //所有服务器
	private int used; //已使用的服务器
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
}
