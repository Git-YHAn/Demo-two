package com.bee.devops.admin.core.vo.request;

import java.util.List;

/**
 * 资源与接口的关联关系
 *
 * @author wanghuajie
 * @date 2018/9/3 17:11
 */
public class ResourceUriRequest {
    private long resourceId;
    private List<Long> interfaceIds;

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public List<Long> getInterfaceIds() {
        return interfaceIds;
    }

    public void setInterfaceIds(List<Long> interfaceIds) {
        this.interfaceIds = interfaceIds;
    }
}
