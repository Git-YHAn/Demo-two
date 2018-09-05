/**
 * @author heping
 * @date2018年4月23日
 */
package com.bee.devops.admin.core.common.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.admin.AdminResource;

@Mapper
public interface AdminResourceDao {

    List<AdminResource> querySysResourceList();

    AdminResource getDetailByResourceId(Long resourceId);

    List<AdminResource> getListByParentId(Long parentId);

    int roleAddResource(Map<String, Object> map);

    List<Long> getResourceIdList(Long adminUserId);

    Integer addResource(AdminResource sysRes);

    Integer deleteResource(Long resourceId);

    Integer editResource(AdminResource sysRes);

    List<Long> queryResourceIdsByRole(@Param("roleId") Long roleId);

    List<Long> queryParentIdList(@Param("resourceIds") List<Long> resourceIds);

    Integer batchAddResources(List<AdminResource> resources);

    AdminResource getTemplateByResourceName(@Param("resourceName") String templateResourceName);

    /**
     * 获取当前登录用户在指定模块下的资源列表
     *
     * @param moduleId 模块id
     * @param userId   当前登录用户id
     */
    List<AdminResource> getResourcesOnModule(@Param("moduleId") Long moduleId, @Param("userId") Long userId);

    /**
     * 根据资源id从<资源-接口>关联表中删除该资源对应的所有接口
     *
     * @param resourceId 资源id
     */
    void deleteResourceUri(Long resourceId);

    /**
     * 重新在<资源-接口>关联表中插入 资源绑定的多个接口
     *
     * @param resourceId   资源id
     * @param interfaceIds 接口id
     */
    void insertResourceUri(@Param("resourceId") Long resourceId, @Param("interfaceIds") List<Long> interfaceIds);

    /**
     * 删除模块,同步删除模块下的资源
     *
     * @param moduleId 模块id
     * @return
     */
    void deleteResourceWithModule(Long moduleId);

    /**
     * 根据模块id获取该模块下的所有资源
     *
     * @param moduleId 模块id
     * @return
     */
    List<Long> getResourceIdsByModuleId(Long moduleId);
}
