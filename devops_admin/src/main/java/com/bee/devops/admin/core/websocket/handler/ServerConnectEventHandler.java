package com.bee.devops.admin.core.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.bee.devops.admin.common.annotation.WsEventHandler;
import com.bee.devops.admin.common.utils.Shell;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerService;
import com.bee.devops.admin.core.websocket.WsBaseEventHandler;
import com.bee.devops.admin.core.websocket.WsEventData;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 连接远程服务器命令交互
 *
 * @author wanghuajie
 * @date 2018/7/16 15:10
 */
@Component
@WsEventHandler("/connect")
public class ServerConnectEventHandler extends WsBaseEventHandler {
    private final static Logger logger = Logger.getLogger(ServerConnectEventHandler.class);
    private static final ThreadLocal<Shell> threadLocal = new ThreadLocal<>();

    @Autowired
    OpsBaseServerService opsBaseServerService;

    @Autowired
    public ServerConnectEventHandler(SocketIOServer server) {
        super(server);
    }

    /**
     * 建立连接时添加用户
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        super.addUser(client);
        String assetsId = client.getHandshakeData().getSingleUrlParam("assetsId");
        OpsBaseServer server = opsBaseServerService.getById(NumberUtils.toLong(assetsId));
        String sshAddress = server.getSshAddress();
        String account = server.getHostAccount();
        String password = server.getHostPassword();
        Integer sshPort = server.getSshPort();
        Shell shell = new Shell(sshAddress, account, password, sshPort);
        threadLocal.set(shell);
    }

    /**
     * 前后台通过此event进行交互
     */
    @OnEvent(value = "show")
    public void onLogEvent(SocketIOClient client, AckRequest request, WsEventData data) {
        if (data != null) {
            Object msg = data.getMsg();
            Shell shell = threadLocal.get();
            Session session = shell.initSession();
            Channel channel = null;
            try {
                channel = shell.exec((String) msg);
                InputStream inputStream = channel.getInputStream();
                String back = IOUtils.toString(inputStream, "utf-8");
                logger.info("back = " + back);
                WsEventData wsEventData = new WsEventData();
                wsEventData.setMsg(back);
                client.sendEvent("show", JSON.toJSONString(wsEventData));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.disconnect();
                if (channel != null) {
                    channel.disconnect();
                }
            }
        }
    }

    /**
     * 断开连接时剔除用户
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        super.removeUser(client);
    }
}
