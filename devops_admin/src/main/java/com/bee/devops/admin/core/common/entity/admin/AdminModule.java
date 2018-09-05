package com.bee.devops.admin.core.common.entity.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块
 *
 * @author wanghuajie
 * @date 2018/8/31 19:21
 */
public class AdminModule {
    private Long moduleId;
    private String moduleName;
    private String moduleCode;
    private String moduleUrl;
    private Long parentId;
    private Integer isEnable;
    private Integer isShow;
    private Integer addChild;

    /**
     * 子模块
     */
    private List<AdminModule> children = new ArrayList<>();

    /**
     * 资源集合
     */
    private List<AdminResource> resources = new ArrayList<>();

    public List<AdminResource> getResources() {
        return resources;
    }

    public void setResources(List<AdminResource> resources) {
        this.resources = resources;
    }

    public List<AdminModule> getChildren() {
        return children;
    }

    public void setChildren(List<AdminModule> children) {
        this.children = children;
    }

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
}
