package com.bee.devops.admin.core.vo.request;

/**
 * 各种版本的未使用数量vo
 *
 * @author wanghuajie
 * @date 2018/6/28 15:02
 */
public class VersionNotUsedVo {
    /**
     * 针对不同的版本类型,返回对应的未使用的数量
     */
    private Integer notUsed;

    /**
     * 具体的版本url,用于前端定位
     */
    private String url;

    public Integer getNotUsed() {
        return notUsed;
    }

    public void setNotUsed(Integer notUsed) {
        this.notUsed = notUsed;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
