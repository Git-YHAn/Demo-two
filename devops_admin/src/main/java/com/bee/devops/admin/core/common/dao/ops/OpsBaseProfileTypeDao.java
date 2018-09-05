package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseProfileType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpsBaseProfileTypeDao {
    int deleteByPrimaryKey(Long profileTypeId);

    int insert(OpsBaseProfileType profileType);

    OpsBaseProfileType selectByPrimaryKey(Long profileTypeId);

    String selectProfileTypeNameById(Long profileTypeId);

    int updateByPrimaryKey(OpsBaseProfileType profileType);

    List<OpsBaseProfileType> queryAllProfileTypes();
}