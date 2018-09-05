package com.bee.devops.admin.core.common.service.admin;

import java.util.HashMap;
import java.util.List;

import com.bee.devops.admin.core.vo.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.admin.AdminRoleDao;
import com.bee.devops.admin.core.common.entity.admin.AdminRole;
import com.github.pagehelper.PageHelper;

/**
 * Created by yangliang on 2018/03/19.
 */
@Service
@Transactional
public class AdminRoleService {

	@Autowired
	AdminRoleDao adminRoleDao;

	public List<AdminRole> queryAllRoleList() {
		return adminRoleDao.queryRoleList();
	}
	
	public PageBean<AdminRole> queryRoleList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AdminRole> res = adminRoleDao.queryRoleList();
		return new PageBean<>(res);
	}

	/**
	 * 查询和按条件
	 * 
	 * @author Yang Chunhai
	 * @param pageNum
	 * @param pageSize
	 * @param roleName
	 * @return
	 */
	public PageBean<AdminRole> queryRoleList(int pageNum, int pageSize, String roleName) {
		PageHelper.startPage(pageNum, pageSize);
		List<AdminRole> res = adminRoleDao.queryAllRole(roleName);
		return new PageBean<>(res);
	}

	/**
	 * 增加用户
	 * 
	 * @author Yang Chunhai
	 * @param adminRole
	 * @return
	 */
	public Integer addRoleInfo(AdminRole adminRole) {
		AdminRole info = adminRoleDao.selectRoleById(adminRole.getRoleId());
		if(adminRole.getRoleName().equals(info.getRoleName())){
			return 2;
		}
		return adminRoleDao.addRoleInfo(adminRole);
	}

	public Integer addRole(HashMap<String, Object> map) {
		// 判断角色是否存在
		List<AdminRole> res = adminRoleDao.queryRoleList();
		for (AdminRole list : res) {
			if (list.getRoleName().equals(map.get("roleName"))) {
				return 2;
			}
		}
		return adminRoleDao.addRole(map);
	}

	@Transactional
	public Integer deleteRole(Long roleId) {
		adminRoleDao.deleteUserRoleByRoleId(roleId);
		adminRoleDao.deleteRoleResource(roleId);
		return adminRoleDao.deleteRole(roleId);
	}

	/**
	 * 通过角色ID查询角色
	 * 
	 * @author Yang Chunhai
	 * @param roleId
	 * @return
	 */
	public AdminRole getRoleById(Long roleId) {
		return adminRoleDao.selectRoleById(roleId);
	}

	/**
	 * 修改角色
	 * 
	 * @author Yang Chunhai
	 * @param adminRole
	 * @return
	 */
	public Integer updateRole(AdminRole adminRole) {
		return adminRoleDao.updateRole(adminRole);
	}

	/**
	 * 通过角色名查找角色
	 * 
	 * @author Yang Chunhai
	 * @param roleName
	 * @return
	 */
	public AdminRole queryByName(String roleName) {
		return adminRoleDao.selectRoleByName(roleName);
	}

	@Transactional
	public void updateRoleResource(Long roleId, List<Long> resourceIds) {
		adminRoleDao.deleteRoleResource(roleId);
		if (resourceIds.size() > 0) {
			adminRoleDao.insertRoleResource(roleId, resourceIds);
		}
	}

	public List<Long> queryRoleIdsByUser(Long userId) {
		return adminRoleDao.queryRoleIdsByUser(userId);
	}

	@Transactional
	public void updateUserRole(Long userId, List<Long> roleIds) {
		adminRoleDao.deleteUserRole(userId);
		if(!roleIds.isEmpty()){
			adminRoleDao.insertUserRole(userId, roleIds);
		}
	}

    public UserInfo getUserCountInfo() {
		return adminRoleDao.getUserCountInfo();
    }
}
