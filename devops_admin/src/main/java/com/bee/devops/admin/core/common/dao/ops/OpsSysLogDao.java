package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsSysLog;

@Mapper
public interface OpsSysLogDao {
	int insertLogRecord(OpsSysLog log);
	
	Long insertLogType(OpsSysLog log);
	
	Long selectLogTypeByName(String typeName);
	
	OpsSysLog selectLogTypeById(Long typeId);
	
	List<OpsSysLog> selectLogType();
	
	int updateSysLogTypeIsEnable(@Param("status")Integer status, @Param("typeId")Long typeId);
	
	List<OpsSysLog> selectSysLogRecord(@Param("searchName")String searchName, @Param("typeId")Long typeId, @Param("startTime")String startTime, @Param("endTime")String endTime);
}
