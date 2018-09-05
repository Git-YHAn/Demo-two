package com.bee.devops.admin.common.error;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;

@RestController
@RequestMapping(value = "/error")
public class ErrorController extends BaseController {
	final static Logger log = Logger.getLogger(ErrorController.class);

	@RequestMapping("/401")
	public ResultHandler<Object> error401() {
		return ResultHandler.error("用户没有权限");
	}
}
