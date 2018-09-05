package com.bee.devops.admin.core.vo.request;

import java.util.List;

/**
 * 执行脚本请求
 *
 * @author wanghuajie
 * @date 2018/7/17 17:29
 */
public class ExecuteRequest {
    /**
     * 服务器id
     */
    private List<Long> assetsIds;
    /**
     * 模板id
     */
    private Long moduleId;
    /**
     * 命令
     */
    private String order;
    /**
     * 页面数量
     */
    private Integer pageSize;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 执行主机SSH地址
     */
    private String sshAddress;
    /**
     * 执行模板名称
     */
    private String tplName;
    /**
     * 执行时间
     */
    private String execTime;

    public List<Long> getAssetsIds() {
        return assetsIds;
    }

    public void setAssetsIds(List<Long> assetsIds) {
        this.assetsIds = assetsIds;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getSshAddress() {
        return sshAddress;
    }

    public void setSshAddress(String sshAddress) {
        this.sshAddress = sshAddress;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }
}
