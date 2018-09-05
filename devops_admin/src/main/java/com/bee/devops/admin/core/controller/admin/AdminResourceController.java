/**
 * @author heping
 * @date2018年4月23日
 */
package com.bee.devops.admin.core.controller.admin;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.admin.AdminResource;
import com.bee.devops.admin.core.common.entity.common.VueTree;
import com.bee.devops.admin.core.common.service.admin.AdminResourceService;
import com.bee.devops.admin.core.vo.request.ResourceUriRequest;
import com.bee.devops.admin.core.vo.request.SysResourceRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sys/resource")
public class AdminResourceController extends BaseController {
    final static Logger log = Logger.getLogger(AdminRoleController.class);
    @Autowired
    AdminResourceService adminResourceService;

    /**
     * 添加资源与接口的关联关系
     *
     * @return
     */
    @RequestMapping(value = "/rel/uri", method = RequestMethod.POST)
    public ResultHandler<String> relWithUri(@RequestBody ResourceUriRequest request) {
        long resourceId = request.getResourceId();
        List<Long> interfaceIds = request.getInterfaceIds();
        adminResourceService.relWithUri(resourceId, interfaceIds);
        return ResultHandler.success("资源跟接口关联成功");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultHandler<PageBean<AdminResource>> getSysResource() {
        PageBean<AdminResource> res = adminResourceService.querySysResourceList(getPageNum(), getPageSize());
        return ResultHandler.success(res);
    }

    @RequestMapping(value = "/edit/resource", method = RequestMethod.POST)
    public ResultHandler<String> deleteResource(@RequestBody SysResourceRequest sysRes) {
        AdminResource resource = new AdminResource();
        resource.setResourceId(sysRes.getResourceId());
        resource.setResourceName(sysRes.getResourceName());
        resource.setResourceCode(sysRes.getResourceCode());
        resource.setResourceUrl(sysRes.getResourceUrl());
        resource.setResourceIcon(sysRes.getResourceIcon());
        resource.setResourceOder(sysRes.getResourceOrder());
        resource.setResourceType(sysRes.getResourceType());
        resource.setParentId(sysRes.getParentId());
        resource.setModuleId(sysRes.getModuleId());
        resource.setIsEnable(sysRes.getIsEnable());
        resource.setIsShow(sysRes.getIsShow());
        Integer res = adminResourceService.editResource(resource);
        if (res == 1) {
            return ResultHandler.success("success");
        }
        return ResultHandler.success("error");
    }

    /**
     * 删除资源
     */
    @RequestMapping(value = "/delete/resource", method = RequestMethod.POST)
    public ResultHandler<String> deleteResource(@RequestBody Long[] resourceId) {
        for (Long resId : resourceId) {
            adminResourceService.deleteResource(resId);
        }
        return ResultHandler.success("success");
    }

    /**
     * 新增资源
     */
    @RequestMapping(value = "/add/resource", method = RequestMethod.POST)
    public ResultHandler<String> addResource(@RequestBody SysResourceRequest sysRes) {
        AdminResource resource = new AdminResource();
        resource.setResourceId(sysRes.getResourceId());
        resource.setResourceName(sysRes.getResourceName());
        resource.setResourceCode(sysRes.getResourceCode());
        resource.setResourceUrl(sysRes.getResourceUrl());
        resource.setResourceIcon(sysRes.getResourceIcon());
        resource.setResourceOder(sysRes.getResourceOrder());
        resource.setResourceType(sysRes.getResourceType());
        resource.setParentId(sysRes.getParentId());
        resource.setModuleId(sysRes.getModuleId());
        resource.setIsEnable(sysRes.getIsEnable());
        resource.setIsShow(sysRes.getIsShow());
        Integer res = adminResourceService.addResource(resource);
        if (res == 1) {
            return ResultHandler.success("success");
        }
        return ResultHandler.success("error");
    }

    /**
     * 根据用户获取当前权限
     */
    @RequestMapping(value = "/get/resource", method = RequestMethod.GET)
    public ResultHandler<List<VueTree>> getResource() {
        List<VueTree> res = adminResourceService.getResource(getAdminUser().getAdminUserId(), 0L);
        if (res != null) {
            return ResultHandler.success(res);
        }
        return ResultHandler.error("error");
    }

    /**
     * 获取资源树
     */
    @RequestMapping(value = "/get/tree", method = RequestMethod.GET)
    public ResultHandler<List<VueTree>> getTree() {
        Long parentId = 0L;
        List<VueTree> res = adminResourceService.getTree(parentId);
        return ResultHandler.success(res);
    }

    /**
     * 根据角色ID获取资源ID
     */
    @RequestMapping(value = "/get/role/resource/{roleId}", method = RequestMethod.GET)
    public ResultHandler<List<Long>> getResource(@PathVariable Long roleId) {
        List<Long> list = adminResourceService.getResourceByRole(roleId);
        return ResultHandler.success(list);
    }

}
