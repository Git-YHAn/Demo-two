package com.bee.devops.admin.core.websocket.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.common.annotation.WsEventHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppService;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.websocket.WsBaseEventHandler;
import com.bee.devops.admin.core.websocket.WsEventData;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

@Component
@WsEventHandler("/publishlog")
public class ReadPublishLogEventHandler extends WsBaseEventHandler {

	private final static Logger log = Logger.getLogger(ReadPublishLogEventHandler.class);
	static ConcurrentHashMap<String, String> logFileMap = new ConcurrentHashMap<>();
	static ConcurrentHashMap<String, Long> lastTimeFileSizeMap = new ConcurrentHashMap<>();

	@Autowired
	OpsBaseServerService opsBaseServerService;
	@Autowired
	OpsDepAppService opsDepAppService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;

	@Autowired
	public ReadPublishLogEventHandler(SocketIOServer server) {
		super(server);
	}

	@OnConnect
	public void onConnect(SocketIOClient client) throws IOException {
		super.addUser(client);
		Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
		List<String> publishIdParams = urlParams.get("publish_id");
		if (publishIdParams == null) {
			client.sendEvent("show", new WsEventData(client, "publish_id 不能为空"));
			return;
		}
		try {
			Long.parseLong(publishIdParams.get(0));
		} catch (Exception e) {
			client.sendEvent("show", new WsEventData(client, "publish_id 不是数字类型"));
			return;
		}
		OpsDepApp opsDepApp = opsDepAppService.get(Long.parseLong(publishIdParams.get(0)));
		if (opsDepApp == null) {
			client.sendEvent("show", new WsEventData(client, "发布记录【" + publishIdParams.get(0) + "】不存在"));
			return;
		}
		// 查询编码路径
		AppEnvProCodeVo appEnvProCodeVo = opsAssembleAppService.getCodes(opsDepApp.getAppEnvId());
		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getByappEnvId(opsDepApp.getAppEnvId());
		File file = new File(opsDepAppService.getDeployLogPath(opsDepApp.getServerId(), appEnvProCodeVo, opsAssembleApp));
		if (!file.exists()) {
			client.sendEvent("show", new WsEventData(client, "发布日志不存在:" + file.getAbsolutePath()));
			return;
		}
		try (BufferedReader input = new BufferedReader(new FileReader(file))) {
			while (input.ready()) {
				String line = input.readLine();
				if (StringUtils.isNotBlank(line)) {
					client.sendEvent("show", new WsEventData(client, line));
				}
			}
		}
		String key = getUserToken(client) + ":" + publishIdParams.get(0);
		logFileMap.put(key, file.getPath());
		lastTimeFileSizeMap.put(key, file.length());
	}

	@OnEvent(value = "show")
	public void onLogEvent(SocketIOClient client, AckRequest request, WsEventData data) throws InterruptedException {
		Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
		List<String> publishIdParams = urlParams.get("publish_id");
		String key = getUserToken(client) + ":" + publishIdParams.get(0);
		String filePath = logFileMap.get(key);
		if (StringUtils.isBlank(filePath)) {
			return;
		}
		int emptyCount = 0;
		while (emptyCount < 15) {
			Thread.sleep(200);
			File file = new File(filePath);
			Long lastTimeFileSize = lastTimeFileSizeMap.get(key);
			long len = file.length();
			try {
				if (len < lastTimeFileSize) {
					log.info("Log file was reset. Restarting logging from start of file.");
					lastTimeFileSize = len;
				} else if (len > lastTimeFileSize) {
					RandomAccessFile randomFile = new RandomAccessFile(file, "r");
					randomFile.seek(lastTimeFileSize);
					while (true) {
						String tmp = randomFile.readLine();
						if(StringUtils.isNotBlank(tmp)) {
							client.sendEvent("show", new WsEventData(client, new String(tmp.getBytes("ISO-8859-1"), "utf-8")));
						}
						if("==END==".equals(tmp)) {
							break;
						}
					}
					lastTimeFileSize = randomFile.length();
					randomFile.close();
					emptyCount = 0;
				}else {
					emptyCount ++ ;
				}
				lastTimeFileSizeMap.put(key, lastTimeFileSize);
			} catch (IOException e) {
				log.error("日志查询错误：" + e);
				client.sendEvent("show", new WsEventData(client, e.getMessage()));
			}
		}
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
		List<String> publishIdParams = urlParams.get("publish_id");
		String key = getUserToken(client) + ":" + publishIdParams.get(0);
		super.removeUser(client);
		if (publishIdParams != null) {
			if (logFileMap.containsKey(key)) {
				logFileMap.remove(key);
			}
			if (lastTimeFileSizeMap.containsKey(key)) {
				lastTimeFileSizeMap.remove(key);
			}
		}
	}
}
