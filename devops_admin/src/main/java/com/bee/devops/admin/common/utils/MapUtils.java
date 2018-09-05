package com.bee.devops.admin.common.utils;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理一个全局的map,保证一个服务器在初始化过程中时,别的线程不能再进行初始化
 *
 * @author wanghuajie
 * @date 2018/7/11 16:03
 */
public class MapUtils {
    public static final Map<Long, OpsBaseServer> MAP = new ConcurrentHashMap<>();
}
