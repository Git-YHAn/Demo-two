package com.bee.devops.admin.core.controller.admin;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.admin.AdminRole;
import com.bee.devops.admin.core.common.service.admin.AdminRoleService;
import com.bee.devops.admin.core.vo.request.RoleRequest;
import com.bee.devops.admin.core.vo.request.RoleResource;

@RestController
@RequestMapping(value = "/role")
public class AdminRoleController extends BaseController {
	final static Logger log = Logger.getLogger(AdminRoleController.class);
	@Autowired
	AdminRoleService adminRoleService;
	
	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResultHandler<List<AdminRole>> queryAllRoleList(){
		List<AdminRole> adminRoles = adminRoleService.queryAllRoleList();
		return ResultHandler.success(adminRoles);
	}
	
	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value={"/list/{pageNum}/{pageSize}/{roleName}","/list/{pageNum}/{pageSize}"}, method=RequestMethod.GET)
	public ResultHandler<PageBean<AdminRole>> queryRoleList(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
	        @PathVariable(required = false, name = "roleName") String roleName){
		PageBean<AdminRole> adminRoles = adminRoleService.queryRoleList(pageNum, pageSize,roleName);
		return ResultHandler.success(adminRoles);
	}
	
	/**
	 * 根据角色ID查询角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value={"/query/byid/{roleId}"}, method=RequestMethod.GET)
	public ResultHandler<AdminRole> queryRoleByid(@PathVariable(required = false, name = "roleId") Long roleId){
		AdminRole adminRole = adminRoleService.getRoleById(roleId);
		return ResultHandler.success(adminRole);
	}
	
	/**
	 * 添加角色
	 * @author Yang Chunhai	 
	 * @param roleInfo
	 * @return
	 */
	@PostMapping("/addRole")
	public ResultHandler<String> addRole(@RequestBody RoleRequest roleInfo){
		AdminRole role = new AdminRole();
		role.setCreateUsername(getAdminUser().getUsername());
		role.setRoleName(roleInfo.getRoleName());
		role.setDescription(roleInfo.getDescription());
		Integer result = adminRoleService.addRoleInfo(role);
		if(result == 1){
			return ResultHandler.success("添加成功");
		}else if(result == 2){
			return ResultHandler.error("添加失败，存在相同角色名");
		}
		return ResultHandler.error("添加失败");
	}
	/*
	 * 删除角色
	 */
	@RequestMapping(value="/delete/{roleId}",method = RequestMethod.DELETE)
	public ResultHandler<String> delRole(@PathVariable Long roleId){
		Integer res = adminRoleService.deleteRole(roleId);
		if (res == 1) {
			return ResultHandler.success("删除成功");
		}
		return ResultHandler.error("删除失败!");
	}
	
	/**
	 * 修改角色
	 * @author Yang Chunhai	 
	 * @param roleInfo
	 * @return
	 */
	@PostMapping("/update")
	public ResultHandler<String> updateRole(@RequestBody RoleRequest roleInfo){
		AdminRole role = adminRoleService.getRoleById(roleInfo.getRoleId());
		if(role != null){
			AdminRole queryByName = adminRoleService.queryByName(roleInfo.getRoleName());
			if(queryByName != null && queryByName.getRoleId() != roleInfo.getRoleId()){
				return ResultHandler.error("修改的角色名与其他角色名重复");
			}
			role.setRoleName(roleInfo.getRoleName());
			role.setDescription(roleInfo.getDescription());
			role.setUpdateTime(new Date());
			role.setUpdateUsername(getAdminUser().getUsername());
			if(adminRoleService.updateRole(role) == 1){
				return ResultHandler.success("修改成功");
			}
		}
		return ResultHandler.error("修改失败");
	}
	
	/**
	 * 角色添加资源
	 * @return
	 */
	@RequestMapping(value = "/update/resource", method = RequestMethod.POST)
	public ResultHandler<String> updateRoleResource(@RequestBody RoleResource roleResource){
		List<Long> resourceIds =  roleResource.getResourceId();
		Long roleId = roleResource.getRoleId();
		adminRoleService.updateRoleResource(roleId, resourceIds);
		return ResultHandler.success("success");
	}
	
}
