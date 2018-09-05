package com.bee.devops.admin.core.controller.ops.base;


import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerExecHis;
import com.bee.devops.admin.core.common.service.ops.OpsBaseServerExecHisService;
import com.bee.devops.admin.core.vo.request.ExecuteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "shell命令执行历史Controller")
@RestController
@RequestMapping(value = "/exec/his")
public class OpsBaseServerExecHisController extends BaseController {
    @Autowired
    OpsBaseServerExecHisService execHisService;


    @ApiOperation(value = "查询服务器", notes = "根据id查询服务器的基本信息")
    @PostMapping(value = "/query/")
    public ResultHandler<PageBean<OpsBaseServerExecHis>> queryAllExecHistory(@RequestBody ExecuteRequest request) {
        Integer pageNum = request.getPageNum();
        Integer pageSize = request.getPageSize();
        String sshAddress = request.getSshAddress();
        String tplName = request.getTplName();
        String execTime = request.getExecTime();
        PageBean<OpsBaseServerExecHis> result = execHisService.queryExecHisBySshAndExecTime(pageNum, pageSize, sshAddress, tplName, execTime);
        return ResultHandler.success(result);
    }
}

