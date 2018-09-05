package com.bee.devops.admin.core.common.service.admin;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.utils.IpAdrressUtil;
import com.bee.devops.admin.core.common.dao.admin.AdminRoleDao;
import com.bee.devops.admin.core.common.dao.admin.AdminUserDao;
import com.bee.devops.admin.core.common.entity.admin.AdminLoginInfo;
import com.bee.devops.admin.core.common.entity.admin.AdminResource;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by yangliang on 2018/03/19.
 */
@Service
@Transactional
public class AdminUserService {

    @Autowired
    AdminUserDao adminUserDao;
    @Autowired
    AdminRoleDao adminRoleDao;
    
    public AdminUser login(String username,String password){
    	return null;
    }

    public List<AdminUser> getUsersByKeywords(String keywords) {
        return null;
    }

    public int updateUser(AdminUser user) {
        return adminUserDao.updateByPrimaryKeySelective(user);
    }

    public AdminUser getUserById(Long userId) {
        return adminUserDao.selectByPrimaryKey(userId);
    }

    @Transactional
    public int deleteUserById(Long userId) {
    	adminRoleDao.deleteUserRole(userId);
        return adminUserDao.deleteByPrimaryKey(userId);
    }

    public List<AdminUser> getAllUserExceptAdmin(Long userId) {
        return adminUserDao.getAllUserExceptAdmin(userId);
    }
    
    public int insertUser(AdminUser adminUser) {
    	if(adminUserDao.loadUserByUsername(adminUser.getUsername()) != null){
    		return -1;
    	}
        return adminUserDao.insertSelective(adminUser);
    }
    
    public AdminUser queryAdminUser(String username,String password) {
        return adminUserDao.queryAdminUser(username,password);
    }

	public PageBean<AdminUser> queryUsersByPageData(int pageOn , int pageSize , String searchName){
		PageHelper.startPage(pageOn, pageSize);
		List<AdminUser> lists = adminUserDao.queryUsersByPageData(searchName);
		return new PageBean<>(lists) ;
	}

	public Integer queryUsersCount() {
		return adminUserDao.queryUsersCount();
	}

	public int insertUserRole(Map<String, Object> param) {
		return adminUserDao.insertUserRole(param);
	}

	public Long getUserId(String userName) {
		return adminUserDao.getUserId(userName);
	}

    /**
     * 用户登陆限制
     * @param username
     * @param password
     * @param request
     * @return 0：登陆成功， -1：IP被ban，-2：用户被禁用， 1：用户名密码错误
     */
	public Integer userLoginLimit(String username, String password, HttpServletRequest request) {
        // 获取用户登陆IP
        String ip = IpAdrressUtil.getIpAdrress(request);
        // 查询IP是否已记录
        AdminLoginInfo adminLoginInfo = adminUserDao.queryLoginIp(ip);
        if (adminLoginInfo == null) {
            // 未查到相关记录
            // 新增此IP的登录信息
            AdminLoginInfo loginInfo = new AdminLoginInfo();
            loginInfo.setLoginIp(ip);
            loginInfo.setLoginNum(0);
            loginInfo.setLoginUsername(username);
            adminUserDao.addLoginInfo(loginInfo);
            adminLoginInfo = loginInfo;
        }
        if (adminLoginInfo.getLoginNum() < 5){
            // 记录本次登陆的用户名
            adminLoginInfo.setLoginUsername(username);
            // Ip地址的登陆次数+1
            adminLoginInfo.setLoginNum(adminLoginInfo.getLoginNum() + 1);
            // 修改此IP的登陆信息
            adminUserDao.updataLoginInfo(adminLoginInfo);

            // 查询用户名是否存在
            AdminUser adminUser = adminUserDao.loadUserByUsername(username);
            // 用户名存在
            if (adminUser != null) {
                // 判断用户是否被禁用
                if (adminUser.getIsActive() == 0) {
                    return -2;
                }
                // 判断密码是否正确
                if (!adminUser.getPassword().equals(password)) {
                    // 获取该用户登陆次数
                    Integer loginNum = adminUser.getLoginNum();
                    // 用户的登陆次数+1
                    adminUser.setLoginNum(loginNum + 1);
                    // 若用户登陆密码输入3次错误，则禁用此用户
                    if(loginNum >= 2) {
                        adminUser.setIsActive(0);
                        adminUserDao.updateByPrimaryKeySelective(adminUser);
                        return -2;
                    } else {
                        adminUserDao.updateByPrimaryKeySelective(adminUser);
                        return 1;
                    }
                } else {
                    // 清空IP的登陆次数
                    adminLoginInfo.setLoginNum(0);
                    int i = adminUserDao.updataLoginInfo(adminLoginInfo);
                    // 清空用户的登陆次数
                    adminUser.setLoginNum(0);
                    adminUserDao.updateByPrimaryKeySelective(adminUser);
                    return 0;
                }
            }
        } else {
            return -1;
        }
        return 1;
    }
}
