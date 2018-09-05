package com.bee.devops.admin.core.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.annotation.WsEventHandler;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.service.admin.AdminUserService;
import com.bee.devops.admin.core.websocket.WsBaseEventHandler;
import com.bee.devops.admin.core.websocket.WsEventData;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WsEventHandler("/")
public class NotificationHandler extends WsBaseEventHandler {

	private final static Logger log = Logger.getLogger(NotificationHandler.class);

	@Autowired
	public AdminUserService adminUserService;
	
	@Autowired
	public NotificationHandler(SocketIOServer server) {
		super(server);
	}

	@OnConnect
	public void onConnect(SocketIOClient client) {
		super.addUser(client);
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		super.removeUser(client);
	}

	@OnEvent(value = "notification")
	public void onEvent(SocketIOClient client, AckRequest request, WsEventData data) {
		Long userId = super.getUserId(client);
		if(userId != null) {
			AdminUser adminUser = adminUserService.getUserById(userId);
			client.sendEvent("usermsg", new WsEventData(client.getSessionId().toString(), null, null, JSONObject.toJSON(adminUser)));
		}
	}
}
