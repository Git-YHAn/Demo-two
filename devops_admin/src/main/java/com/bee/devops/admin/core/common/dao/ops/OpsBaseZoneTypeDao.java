package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseZoneType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpsBaseZoneTypeDao {
    int deleteByPrimaryKey(Long zoneTypeId);

    int insert(OpsBaseZoneType zoneType);

    OpsBaseZoneType selectByPrimaryKey(Long profileTypeId);

    String selectZoneTypeNameById(Long profileTypeId);

    int updateByPrimaryKey(OpsBaseZoneType zoneType);

    List<OpsBaseZoneType> queryAllZoneTypes();
}