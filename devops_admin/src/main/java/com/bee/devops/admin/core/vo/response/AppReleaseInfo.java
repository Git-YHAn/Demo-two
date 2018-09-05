package com.bee.devops.admin.core.vo.response;

/**
 * 应用发布统计
 *
 * @author wanghuajie
 * @date 2018/8/21 17:47
 */
public class AppReleaseInfo {
    /**
     * 总数
     */
    private int total;
    /**
     * 发布成功数量
     */
    private int successNum;

    /**
     * 失败数量
     */
    private int failNum;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }
}
