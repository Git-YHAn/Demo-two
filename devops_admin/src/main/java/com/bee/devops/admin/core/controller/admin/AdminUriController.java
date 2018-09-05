package com.bee.devops.admin.core.controller.admin;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.admin.AdminUri;
import com.bee.devops.admin.core.common.service.admin.AdminUriService;
import com.bee.devops.admin.core.vo.request.SysUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 资源对应的接口controller
 *
 * @author wanghuajie
 * @date 2018/9/3 16:18
 */
@RestController
@RequestMapping("/uri")
public class AdminUriController extends BaseController {
    @Autowired
    private AdminUriService adminUriService;

    /**
     * 分页获取接口列表
     *
     * @return
     */
    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultHandler<PageBean<AdminUri>> getUriList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageBean<AdminUri> res = adminUriService.getUriList(pageNum, pageSize);
        return ResultHandler.success(res);
    }

    /**
     * 新增接口
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultHandler<String> addUri(@RequestBody SysUriRequest request) {
        AdminUri adminUri = request.transferTo();
        Integer result = adminUriService.addUri(adminUri);
        if (result == 1) {
            return ResultHandler.success("success");
        }
        return ResultHandler.success("error");
    }

    /**
     * 删除接口
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultHandler<String> deleteUri(@RequestBody Long[] interfaceIds) {
        for (Long interfaceId : interfaceIds) {
            adminUriService.deleteUri(interfaceId);
        }
        return ResultHandler.success("success");
    }

    /**
     * 编辑接口
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultHandler<String> editUri(@RequestBody SysUriRequest request) {
        AdminUri adminUri = request.transferTo();
        Integer result = adminUriService.editUri(adminUri);
        if (result == 1) {
            return ResultHandler.success("success");
        }
        return ResultHandler.success("error");
    }

    /**
     * 根据接口id获取接口详情
     *
     * @param interfaceId 接口id
     * @return
     */
    @RequestMapping(value = "/get/{interfaceId}", method = RequestMethod.GET)
    public ResultHandler<AdminUri> getUri(@PathVariable("interfaceId") Long interfaceId) {
        AdminUri uri = adminUriService.getUriById(interfaceId);
        if (uri != null) {
            return ResultHandler.success(uri);
        }
        return null;
    }

}
