package com.bee.devops.admin.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.utils.LoggerUtil;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.ops.OpsSysLog;
import com.bee.devops.admin.core.common.service.ops.OpsSysLogService;

/**
 * 操作日志切面
 * @author Administrator
 *
 */
@Aspect
@Order(1)
@Component
public class LogAopAspect {
	final static Logger logger = Logger.getLogger(LogAopAspect.class);
	private static ThreadLocal<AdminUser> adminUser = new ThreadLocal<>();
	@Autowired
	OpsSysLogService opsSysLogService;
	
	public static void setAdminUser(AdminUser user) {
		adminUser.set(user);
	}

	protected AdminUser getAdminUser() {
		return adminUser.get();
	}

	/**
	 * 优化pointcut,将切点设置在使用了LogAnnotation注解的方法上
	 * @author Yang Chunhai
	 */
	@Pointcut("@annotation(com.bee.devops.admin.common.annotation.LogAnnotation)")
	private void pointCutMethod() {
	}

	/**
	 * 记录操作日志
	 * @author Yang Chunhai	 
	 * @param joinPoint
	 */
	@After("pointCutMethod()")
	public void recordLog(JoinPoint joinPoint) {
		// 初始化日志类
		OpsSysLog log = new OpsSysLog();
		// 拿到用户信息
		AdminUser adminUser = getAdminUser();
		if (adminUser == null) {
			logger.warn("用户信息为空");
		} else {
			log.setUserId(adminUser.getAdminUserId());
			log.setUserName(adminUser.getRealName());
		}
		try {
			Map<String, String> map = getLogMark(joinPoint);
			String module = map.get(LoggerUtil.LOG_MODULE);
			String methods = map.get(LoggerUtil.LOG_METHODS);
			String operation = "用户【"+adminUser.getRealName()+"】在【"+module+"】模块进行了【"+methods+"】操作";
			String details = LoggerUtil.getDescription();
			if(details.indexOf("Ex:") > -1) {
				log.setIsSuccess(1);
			} else {
				log.setIsSuccess(0);
			}
			log.setOperation(operation);
			log.setTypeName(module);
			log.setDetails(details);
			if(opsSysLogService.insert(log) == 0) {
			logger.error("【"+module+"】日志未开启");
			}
		} catch (ClassNotFoundException c) {
			logger.error(c.getMessage());
		} catch (Exception e) {
			logger.error("插入日志异常:"+ e.getMessage());
		}
	}

	/**
	 * 拿到注解上的字段
	 * @author Yang Chunhai	 
	 * @param joinPoint
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Map<String, String> getLogMark(JoinPoint joinPoint) throws ClassNotFoundException {
		Map<String, String> map = new HashMap<>();
		String methodName = joinPoint.getSignature().getName();
		Class<?> targetClass = joinPoint.getTarget().getClass();
		LogAnnotation annotation = targetClass.getAnnotation(LogAnnotation.class);
		map.put(LoggerUtil.LOG_MODULE, annotation.module());
		Method[] methods = targetClass.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
				map.put(LoggerUtil.LOG_METHODS, logAnnotation.methods());
			}
		}
		return map;
	}
}