package com.bee.devops.admin.core.vo.response;

import java.util.List;

/**
 * 服务器应用状态包装类
 * @author Administrator
 *
 */
public class ServerStatusVo {
	private int status; //-1已停止   0运行中   1发布中
	private List<ServerDepAppStatusVo> lists;// 数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<ServerDepAppStatusVo> getLists() {
		return lists;
	}
	public void setLists(List<ServerDepAppStatusVo> lists) {
		this.lists = lists;
	}
	
}
