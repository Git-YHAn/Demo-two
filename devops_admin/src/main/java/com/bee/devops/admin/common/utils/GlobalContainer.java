package com.bee.devops.admin.common.utils;

import ch.ethz.ssh2.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局的map,存放服务器执行结果
 *
 * @author wanghuajie
 * @date 2018/7/17 17:55
 */
public class GlobalContainer {
    /**
     * 针对普通命令的map容器
     */
    public static final Map<String, String> MAP = new ConcurrentHashMap<>();
    /**
     * 获取结果的线程容器
     */
    public static final Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();
}
