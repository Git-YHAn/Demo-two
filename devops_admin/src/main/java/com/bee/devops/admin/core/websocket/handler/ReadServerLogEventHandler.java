package com.bee.devops.admin.core.websocket.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.common.annotation.WsEventHandler;
import com.bee.devops.admin.common.utils.Shell;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
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

@Component
@WsEventHandler("/serverlog")
public class ReadServerLogEventHandler extends WsBaseEventHandler {

	private final static Integer MAX_LOG_LINES = 500;// 单次打印最大行数
	private final static Integer LOG_OUT_FREQ = 100;// 日志输出频率(毫秒)
	private final static Logger log = Logger.getLogger(ReadServerLogEventHandler.class);
	static ConcurrentHashMap<String, Shell> shellMap = new ConcurrentHashMap<>();

	@Autowired
	OpsBaseAppService opsBaseAppService;
	@Autowired
	OpsBaseServerService opsBaseServerService;

	@Autowired
	public ReadServerLogEventHandler(SocketIOServer server) {
		super(server);
	}

	@OnConnect
	public void onConnect(SocketIOClient client) {
		super.addUser(client);
		Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
		List<String> appIdParams = urlParams.get("app_id");
		List<String> hostIdParams = urlParams.get("host_id");
		if (appIdParams == null) {
			client.sendEvent("show", new WsEventData(client, "app_id 不能为空"));
			return;
		}
		if (hostIdParams == null) {
			client.sendEvent("show", new WsEventData(client, "host_id 不能为空"));
			return;
		}
		try {
			Integer.parseInt(appIdParams.get(0));
		} catch (Exception e) {
			client.sendEvent("show", new WsEventData(client, "app_id 不是int类型"));
			return;
		}
		try {
			Integer.parseInt(hostIdParams.get(0));
		} catch (Exception e) {
			client.sendEvent("show", new WsEventData(client, "host_id 不是int类型"));
			return;
		}

		OpsBaseServer assetsHost = opsBaseServerService.getById(Integer.parseInt(hostIdParams.get(0)));
		if (assetsHost == null) {
			client.sendEvent("show", new WsEventData(client, "未找到日志存放主机【" + hostIdParams.get(0) + "】"));
			return;
		}
		String userName = assetsHost.getHostAccount();
		String password = assetsHost.getHostPassword();
		String roomKey = "room:" + Integer.parseInt(hostIdParams.get(0)) + ":" + Integer.parseInt(appIdParams.get(0));
		client.joinRoom(roomKey);
		Shell shell = shellMap.get(roomKey);
		if (shell == null) {
			shell = new Shell(assetsHost.getSshAddress(), userName, password, assetsHost.getSshPort());
			Session session = shell.initSession();
			if (session != null) {
				shellMap.put(roomKey, shell);
			} else {
				String msg = "应用【" + assetsHost.getSshAddress() + "#" + appIdParams.get(0) + "】读取日志信息异常：服务器连接失败";
				log.error(msg);
				client.sendEvent("show", new WsEventData(client, msg));
//				client.disconnect();
			}
		}
	}

	@OnEvent(value = "show")
	public void onLogEvent(SocketIOClient client, AckRequest request, WsEventData data) {
		Set<String> allRooms = client.getAllRooms();
		for (String room : allRooms) {
			Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
			List<String> appIdParams = urlParams.get("app_id");
			OpsBaseApp application = opsBaseAppService.getAppById(Long.parseLong(appIdParams.get(0)));
			if (application == null) {
				client.sendEvent("show", new WsEventData(client, "项目【" + appIdParams.get(0) + "】不存在"));
				return;
			}
			if (StringUtils.isEmpty(application.getLogPath())) {
				client.sendEvent("show", new WsEventData(client, "项目日志地址错误：" + application.getLogPath()));
				return;
			}
			Shell shell = shellMap.get(room);
			if (shell != null) {
				String cmd = "ls -lt " + application.getLogPath() + " | grep -v %Y-%m-%d-%H.log | grep .log$ | head -n 1 |awk '{print $9}'";
				try {
					List<String> logNameList = shell.getExecResult(shell.exec(cmd));
					if (logNameList.size() > 0) {
						String logName = application.getLogPath() + "/" + logNameList.get(0);
						log.info("用户【"+super.getUserId(client)+"】正在查询项目【"+application.getProName()+"-"+application.getAppName()+"】日志: " + logName);
						List<String> logs = shell.getExecResult(shell.exec("tail -n50 " + logName));
						for (String msg : logs) {
							client.getNamespace().getRoomOperations(room).sendEvent("show", new WsEventData(client, msg));
						}
						if (client.getNamespace().getRoomOperations(room).getClients().size() <= 1) {// 只有第一个进入房间才继续查询日志
							Channel channel = shell.exec("tail -f " + logName);
							try (BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));) {
								List<String> msg = new ArrayList<>();
								long begin = System.currentTimeMillis();
								while (channel.getSession().isConnected()) {
									String line = in.readLine();
									if (line != null) {
										if (line.length() > 2000) {
											line = line.substring(0, 2000);
										}
										msg.add(line);
									}
									long now = System.currentTimeMillis();
									if (msg.size() > 0 && now - begin >= LOG_OUT_FREQ) {
										if (msg.size() > MAX_LOG_LINES) {
											msg = msg.subList(0, MAX_LOG_LINES);
										}
										client.getNamespace().getRoomOperations(room).sendEvent("show", new WsEventData(client, msg));
										begin = now;
										msg.clear();
									}
								}
							} catch (IOException e) {
								client.getNamespace().getRoomOperations(room).sendEvent("show", new WsEventData(client, e.getMessage()));
							}finally {
								channel.disconnect();
							}
						}
					}
				} catch (Exception e) {
					log.error("查询日志失败，错误原因：" + e.getMessage());
					client.sendEvent("show", new WsEventData(client, "查询日志失败，错误原因：" + e.getMessage()));
				}
			}
		}
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		super.removeUser(client);
		Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
		List<String> appIdParams = urlParams.get("app_id");
		List<String> hostIdParams = urlParams.get("host_id");
		if (appIdParams != null && hostIdParams != null) {
			String roomKey = "room:" + hostIdParams.get(0) + ":" + appIdParams.get(0);
			client.leaveRoom(roomKey);
			Collection<SocketIOClient> clients = client.getNamespace().getRoomOperations(roomKey).getClients();
			if (clients.size() <= 0) {
				Shell shell = shellMap.get(roomKey);
				if (shell != null) {
					shell.closeSession();
				}
				if (shellMap.containsKey(roomKey)) {
					shellMap.remove(roomKey);
				}
			}
		}
	}
}
