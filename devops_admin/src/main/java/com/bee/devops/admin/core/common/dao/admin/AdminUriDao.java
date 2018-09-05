package com.bee.devops.admin.core.common.dao.admin;

import com.bee.devops.admin.core.common.entity.admin.AdminUri;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接口mapper
 *
 * @author wanghuajie
 * @date 2018/9/3 16:31
 */
@Mapper
public interface AdminUriDao {
    /**
     * 获取接口列表
     *
     * @return
     */
    List<AdminUri> getUriList();

    /**
     * 添加接口
     *
     * @param adminUri
     * @return
     */
    Integer addUri(AdminUri adminUri);

    /**
     * 根据接口id删除接口
     *
     * @param interfaceId
     * @return
     */
    Integer deleteUri(Long interfaceId);

    /**
     * 编辑接口
     *
     * @param adminUri
     * @return
     */
    Integer editUri(AdminUri adminUri);

    /**
     * 根据接口id获取接口信息
     *
     * @param interfaceId
     * @return
     */
    AdminUri getUriById(Long interfaceId);

    /**
     * 获取当前登录用户可以访问的接口集合
     *
     * @param userId 当前登录用户id
     * @return
     */
    List<String> getPermissions(Long userId);

    /**
     * 根据接口id删除  <资源-接口>关联表中对应的记录
     *
     * @param interfaceId
     */
    void deleteResourceWithInterfaceId(Long interfaceId);
}
