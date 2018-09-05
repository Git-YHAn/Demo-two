package com.bee.devops.admin.core.controller.ops.base;

import java.util.Collections;
import java.util.List;

import com.bee.devops.admin.core.common.service.admin.AdminResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseEnvService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.vo.request.DeployEnvironmentRequest;
import com.bee.devops.admin.core.vo.response.AppEnvVo;
import com.bee.devops.admin.core.vo.response.EnvProVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("发布环境类型Controller")
@RestController
@RequestMapping(value = {"/env", "/environ"})
public class OpsBaseEnvController extends BaseController {
	final static Logger log = Logger.getLogger(OpsBaseEnvController.class);

	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	OpsBaseProService opsBaseProService;
	@Autowired
	OpsBaseAppService opsBaseAppService;
	@Autowired
	AdminResourceService adminResourceService;

	/**
	 * 通过环境ID查询
	 * 
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "获取环境详细信息", notes = "根据url的id来获取环境详细信息")
	@RequestMapping(value = "/query/byid/{envId}", method = RequestMethod.GET)
	public ResultHandler<OpsBaseEnv> getEnvByenvId(@PathVariable("envId") Long envId) {
		OpsBaseEnv envConfig = opsBaseEnvService.getEnvById(envId);
		return ResultHandler.success(envConfig);
	}

	/**
	 * 分页查询所有环境
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有环境", notes = "分页查询所有环境")
	@RequestMapping(value = "/query_page", method = RequestMethod.GET)
	public ResultHandler<PageBean<OpsBaseEnv>> querydeployEnvironmentsByPageData() {
		PageBean<OpsBaseEnv> pageBean = opsBaseEnvService.queryEnvsByPageData(getPageNum(),
				getPageSize());
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询所有环境
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有环境", notes = "查询所有环境")
	@GetMapping(value = "/query/all")
	public ResultHandler<List<OpsBaseEnv>> queryALLdeployEnvironments() {
		return ResultHandler.success(opsBaseEnvService.getAllEnvs());
	}

	/**
	 * 更新环境配置信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "更新环境配置信息", notes = "更新环境配置信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updatedeployEnvironment(@RequestBody DeployEnvironmentRequest deployEnvironmentRequest) {
		if (opsBaseEnvService.getEnvByPriorityCount(Integer.parseInt(deployEnvironmentRequest.getPriority())) >= 1
				&& opsBaseEnvService.queryPriorityById(deployEnvironmentRequest.getEnvId()) != 
				Integer.parseInt(deployEnvironmentRequest.getPriority())) {
			return ResultHandler.error("已有此优先级!");
		}
		OpsBaseEnv opsBaseEnv = new OpsBaseEnv();
		opsBaseEnv.setEnvId(deployEnvironmentRequest.getEnvId());
		opsBaseEnv.setEnvName(deployEnvironmentRequest.getEnvName());
		opsBaseEnv.setPriority(Integer.parseInt(deployEnvironmentRequest.getPriority()));
		opsBaseEnv.setDescription(deployEnvironmentRequest.getDescription());
		if (opsBaseEnvService.updateEnv(opsBaseEnv) == 1) {
			return ResultHandler.success("更新成功!");
		}
		return ResultHandler.error("更新失败!");
	}

	/**
	 * 通过环境ID删除环境
	 * 
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "删除环境", notes = "通过Id删除环境")
	@RequestMapping(value = "/delete/{envId}", method = RequestMethod.DELETE)
	public ResultHandler<String> deleteEnvById(@PathVariable("envId") Long envId) {
		if (opsAssembleAppService.selectByEnvid(envId) > 0) {
			return ResultHandler.error("删除失败!该ID为" + envId + "环境尚有关联资源或应用");
		}
		if (opsBaseEnvService.deleteEnvById(envId) == 1) {
			return ResultHandler.success("删除成功!");
		}
		return ResultHandler.error("删除失败!");
	}

	/**
	 * 添加环境
	 * 
	 * @return
	 */
	@ApiOperation(value = "添加环境", notes = "添加环境")
	@PostMapping(value = "/add")
	public ResultHandler<String> addDeployEnvironment(@RequestBody DeployEnvironmentRequest deployEnvironmentRequest) {
		if (opsBaseEnvService.getEnvByCodeCount(deployEnvironmentRequest.getEnvCode()) >= 1) {
			return ResultHandler.error("环境编号不能相同");
		}
		if (opsBaseEnvService.getEnvByPriorityCount(Integer.parseInt(deployEnvironmentRequest.getPriority())) >= 1) {
			return ResultHandler.error("已有此优先级!");
		}
		OpsBaseEnv opsBaseEnv = new OpsBaseEnv();
		opsBaseEnv.setEnvName(deployEnvironmentRequest.getEnvName());
		opsBaseEnv.setEnvCode(deployEnvironmentRequest.getEnvCode());
		opsBaseEnv.setPriority(Integer.parseInt(deployEnvironmentRequest.getPriority()));
		opsBaseEnv.setDescription(deployEnvironmentRequest.getDescription());
		if (opsBaseEnvService.insertEnv(opsBaseEnv) == 1) {
			return ResultHandler.success("添加成功!");
		}
		return ResultHandler.error("添加失败!");
	}

	/**
	 * 应用环境保存
	 * 
	 * @return
	 */
	@ApiOperation(value = "应用环境保存", notes = "应用环境保存")
	@RequestMapping(value = "/setapp", method = RequestMethod.POST)
	public ResultHandler<StringBuffer> saveAppEnvtConfig(@RequestBody AppEnvVo appEnvVo) {
		int successCount = 0;// 成功记录数
		int errorCount = 0;// 失败记录数
		StringBuffer hint = new StringBuffer();
		OpsBaseEnv opsBaseEnv = opsBaseEnvService.getEnvById(appEnvVo.getEnvId());
		if (opsBaseEnv == null) {
			return ResultHandler.error("没有该应用环境，失败环境ID为：" + appEnvVo.getEnvId());
		}

		OpsBasePro pro = opsBaseProService.getProjectById(appEnvVo.getProId());
		//异步 添加环境资源
		adminResourceService.addEnvResources(opsBaseEnv, pro);
		for (int i = 0; i < appEnvVo.getAppId().length; i++) {
			OpsAssembleApp opsAssembleApp = new OpsAssembleApp();
			opsAssembleApp.setEnvId(appEnvVo.getEnvId());
			opsAssembleApp.setAppId(appEnvVo.getAppId()[i]);
			opsAssembleApp.setProId(appEnvVo.getProId());
			
			opsAssembleApp.setEnvName(opsBaseEnv.getEnvName());
			OpsBaseApp app = opsBaseAppService.getAppById(appEnvVo.getAppId()[i]);
			opsAssembleApp.setAppName(app.getAppName());
			opsAssembleApp.setProName(pro.getProName());
			if (opsAssembleAppService.getByEnvidAppidCount(appEnvVo.getEnvId(), appEnvVo.getAppId()[i]) > 0) {
				errorCount++;
			} else if (opsAssembleAppService.saveEnvironmentConfig(opsAssembleApp) == 1) {
				successCount++;
			}
		}

		if (errorCount == 0) {
			hint.append("全部成功");
		} else {
			hint.append("成功数：" + successCount + "失败数：" + "。" + errorCount + "失败原因：保存的应用环境重复。");
		}
		return ResultHandler.success(hint);
	}

	/**
	 * 通过名称分页查询环境，输入名称查询指定环境，不输入名称默认查询全部
	 * 
	 * @param envName
	 * @return
	 */
	@ApiOperation(value = "查询所有环境", notes = "通过名称分页查询环境")
	@GetMapping(value ={ "/query/{pageNum}/{pageSize}/{searchName}", "/query/{pageNum}/{pageSize}" })
	public ResultHandler<PageBean<OpsBaseEnv>> queryByNamePageData(
			@PathVariable(name = "searchName", required = false) String envName,
			@PathVariable Integer pageNum,@PathVariable Integer pageSize) {
		PageBean<OpsBaseEnv> pageBean = opsBaseEnvService.queryEnvsByName(pageNum, pageSize, envName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 通过环境ID查询优先级和应用版本下的项目
	 * 
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "获取环境信息", notes = "通过环境ID查询优先级和项目应用")
	@RequestMapping(value = "/query_priority_pro/{envId}", method = RequestMethod.GET)
	public ResultHandler<EnvProVo> queryByPriorityAndProApp(@PathVariable("envId") Long envId) {
		OpsBaseEnv envConfig = opsBaseEnvService.getEnvById(envId);
		List<OpsBasePro> listPros = opsBaseProService.selectProByEnvid(envId);
		List<OpsBaseEnv> listEnvs = opsBaseEnvService.selectEnvByPriority(envConfig.getPriority());
		EnvProVo envProVo = new EnvProVo(listPros, listEnvs);
		return ResultHandler.success(envProVo);
	}

	/**
	 * 查询应用版本中存在的环境
	 * 
	 * @author Yang Chunhai
	 * @return
	 */
	@ApiOperation(value = "查询应用版本中的环境", notes = " 查询应用版本中存在的环境")
	@RequestMapping(value = "/query_appver_env", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseEnv>> selectByAppVer() {
		List<OpsBaseEnv> listEnvs = opsBaseEnvService.selectByAppVer();
		// 去除集合中null元素
		listEnvs.removeAll(Collections.singleton(null));
		return ResultHandler.success(listEnvs);
	}

	/**
	 * 查询小于当前优先级的环境
	 * 
	 * @author Yang Chunhai
	 * @param priority
	 * @return
	 */
	@ApiOperation(value = "查询环境", notes = "通过优先级查询环境")
	@RequestMapping(value = "/query/low/{priority}", method = RequestMethod.GET)
	public ResultHandler<List<OpsBaseEnv>> queryByPriority(@PathVariable("priority") Integer priority) {
		List<OpsBaseEnv> listEnvs = opsBaseEnvService.selectEnvByPriority(priority);
		return ResultHandler.success(listEnvs);
	}

}
