package com.bee.devops.admin.core.vo.response;

/**
 * 服务器使用情况
 *
 * @author wanghuajie
 * @date 2018/8/20 15:02
 */
public class ServerUseInfo {
    /**
     * 当前环境下已使用数量
     */
    private int used;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

}
