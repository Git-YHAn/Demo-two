package com.bee.devops.admin.core.vo.response;

/**
 * 用户启用和禁用数量
 *
 * @author wanghuajie
 * @date 2018/8/20 16:03
 */
public class UserInfo {
    /**
     * 启用
     */
    private int enable;

    /**
     * 禁用
     */
    private int disable;

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }
}
