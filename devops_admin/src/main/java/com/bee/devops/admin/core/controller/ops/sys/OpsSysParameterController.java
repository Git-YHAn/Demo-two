package com.bee.devops.admin.core.controller.ops.sys;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.core.common.service.ops.OpsSysParameterService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Roc created on 2018/8/2
 */
@RestController
@RequestMapping(value = "/sys/param")
public class OpsSysParameterController {
    private final static Logger logger = Logger.getLogger(OpsSysParameterController.class);

    @Autowired
    private OpsSysParameterService opsSysParameterService;

    @ApiOperation(value = "查询所有可配置的系统参数", notes = "查询所有可配置的系统参数")
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ResultHandler<Map<String, String>> searchAllOpsSysParameters() {
        return ResultHandler.success(OpsSysParameterUtil.getOpsParameterMap());
    }

    @ApiOperation(value = "根据页面返回的系统参数项进行更新", notes = "根据页面返回的系统参数项进行更新")
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public ResultHandler<String> updateOpsSysParameter(@RequestBody Map<String, String> request) {
        if (request == null || request.isEmpty()) {
            return ResultHandler.error("Failed for parameter is empty!");
        }
        boolean result = opsSysParameterService.saveOrUpdateOpsParameter(request);
        if (result) {
            return ResultHandler.success("Success Updated!");
        } else {
            return ResultHandler.error("Failed Updated!");
        }
    }
}
