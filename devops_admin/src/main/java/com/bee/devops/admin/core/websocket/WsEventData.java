package com.bee.devops.admin.core.websocket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.corundumstudio.socketio.SocketIOClient;

/**
 * @description websocket请求时间数据格式
 * @author TurnerXi
 * @date 2018年5月4日
 */
public class WsEventData {
	// 源客户端id
	private String source;
	// 目标客户端id
	private String target;
	// 消息类型
	private String type;
	// 消息内容
	private Object msg;

	public WsEventData(String source, String target, String type, Object msg) {
		super();
		this.source = source;
		this.target = target;
		this.type = type;
		this.msg = msg;
	}

	public WsEventData(SocketIOClient client, Object msg) {
		super();
		this.source = client.getSessionId().toString();
		this.target = null;
		this.type = null;
		this.msg = msg;
	}

	public WsEventData() {
		super();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
