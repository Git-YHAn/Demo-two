package com.bee.devops.admin.core.common.dao.admin;

import java.util.List;
import java.util.Map;

import com.bee.devops.admin.core.common.entity.admin.AdminLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.admin.AdminUser;
@Mapper
public interface AdminUserDao {
    int deleteByPrimaryKey(Long adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    AdminUser queryAdminUser(@Param("username") String username,@Param("password") String password);
    
    AdminUser loadUserByUsername(String username);
    
    List<AdminUser> getAllUserExceptAdmin(@Param("currentId") Long currentId);
    
    List<AdminUser> queryUsersByPageData(@Param("searchName") String searchName) ;

	Integer queryUsersCount();

	int insertUserRole(Map<String, Object> param);

	Long getUserId(String userName);

    int addLoginInfo(AdminLoginInfo adminLoginInfo);

    AdminLoginInfo queryLoginIp(String loginIp);

    int updataLoginInfo(AdminLoginInfo adminLoginInfo);
}