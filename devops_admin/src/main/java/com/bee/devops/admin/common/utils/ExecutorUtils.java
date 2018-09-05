package com.bee.devops.admin.common.utils;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author wanghuajie
 * @date 2018/7/18 10:29
 */
public final class ExecutorUtils {
    private static volatile ExecutorService executorService;

    public static ExecutorService getFixedThreadPool(int size) {
        return new ThreadPoolExecutor(size, size, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    public static ExecutorService getCachedThreadPool() {
        if (executorService == null) {
            synchronized (ExecutorUtils.class) {
                if (executorService == null) {
                    executorService = new ThreadPoolExecutor(0, 50, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
                }
            }
        }
        return executorService;
    }
}
