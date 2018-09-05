package com.bee.devops.admin.core.common.entity.admin;

/**
 * 资源对应的接口
 *
 * @author wanghuajie
 * @date 2018/9/3 16:27
 */
public class AdminUri {
    private Long interfaceId;
    private String interfaceName;
    private String interfaceUrl;

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }
}
