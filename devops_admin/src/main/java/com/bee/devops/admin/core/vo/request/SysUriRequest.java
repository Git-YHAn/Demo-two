package com.bee.devops.admin.core.vo.request;

import com.bee.devops.admin.common.request.RestRequest;
import com.bee.devops.admin.core.common.entity.admin.AdminUri;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 资源对应的接口请求参数
 *
 * @author wanghuajie
 * @date 2018/9/3 16:18
 */
public class SysUriRequest extends RestRequest {
    private static final long serialVersionUID = 3040975938181673061L;

    private Long interfaceId;

    @NotBlank(message = "{sys.uri.interfaceName.notnull}")
    private String interfaceName;

    @NotBlank(message = "{sys.uri.interfaceUrl.notnull}")
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

    /**
     * 转换成AdminUri
     *
     * @return
     */
    public AdminUri transferTo() {
        AdminUri adminUri = new AdminUri();
        adminUri.setInterfaceId(interfaceId);
        adminUri.setInterfaceName(interfaceName);
        adminUri.setInterfaceUrl(interfaceUrl);
        return adminUri;
    }
}
