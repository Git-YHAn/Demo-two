package com.bee.devops.admin.core.controller.ops.base;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.vo.request.OpsBaseAppRequest;
import com.bee.devops.admin.core.vo.response.OpsBaseAppSearchResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("应用控制模块Controller")
@RestController
@RequestMapping(value = "/app")
public class OpsBaseAppController extends BaseController {
	final static Logger log = Logger.getLogger(OpsBaseAppController.class);

	@Autowired
	OpsBaseProService opsBaseProService;
	@Autowired
	OpsBaseAppService opsBaseAppService;

	@Autowired
	OpsAssembleAppService opsAssembleAppService;

	/**
	 * 根据ID查询应用
	 * 
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "查询应用", notes = "根据id查询应用")
	@RequestMapping(value = "/query/byid/{appId}", method = RequestMethod.GET)
	public ResultHandler<OpsBaseApp> getAppByproId(@PathVariable(name = "appId") Long appId) {
		OpsBaseApp opsBaseApp = opsBaseAppService.getAppById(appId);
		return ResultHandler.success(opsBaseApp);
	}

	/**
	 * 模糊查询所有应用
	 * 
	 * @return
	 */
	@ApiOperation(value = "模糊查询所有应用", notes = "模糊查询所有应用")
	@GetMapping(value = {"/query/{getPageNum}/{getPageSize},{searchName}","/query/{getPageNum}/{getPageSize}"})
	public ResultHandler<PageBean<OpsBaseApp>> queryAppsByPageData(
			@RequestParam(name = "searchName", required = false) String searchName,
			@PathVariable Integer getPageNum, @PathVariable Integer getPageSize) {
		PageBean<OpsBaseApp> pageBean = opsBaseAppService.queryAppsByPageData(getPageNum, getPageSize,searchName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询某项目的所有应用(带分页)
	 * 
	 * @return
	 */
	@ApiOperation(value = "通过项目查询应用", notes = "查询传入的项目id所对应的项目所有的所有应用")
	@GetMapping(value={"/query/bypro/{getPageNum}/{getPageSize}/{proId}","/query/bypro/{getPageNum}/{getPageSize}/{proId}/{keyWord}"})
	public ResultHandler<PageBean<OpsBaseAppSearchResponse>> searchAppsByPro(
			@PathVariable Long proId, @PathVariable(required=false) String keyWord, 
			@PathVariable Integer getPageNum, @PathVariable Integer getPageSize) {
		PageBean<OpsBaseApp> pageBean = opsBaseAppService.searchAppsByPro(getPageNum, getPageSize,proId,keyWord);
		List<OpsBaseAppSearchResponse> list = OpsBaseAppSearchResponse.transList(pageBean.getResults());
		return ResultHandler.success(new PageBean<>(pageBean.getMeta(), list));
	}
	
	/**
	 * 查询某项目的所有应用
	 * 
	 * @return
	 */
	@ApiOperation(value = "通过项目查询应用", notes = "查询传入的项目id所对应的项目所有的所有应用")
	@GetMapping(value={"/query/pro/{proId}"})
	public ResultHandler<List<OpsBaseApp>> queryAppsByPro(
			@PathVariable(name = "proId", required = false) Long proId) {
		List<OpsBaseApp> lists = opsBaseAppService.queryAllAppsByPro(proId);
		return ResultHandler.success(lists);
	}

	/**
	 * 模糊查询子应用
	 * 
	 * @author Yang Chunhai
	 * @param opsBaseApp
	 * @return
	 */
	@ApiOperation(value = "模糊查询子应用", notes = "模糊查询子应用")
	@RequestMapping(value = "/query_servletive", method = RequestMethod.POST)
	public ResultHandler<PageBean<OpsBaseApp>> queryProAllAppsByName(@RequestBody OpsBaseApp opsBaseApp) {
		PageBean<OpsBaseApp> pageBean = opsBaseAppService.queryProAppsByName(getPageNum(), getPageSize(), opsBaseApp);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 修改应用
	 * 
	 * @return
	 */
	@ApiOperation(value = "修改应用", notes = "修改应用")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updateDeployProject(@RequestBody OpsBaseAppRequest daApplicationRequest) {
		OpsBaseApp opsBaseApp = opsBaseAppService.getAppById(daApplicationRequest.getAppId());
		if (opsBaseApp == null) {
			return ResultHandler.error("更新失败");
		}
		//更新应用时检测其它应用是否包含请求的git路径
		boolean existedGitUrl = opsBaseAppService.checkCurrentGitURLExisted(daApplicationRequest.getAppGitUrl(), daApplicationRequest.getAppId());
		if (existedGitUrl) {
			return ResultHandler.error(" GIT库地址已存在！");
		}

		opsBaseApp.setAppName(daApplicationRequest.getAppName());
		opsBaseApp.setAppType(daApplicationRequest.getAppType());
		opsBaseApp.setLogPath(daApplicationRequest.getLogPath());
		opsBaseApp.setDescription(daApplicationRequest.getDescription());
		opsBaseApp.setAppGitUrl(daApplicationRequest.getAppGitUrl());
		opsBaseApp.setAppTypeId(daApplicationRequest.getAppTypeId());
		opsBaseApp.setAppTypeName(daApplicationRequest.getAppTypeName());
		if (opsBaseAppService.updateApp(opsBaseApp) == 1) {
			return ResultHandler.success("更新成功");
		}
		return ResultHandler.error("更新失败");
	}

	/**
	 * 删除应用
	 * 
	 * @param appId
	 * @return
	 */
	@ApiOperation(value = "删除应用", notes = "通过传入的appId删除应用")
	@RequestMapping(value = "/delete/{appId}", method = RequestMethod.DELETE)
	public ResultHandler<String> deleteAppById(@PathVariable("appId") Long appId) {
		if (opsAssembleAppService.selectByAppidCount(appId) > 0) {
			return ResultHandler.error("该应用被占用，删除失败");
		}
		if (opsBaseAppService.deleteAppById(appId) == 1) {
			return ResultHandler.success("删除成功!");
		}
		return ResultHandler.error("删除失败!");
	}

	/**
	 * 增加应用
	 * 
	 * @return
	 */
	@ApiOperation(value = "添加应用", notes = "添加应用")
	@PostMapping(value = "/add")
	public ResultHandler<String> addOpsBaseApp(@RequestBody OpsBaseAppRequest deployApplicationRequest) {
		if (opsBaseAppService.getAppByCode(deployApplicationRequest.getAppCode()) >= 1) {
			return ResultHandler.error("添加失败!已存在此编码");
		}
		OpsBaseApp application = deployApplicationRequest.transEntity();
		//添加应用时不同应用的Git路径唯一
		boolean existedGitUrl = opsBaseAppService.checkCurrentGitURLExisted(application.getAppGitUrl(), null);
		if (existedGitUrl) {
			return ResultHandler.error("GIT库地址已存在！");
		}

		OpsBasePro project = opsBaseProService.getProjectById(application.getProId());
		application.setProName(project.getProName());
		application.setCreateTime(new Date());
		if (opsBaseAppService.insertApp(application) == 1) {
			return ResultHandler.success("添加成功!");
		}
		return ResultHandler.error("添加失败!");
	}

	/**
	 * 查询未配置应用
	 * 
	 * @author Yang Chunhai
	 * @return
	 */
	@ApiOperation(value = "查询未配置应用", notes = "查询该环境没有配置的应用")
	@RequestMapping(value = "/query/noset/{proId}/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseApp>> getNotSetApp(@PathVariable Long proId,@PathVariable Long envId) {
		Long[] appIds = opsAssembleAppService.selectAppidByEnvid(envId);
		List<OpsBaseApp> appNotSet = opsBaseAppService.selectNotSetApp(appIds, proId);
		return ResultHandler.success(appNotSet);
	}

	/**
	 * 查询应用版本下项目中的应用
	 * 
	 * @author Yang Chunhai
	 * @param proId
	 * @return
	 */
	@ApiOperation(value = "查询应用版本应用", notes = "查询应用版本下项目中的应用")
	@RequestMapping(value = "/query_appver_app/{proId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseApp>> selectAppVerInApp(@PathVariable("proId") Long proId) {
		List<OpsBaseApp> listApps = opsBaseAppService.selectAppVerInApp(proId);
		return ResultHandler.success(listApps);
	}

	/**
	 * 查询应用环境中项目下存在的应用
	 * 
	 * @author Yang Chunhai
	 * @param proId
	 * @return
	 */
	@ApiOperation(value = "查询应用环境应用", notes = "查询应用环境中项目下存在的应用")
	@GetMapping("/query/appenv/{proId}/{envId}")
	public ResultHandler<List<OpsBaseApp>> selectAppByEnvPro(@PathVariable("proId") Long proId,
			@PathVariable(name = "envId", required = false) Long envId) {
		List<OpsBaseApp> listApps = opsBaseAppService.selectEnvInProApp(proId, envId);
		return ResultHandler.success(listApps);
	}

	/**
	 * 配置应用
	 * 
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "配置应用", notes = "配置应用")
	@RequestMapping(value = "/setapp/{envId}/{apps}", method = RequestMethod.GET)
	public ResultHandler<StringBuffer> saveAppEnvtConfig(@PathVariable Integer envId,
			@PathVariable Long[] apps) {
//		int successCount = 0;// 成功记录数
//		int errorCount = 0;// 失败记录数
		StringBuffer inform = new StringBuffer();
		inform.append("asd");
		return ResultHandler.success(inform);
	}

	/**
	 * 查询所有应用的数量
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有应用的数量", notes = "查询所有应用的数量")
	@RequestMapping(value = "/select_count", method = RequestMethod.GET)
	public ResultHandler<Integer> queryProjectCount() {
		return ResultHandler.success(opsBaseAppService.queryAppCount());
	}

	/**
	 * 查询所有应用的数量
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有应用类型", notes = "查询所有应用类型")
	@RequestMapping(value = "/query/apptypes", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseAppType>> queryAppTypes() {
		return ResultHandler.success(opsBaseAppService.queryAppTypes());
	}
}
