package com.bee.devops.admin.core.common.service.admin;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.admin.AdminUriDao;
import com.bee.devops.admin.core.common.entity.admin.AdminUri;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 接口service
 *
 * @author wanghuajie
 * @date 2018/9/3 16:53
 */
@Service
public class AdminUriService {
    @Autowired
    private AdminUriDao adminUriDao;

    public PageBean<AdminUri> getUriList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminUri> uriList = adminUriDao.getUriList();
        return new PageBean<>(uriList);
    }

    public Integer addUri(AdminUri adminUri) {
        return adminUriDao.addUri(adminUri);
    }

    /**
     * 删除接口的同时,应该同步删除<资源-接口>表中的关联信息
     *
     * @param interfaceId 接口id
     * @return
     */
    @Transactional
    public Integer deleteUri(Long interfaceId) {
        // 删除资源-接口关联信息
        adminUriDao.deleteResourceWithInterfaceId(interfaceId);
        return adminUriDao.deleteUri(interfaceId);
    }

    public Integer editUri(AdminUri adminUri) {
        return adminUriDao.editUri(adminUri);
    }

    public AdminUri getUriById(Long interfaceId) {
        return adminUriDao.getUriById(interfaceId);
    }

    /**
     * 获取当前登录用户可以访问的接口集合
     *
     * @param userId 当前登录用户id
     * @return
     */
    public List<String> getPermissions(Long userId) {
        return adminUriDao.getPermissions(userId);
    }
}
