package com.bee.devops.admin.core.common.dao.ops;

import org.apache.ibatis.annotations.Mapper;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
@Mapper
public interface OpsBaseAppTypeDao {
    int deleteByPrimaryKey(Long appTypeId);

    int insert(OpsBaseAppType record);

    int insertSelective(OpsBaseAppType record);

    OpsBaseAppType selectByPrimaryKey(Long appTypeId);

    int updateByPrimaryKeySelective(OpsBaseAppType record);

    int updateByPrimaryKey(OpsBaseAppType record);
}