package com.bee.devops.admin.core.vo.request;

/**
 * 请求传递对象
 *
 * @author wanghuajie
 * @date 2018/7/18 19:58
 */
public class ServerTempRequest {
    private int pageNum;
    private int pageSize;
    private String tplName;
    private String tplType;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public String getTplType() {
        return tplType;
    }

    public void setTplType(String tplType) {
        this.tplType = tplType;
    }
}
