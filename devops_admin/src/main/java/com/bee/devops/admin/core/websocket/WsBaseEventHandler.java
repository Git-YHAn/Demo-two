package com.bee.devops.admin.core.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bee.devops.admin.common.token.TokenPayLoad;
import com.bee.devops.admin.common.token.TokenUtil;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;

public class WsBaseEventHandler {

	final protected SocketIOServer server;

	protected final Map<String, Long> users = new HashMap<>();

	public WsBaseEventHandler(SocketIOServer server) {
		this.server = server;
	}

	@OnConnect
	public void OnConnect(SocketIOClient client) {
		if(StringUtils.isEmpty(client.getNamespace().getName())) {
			client.disconnect();
		}
	}
	
	public void addUser(SocketIOClient client) {
		String token = getUserToken(client);
		TokenPayLoad payload = TokenUtil.parseToken(token);
		if (payload != null) {
			users.put(token, payload.getUserid());
		}
	}

	public void removeUser(SocketIOClient client) {
		String token = getUserToken(client);
		users.remove(token);
	}

	public String getUserToken(SocketIOClient client) {
		return client.getHandshakeData().getSingleUrlParam("token");
	}

	public Long getUserId(SocketIOClient client) {
		return users.get(getUserToken(client));
	}
}
