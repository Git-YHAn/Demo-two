package com.bee.devops.admin.core.websocket;

import com.bee.devops.admin.common.annotation.WsEventHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsSysParameter;
import com.bee.devops.admin.core.common.service.ops.OpsSysParameterService;
import com.corundumstudio.socketio.SocketIOServer;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Component
public class WsServerRunner implements CommandLineRunner, ApplicationContextAware {

	private final static Logger log = Logger.getLogger(WsServerRunner.class);

	private final SocketIOServer server;
	private ApplicationContext applicationContext;

	@Autowired
	public WsServerRunner(SocketIOServer server) {
		this.server = server;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> map = this.applicationContext.getBeansWithAnnotation(WsEventHandler.class);
		for (String key : map.keySet()) {
			Object bean = map.get(key);
			String namespace = bean.getClass().getAnnotation(WsEventHandler.class).value();
			server.removeNamespace("");
			server.addNamespace(namespace).addListeners(bean, bean.getClass());
			log.info("WebSocket Mapped \"{" + namespace + "}\" onto " + bean.getClass().toGenericString());
		}

		this.server.start();
	}

	@PreDestroy
	public void destory() {
		server.stop();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
