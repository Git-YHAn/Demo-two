package com.bee.devops.admin.core.controller.ops.deploy;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.service.ops.OpsDepAppHisService;
import com.bee.devops.admin.core.vo.response.HistoryVersionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "应用发布历史Controller")
@RestController
@RequestMapping("/publish/history")
public class OpsDepAppHisController {

    @Autowired
    private OpsDepAppHisService opsDepAppHisService;

    @ApiOperation(value = "查询发布应用历史", notes = "查询发布应用历史")
    @RequestMapping(value = {"/search/{pageNum}/{pageSize}/{proId}/{envId}/{publishStatus}",
            "/search/{pageNum}/{pageSize}/{proId}/{envId}/{publishStatus}/{appName}"}, method = RequestMethod.GET)
    public ResultHandler<PageBean<HistoryVersionVo>> queryAllPublishAppsByPageData(@PathVariable Integer pageNum,
                                                                                   @PathVariable Integer pageSize,
                                                                                   @PathVariable Long proId,
                                                                                   @PathVariable Long envId,
                                                                                   @PathVariable Integer publishStatus,
                                                                                   @PathVariable(required = false) String appName) {
        PageBean<HistoryVersionVo> pageBean = opsDepAppHisService.queryPublishingAppsByPage(pageNum, pageSize, envId, proId, appName, publishStatus);
        return ResultHandler.success(pageBean);
    }
}
