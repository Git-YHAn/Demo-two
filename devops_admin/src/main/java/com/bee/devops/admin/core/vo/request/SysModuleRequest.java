package com.bee.devops.admin.core.vo.request;

import com.bee.devops.admin.common.request.RestRequest;
import com.bee.devops.admin.core.common.entity.admin.AdminModule;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 模块对应的接口请求参数
 *
 * @author wanghuajie
 * @date 2018/9/3 16:18
 */
public class SysModuleRequest extends RestRequest {
    private static final long serialVersionUID = 3040975938181673061L;

    private Long moduleId;
    @NotBlank(message = "{sys.module.moduleName.notnull}")
    private String moduleName;
    @NotBlank(message = "{sys.module.moduleCode.notnull}")
    private String moduleCode;
    private String moduleUrl;
    @NotBlank(message = "{sys.module.parentId.notnull}")
    private Long parentId;
    private Integer isEnable;
    private Integer isShow;
    private Integer addChild;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getAddChild() {
        return addChild;
    }

    public void setAddChild(Integer addChild) {
        this.addChild = addChild;
    }

    /**
     * 转换成AdminModule
     *
     * @return
     */
    public AdminModule tranferTo() {
        AdminModule module = new AdminModule();
        module.setModuleId(moduleId);
        module.setModuleName(moduleName);
        module.setModuleCode(moduleCode);
        module.setModuleUrl(moduleUrl);
        module.setIsEnable(isEnable);
        module.setIsShow(isShow);
        module.setParentId(parentId);
        module.setAddChild(addChild);
        return module;
    }
}
