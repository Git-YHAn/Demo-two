package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseRegionType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpsBaseRegionTypeDao {
    int deleteByPrimaryKey(Long regionTypeId);

    int insert(OpsBaseRegionType regionType);

    OpsBaseRegionType selectByPrimaryKey(Long regionTypeId);

    int updateByPrimaryKey(OpsBaseRegionType regionType);

    String selectRegionTypeNameById(Long regionTypeId);

    List<OpsBaseRegionType> queryAllRegionTypes();
}