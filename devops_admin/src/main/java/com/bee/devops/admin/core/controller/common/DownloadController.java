package com.bee.devops.admin.core.controller.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.utils.DownloadUtils;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/download")
public class DownloadController extends BaseController {

    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public ResultHandler<String> fileDownload() {
        DownloadUtils.download("download/userProcedures_V2.doc", "用户操作手册.doc", getResponse());
        return ResultHandler.success("用户手册下载成功");
    }

	@ApiOperation(value = "下载应用日志", notes = "下载应用日志")
	@GetMapping("/app/log/{logPath}")
	public ResultHandler<String> downLoadAppLogs(@PathVariable String logPath) throws IOException {
		String path = new String(Base64Utils.decodeFromUrlSafeString(logPath));
		String rootPath = OpsSysParameterUtil.getBackLogPath();
		File file = FileUtils.getFile(rootPath, path);
		DownloadUtils.download(file, getResponse());
		return ResultHandler.success("日志文件下载成功");
	}
}
