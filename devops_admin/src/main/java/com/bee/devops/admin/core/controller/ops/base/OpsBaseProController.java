package com.bee.devops.admin.core.controller.ops.base;

import java.util.List;
import java.util.Map;

import com.bee.devops.admin.core.common.service.admin.AdminResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import com.bee.devops.admin.core.common.service.ops.OpsBaseAppService;
import com.bee.devops.admin.core.common.service.ops.OpsBaseProService;
import com.bee.devops.admin.core.vo.request.DeployProjectRequest;
import com.bee.devops.admin.core.vo.response.DashBoardProDeployAppVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/pro")
public class OpsBaseProController extends BaseController{
	final static Logger log = Logger.getLogger(OpsBaseProController.class);

	@Autowired
	OpsBaseProService opsBaseProService;
	@Autowired
	OpsBaseAppService opsBaseAppService;
    @Autowired
    AdminResourceService adminResourceService;


	@ApiOperation(value="查询服务器",notes="通过查询项目不同应用下的服务器信息")
	@RequestMapping(value="/query_app_service_info/{appEvnId}",method=RequestMethod.GET)
	public ResultHandler<List<Map<String,Object>>> getServiceInfo(@PathVariable("appEvnId") Long appEvnId) {
		List<Map<String,Object>> res = opsBaseProService.getServiceInfo(appEvnId);
		return ResultHandler.success(res) ;
	}
	@ApiOperation(value="查询项目应用服务器",notes="通过项目id查询项目不同应用下的服务器信息")
	@RequestMapping(value="/query_app_service/{proId}",method=RequestMethod.GET)
	public ResultHandler<List<Map<String,Object>>> getAppService(@PathVariable("proId") Long proId) {
		List<Map<String,Object>> res = opsBaseProService.getAppService(proId);
		return ResultHandler.success(res) ;
	}
	/**
	 * 通过项目ID查询
	 * @param proId
	 * @return
	 */
	@ApiOperation(value = "查询项目", notes = "通过项目id查询项目")
	@RequestMapping(value = "/query/byid/{proId}", method = RequestMethod.GET)
	public ResultHandler<OpsBasePro> getProjectByproId(@PathVariable("proId") Long proId) {
		OpsBasePro opsBasePro = opsBaseProService.getProjectById(proId);
		return ResultHandler.success(opsBasePro);
	}

	/**
	 * 模糊查询所有项目(带分页)
	 * 
	 * @return
	 */
	@ApiOperation(value = "模糊查询项目", notes = "通过名称模糊查询项目")
	@GetMapping(value={"/query/{getPageNum}/{getPageSize}/{searchName}","/query/{getPageNum}/{getPageSize}"})
	public ResultHandler<PageBean<OpsBasePro>> queryProjectsByPageData(
			@PathVariable(name = "searchName", required = false) String searchName,
			@PathVariable(name = "getPageNum", required = false) Integer getPageNum,
			@PathVariable(name = "getPageSize", required = false) Integer getPageSize) {
		PageBean<OpsBasePro> pageBean = opsBaseProService.queryProjectsByPageData(getPageNum, getPageSize,
				searchName == null ? searchName : searchName.trim());
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询所有项目
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有项目", notes = "查询所有项目")
	@GetMapping("/query/all")
	public ResultHandler<List<OpsBasePro>> queryProjects() {
		List<OpsBasePro> lists = opsBaseProService.getAllProjects();
		return ResultHandler.success(lists);
	}

	/**
	 * 更新项目信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "修改项目信息", notes = "修改项目信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updateDeployProject(@RequestBody DeployProjectRequest deployProjectRequest) {
		if (opsBaseProService.getProjectById(deployProjectRequest.getProId()) == null) {
			return ResultHandler.error("更新失败");
		}
		OpsBasePro opsBasePro = new OpsBasePro();
		opsBasePro.setProId(deployProjectRequest.getProId());
		opsBasePro.setProName(deployProjectRequest.getProName());
		opsBasePro.setWebHook(deployProjectRequest.getWebHook());
		opsBasePro.setDescription(deployProjectRequest.getDescription());
		if (opsBaseProService.updateProject(opsBasePro) == 1) {
			return ResultHandler.success("更新成功!");
		}
		return ResultHandler.error("更新失败!");
	}

	/**
	 * 通过项目ID删除项目
	 * 
	 * @param proId
	 * @return
	 */
	@ApiOperation(value = "删除项目", notes = "通过项目id删除项目")
	@RequestMapping(value = "/delete/{proId}", method = RequestMethod.DELETE)
	public ResultHandler<String> deleteProjectById(@PathVariable("proId") Long proId) {
		if (opsBaseAppService.queryAllAppsByPro(proId).size() != 0) {
			return ResultHandler.error("该项目有子应用，无法删除");
		}
		if (opsBaseProService.deleteProjectById(proId) == 1) {
			return ResultHandler.success("删除成功!");
		}
		return ResultHandler.error("删除失败!");
	}

	/**
	 * 添加项目
	 * 
	 * @return
	 */
	@ApiOperation(value = "添加项目", notes = "添加项目")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultHandler<String> addDeployProject(@RequestBody DeployProjectRequest deployProjectRequest) {
		if (opsBaseProService.getProjectByCode(deployProjectRequest.getProCode()) >= 1) {
			return ResultHandler.error("添加失败，已存在此编码!");
		}
		OpsBasePro opsBasePro = new OpsBasePro();
		opsBasePro.setProName(deployProjectRequest.getProName());
		opsBasePro.setProCode(deployProjectRequest.getProCode());
		opsBasePro.setWebHook(deployProjectRequest.getWebHook());
		opsBasePro.setDescription(deployProjectRequest.getDescription());
		if (opsBaseProService.insertProject(opsBasePro) == 1) {
			adminResourceService.addProjectResources(opsBasePro.getProName(), opsBasePro.getProId());
			return ResultHandler.success("添加成功!");
		}
		return ResultHandler.error("添加失败!");
	}

	/**
	 * 根据环境ID查询应用环境下的项目
	 * 
	 * @author Yang Chunhai
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "查询项目", notes = "根据环境ID查询应用环境下的项目")
	@GetMapping("/query/appenv/{envId}")
	public ResultHandler<List<OpsBasePro>> queryProByEnvId(@PathVariable("envId") Long envId) {
		List<OpsBasePro> opsBasePros = opsBaseProService.selectByEnvid(envId);
		return ResultHandler.success(opsBasePros);
	}

	/**
	 * 根据环境ID查询应用版本中存在的项目
	 * 
	 * @param envId
	 * @return
	 */
	@ApiOperation(value = "查询应用版本的项目", notes = "根据环境ID查询应用版本中存在的项目")
	@RequestMapping(value = "/select_byenvid/{envId}", method = RequestMethod.GET)
	public ResultHandler<List<OpsBasePro>> queryAppVerProByEnvId(@PathVariable("envId") Long envId) {
		return ResultHandler.success(opsBaseProService.selectProByEnvid(envId));
	}

	/**
	 * 查询所有项目的数量
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有项目的数量", notes = "查询所有项目的数量")
	@RequestMapping(value = "/select/count", method = RequestMethod.GET)
	public ResultHandler<Integer> queryProjectCount() {
		return ResultHandler.success(opsBaseProService.queryProjectCount());
	}
	
	/**
	 * 查询所有项目的数量
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有项目的数量", notes = "查询所有项目的数量")
	@RequestMapping(value = "/select/dashboard/{envId}/{proId}", method = RequestMethod.GET)
	public ResultHandler<DashBoardProDeployAppVo> queryDashboard(@PathVariable Long envId, @PathVariable Long proId) {
		DashBoardProDeployAppVo dashboardDate = opsBaseProService.getDashboardDate(envId, proId);
		return ResultHandler.success(dashboardDate);
	}
}
