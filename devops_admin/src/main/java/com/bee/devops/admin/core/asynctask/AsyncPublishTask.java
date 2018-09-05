package com.bee.devops.admin.core.asynctask;

import ch.ethz.ssh2.Connection;
import com.bee.devops.admin.common.enums.ApplicationStatusEnums;
import com.bee.devops.admin.common.enums.AssetsTypeEnums;
import com.bee.devops.admin.common.enums.DeployStatusEnums;
import com.bee.devops.admin.common.enums.OrderStatusEnums;
import com.bee.devops.admin.common.utils.*;
import com.bee.devops.admin.core.common.dao.ops.*;
import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.bee.devops.admin.core.common.entity.ops.*;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppService;
import com.bee.devops.admin.core.vo.request.OpsDepAppOrderInfoRequest;
import com.bee.devops.admin.core.vo.request.OpsDepAppOrderRequest;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoResponse;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.DateUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AsyncPublishTask {

    private final static Logger log = Logger.getLogger(AsyncPublishTask.class);
    @Autowired
    OpsBaseServerDao opsBaseServerDao;
    @Autowired
    OpsAssembleAppDao opsAssembleAppDao;
    @Autowired
    OpsAssembleAppDao deployAppEnvService;
    @Autowired
    OpsBaseAppDao opsBaseAppDao;
    @Autowired
    OpsBaseAppTypeDao opsBaseAppTypeDao;
    @Autowired
    OpsDepAppDao opsDepAppDao;
    @Autowired
    OpsBaseProfileTypeDao opsBaseProfileTypeDao;
    @Autowired
    OpsBaseRegionTypeDao opsBaseRegionTypeDao;
    @Autowired
    OpsBaseZoneTypeDao opsBaseZoneTypeDao;
    @Autowired
    OpsDepAppService opsDepAppService;
    @Autowired
    OpsBaseProDao opsBaseProDao;
    @Autowired
    OpsBaseEnvService opsBaseEnvService;
    @Autowired
    OpsDepAppOrderInfoDao opsDepAppOrderInfoDao;
    @Autowired
    OpsDepAppOrderDao opsDepAppOrderDao;
    @Autowired
    OpsVersionAppDepDao opsVersionAppDepDao;
    @Autowired
    OpsBaseEnvServerDao opsBaseEnvServerDao;
    private static final ExecutorService APP_DEPLOY_EXECUTORS = ExecutorUtils.getCachedThreadPool();

    private static volatile Map<String, Boolean> ORDER_DEPLOYING_FLAG_MAP = new ConcurrentHashMap<>();
    /*工单发布 单个应用发布超时时间 10分钟*/
    private static final int ORDER_DEPLOY_TIMEOUT_MINUTES = 10;
    /*工单发布 shell执行超时时间 3分钟*/
    private static final int SHELL_EXEC_TIMEOUT_MILLIS = 1000 * 60 * 3;

    @Async
	public void multiDeployApp(Long appEnvId, List<OpsBaseServer> assetsHosts, String header) throws UnsupportedEncodingException {
		boolean stopFlag = false;
		for (OpsBaseServer server : assetsHosts) {
			if (stopFlag) {
				OpsDepApp opsDepApp = opsDepAppDao.getAppByAppEnv(appEnvId, server.getAssetsId());
				opsDepAppDao.updateStatus(opsDepApp.getPublishStatus(), OpsDepApp.PUBLISH_STATUS_PUBLISH_STOP, opsDepApp.getPublishId());
				continue;
			}
			if (server.getAssetsType() == 2) {
				if (!deployContainerApplication(appEnvId, server.getAssetsId())) {
					stopFlag = true;
				}
			} else if (!deployApplication(appEnvId, server.getAssetsId(), header)) {
				stopFlag = true;
			}
		}
	}

	/**
	 * 单台服务器单个项目发布(容器发布)
	 *
	 * @param appEnvId
	 * @param serverId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Async
	public boolean deployContainerApplication(Long appEnvId, Long serverId) throws UnsupportedEncodingException {
		boolean isSuccess = true;
		OpsBaseServer server = opsBaseServerDao.getById(serverId);
		OpsAssembleApp appEnv = opsAssembleAppDao.getByappEnvId(appEnvId);

		OpsDepApp opsDepApp = opsDepAppDao.getAppByAppEnv(appEnvId, serverId);
		if (opsDepApp == null) {
			return true;
		}
		Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
		opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEFAULT, OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, opsDepApp.getPublishId());

		Session session = shell.initSession();
		// 部署应用
		try {
			int result = 0;
			String cmd = " kubectl delete pod -l " + appEnv.getLabels() + " --namespace=" + appEnv.getNamespace();
			Channel channel = shell.exec(cmd);
			result = shell.getExitStatus(channel);
			if (result > 0) {
				throw new RuntimeException("部署脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
			}
			opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, OpsDepApp.PUBLISH_STATUS_FINISH, opsDepApp.getPublishId());
		} catch (Exception e) {
			opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, OpsDepApp.PUBLISH_STATUS_DEPLOY_FAIL, opsDepApp.getPublishId());
			throw new RuntimeException("发布项目【" + opsDepApp.getPublishId() + "】部署失败：" + e.getMessage());
		} finally {
			opsDepAppDao.updatePublishDate(new Date(), opsDepApp.getPublishId());
			session.disconnect();
		}
		return isSuccess;
	}

    /**
     * 单台服务器单个项目发布(常规发布)
     *
     * @param appEnvId
     * @param serverId
     * @return
     * @throws UnsupportedEncodingException
     */
    @Async
    public boolean deployApplication(Long appEnvId, Long serverId, String header) throws UnsupportedEncodingException {
        boolean isSuccess = true;
        OpsBaseServer server = opsBaseServerDao.getById(serverId);
        OpsAssembleApp appEnv = opsAssembleAppDao.getByappEnvId(appEnvId);
        OpsDepApp opsDepApp = opsDepAppDao.getAppByAppEnv(appEnvId, serverId);
        OpsBaseApp baseApp = opsBaseAppDao.selectByPrimaryKey(appEnv.getAppId());
        OpsBaseAppType appType = opsBaseAppTypeDao.selectByPrimaryKey(baseApp.getAppTypeId());
        // 获取钉钉对象
        String webHook = opsBaseProDao.getWebHookByProId(appEnv.getProId());
        DingTalkUtil dingTalkUtil = new DingTalkUtil(webHook);
        // 获取环境编码
        String envCode = opsBaseEnvService.getEnvById(appEnv.getEnvId()).getEnvCode();
        // 拼接请求头，找到相应环境
        header = header + "?env_code=" + envCode;
        if (opsDepApp == null) {
            return true;
        }
        AppEnvProCodeVo appEnvProCodeVo = deployAppEnvService.getCodes(opsDepApp.getAppEnvId());
        Shell shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
        String deployBasePath = appType.getDeployPath().replace("#{NAME}", appEnv.getAppName());
        String gitUsername = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_USERNAME);
        String gitPassword = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_PASSWORD);
        String repository = appEnv.getDeployAppGitUrl().replace("http://",
                "http://" + URLEncoder.encode(gitUsername, "utf-8") + ":" + gitPassword + "@");

		opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEFAULT, OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, opsDepApp.getPublishId());
//		dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
//				getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(), null, null, DingTalkMessage.DEP_ING));
		// 日志路径
		String logPath = opsDepAppService.getDeployLogPath(serverId, appEnvProCodeVo, appEnv);
		File file = new File(logPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} else {
			try (FileWriter write = new FileWriter(file);) {
				write.write("");
				write.flush();
			} catch (IOException e) {
				log.error("清空日志文件【" + logPath + "】失败：", e);
			}
		}

		Session session = shell.initSession();
		try {
			OpsBaseApp application = opsBaseAppDao.selectByPrimaryKey(appEnv.getAppId());
			// 部署应用
			try {
				String deployShellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/git_checkout.sh";
				String tagName = opsDepApp.getDeployVersionCode();

				Map<String, String> params = new HashMap<>();
				params.put("GIT_LINK", repository);
				params.put("PROJECT_DEPLOY_DIR", deployBasePath);
				params.put("GIT_TAG", tagName);
				try (ByteArrayInputStream in = generateTamplateInputStream("shell/git_checkout.sh", params);) {
					shell.scp(in, deployShellPath);
					int result = 0;
					String cmd = "sh " + deployShellPath + " 2>&1 ";
					Channel channel = shell.exec(cmd);
					writeLogFile(file, channel);
					result = shell.getExitStatus(channel);

					if (result > 0) {
						throw new RuntimeException("部署脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
					}
				}
			} catch (Exception e) {
				opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, OpsDepApp.PUBLISH_STATUS_DEPLOY_FAIL, opsDepApp.getPublishId());
				dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
						getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(), "重新发布", header, DingTalkMessage.DEP_FAIL, e.getMessage()));

				throw new RuntimeException("发布项目【" + opsDepApp.getPublishId() + "】部署失败：" + e.getMessage());
			}

			// 重启应用
			if (opsDepApp.getAutoRestart() == 1) {
				opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, OpsDepApp.PUBLISH_STATUS_RESTART_ING, opsDepApp.getPublishId());
//				dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
//						getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(), null, null, DingTalkMessage.RE_ING));
				try {
					String cmd;
					if (application.getAppTypeId() == OpsBaseAppType.APP_TYPE_WEB) {
						String shellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/start_tomcat.sh";

						Map<String, String> params = new HashMap<>();
						params.put("CATALINA_HOME", deployBasePath.replace("webapps/ROOT/", ""));
						try (ByteArrayInputStream in = generateTamplateInputStream("shell/start_tomcat.sh", params);) {
							shell.scp(in, shellPath);
							cmd = "sh " + shellPath + " restart ";
							Channel channel = shell.exec(cmd);
							writeLogFile(file, channel);

							// 执行检测脚本,获取执行结果
							ClassPathResource resource = new ClassPathResource("shell/check_tomcat.sh");
							String checkPath = StringUtils.replace(shellPath, "start_tomcat", "check_tomcat");
                            checkPath = checkPath + " " + appEnv.getMsPort();
							shell.scp(resource.getInputStream(), checkPath);

							// 循环检测,检测总时长为3分钟,在此检测时间内,只要tomcat正常启动就返回
							int exitStatus = getExitStatus(shell, checkPath);

							if (exitStatus > 0) {
								throw new RuntimeException("重启脚本执行失败: ERROR-STATUS " + exitStatus + ", cmd: " + cmd);
							}
						}

					} else if (application.getAppTypeId() == OpsBaseAppType.APP_TYPE_JAVA) {
						String startShellName = "startup.sh";
						String startShellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/" + startShellName;
						shell.scp(new ClassPathResource("shell/" + appEnv.getAppName() + "_startup.sh").getInputStream(), startShellPath);
						String stopShellName = "shutdown.sh";
						String stopShellPath = appType.getScriptPath().replace("#{NAME}", appEnv.getAppName()) + "/" + stopShellName;
						shell.scp(new ClassPathResource("shell/" + appEnv.getAppName() + "_shutdown.sh").getInputStream(), stopShellPath);

						cmd = "sh " + stopShellPath + " 2>&1 ";
						Channel channel = shell.exec(cmd);
						writeLogFile(file, channel);
						int result = shell.getExitStatus(channel);
						if (result > 0) {
							throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
						}

						cmd = "sh " + startShellPath + " 2>&1 ";
						channel = shell.exec("sh " + startShellPath + " 2>&1 ");
						writeLogFile(file, channel);
						result = shell.getExitStatus(channel);
						if (result > 0) {
							throw new RuntimeException("启动脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
						}
					} else if (application.getAppTypeId() == OpsBaseAppType.APP_TYPE_MS) {
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
							cmd = "sh " + shellPath + " stop ";
							Channel channel = shell.exec(cmd);
							writeLogFile(file, channel);
							int result = shell.getExitStatus(channel);
							if (result > 0) {
								throw new RuntimeException("停止脚本执行失败: ERROR-STATUS " + result + ", cmd: " + cmd);
							}

							cmd = "sh " + shellPath + " start ";
							channel = shell.exec(cmd);
							writeLogFile(file, channel);

							// 微服务启动检测脚本
							ClassPathResource resource = new ClassPathResource("shell/check_microservices.sh");
							String checkPath = StringUtils.replace(shellPath, "springboot-exec", "check_microservices");

							// 执行替换脚本中的微服务端口号
							Map<String, String> portParam = new HashMap<>();
							portParam.put("MS_PORT", appEnv.getMsPort().toString());
							ByteArrayInputStream inputStream = generateTamplateInputStream(resource.getPath(), portParam);
							shell.scp(inputStream, checkPath);

							// 循环检测,检测总时长为3分钟,在此检测时间内,只要微服务正常启动就返回(检测时常与tomcat检测时常公用,都是3分钟)
							int exitStatus = getExitStatus(shell, checkPath);

							if (exitStatus > 0) {
								throw new RuntimeException("重启脚本执行失败: ERROR-STATUS " + exitStatus + ", cmd: " + cmd);
							}
						}
					}
					opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_RESTART_ING, OpsDepApp.PUBLISH_STATUS_FINISH, opsDepApp.getPublishId());
					dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
							getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(), "点击查看", header, DingTalkMessage.RE_SUCCESS, null));
				} catch (Exception e) {
					opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_RESTART_ING, OpsDepApp.PUBLISH_STATUS_RESTART_FAIL, opsDepApp.getPublishId());
					dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
							getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(), "重新发布", header, DingTalkMessage.RE_FAIL, e.getMessage()));
					throw new RuntimeException("发布项目【" + opsDepApp.getPublishId() + "】发布失败：" + e.getMessage());
				}
			} else {
				opsDepAppDao.updateStatus(OpsDepApp.PUBLISH_STATUS_DEPLOY_ING, OpsDepApp.PUBLISH_STATUS_FINISH, opsDepApp.getPublishId());
				dingTalkUtil.depAppDingTalkSend(new DingTalkMessage().
						getDepAppMessage(appEnv.getAppName(), appEnv.getEnvName(), server.getAssetsName(), opsDepApp.getDeployVersionCode(),"点击查看", header, DingTalkMessage.DEP_SUCCESS, null));
			}
		} catch (Exception e) {
			isSuccess = false;
			log.error("发布项目【" + opsDepApp.getPublishId() + "】发布失败：", e);
			appendFileLog(file, "[" + DateFormatUtils.format(DateUtils.createNow(), "yyyy-MM-dd HH:mm:ss") + "]" + e.getMessage());
		} finally {
			opsDepAppDao.updatePublishDate(new Date(), opsDepApp.getPublishId());
			appendFileLog(file, "==END==");
			session.disconnect();
		}
		return isSuccess;
	}

	private int getExitStatus(Shell shell, String checkPath) throws Exception {
		int exitStatus = 1;
		AtomicLong count = new AtomicLong(0);
		while (true) {
            long checkTime = OpsSysParameterUtil.getLongProperty(OpsSysParameterUtil.TOMCAT_CHECK_TIME);
            if (count.get() >= checkTime) {
                break;
            }
            Channel chan = shell.exec("sh " + checkPath);
            exitStatus = shell.getExitStatus(chan);
            if (exitStatus == 0) { // 启动成功
                break;
            } else if (exitStatus == 1) { // 启动不成功,循环检测,超时退出
                count.addAndGet(10);
                TimeUnit.SECONDS.sleep(10);
            }
        }
        return exitStatus;
    }

    private void writeLogFile(File file, Channel channel) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
             FileWriterWithEncoding write = new FileWriterWithEncoding(file, "utf-8", true);) {
            // 接收远程服务器执行命令的结果
            int emptyCount = 0;
            while (emptyCount < 15) {
                String line = input.readLine();
                log.debug("读取日志：" + line);
                if (StringUtils.isNotBlank(line)) {
                    write.append("[" + DateFormatUtils.format(DateUtils.createNow(), "yyyy-MM-dd HH:mm:ss") + "] " + line + "\r\n");
                    write.flush();
                    emptyCount = 0;
                } else {
                    Thread.sleep(200);
                    emptyCount++;
                }
            }
        } catch (Exception e) {
            log.error("保存部署日志失败：", e);
        }
    }

    private void appendFileLog(File file, String str) {
        try (FileWriterWithEncoding write = new FileWriterWithEncoding(file, "utf-8", true);) {
            write.append(str + "\r\n");
            write.flush();
        } catch (IOException e) {
            log.error("日志输入错误", e);
        }
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

    public void multiOrderDeployApp(OpsDepAppOrderRequest request, String header) {
        orderDeployApp(request, OrderTaskEnums.AUTO_DEPLOY_APP, header);
    }

    public void orderDeployCode(OpsDepAppOrderRequest request, String header) {
        orderDeployApp(request, OrderTaskEnums.ONLY_DEPLOY_CODE, header);
    }

    public void orderRestartService(OpsDepAppOrderRequest request, String header) {
        orderDeployApp(request, OrderTaskEnums.ONLY_RESTART_SERVICE, header);
    }

    private void orderDeployApp(OpsDepAppOrderRequest request, OrderTaskEnums orderTask, String header) {
        Long orderId = request.getOrderId();

        Map<String, List<OpsDepAppOrderInfoRequest>> deployAppMap = checkAndGroupByAppName(request);
        if (!deployAppMap.isEmpty()) {
            opsDepAppOrderDao.updateOrderStatus(orderId, OrderStatusEnums.DEPLOYING.getValue());
        }

        AtomicInteger deployedCount = new AtomicInteger(0);
        AtomicInteger deployedSuccessCount = new AtomicInteger(0);
        // 应用之间发布 并发进行
        for (Map.Entry<String, List<OpsDepAppOrderInfoRequest>> orderInfoRequest : deployAppMap.entrySet()) {
            APP_DEPLOY_EXECUTORS.execute(() -> {
                boolean appDeployResult = startOrderDeployByApp(orderId, orderInfoRequest.getValue(), orderTask, header);

                deployedCount.incrementAndGet();
                //发布成功 +1
                if (appDeployResult) {
                    deployedSuccessCount.incrementAndGet();
                }
                updateOrderStatus(deployedCount.get(), deployedSuccessCount.get(), deployAppMap.size(), orderId, orderTask);
            });
        }
    }

    private boolean startOrderDeployByApp(Long orderId, List<OpsDepAppOrderInfoRequest> appOrderInfoRequestList, OrderTaskEnums orderTask, String header) {
        ExecutorService fixedThreadPool = null;
        String orderIdAndAppEnvId = orderId + "_" + appOrderInfoRequestList.get(0).getAppEnvId();
        ORDER_DEPLOYING_FLAG_MAP.put(orderIdAndAppEnvId, true);

        int totalSize = appOrderInfoRequestList.size();
        boolean result = false;
        AtomicInteger deployedSuccessCount = new AtomicInteger(0);
        try {
            CountDownLatch countDownLatch = null;
            int deployType;
            for (OpsDepAppOrderInfoRequest orderInfo : appOrderInfoRequestList) {
                Long serverId = orderInfo.getServerId();
                String appName = orderInfo.getAppName();
                StringBuilder deployLog = orderInfo.getDeployLog();
                Boolean deployingFlag = ORDER_DEPLOYING_FLAG_MAP.get(orderIdAndAppEnvId);
                //如果在某台服务器应用发布失败，则停止当前应用在其他服务器发布，应用之间不影响
                if (deployingFlag == null || !deployingFlag) {
                    opsDepAppOrderDao.updateOrderStatus(orderId, OrderStatusEnums.DEPLOYING_PART_FAILURE.getValue());
                    clearOrderAppDeploying(orderIdAndAppEnvId);
                    appendDeployLog(deployLog, appName, orderInfo.getServerName(), "发布结束！", new Date());
                    return false;
                }
                updateAppRunningStatus(serverId, orderInfo.getAppEnvId(), ApplicationStatusEnums.APPLICATION_DEPLOYING.getValue());

                //版本发布时，发布版本状态改为 已使用(1)
                Long depAppVerId = orderInfo.getDepAppVerId();
                OpsVersionAppDep deployVersion = new OpsVersionAppDep();
                deployVersion.setDepAppVerId(depAppVerId);
                deployVersion.setUsed(1);
                opsVersionAppDepDao.updateByPrimaryKeySelective(deployVersion);

                OpsBaseServer server = opsBaseServerDao.getById(serverId);
                InnerDeployObj innerDeployObj = new InnerDeployObj();
                innerDeployObj.setServer(server);
                innerDeployObj.setOrderId(orderId);
                innerDeployObj.setAppName(appName);
                innerDeployObj.setOrderTask(orderTask);
                innerDeployObj.setAppEnvId(orderInfo.getAppEnvId());
                innerDeployObj.setDeployLog(deployLog);
                innerDeployObj.setDepAppVerId(depAppVerId);
                innerDeployObj.addDingTalkMessageHeader(header);

                //发布类型，同一应用在多台服务器并发或串行发布.
                // 0-顺序执行，1-并发执行
                deployType = orderInfo.getDeployType();
                if (deployType == 1) {
                    if (fixedThreadPool == null) {
                        fixedThreadPool = ExecutorUtils.getFixedThreadPool(totalSize);
                        countDownLatch = new CountDownLatch(totalSize);
                    }
                    CountDownLatch finalDownLatch = countDownLatch;
                    fixedThreadPool.submit(() -> {
                        deployOrderDeploy(innerDeployObj);
                        if (innerDeployObj.isAppDeploySuccess()) {
                            deployedSuccessCount.incrementAndGet();
                        }
                        finalDownLatch.countDown();

                        appendAndUpdateOrderInfoDeployLog(innerDeployObj, "发布完成！");
                    });
                } else if (orderInfo.getDeployType() == 0) {
                    deployOrderDeploy(innerDeployObj);
                    if (innerDeployObj.isAppDeploySuccess()) {
                        deployedSuccessCount.incrementAndGet();
                    }
                    appendAndUpdateOrderInfoDeployLog(innerDeployObj, "发布完成！");
                }
            }

            if (fixedThreadPool != null) {
                fixedThreadPool.shutdown();
                boolean await = countDownLatch.await(ORDER_DEPLOY_TIMEOUT_MINUTES, TimeUnit.MINUTES);
                result = await && deployedSuccessCount.get() == totalSize;
            } else {
                result = deployedSuccessCount.get() == totalSize;
            }
        } catch (Exception e) {
            log.error("Order Deploy Exception: " + e.getMessage());
        }

        clearOrderAppDeploying(orderIdAndAppEnvId);
        return result;
    }

    /**
     * 根据服务器类型调用不同的发布方式
     */
    private void deployOrderDeploy(InnerDeployObj innerDeployObj) {
        if (innerDeployObj.getServer().getAssetsType() == AssetsTypeEnums.CONTAINER.getValue()) {
            innerDeployObj.setAppDeploySuccess(false);
            appendAndUpdateOrderInfoDeployLog(innerDeployObj, "发布错误，不支持容器发布！");
            log.error("Unsupported Container Deploy！");
        } else {
            deployGeneralOrderDeploy(innerDeployObj);
        }
        String orderIdAndAppEnvId = innerDeployObj.getOrderId() + "_" + innerDeployObj.getAppEnvId();
        stopOrderAppDeploying(orderIdAndAppEnvId, innerDeployObj.isAppDeploySuccess());
    }

    /**
     * 常规工单发布
     */
    private void deployGeneralOrderDeploy(InnerDeployObj innerDeployObj) {
        OpsBaseServer server = innerDeployObj.getServer();
        Long appEnvId = innerDeployObj.getAppEnvId();
        OrderTaskEnums orderTask = innerDeployObj.getOrderTask();

        Shell shell = null;
        Integer deployStatus = null;
        Connection connection = null;
        OpsAssembleApp appEnv = null;
        OpsBaseAppType appType = null;
        try {
            appEnv = opsAssembleAppDao.getByappEnvId(appEnvId);
            OpsBaseApp baseApp = opsBaseAppDao.selectByPrimaryKey(appEnv.getAppId());
            appType = opsBaseAppTypeDao.selectByPrimaryKey(baseApp.getAppTypeId());
            OpsVersionAppDep deployVersion = opsVersionAppDepDao.selectByPrimaryKey(innerDeployObj.getDepAppVerId());

            String scriptPath = appType.getScriptPath().replace("#{NAME}", innerDeployObj.getAppName());

            // 获取钉钉对象
            String webHook = opsBaseProDao.getWebHookByProId(appEnv.getProId());
            innerDeployObj.initDingTalkMessage(webHook, appEnv.getEnvName(), deployVersion.getVersionCode());

            shell = new Shell(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
            shell.initSession();
            connection = SshUtils.getConnection(server.getSshAddress(), server.getHostAccount(), server.getHostPassword(), server.getSshPort());
            innerDeployObj.setConnection(connection);

            switch (orderTask) {
                case ONLY_DEPLOY_CODE:
                    checkDirectory(innerDeployObj, appType.getAppTypeId(), scriptPath);
                    checkoutGitBranch(innerDeployObj, appType, shell, deployVersion.getVersionCode(), appEnv.getDeployAppGitUrl());
                    checkGitTag(innerDeployObj, appType.getDeployPath(), scriptPath, deployVersion.getVersionCode());
                    return;

                case AUTO_DEPLOY_APP:
                    checkDirectory(innerDeployObj, appType.getAppTypeId(), scriptPath);
                    checkoutGitBranch(innerDeployObj, appType, shell, deployVersion.getVersionCode(), appEnv.getDeployAppGitUrl());
                    checkGitTag(innerDeployObj, appType.getDeployPath(), scriptPath, deployVersion.getVersionCode());
                    break;

                case ONLY_RESTART_SERVICE:
                default:
                    break;
            }

            runAppService(innerDeployObj, shell, appType, appEnv);
            checkAppRunningStatus(innerDeployObj, appEnv.getMsPort(), appType.getAppTypeId(), shell, scriptPath);
        } catch (Exception e) {
            String errStr = "Order Deploy Found Exception: " + e.getMessage();
            innerDeployObj.appendDeployLog(errStr);
            log.error(innerDeployObj.getDeployLog());
            innerDeployObj.setAppDeploySuccess(false);
            deployStatus = DeployStatusEnums.DEPLOY_DIRECTORY_TEST_FAILURE.getValue();
            //更新服务器上的应用状态为已停止
            updateAppRunningStatus(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getAppEnvId(), ApplicationStatusEnums.APPLICATION_STOPPED.getValue());
        } finally {
            if (appEnv != null && appType != null) {
                checkAndResetLastSuccessVersion(innerDeployObj, shell, appType, appEnv);
            }
            if (shell != null) {
                shell.closeSession();
            }
            if (connection != null) {
                connection.close();
            }
            String deployLog = innerDeployObj.getDeployLog().toString();
            updateDeployOrderInfo(server.getAssetsId(), innerDeployObj.getOrderId(), deployLog, deployStatus, null, new Date());
        }
    }

    private void checkDirectory(InnerDeployObj innerDeployObj, long appTypeId, String scriptPath) {
        updateDeployLogAndDeployStatus(innerDeployObj, DeployStatusEnums.DEPLOY_DIRECTORY_TESTING);
        boolean stepPassed = false;
        try {
            String shellName = "check_dir.sh";
            String shellPath = scriptPath + shellName + " " + innerDeployObj.getAppName() + " " + appTypeId;
            String cmd = "sh " + shellPath;
            Integer exitStatus = scpScriptAndExec(innerDeployObj, shellName, scriptPath, cmd);
            stepPassed = exitStatus == 0;
        } finally {
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_DIRECTORY_TESTING, stepPassed);
        }
    }

    private void checkoutGitBranch(InnerDeployObj innerDeployObj, OpsBaseAppType appType, Shell shell, String deployVersion, String deployAppGitUrl) {
        if (checkStepNotPassed(innerDeployObj)) {
            return;
        }

        String shellName = "git_checkout.sh";
        String appName = innerDeployObj.getAppName();
        boolean stepPassed = false;
        String deployBasePath = appType.getDeployPath().replace("#{NAME}", appName);
        // 部署应用
        try {
            String scriptPath = appType.getScriptPath().replace("#{NAME}", appName) + shellName;
            String gitUsername = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_USERNAME);
            String gitPassword = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_PASSWORD);
            String repository = deployAppGitUrl.replace("http://", "http://" + URLEncoder.encode(gitUsername, "utf-8") + ":" + gitPassword + "@");

            Map<String, String> params = new HashMap<>(3);
            params.put("GIT_LINK", repository);
            params.put("PROJECT_DEPLOY_DIR", deployBasePath);
            params.put("GIT_TAG", deployVersion);
            Channel scp = null;
            try (ByteArrayInputStream in = generateTamplateInputStream("shell/" + shellName, params)) {
                scp = shell.scp(in, scriptPath);
                int exitStatus = execAndGetExecResult(innerDeployObj, "sh " + scriptPath + " 2>&1");
                stepPassed = exitStatus == 0;
            } finally {
                if (scp != null) {
                    scp.disconnect();
                }
            }
        } catch (Exception e) {
            String errStr = "Checkout Git Branch Found Exception: " + e.getMessage();
            log.error(errStr);
            innerDeployObj.appendDeployLog(errStr);
        } finally {
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_GIT_CHECKOUTING, stepPassed);
        }
    }

    private void checkGitTag(InnerDeployObj innerDeployObj, String deployPath, String remoteScriptPath, String versionCode) {
        if (checkStepNotPassed(innerDeployObj)) {
            return;
        }

        boolean stepPassed = false;
        String shellName = "check_git_tag.sh";
        String remotePath = remoteScriptPath + shellName;
        try {
            String cmd = "sh " + remotePath + " " + deployPath.replace("#{NAME}", innerDeployObj.getAppName()) + " " + versionCode;
            int exitStatus = scpScriptAndExec(innerDeployObj, shellName, remoteScriptPath, cmd);
            stepPassed = exitStatus == 0;
        } finally {
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_VERSION_TESTING, stepPassed);
        }
    }

    private void runAppService(InnerDeployObj innerDeployObj, Shell shell, OpsBaseAppType appType, OpsAssembleApp appEnv) {
        if (checkStepNotPassed(innerDeployObj)) {
            return;
        }
        checkShellConnectStatus(shell);

        boolean stepPassed = false;
        String appName = appEnv.getAppName();
        Channel scp = null;
        Channel scp2 = null;
        try {
            String cmd;
            int exitStatus;
            if (appType.getAppTypeId() == OpsBaseAppType.APP_TYPE_WEB) {
                String shellPath = appType.getScriptPath().replace("#{NAME}", appName) + "start_tomcat.sh";

                String deployBasePath = appType.getDeployPath().replace("#{NAME}", appName);
                Map<String, String> params = new HashMap<>(1);
                params.put("CATALINA_HOME", deployBasePath.replace("webapps/ROOT/", ""));
                try (ByteArrayInputStream in = generateTamplateInputStream("shell/start_tomcat.sh", params)) {
                    scp = shell.scp(in, shellPath);
                    cmd = "sh " + shellPath + " restart";
                    exitStatus = execAndGetExecResult(innerDeployObj, cmd);
                    stepPassed = exitStatus == 0;
                }
            } else if (appType.getAppTypeId() == OpsBaseAppType.APP_TYPE_JAVA) {
                String startShellName = "startup.sh";
                String startShellPath = appType.getScriptPath().replace("#{NAME}", appName) + startShellName;
                String stopShellName = "shutdown.sh";
                String stopShellPath = appType.getScriptPath().replace("#{NAME}", appName) + stopShellName;
                String shellName;
                String startCmd;
                if (appName.equalsIgnoreCase("message")) {
                    shellName = "shell/message";
                    startCmd = "sh " + startShellPath;
                } else {
                    shellName = "shell/taskserver";
                    startCmd = "sh " + startShellPath + " " + appName;
                }
                scp = shell.scp(new ClassPathResource(shellName + "_startup.sh").getInputStream(), startShellPath);
                scp2 = shell.scp(new ClassPathResource(shellName + "_shutdown.sh").getInputStream(), stopShellPath);

                cmd = "sh " + stopShellPath;
                exitStatus = execAndGetExecResult(innerDeployObj, cmd);
                stepPassed = exitStatus == 0;
                if (!stepPassed) {
                    String errStr = "停止脚本执行失败: ERROR-STATUS " + exitStatus + ", script: " + stopShellPath;
                    log.error(errStr);
                    innerDeployObj.appendDeployLog(errStr);
                } else {
                    //停止成功执行启动脚本
                    exitStatus = execAndGetExecResult(innerDeployObj, startCmd);
                    stepPassed = exitStatus == 0;
                    if (!stepPassed) {
                        String errStr = "启动脚本执行失败: ERROR-STATUS " + exitStatus + ", script: " + startShellPath;
                        log.error(errStr);
                        innerDeployObj.appendDeployLog(errStr);
                    }
                }
            } else if (appType.getAppTypeId() == OpsBaseAppType.APP_TYPE_MS) {
                String shellName = "springboot-exec.sh";
                String shellPath = appType.getScriptPath().replace("#{NAME}", appName) + "/" + shellName;
                Map<String, String> params = new HashMap<>(9);
                params.put("INNER_IP", innerDeployObj.getServer().getInnerIp());
                params.put("MS_PORT", appEnv.getMsPort() + "");
                params.put("MS_EUREKA_ADDRESS", appEnv.getMsEurekaAddress());
                OpsBaseProfileType profileType = opsBaseProfileTypeDao.selectByPrimaryKey(appEnv.getMsProfileTypeId());
                params.put("MS_PROFILE_CODE", profileType.getProfileTypeName());
                OpsBaseZoneType zoneType = opsBaseZoneTypeDao.selectByPrimaryKey(appEnv.getMsZoneTypeId());
                params.put("MS_ZONE_CODE", zoneType.getZoneTypeName());
                OpsBaseRegionType regionType = opsBaseRegionTypeDao.selectByPrimaryKey(appEnv.getMsRegionTypeId());
                params.put("EUREKA_REGION_CODE", regionType.getRegionTypeName());
                params.put("CONFIG_URI", appEnv.getMsConfigUrl());
                params.put("APP_NAME", appName);
                params.put("LOGGING_CONFIG", appType.getDeployPath().replace("#{NAME}", appName) + "/config/logback-spring.xml");
                try (ByteArrayInputStream in = generateTamplateInputStream("shell/" + shellName, params)) {
                    scp = shell.scp(in, shellPath);
                    cmd = "sh " + shellPath + " stop";
                    exitStatus = execAndGetExecResult(innerDeployObj, cmd);
                    stepPassed = exitStatus == 0;
                    if (!stepPassed) {
                        String errStr = "停止脚本执行失败: ERROR-STATUS " + exitStatus + ", script: " + shellPath;
                        log.error(errStr);
                        innerDeployObj.appendDeployLog(errStr);
                    } else {
                        cmd = "sh " + shellPath + " start";
                        exitStatus = execAndGetExecResult(innerDeployObj, cmd);
                        stepPassed = exitStatus == 0;
                        if (!stepPassed) {
                            String errStr = "启动脚本执行失败: ERROR-STATUS " + exitStatus + ", script: " + shellPath;
                            log.error(errStr);
                            innerDeployObj.appendDeployLog(errStr);
                        }
                    }
                }
            } else if (appType.getAppTypeId() == OpsBaseAppType.APP_TYPE_STATIC) {
                //STATIC应用静态资源无需启动
                stepPassed = true;
            } else {
                String errStr = "Unsupported AppType and appTypeId is " + appType.getAppTypeId();
                log.error(errStr);
                stepPassed = false;
                innerDeployObj.appendDeployLog(errStr);
            }
        } catch (Exception e) {
            String errStr = "Run Application Service Found Exception: " + e.getMessage();
            log.error(errStr);
            innerDeployObj.appendDeployLog(errStr);
        } finally {
            if (scp != null) {
                scp.disconnect();
            }
            if (scp2 != null) {
                scp2.disconnect();
            }
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_SERVICE_STARTING, stepPassed);
        }
    }

    /**
     * 检测应用运行状态
     * 应用类型是 JAVA 或 STATIC 的暂不检测，直接默认成功
     */
    private void checkAppRunningStatus(InnerDeployObj innerDeployObj, int msPort, long appTypeId, Shell shell, String scriptPath) {
        if (checkStepNotPassed(innerDeployObj)) {
            return;
        }

        checkShellConnectStatus(shell);

        String checkShellName;
        boolean appTypeIsStatic = appTypeId == OpsBaseAppType.APP_TYPE_STATIC;
        if (appTypeId == OpsBaseAppType.APP_TYPE_MS) {
            checkShellName = "check_microservices.sh";
        } else if (appTypeId == OpsBaseAppType.APP_TYPE_WEB || appTypeIsStatic) {
            checkShellName = "check_tomcat.sh";
        } else {
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_STATUS_TESTING, true);
            return;
        }

        boolean stepPassed = false;
        try {
            // 执行检测脚本,获取执行结果
            scriptPath = scriptPath + checkShellName;
            ClassPathResource resource = new ClassPathResource("shell/" + checkShellName);
            shell.scp(resource.getInputStream(), scriptPath);

            String cmd = "sh " + (appTypeIsStatic ? scriptPath : scriptPath + " " + msPort );
            int exitStatus;
            AtomicLong count = new AtomicLong(0);
            long checkTime = OpsSysParameterUtil.getLongProperty(OpsSysParameterUtil.TOMCAT_CHECK_TIME);
            long tenMilliSeconds = 10000L;
            while (true) {
                long start = System.currentTimeMillis();
                if (count.get() >= checkTime) {
                    break;
                }
                exitStatus = execAndGetExecResult(innerDeployObj, cmd);
                // 启动成功
                if (exitStatus == 0) {
                    stepPassed = true;
                    break;
                } else if (exitStatus == 1) {
                    long cost = System.currentTimeMillis() - start;
                    long sleepTime;
                    if (cost >= tenMilliSeconds) {
                        sleepTime = 0;
                    } else {
                        sleepTime = tenMilliSeconds - cost;
                    }
                    updateOrderInfoDeployLog(innerDeployObj);
                    // 启动不成功,循环检测,超时退出
                    count.addAndGet(10);
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                }
            }
        } catch (Exception e) {
            String errStr = "Check Application Running Status Found Exception: " + e.getMessage();
            log.error(errStr);
            innerDeployObj.appendDeployLog(errStr);
        } finally {
            //应用状态检查失败时标记回退并重启
            markStepStatus(innerDeployObj, DeployStatusEnums.DEPLOY_STATUS_TESTING, stepPassed);
            innerDeployObj.setResetFlag(!stepPassed);
        }
    }

    /**
     * 检查是否需要回退版，若需要则回退到上一次成功发布的版本，重启服务
     * 目录检查后的每个步骤若执行失败，则尝试进行回退
     */
    private void checkAndResetLastSuccessVersion(InnerDeployObj innerDeployObj, Shell shell, OpsBaseAppType appType, OpsAssembleApp appEnv) {
        if (innerDeployObj.isResetFlag()) {
            //版本回退时，发布版本状态改为 未使用(0)
            Long depAppVerId = innerDeployObj.getDepAppVerId();
            OpsVersionAppDep deployVersion = new OpsVersionAppDep();
            deployVersion.setDepAppVerId(depAppVerId);
            deployVersion.setUsed(0);
            opsVersionAppDepDao.updateByPrimaryKeySelective(deployVersion);

            String msg;
            try {
                checkShellConnectStatus(shell);
                String appName = innerDeployObj.getAppName();
                String lastSuccessVersion = opsBaseEnvServerDao.queryLastSuccessVersion(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getAppEnvId());
                log.info(appName + " on " + innerDeployObj.getServer().getSshAddress() + " 回退版本 --> " + lastSuccessVersion);
                if (StringUtils.isNotBlank(lastSuccessVersion)) {
                    appendAndUpdateOrderInfoDeployLog(innerDeployObj, " 回退版本 --> " + lastSuccessVersion);

                    boolean needRestart = innerDeployObj.getDeployStatus() == DeployStatusEnums.DEPLOY_SERVICE_START_FAILURE
                            || innerDeployObj.getDeployStatus() == DeployStatusEnums.DEPLOY_STATUS_TEST_FAILURE;

                    String scriptPath = appType.getScriptPath().replace("#{NAME}", appName);
                    String deployPath = appType.getDeployPath().replace("#{NAME}", appName);
                    String shellName = "deploy_revert.sh";
                    String cmd = "sh " + scriptPath + shellName + " " + lastSuccessVersion + " " + deployPath + " 2>&1";
                    Integer exitStatus = scpScriptAndExec(innerDeployObj, shellName, scriptPath, cmd);
                    boolean resetResult = exitStatus == 0;
                    innerDeployObj.appendDeployLogFormat(" 回退版本" + (resetResult ? "成功！" : "失败！"));
                    innerDeployObj.setDeployStatus(DeployStatusEnums.DEPLOY_VERSION_TESTING);
                    if (resetResult) {
                        appendAndUpdateOrderInfoDeployLog(innerDeployObj, DeployStatusEnums.DEPLOY_VERSION_TESTING.getDescription());
                        checkGitTag(innerDeployObj, deployPath, scriptPath, lastSuccessVersion);

                        if (needRestart) {
                            runAppService(innerDeployObj, shell, appType, appEnv);
                            checkAppRunningStatus(innerDeployObj, appEnv.getMsPort(), appType.getAppTypeId(), shell, scriptPath);
                        }
                    }
                } else {
                    appendAndUpdateOrderInfoDeployLog(innerDeployObj, "没有找到可回退版本！");
                }
            } catch (Exception e) {
                msg = "Reset Version Exception, e: " + e.getMessage();
                log.error(msg);
                appendAndUpdateOrderInfoDeployLog(innerDeployObj, msg);
            } finally {
                shell.closeSession();
            }
        }
    }

    /**
     * 将发布应用请求按照应用进行分组，使不同的应用发布任务并发进行
     */
    private Map<String, List<OpsDepAppOrderInfoRequest>> checkAndGroupByAppName(OpsDepAppOrderRequest request) {
        Map<String, List<OpsDepAppOrderInfoRequest>> map = new HashMap<>(request.getDeployOrderInfos().size());
        Long orderId = request.getOrderId();
        List<Long> deployingServers = opsDepAppOrderInfoDao.queryDeployingServersByOrderId(orderId, DeployStatusEnums.DEPLOY_STATUS_TESTING.getValue());

        for (OpsDepAppOrderInfoRequest orderInfo : request.getDeployOrderInfos()) {
            StringBuilder deployLog = new StringBuilder();
            String appName = orderInfo.getAppName();
            Date startDate = new Date();
            if (deployingServers.indexOf(orderInfo.getServerId()) == -1) {
                List<OpsDepAppOrderInfoRequest> orderInfoRequestList = map.get(appName);
                if (orderInfoRequestList == null) {
                    orderInfoRequestList = new ArrayList<>();
                }
                orderInfoRequestList.add(orderInfo);
                map.put(appName, orderInfoRequestList);
                appendDeployLog(deployLog, appName, orderInfo.getServerIp(), "准备发布", startDate);
                log.info(deployLog);
            } else {
                appendDeployLog(deployLog, appName, orderInfo.getServerIp(), "重复发布！", startDate);
                log.warn(deployLog.toString());
            }
            orderInfo.setDeployLog(deployLog.append("\n"));
            //记录工单任务发布开始
            updateDeployOrderInfo(orderInfo.getServerId(), orderId, deployLog.toString(), null, startDate, null);
        }
        return map;
    }

    private void appendDeployLog(StringBuilder deployLog, String appName, String sshAddress, String msg, Date startDate) {
        String nowDate = DateFormatUtils.format(startDate, "yyyy-MM-dd HH:mm:ss") + " ";
        deployLog.append(nowDate).append(appName).append(" on ").append(sshAddress).append(" ").append(msg);
    }

    private void stopOrderAppDeploying(String orderIdAndAppEnvId, boolean deployingFlag) {
        if (!deployingFlag) {
            ORDER_DEPLOYING_FLAG_MAP.put(orderIdAndAppEnvId, false);
        }
    }

    private void clearOrderAppDeploying(String orderIdAndAppEnvId) {
        ORDER_DEPLOYING_FLAG_MAP.remove(orderIdAndAppEnvId);
    }

    private boolean checkStepNotPassed(InnerDeployObj innerDeployObj) {
        boolean notPassed = innerDeployObj.getDeployStatus().getValue() < 0;
        innerDeployObj.setResetFlag(notPassed);
        return notPassed;
    }

    private Integer scpScriptAndExec(InnerDeployObj innerDeployObj, String shellName, String remoteScriptAbsPath, String cmd) {
        Integer exitStatus = 1;
        ClassPathResource resource = new ClassPathResource("shell/" + shellName);
        String scpResult = SshUtils.ethzScp(innerDeployObj.getConnection(), resource, shellName, remoteScriptAbsPath);
        if (!scpResult.isEmpty()) {
            appendAndUpdateOrderInfoDeployLog(innerDeployObj, scpResult);
        } else {
            exitStatus = execAndGetExecResult(innerDeployObj, cmd);
        }
        return exitStatus;
    }

    private Integer execAndGetExecResult(InnerDeployObj innerDeployObj, String cmd) {
        StringBuilder execResult = new StringBuilder();
        Integer exitStatus = SshUtils.ethzExecute(innerDeployObj.getConnection(), cmd, execResult, SHELL_EXEC_TIMEOUT_MILLIS);
        innerDeployObj.getDeployLog().append(execResult.toString());
        return exitStatus;
    }

    /**
     * 更新工单状态
     */
    private void updateOrderStatus(int deployedCount, int deployedSuccessCount, int total, Long orderId, OrderTaskEnums orderTask) {
        Integer orderStatus = null;
        if (deployedSuccessCount == 0) {
            orderStatus = OrderStatusEnums.DEPLOY_COMPLETED_ALL_FAILURE.getValue();
        } else if (deployedSuccessCount > 0 && deployedSuccessCount < deployedCount) {
            orderStatus = OrderStatusEnums.DEPLOYING_PART_FAILURE.getValue();
        } else if (deployedSuccessCount == total && orderTask != OrderTaskEnums.ONLY_DEPLOY_CODE) {
            orderStatus = OrderStatusEnums.DEPLOY_COMPLETED_ALL_SUCCESS.getValue();
        }
        if (orderStatus != null) {
            opsDepAppOrderDao.updateOrderStatus(orderId, orderStatus);
        }
    }

    /**
     * 更新服务器的应用运行状态
     */
    private void updateAppRunningStatus(Long serverId, Long appEnvId, int appRunStatus) {
        opsBaseEnvServerDao.updateAppStatusByHostAndAppEnvId(serverId, appEnvId, appRunStatus);
    }

    /**
     * 更新工单发布信息中应用的发布状态
     */
    private void appendAndUpdateOrderInfoDeployLog(InnerDeployObj innerDeployObj, String msg) {
        innerDeployObj.appendDeployLogFormat(msg);
        updateOrderInfoDeployLog(innerDeployObj);
    }

    private void updateOrderInfoDeployLog(InnerDeployObj innerDeployObj) {
        updateDeployOrderInfo(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getOrderId(), innerDeployObj.getDeployLog().toString(), null, null, null);
    }

    private void updateDeployLogAndDeployStatus(InnerDeployObj innerDeployObj, DeployStatusEnums deployStatus) {
        String deployLog = innerDeployObj.appendDeployLogFormat(deployStatus.getDescription()).toString();
        updateDeployOrderInfo(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getOrderId(), deployLog, deployStatus.getValue(), null, null);
    }

    private void updateDeployOrderInfo(Long serverId, Long orderId, String deployLog, Integer deployStatus, Date deployStartDate, Date deployEndDate) {
        int length = deployLog.length();
        if (length >= Short.MAX_VALUE) {
            deployLog = deployLog.substring(0, Short.MAX_VALUE - 1);
        }
        OpsDepAppOrderInfoResponse orderInfoVo = new OpsDepAppOrderInfoResponse();
        orderInfoVo.setServerId(serverId);
        orderInfoVo.setOrderId(orderId);
        orderInfoVo.setDeployStatus(deployStatus);
        orderInfoVo.setStartDate(deployStartDate);
        orderInfoVo.setEndDate(deployEndDate);
        orderInfoVo.setDeployLog(deployLog);
        opsDepAppOrderInfoDao.updateByOrderAndServerIdSelective(orderInfoVo);
    }

    /**
     * 标记发布过程状态
     *
     * @param deployStatus 发布过程状态 必须为正数
     * @param stepPassed   发布过程结果 true 代表成功， false表示失败
     */
    private void markStepStatus(InnerDeployObj innerDeployObj, DeployStatusEnums deployStatus, boolean stepPassed) {
        if (stepPassed) {
            markStepSuccess(innerDeployObj, deployStatus);
        } else {
            deployStatus = DeployStatusEnums.getDeployStatus(0 - deployStatus.getValue());
            markStepFailure(innerDeployObj, deployStatus);
        }
    }

    private void markStepFailure(InnerDeployObj innerDeployObj, DeployStatusEnums deployStatus) {
        innerDeployObj.setDeployStatus(deployStatus);
        innerDeployObj.setAppDeploySuccess(false);

        if (innerDeployObj.isResetFlag()) {
            appendAndUpdateOrderInfoDeployLog(innerDeployObj, deployStatus.getDescription());
        } else {
            updateDeployLogAndDeployStatus(innerDeployObj, deployStatus);
        }
        //发布失败时更新服务器状态为 应用已停止
        updateAppRunningStatus(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getAppEnvId(), ApplicationStatusEnums.APPLICATION_STOPPED.getValue());
        log.error(innerDeployObj.getAppName() + " on " + innerDeployObj.getServer().getSshAddress() + " " + deployStatus.getDescription());

        //钉钉通知
        innerDeployObj.sendDingTalkMessage(deployStatus.getDescription());
    }

    private void markStepSuccess(InnerDeployObj innerDeployObj, DeployStatusEnums deployStatus) {
        innerDeployObj.setDeployStatus(deployStatus);
        innerDeployObj.setAppDeploySuccess(true);
        int deployStatusValue = deployStatus.getValue();

        boolean deployCompleted = false;
        if (deployStatus.equals(DeployStatusEnums.DEPLOY_STATUS_TESTING)) {
            deployCompleted = true;
            deployStatusValue = DeployStatusEnums.DEPLOY_SUCCESS.getValue();

            //发布成功后更新服务器状态为 应用运行中
            updateAppRunningStatus(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getAppEnvId(), ApplicationStatusEnums.APPLICATION_RUNNING.getValue());

            //更新服务器环境中的 一次成功发布版本
            String versionCode = innerDeployObj.dingTalkMessage.getVersionCode();
            opsBaseEnvServerDao.updateVersionCodeByHostAndAppEnvId(innerDeployObj.getServer().getAssetsId(), innerDeployObj.getAppEnvId(), versionCode);
        } else if (innerDeployObj.getOrderTask().equals(OrderTaskEnums.ONLY_DEPLOY_CODE) && deployStatus.equals(DeployStatusEnums.DEPLOY_VERSION_TESTING)) {
            deployCompleted = true;
            deployStatusValue = DeployStatusEnums.DEPLOY_CODE_SUCCESS.getValue();
        } else {
            deployStatusValue += 50;
        }
        deployStatus = DeployStatusEnums.getDeployStatus(deployStatusValue);
        if (innerDeployObj.isResetFlag()) {
            appendAndUpdateOrderInfoDeployLog(innerDeployObj, deployStatus.getDescription());
        } else {
            updateDeployLogAndDeployStatus(innerDeployObj, deployStatus);
        }

        if (deployCompleted) {
            //钉钉通知
            innerDeployObj.sendDingTalkMessage(deployStatus.getDescription());
        }
        log.info(innerDeployObj.getAppName() + " on " + innerDeployObj.getServer().getSshAddress() + " " + deployStatus.getDescription());
    }

    private void checkShellConnectStatus(Shell shell) {
        if (!shell.getSession().isConnected()) {
            shell.initSession();
        }
    }


    /**
     * 工单发布应用辅助类
     */
    private class InnerDeployObj {
        private final int deployLogAppendMaxLength = 1024 * 4;
        /**
         * 以应用为组，应用组发布结果 true表示该应用在所有服务器发布成功，false表示存在发布失败的服务器
         */
        private boolean appDeploySuccess = true;
        /**
         * 版本回退标志
         */
        private boolean resetFlag = false;
        /**
         * 发布状态
         */
        private DeployStatusEnums deployStatus;
        /**
         * 工单Id
         */
        private Long orderId;
        /**
         * 应用环境Id
         */
        private Long appEnvId;
        /**
         * 发布版本Id
         */
        private Long depAppVerId;
        /**
         * 应用名称
         */
        private String appName;
        /**
         * 应用发布所在的服务器
         */
        private OpsBaseServer server;
        /**
         * 发布工单任务类型
         */
        private OrderTaskEnums orderTask;
        /**
         * 请求消息头，用于钉钉通知
         */
        private StringBuilder deployLog;

        private Connection connection;

        private DingTalkMessage dingTalkMessage;

        private DingTalkUtil dingTalkUtil;

        boolean isAppDeploySuccess() {
            return appDeploySuccess;
        }

        void setAppDeploySuccess(boolean appDeploySuccess) {
            this.appDeploySuccess = appDeploySuccess;
        }

        Long getAppEnvId() {
            return appEnvId;
        }

        void setAppEnvId(Long appEnvId) {
            this.appEnvId = appEnvId;
        }

        OpsBaseServer getServer() {
            return server;
        }

        void setServer(OpsBaseServer server) {
            this.server = server;
        }

        Long getOrderId() {
            return orderId;
        }

        void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        String getAppName() {
            return appName;
        }

        void setAppName(String appName) {
            this.appName = appName;
        }

        OrderTaskEnums getOrderTask() {
            return orderTask;
        }

        void setOrderTask(OrderTaskEnums orderTaskEnums) {
            this.orderTask = orderTaskEnums;
        }

        StringBuilder getDeployLog() {
            return deployLog;
        }

        void setDeployLog(StringBuilder deployLog) {
            this.deployLog = deployLog;
        }

        StringBuilder appendDeployLogFormat(String msg) {
            String currentTime = DateFormatUtils.format(DateUtils.createNow(), "yyyy-MM-dd HH:mm:ss") + " ";
            msg = currentTime + appName + " on " + server.getSshAddress() + " " + msg;
            return appendDeployLog(msg);
        }

        StringBuilder appendDeployLog(String msg) {
            msg = msg.replaceAll("\"", "\'");
            if (msg.length() >= deployLogAppendMaxLength) {
                msg = msg.substring(0, msg.indexOf("\n", 128) + 1);
            }
            return this.deployLog.append("\r\n").append(msg).append("\r\n");
        }

        Long getDepAppVerId() {
            return depAppVerId;
        }

        void setDepAppVerId(Long depAppVerId) {
            this.depAppVerId = depAppVerId;
        }

        Connection getConnection() {
            return connection;
        }

        void setConnection(Connection connection) {
            this.connection = connection;
        }

        void addDingTalkMessageHeader(String header) {
            this.dingTalkMessage = new DingTalkMessage();
            dingTalkMessage.setUrl(header);
        }

        void initDingTalkMessage(String webHook, String envName, String deployVersion) {
            this.dingTalkUtil = new DingTalkUtil(webHook);
            this.dingTalkMessage.setAppName(appName);
            dingTalkMessage.setVersionCode(deployVersion);
            dingTalkMessage.setEnvName(envName);
            dingTalkMessage.setMotif(orderTask.desc);
            dingTalkMessage.setServerName(server.getAssetsName());
        }

        void sendDingTalkMessage(String message) {
            String operateResult = getOrderTask().desc;
            if (isAppDeploySuccess()) {
                operateResult += "成功！";
            } else {
                operateResult += "失败！";
                dingTalkMessage.setFailMessage(message);
            }
            dingTalkMessage.setOperation(operateResult);
            dingTalkUtil.depAppDingTalkSend(dingTalkMessage);
        }

        boolean isResetFlag() {
            return resetFlag;
        }

        void setResetFlag(boolean resetFlag) {
            this.resetFlag = resetFlag;
        }

        DeployStatusEnums getDeployStatus() {
            return deployStatus;
        }

        void setDeployStatus(DeployStatusEnums deployStatus) {
            this.deployStatus = deployStatus;
        }
    }

    private enum OrderTaskEnums {
        /**
         * 发布发布任务类型枚举
         */
        AUTO_DEPLOY_APP("工单任务-自动发布"),
        ONLY_DEPLOY_CODE("工单任务-部署代码"),
        ONLY_RESTART_SERVICE("工单任务-重启服务"),;

        String desc;

        OrderTaskEnums(String desc) {
            this.desc = desc;
        }
    }
}
