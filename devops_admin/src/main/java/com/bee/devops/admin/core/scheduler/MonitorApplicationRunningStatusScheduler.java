package com.bee.devops.admin.core.scheduler;

import ch.ethz.ssh2.Connection;
import com.bee.devops.admin.common.enums.ApplicationStatusEnums;
import com.bee.devops.admin.common.utils.ExecutorUtils;
import com.bee.devops.admin.common.utils.SshUtils;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseAppTypeDao;
import com.bee.devops.admin.core.common.entity.ops.*;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvServerService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 应用运行状态检测 定时任务
 * 每10s检测一次
 *
 * @author Roc created on 2018/8/13
 */
@Component
public class MonitorApplicationRunningStatusScheduler {
    private final static Logger logger = Logger.getLogger(MonitorApplicationRunningStatusScheduler.class);
    @Autowired
    private OpsBaseEnvServerService envServerService;
    @Autowired
    private OpsAssembleAppService assembleAppService;
    @Autowired
    private OpsBaseAppService appService;
    @Autowired
    private OpsBaseServerService serverService;
    @Autowired
    private OpsBaseAppTypeDao appTypeDao;

    @Scheduled(cron = "*/30 * * * * ?")
    public void monitorAndUpdateApplicationStatus() {
        List<OpsBaseEnvServer> envServers = envServerService.getRunningAndStoppedApplications();
        if (envServers.isEmpty()) {
            return;
        }
        HashMap<Long, List<OpsBaseEnvServer>> envServerMap = convertToEnvServersMap(envServers);

        ExecutorService fixedThreadPool = ExecutorUtils.getFixedThreadPool(envServerMap.size());
        // 执行检测脚本,获取执行结果
        for (Map.Entry<Long, List<OpsBaseEnvServer>> envServerEntry : envServerMap.entrySet()) {
            fixedThreadPool.submit(() -> {
                for (OpsBaseEnvServer appEnvHost : envServerEntry.getValue()) {
                    OpsAssembleApp assembleApp = assembleAppService.getByappEnvId(appEnvHost.getAppEnvId());
                    OpsBaseApp baseApp = appService.getAppById(assembleApp.getAppId());
                    OpsBaseAppType appType = appTypeDao.selectByPrimaryKey(baseApp.getAppTypeId());
                    OpsBaseServer server = serverService.getById(appEnvHost.getHostId());
                    long appTypeId = appType.getAppTypeId();
                    String remoteScriptPath = appType.getScriptPath().replace("#{NAME}", baseApp.getAppName());
                    String shellName = "";
                    boolean appTypeIsStatic = appTypeId == OpsBaseAppType.APP_TYPE_STATIC;
                    if (appTypeId == OpsBaseAppType.APP_TYPE_MS) {
                        shellName = "check_microservices.sh";
                    } else if (appTypeId == OpsBaseAppType.APP_TYPE_WEB || appTypeIsStatic) {
                        shellName = "check_tomcat.sh";
                    } else if (appTypeId == OpsBaseAppType.APP_TYPE_JAVA) {
                        return;
                    }
                    String sshAddress = server.getSshAddress();
                    int sshPort = server.getSshPort();
                    Connection connection = null;
                    int exitStatus;
                    boolean found;
                    try {
                        connection = SshUtils.getConnection(sshAddress, server.getHostAccount(), server.getHostPassword(), sshPort);
                        StringBuilder execResult = new StringBuilder();
                        SshUtils.ethzScp(connection, new ClassPathResource("shell/" + shellName), shellName, remoteScriptPath);
                        String cmd = "sh " + (appTypeIsStatic ? remoteScriptPath + shellName : remoteScriptPath + shellName + " " + assembleApp.getMsPort());
                        exitStatus = SshUtils.ethzExecute(connection, cmd, execResult, 1000);
                        found = exitStatus == 0;
                    } catch (Exception e) {
                        logger.warn("【" + baseApp.getAppName() + "#" + server.getAssetsName() + "】Shell Execute Error, e: " + e.getMessage());
                        return;
                    } finally {
                        if (connection != null) {
                            connection.close();
                        }
                    }
                    int oldStatus = envServerService.getApplicationRunningStatus(appEnvHost.getHostAppEnvId());
                    boolean updated = false;
                    int newStatus = oldStatus;
                    String msg = "";
                    if (found && oldStatus == ApplicationStatusEnums.APPLICATION_STOPPED.getValue()) {
                        updated = true;
                        newStatus = ApplicationStatusEnums.APPLICATION_RUNNING.getValue();
                        msg = ApplicationStatusEnums.APPLICATION_RUNNING.getDescription();
                    } else if (!found && oldStatus == ApplicationStatusEnums.APPLICATION_RUNNING.getValue()) {
                        updated = true;
                        newStatus = ApplicationStatusEnums.APPLICATION_STOPPED.getValue();
                        msg = ApplicationStatusEnums.APPLICATION_STOPPED.getDescription();
                    }
                    if (updated) {
                        envServerService.updateAppStatus(appEnvHost.getHostAppEnvId(), newStatus);
                        logger.warn("应用【" + baseApp.getAppName() + "#" + server.getAssetsName() + "】运行状态更新为: " + msg);
                    }
                }
            });
        }

        fixedThreadPool.shutdown();
    }

    /**
     * 根据服务器Id对服务器进行分组, 同一个服务器在一个线程内执行
     */
    private HashMap<Long, List<OpsBaseEnvServer>> convertToEnvServersMap(List<OpsBaseEnvServer> envServers) {
        HashMap<Long, List<OpsBaseEnvServer>> map = new HashMap<>();
        for (OpsBaseEnvServer envServer : envServers) {
            Long hostId = envServer.getHostId();
            List<OpsBaseEnvServer> envServerList = map.get(hostId);
            if (envServerList == null) {
                envServerList = new ArrayList<>();
            }
            envServerList.add(envServer);
            map.put(hostId, envServerList);
        }
        return map;
    }
}
