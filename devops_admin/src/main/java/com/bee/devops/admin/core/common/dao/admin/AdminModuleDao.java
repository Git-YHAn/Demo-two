package com.bee.devops.admin.core.common.dao.admin;

import com.bee.devops.admin.core.common.entity.admin.AdminModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminModuleDao {

    int addModule(AdminModule adminModule);

    int editModule(AdminModule adminModule);

    int deleteModule(Long moduleId);

    /**
     * 根据父模块id获取子模块列表
     *
     * @param parentId 父模块id
     * @return
     */
    List<AdminModule> getModulesByParentId(Long parentId);

}
