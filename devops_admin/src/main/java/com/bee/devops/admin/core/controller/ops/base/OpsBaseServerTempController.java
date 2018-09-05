package com.bee.devops.admin.core.controller.ops.base;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerTemp;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerTempService;
import com.bee.devops.admin.core.vo.request.ServerTempRequest;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务器模板controller
 */
@Api(value = "服务器信息Controller")
@RestController
@RequestMapping(value = "/temp")
public class OpsBaseServerTempController extends BaseController {
    final static Logger log = Logger.getLogger(OpsBaseServerTempController.class);

    @Autowired
    OpsBaseServerTempService tempService;

    @PostMapping(value = {"/query"})
    public ResultHandler<PageBean<OpsBaseServerTemp>> queryByNamePageData(@RequestBody ServerTempRequest request) {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        String tplName = request.getTplName();
        String tplType = request.getTplType();
        PageBean<OpsBaseServerTemp> pageBean = tempService.selectAll(pageNum, pageSize, tplName, tplType);
        return ResultHandler.success(pageBean);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultHandler<String> addTemp(@RequestBody OpsBaseServerTemp serverTemp) {
        tempService.insetServerTemp(serverTemp);
        return ResultHandler.success("添加成功!");
    }

    @RequestMapping(value = "/select/{tempId}", method = RequestMethod.GET)
    public ResultHandler<OpsBaseServerTemp> queryById(@PathVariable("tempId") Long tempId) {
        OpsBaseServerTemp serverTemp = tempService.getById(tempId);
        return ResultHandler.success(serverTemp);
    }

    @RequestMapping(value = "/delete/{tplId}", method = RequestMethod.GET)
    public ResultHandler<String> deleteById(@PathVariable("tplId") Long tplId) {
        tempService.deleteServerTemp(tplId);
        return ResultHandler.success("删除成功");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultHandler<String> updateById(@RequestBody OpsBaseServerTemp serverTemp) {
        tempService.updateServerTemp(serverTemp);
        return ResultHandler.success("修改成功");
    }
}

