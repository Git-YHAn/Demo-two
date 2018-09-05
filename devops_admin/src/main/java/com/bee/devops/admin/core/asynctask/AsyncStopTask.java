package com.bee.devops.admin.core.asynctask;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.common.utils.Shell;
import com.bee.devops.admin.core.common.dao.ops.OpsAssembleAppDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseAppDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseAppTypeDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseProfileTypeDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseRegionTypeDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseZoneTypeDao;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseProfileType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseRegionType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseZoneType;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class AsyncStopTask {

	@Autowired
	OpsBaseServerDao opsBaseServerDao;
	@Autowired
	OpsAssembleAppDao opsAssembleAppDao;
	@Autowired
	OpsBaseAppDao opsBaseAppDao;
	@Autowired
	OpsBaseAppTypeDao opsBaseAppTypeDao;
	@Autowired
	OpsBaseProfileTypeDao opsBaseProfileTypeDao;
	@Autowired
	OpsBaseZoneTypeDao opsBaseZoneTypeDao;
	@Autowired
	OpsBaseRegionTypeDao opsBaseRegionTypeDao;
	/**
	 * 停止应用
	 * 
	 * @param appEnvId
	 * @param serverId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Async
	public void asyncStopApp(Long appEnvId, Long serverId) throws UnsupportedEncodingException {
		OpsBaseServer server = opsBaseServerDao.getById(serverId);
		OpsAssembleApp appEnv = opsAssembleAppDao.getByappEnvId(appEnvId);
		Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
		Session session = shell.initSession();
		try {
			OpsBaseApp application = opsBaseAppDao.selectByPrimaryKey(appEnv.getAppId());
			try {
				if (application.getAppTypeId() == OpsBaseAppType.APP_TYPE_WEB) {
					String shellPath = "/usr/local/action_" + appEnv.getAppName() + "/start_tomcat.sh";
					String cmd = "sh " + shellPath + " stop ";
					Channel channel = shell.exec(cmd);
					int result = shell.getExitStatus(channel);
					if (result > 0) {
						throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
					}
				} else if(application.getAppTypeId() == OpsBaseAppType.APP_TYPE_JAVA){
					String stopShellName = "shutdown.sh";
					String stopShellPath = "/usr/local/action_" + appEnv.getAppName() + "/" + stopShellName;
					String cmd = "sh " + stopShellPath + " 2>&1 ";
					Channel channel = shell.exec(cmd);
					int result = shell.getExitStatus(channel);
					if (result > 0) {
						throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
					}
					channel.disconnect();
				}else if(application.getAppTypeId() == OpsBaseAppType.APP_TYPE_MS){
					OpsBaseAppType appType = opsBaseAppTypeDao.selectByPrimaryKey(application.getAppTypeId());
					String shellName = "springboot-exec.sh";
					String shellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/" + shellName;
					Map<String, String> params = new HashMap<>();
					params.put("INNER_IP", server.getInnerIp());
					params.put("MS_PORT", appEnv.getMsPort()+"");
					params.put("MS_EUREKA_ADDRESS", appEnv.getMsEurekaAddress());
					OpsBaseProfileType profileType = opsBaseProfileTypeDao.selectByPrimaryKey(appEnv.getMsProfileTypeId());
					params.put("MS_PROFILE_CODE", profileType.getProfileTypeName());
					OpsBaseZoneType zoneType = opsBaseZoneTypeDao.selectByPrimaryKey(appEnv.getMsZoneTypeId());
					params.put("MS_ZONE_CODE", zoneType.getZoneTypeName());
					OpsBaseRegionType regionType = opsBaseRegionTypeDao.selectByPrimaryKey(appEnv.getMsRegionTypeId());
					params.put("EUREKA_REGION_CODE", regionType.getRegionTypeName());
					params.put("CONFIG_URI", appEnv.getMsConfigUrl());
					params.put("APP_NAME", application.getAppName());
					params.put("LOGGING_CONFIG", appType.getDeployPath().replace("#{NAME}", appEnv.getAppName()) + "/config/logback-spring.xml");
					try (ByteArrayInputStream in = generateTamplateInputStream("shell/"+shellName, params);) {
						shell.scp(in, shellPath);
						String cmd = "sh " + shellPath + " stop ";
						Channel channel = shell.exec(cmd);
						int result = shell.getExitStatus(channel);
						if (result > 0) {
							throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
						}
					}	
				}
			} catch (Exception e) {
				throw new RuntimeException("应用【" + appEnv.getAppName() + "@" + server.getAssetsName() + "】停止失败：" + e.getMessage());
			}
		} finally {
			session.disconnect();
		}
	}

	public boolean stopApp(Long appEnvId, Long serverId){
		OpsBaseServer server = opsBaseServerDao.getById(serverId);
		OpsAssembleApp appEnv = opsAssembleAppDao.getByappEnvId(appEnvId);
		Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
		Session session = shell.initSession();
		try {
			OpsBaseApp application = opsBaseAppDao.selectByPrimaryKey(appEnv.getAppId());
			try {
				if (application.getAppTypeId() == OpsBaseAppType.APP_TYPE_WEB) {
					String shellPath = "/usr/local/action_" + appEnv.getAppName() + "/start_tomcat.sh";
					String cmd = "sh " + shellPath + " stop ";
					Channel channel = shell.exec(cmd);
					int result = shell.getExitStatus(channel);
					if (result > 0) {
						throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
					}else{
						return true;
					}
				} else if(application.getAppTypeId() == OpsBaseAppType.APP_TYPE_JAVA){
					String stopShellName = "shutdown.sh";
					String stopShellPath = "/usr/local/action_" + appEnv.getAppName() + "/" + stopShellName;
					String cmd = "sh " + stopShellPath + " 2>&1 ";
					Channel channel = shell.exec(cmd);
					int result = shell.getExitStatus(channel);
					if (result > 0) {
						throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
					}else{
						channel.disconnect();
						return true;
					}
				}else if(application.getAppTypeId() == OpsBaseAppType.APP_TYPE_MS){
					OpsBaseAppType appType = opsBaseAppTypeDao.selectByPrimaryKey(application.getAppTypeId());
					String shellName = "springboot-exec.sh";
					String shellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/" + shellName;
					Map<String, String> params = new HashMap<>();
					params.put("INNER_IP", server.getInnerIp());
					params.put("MS_PORT", appEnv.getMsPort()+"");
					params.put("MS_EUREKA_ADDRESS", appEnv.getMsEurekaAddress());
					OpsBaseProfileType profileType = opsBaseProfileTypeDao.selectByPrimaryKey(appEnv.getMsProfileTypeId());
					params.put("MS_PROFILE_CODE", profileType.getProfileTypeName());
					OpsBaseZoneType zoneType = opsBaseZoneTypeDao.selectByPrimaryKey(appEnv.getMsZoneTypeId());
					params.put("MS_ZONE_CODE", zoneType.getZoneTypeName());
					OpsBaseRegionType regionType = opsBaseRegionTypeDao.selectByPrimaryKey(appEnv.getMsRegionTypeId());
					params.put("EUREKA_REGION_CODE", regionType.getRegionTypeName());
					params.put("CONFIG_URI", appEnv.getMsConfigUrl());
					params.put("APP_NAME", application.getAppName());
					params.put("LOGGING_CONFIG", appType.getDeployPath().replace("#{NAME}", appEnv.getAppName()) + "/config/logback-spring.xml");
					try (ByteArrayInputStream in = generateTamplateInputStream("shell/"+shellName, params);) {
						shell.scp(in, shellPath);
						String cmd = "sh " + shellPath + " stop ";
						Channel channel = shell.exec(cmd);
						int result = shell.getExitStatus(channel);
						if (result > 0) {
							throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
						}else{
							return true;
						}
					}	
				}
			} catch (Exception e) {
				throw new RuntimeException("应用【" + appEnv.getAppName() + "@" + server.getAssetsName() + "】停止失败：" + e.getMessage());
			}
		} finally {
			session.disconnect();
		}
		return false;
	}
	private ByteArrayInputStream generateTamplateInputStream(String tempPath, Map<String, String> params) throws IOException, TemplateException {
		Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		ClassPathResource pathResource = new ClassPathResource(tempPath);
		InputStream resourceStream = pathResource.getInputStream();
		ByteArrayTemplateLoader tempLoader = new ByteArrayTemplateLoader();
		tempLoader.putTemplate("xtemplate", IOUtils.toByteArray(resourceStream));
		conf.setTemplateLoader(tempLoader);
		Template template = conf.getTemplate("xtemplate", "utf-8");
		StringWriter writer = new StringWriter();
		template.process(params, writer);
		return new ByteArrayInputStream(writer.toString().getBytes("utf-8"));
	}
}
