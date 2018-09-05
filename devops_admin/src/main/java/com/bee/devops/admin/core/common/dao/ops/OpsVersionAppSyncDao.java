package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppSync;

@Mapper
public interface OpsVersionAppSyncDao {
	
	OpsVersionAppSync selectByKeyId(@Param("syncId") Long syncId);

	int insertSelective(OpsVersionAppSync opsVersionAppSync);
	
	List<OpsVersionAppSync> selectSelective(@Param("envId")Long envId, @Param("proId")Long proId, @Param("appName")String appName);
}
