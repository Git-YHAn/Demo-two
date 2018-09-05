package com.bee.devops.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.bee.devops.admin.common.utils.SpringUtil;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class OpsAdminApplication {

	@Value("${wss.server.host}")
	private String host;

	@Value("${wss.server.port}")
	private Integer port;

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(OpsAdminApplication.class, args);
		SpringUtil.setApplicationContext(app);
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
//		config.setHostname(host);
		config.setPort(port);
		config.setWorkerThreads(100);
		config.setOrigin("*");
		// 该处可以用来进行身份验证
		config.setAuthorizationListener(new AuthorizationListener() {
			@Override
			public boolean isAuthorized(HandshakeData data) {
				// http://localhost:8081?username=test&password=test
				// 例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
				// String username = data.getSingleUrlParam("username");
				// String password = data.getSingleUrlParam("password");
				return true;
			}
		});
		SocketIOServer server = new SocketIOServer(config);
		return server;
	}
	
}
