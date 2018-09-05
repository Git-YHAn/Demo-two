package com.bee.devops.admin.core.controller.admin;

import java.util.Date;
import java.util.List;

import com.bee.devops.admin.core.vo.response.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.service.admin.AdminUserService;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.service.admin.AdminRoleService;
import com.bee.devops.admin.core.vo.request.UserRequest;
import com.bee.devops.admin.core.vo.request.UserRoleRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理模块Controller")
@RestController
@RequestMapping(value = "/user")
public class AdminUserController extends BaseController {
	final static Logger log = Logger.getLogger(AdminUserController.class);

	private final static String PASS_PARAM = "adminuser";
	@Autowired
	AdminUserService adminUserService;
	@Autowired
	AdminRoleService adminRoleService;

	/**
	 * 当前用户信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "当前用户信息", notes = "查询当前用户信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ResultHandler<AdminUser> getUserInfo() {
		return ResultHandler.success(getAdminUser());
	}

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
	public ResultHandler<AdminUser> getUserByuserId(@PathVariable Long userId) {
		AdminUser adminUser = adminUserService.getUserById(userId);
		return ResultHandler.success(adminUser);
	}

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "通过id删除用户")
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	public ResultHandler<String> deleteUserById(@PathVariable Long userId) {
		if (adminUserService.deleteUserById(userId) == 1) {
			return ResultHandler.success("删除成功!");
		}
		return ResultHandler.error("删除失败!");
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "更新用户信息", notes = "根据传入的信息来修改用户信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultHandler<String> updateUser(@RequestBody UserRequest user) {
		AdminUser adminUser = adminUserService.getUserById(user.getAdminUserId());
		if (adminUser == null) {
			return ResultHandler.error("更新失败");
		}
		adminUser.setRealName(user.getRealName());
		adminUser.setUsername(user.getUsername());
		adminUser.setMobile(user.getMobile());
		adminUser.setEmail(user.getEmail());
		if (!adminUser.getPassword().equals(user.getPassword())) {
			String encodePassword = new Md5PasswordEncoder().encodePassword(user.getPassword(), user.getUsername() + PASS_PARAM);
			adminUser.setPassword(encodePassword);
		} else {
			adminUser.setPassword(null);
		}
		adminUser.setIsActive(user.getIsActive());
		adminUser.setUpdateTime(new Date());
		if (adminUserService.updateUser(adminUser) == 1) {
			return ResultHandler.success("更新成功!");
		}
		return ResultHandler.error("更新失败!");
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@ApiOperation(value = "添加用户", notes = "根据传入的用户信息参数添加用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultHandler<String> addAdminUser(@RequestBody UserRequest user) {
		AdminUser adminUser = new AdminUser();
		adminUser.setRealName(user.getRealName());
		adminUser.setMobile(user.getMobile());
		adminUser.setEmail(user.getEmail());
		adminUser.setUsername(user.getUsername());
		adminUser.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), user.getUsername() + PASS_PARAM));
		adminUser.setIsActive(user.getIsActive());
		int i = adminUserService.insertUser(adminUser);
		if (i == 1) {
			return ResultHandler.success("新增用户成功");
		} else if (i == -1) {
			return ResultHandler.error("用户名重复，新增失败!");
		}
		return ResultHandler.error("新增失败!");
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@RequestMapping(value = {"/search/{pageNum}/{pageSize}/{searchName}","/search/{pageNum}/{pageSize}"}, method = RequestMethod.GET)
	public ResultHandler<PageBean<AdminUser>> queryAdminUsersByPageData(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
	        @PathVariable(required = false, name = "searchName") String searchName) {
		PageBean<AdminUser> pageBean = adminUserService.queryUsersByPageData(pageNum, pageSize, searchName);
		return ResultHandler.success(pageBean);
	}

	/**
	 * 查询用户总数量
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取用户数量", notes = "获取用户数量")
	@RequestMapping(value = "/query_users_count", method = RequestMethod.GET)
	public ResultHandler<Integer> queryUsersCount() {
		return ResultHandler.success(adminUserService.queryUsersCount());
	}

	/**
	 * 查询用户角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query/role/{userId}", method = RequestMethod.GET)
	public ResultHandler<List<Long>> queryUserRole(@PathVariable("userId") Long userId) {
		List<Long> list = adminRoleService.queryRoleIdsByUser(userId);
		return ResultHandler.success(list);
	}

	/**
	 * 保存用户角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save/role", method = RequestMethod.POST)
	public ResultHandler<String> saveUserRole(@RequestBody UserRoleRequest userRole) {
		Long userId = userRole.getUserId();
		List<Long> roleIds = userRole.getRoleId();
		adminRoleService.updateUserRole(userId, roleIds);
		return ResultHandler.success("保存成功");
	}

	/**
	 * 获取禁用和启用的用户数量
	 *
	 * @return
	 */
	@RequestMapping(value = "/count/info", method = RequestMethod.GET)
	public ResultHandler<UserInfo> getUserCountInfo() {
		UserInfo userInfo = adminRoleService.getUserCountInfo();
		return ResultHandler.success(userInfo == null ? new UserInfo() : userInfo);
	}
}
