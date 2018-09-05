package com.bee.devops.admin.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.core.common.entity.ops.OpsSysParameter;
import com.bee.devops.admin.core.common.service.ops.OpsSysParameterService;

/**
 * 系统配置工具类
 *
 * @author Roc created on 2018/8/2
 */
@Component
public class OpsSysParameterUtil implements ApplicationContextAware {
	/**
	 * 登陆参数
	 */
	private static final String LOGIN_SINGING = "loginSinging";
	private static final String LOGIN_TIMEOUT = "loginTimeout";
	public static final String LOGIN_EXPIRE_TIME = "loginExpireTime";
	/**
	 * 路径参数
	 */
	public static final String APP_VERSION_HOME_PATH = "applicationVersionHomePath";
	public static final String CONFIG_VERSION_HOME_PATH = "configVersionHomePath";
	public static final String DEPLOY_VERSION_HOME_PATH = "deployVersionHomePath";
	public static final String DEPLOY_LOG_PATH = "deployLogPath";
	/**
	 * GITLAB参数
	 */
	private static final String GITLAB_REPO_BRANCH = "gitlabRepoBranch";
	public static final String GITLAB_USERNAME = "gitlabUsername";
	public static final String GITLAB_PASSWORD = "gitlabPassword";
	/**
	 * KSC参数
	 */
	private static final String KSC_ACCESS_KEY = "kscAccessKey";
	private static final String KSC_PRIVATE_KEY = "kscPrivateKey";
	private static final String KSC_ENDPOINT = "kscEndpoint";
	/**
	 * 备份日志路径
	 */
	private static final String BACK_LOG_PATH = "backLogPath";
	/**
	 * TOMCAT启动检测时间
	 */
	public static final String TOMCAT_CHECK_TIME = "tomcatCheckTime";

	private static final Map<String, String> OPS_PARAMETER_MAP = new ConcurrentHashMap<>();

	public static Map<String, String> getOpsParameterMap() {
		return OPS_PARAMETER_MAP;
	}

	public static void cacheSysParameter(String key, String prop) {
		OPS_PARAMETER_MAP.put(key, prop);
	}

	public static String getStringProperty(String key) {
		String prop = OPS_PARAMETER_MAP.get(key);
		if (StringUtils.isBlank(prop)) {
			OpsSysParameterService service = SpringUtil.getBean(OpsSysParameterService.class);
			if (service != null) {
				OpsSysParameter param = service.getSystParameterByKey(key);
				if (param != null) {
					prop = param.getSysParameterValue();
					cacheSysParameter(key, prop);
				}
			}
		}
		return prop;
	}

	public static long getLongProperty(String key) {
		return NumberUtils.toLong(getStringProperty(key));
	}

	public static String getLoginSigning() {
		return getStringProperty(LOGIN_SINGING);
	}

	public static long getLoginTimeoutSeconds() {
		return NumberUtils.toLong(getStringProperty(LOGIN_TIMEOUT));
	}

	public static String getGitBranch() {
		return getStringProperty(GITLAB_REPO_BRANCH);
	}

	public static String getKscAccessKey() {
		return getStringProperty(KSC_ACCESS_KEY);
	}

	public static String getKscPrivateKey() {
		return getStringProperty(KSC_PRIVATE_KEY);
	}

	public static String getKscEndpoint() {
		return getStringProperty(KSC_ENDPOINT);
	}

	public static String getBackLogPath() {
		return getStringProperty(BACK_LOG_PATH);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		OpsSysParameterService opsSysParameterService = applicationContext.getBean(OpsSysParameterService.class);
		opsSysParameterService.getAllSysParameters();
	}
}
