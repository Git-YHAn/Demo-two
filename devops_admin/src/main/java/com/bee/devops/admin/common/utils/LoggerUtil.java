package com.bee.devops.admin.common.utils;

/**
 * 日志管理工具类
 * @author Administrator
 *
 */
public class LoggerUtil {
	public static final String LOG_MODULE="module";
    public static final String LOG_METHODS="methods";
    
    public static ThreadLocal<String> description = new ThreadLocal<>();

	public static String getDescription() {
		return description.get();
	}

	public static void setDescription(String info) {
		description.set(info);
	}
	
	
}
