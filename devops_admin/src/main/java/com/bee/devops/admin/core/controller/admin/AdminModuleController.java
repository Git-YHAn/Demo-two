package com.bee.devops.admin.core.controller.admin;

import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.admin.AdminModule;
import com.bee.devops.admin.core.common.service.admin.AdminModuleService;
import com.bee.devops.admin.core.vo.request.SysModuleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模块controller
 *
 * @author wanghuajie
 * @date 2018/9/3 9:52
 */
@RestController
@RequestMapping("/module")
public class AdminModuleController extends BaseController {
    @Autowired
    private AdminModuleService moduleService;

    /**
     * 获取模块列表,同时返回当前登录用户在每个模块下的权限
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultHandler<List<AdminModule>> getModules() {
        List<AdminModule> modules = moduleService.getModules(0L, getAdminUser().getAdminUserId());
        return ResultHandler.success(modules);
    }

    /**
     * 添加模块
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultHandler<String> addModule(@RequestBody SysModuleRequest request) {
        AdminModule module = request.tranferTo();
        int result = moduleService.addModule(module);
        if (result == 1) {
            return ResultHandler.success("添加模块成功");
        }
        return ResultHandler.error("添加模块异常");
    }

    /**
     * 编辑模块
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultHandler<String> editModule(@RequestBody SysModuleRequest request) {
        AdminModule module = request.tranferTo();
        int result = moduleService.editModule(module);
        if (result == 1) {
            return ResultHandler.success("编辑模块成功");
        }
        return ResultHandler.error("编辑模块异常");
    }

    /**
     * 删除模块
     *
     * @param moduleIds 要删除的模块id数组
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultHandler<String> deleteModule(@RequestBody Long[] moduleIds) {
        for (Long moduleId : moduleIds) {
            moduleService.deleteModule(moduleId);
        }
        return ResultHandler.error("删除模块成功");
    }
}
