package com.bee.devops.admin.core.common.dao.admin;

import java.util.HashMap;
import java.util.List;

import com.bee.devops.admin.core.vo.response.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.admin.AdminRole;

@Mapper
public interface AdminRoleDao {
	List<AdminRole> queryRoleList();

	List<AdminRole> queryAllRole(@Param("roleName")String roleName);
	
	Integer addRole(HashMap<String, Object> map);

	Integer deleteRole(@Param("roleId")Long roleId);
	
	AdminRole selectRoleById(@Param("roleId")Long roleId);
	
	Integer updateRole(AdminRole adminRole);
	
	Integer addRoleInfo(AdminRole adminRole);
	
	AdminRole selectRoleByName(@Param("roleName")String roleName);

	void deleteRoleResource(@Param("roleId")Long roleId);

	void insertRoleResource(@Param("roleId")Long roleId,@Param("resourceIds") List<Long> resourceIds);

	void deleteRoleResourceByResourceId(@Param("resourceId")Long resourceId);

	List<Long> queryRoleIdsByUser(@Param("userId")Long userId);

	List<AdminRole> queryRolesByUserId(@Param("userId")Long userId);
	
	void deleteUserRole(@Param("userId")Long userId);

	void insertUserRole(@Param("userId")Long userId,@Param("roleIds") List<Long> roleIds);

	void deleteUserRoleByRoleId(@Param("roleId")Long roleId);

	UserInfo getUserCountInfo();
}
