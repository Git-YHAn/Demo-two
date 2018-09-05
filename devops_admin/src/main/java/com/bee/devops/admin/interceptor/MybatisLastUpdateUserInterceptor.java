package com.bee.devops.admin.interceptor;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bee.devops.admin.core.common.entity.admin.AdminUser;


/**
 * 用于对所有更新的Mybatis操作进行拦截，
 */
@Intercepts({@Signature(
		  type= Executor.class,
		  method = "update",
		  args = {MappedStatement.class, Object.class})})
public class MybatisLastUpdateUserInterceptor implements Interceptor {
	private Logger logger = LoggerFactory.getLogger(MybatisLastUpdateUserInterceptor.class);
	private static String METHOD_NAME = "setUpdateUserName";
	private Map<Class<?>, Method> methodCache = new HashMap<>();
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		wired(args);
		return invocation.proceed();
	}
	
	private void wired(Object[] args) {
		if(args.length < 2) {
			return;
		}
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if(requestAttributes == null) {
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		if(request == null) {
			return;
		}
		AdminUser adminUser = (AdminUser) request.getAttribute("LOGIN_USER");
		if(adminUser == null) {
			return;
		}
		String username = adminUser.getUsername();
		if(StringUtils.isBlank(username)) {
			return;
		}
		for (int i = 1; i < args.length; i++) {
			Object arg = args[i];
			Method method = methodCache.get(arg.getClass());
			if(method == null) {
				Method[] methods = arg.getClass().getMethods();
				for(Method temp : methods) {
					if(METHOD_NAME.equals(temp.getName())) {
						method = temp;
						methodCache.put(arg.getClass(), method);
						break;
					}
				}
			}
			if(method != null) {
				try {
					method.invoke(arg, username);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
