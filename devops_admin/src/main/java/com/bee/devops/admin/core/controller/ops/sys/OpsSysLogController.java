package com.bee.devops.admin.core.controller.ops.sys;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsSysLog;
import com.bee.devops.admin.core.common.service.ops.OpsSysLogService;
import com.bee.devops.admin.core.vo.request.OpsSysLogRequest;

@RestController
@RequestMapping(value = "/sys/log")
public class OpsSysLogController {
	
	@Autowired
	OpsSysLogService opsSysLogService;
	
	@RequestMapping(value = { "/search/record/{pageNum}/{pageSize}/{typeId}",
			"/search/record/{pageNum}/{pageSize}/{typeId}/{startTime}/{endTime}",
			"/search/record/{pageNum}/{pageSize}/{typeId}/{searchName}",
			"/search/record/{pageNum}/{pageSize}/{typeId}/{searchName}/{startTime}/{endTime}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<OpsSysLog>> searchSysLogRecord(@PathVariable(value="typeId") Long typeId,
			@PathVariable(value = "searchName", required = false) String searchName,
			@PathVariable(value = "startTime", required = false) String startTime,
			@PathVariable(value = "endTime", required = false) String endTime,
			@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
		PageBean<OpsSysLog> selectSysLogRecord = opsSysLogService.selectSysLogRecord(pageNum, pageSize, searchName, typeId, startTime, endTime);
		return ResultHandler.success(selectSysLogRecord);
	}
	
	@RequestMapping(value = { "/search/type"}, method = RequestMethod.GET)
	public ResultHandler<List<OpsSysLog>> searchSysLogType() {
		return ResultHandler.success(opsSysLogService.selectLogType());
	}
	
	@RequestMapping(value = { "/change/type/"}, method = RequestMethod.POST)
	public ResultHandler<List<OpsSysLog>> updateSysLogTypeIsEnable(@RequestBody OpsSysLogRequest opsSysLogRequest) {
		opsSysLogService.updateSysLogTypeIsEnable(opsSysLogRequest.getIsEnable(), opsSysLogRequest.getTypeId());
		return ResultHandler.success(opsSysLogService.selectLogType());
	}
}
