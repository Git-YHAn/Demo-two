package com.bee.devops.admin.core.asynctask;

import com.bee.devops.admin.common.utils.MapUtils;
import com.bee.devops.admin.common.utils.Shell;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * 初始化服务器任务
 *
 * @author wanghuajie
 * @date 2018/7/11 10:17
 */
public class InitServerTask implements Runnable {
    private final static Logger logger = Logger.getLogger(InitServerTask.class);
    private OpsBaseServer server;
    private ClassPathResource pathResource;
    private OpsBaseServerDao opsBaseServerDao;
    private String appName;

    public InitServerTask(OpsBaseServerDao opsBaseServerDao, OpsBaseServer server, ClassPathResource pathResource, String appName) {
        this.opsBaseServerDao = opsBaseServerDao;
        this.server = server;
        this.pathResource = pathResource;
        this.appName = appName;
    }

    /**
     * 执行服务器初始化时,多条线程同时初始化不同的服务器不受影响;多条线程同时初始化同一个服务器,要做枷锁控制
     *
     * @throws Exception
     */
    @Override
    public void run() {
        String assetsName = "";
        Session session = null;
        long assetsId = 0L;
        try {
            // 执行初始化
            assetsName = server.getAssetsName();
            assetsId = server.getAssetsId();
            // 连接到对应的服务器
            Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
            session = shell.initSession();
            // 将脚本文件复制到服务器目录下
            String shellPath = "/usr/local/server/" + assetsId + "/lemi_init.sh";
            shell.scp(pathResource.getInputStream(), shellPath);

            // 执行脚本的时候需要传入对应的应用名
            if (StringUtils.isNotEmpty(appName)) {
                String[] apps = StringUtils.split(appName, ",");
                for (String app : apps) {
                    String cmd = "sh " + shellPath + " " + app;
                    logger.info("执行远程脚本,cmd = " + cmd);
                    Channel channel = shell.exec(cmd);
                    List<String> result = shell.getExecResult(channel);
                    if (result != null) {
                        logger.info("执行完毕,关闭通道,result = " + result.size());
                    }
                }
                logger.info("服务器【" + server.getAssetsName() + "】脚本执行应用【" + appName + "】成功,已初始化");
                // 更新服务器初始化状态为 已初始化
                server.setInitialStatus(1);
                opsBaseServerDao.updateHostInitStatus(server);
                MapUtils.MAP.remove(assetsId);
            }
        } catch (Exception e) {
            MapUtils.MAP.remove(assetsId);
            logger.error("服务器【" + assetsName + "】脚本执行应用【" + appName + "】异常,初始化失败", e);
            throw new RuntimeException("服务器【" + assetsName + "】脚本执行应用【" + appName + "】异常,初始化失败");
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
