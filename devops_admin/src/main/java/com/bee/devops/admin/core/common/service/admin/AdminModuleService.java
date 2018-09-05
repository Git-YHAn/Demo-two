package com.bee.devops.admin.core.common.service.admin;

import com.bee.devops.admin.core.common.dao.admin.AdminModuleDao;
import com.bee.devops.admin.core.common.dao.admin.AdminResourceDao;
import com.bee.devops.admin.core.common.entity.admin.AdminModule;
import com.bee.devops.admin.core.common.entity.admin.AdminResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminModuleService {
    final static Logger log = Logger.getLogger(AdminModuleService.class);
    @Autowired
    private AdminModuleDao adminModuleDao;
    @Autowired
    private AdminResourceDao adminResourceDao;

    public int addModule(AdminModule adminModule) {
        return adminModuleDao.addModule(adminModule);
    }

    public int editModule(AdminModule adminModule) {
        return adminModuleDao.editModule(adminModule);
    }

    /**
     * 删除模块,同步删除模块下的资源
     *
     * @param moduleId 模块id
     * @return
     */
    @Transactional
    public int deleteModule(Long moduleId) {
        // 先根据模块id获取该模块下的所有资源
        List<Long> resourceIds = adminResourceDao.getResourceIdsByModuleId(moduleId);
        // 同步删除该模块下的所有资源
        adminResourceDao.deleteResourceWithModule(moduleId);
        // 同步删除该模块下的资源对应的接口
        for (Long resourceId : resourceIds) {
            adminResourceDao.deleteResourceUri(resourceId);
        }
        return adminModuleDao.deleteModule(moduleId);
    }

    /**
     * 获取模块列表,初始parentId=0,测试  userId=100 --> roleId=59
     *
     * @return
     */
    public List<AdminModule> getModules(Long parentId, Long userId) {
        List<AdminModule> modules = adminModuleDao.getModulesByParentId(parentId);
        for (AdminModule module : modules) {
            Long moduleId = module.getModuleId();
            // 获取当前登录用户在此模块下的资源列表
            List<AdminResource> resourceList = adminResourceDao.getResourcesOnModule(moduleId, userId);
            module.setResources(resourceList);
            List<AdminModule> children = adminModuleDao.getModulesByParentId(moduleId);
            if (children.size() > 0) {
                module.setChildren(getModules(moduleId, userId));
            }
        }
        return modules;
    }
}
