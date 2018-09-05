package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsConfig;

@Mapper
public interface OpsConfigDao {

	void addConfigRecord(OpsConfig record);

	void updateConfigRecord(OpsConfig record);

	void updateAuditInfo(OpsConfig record);

	void updateConfigRecordStatus(@Param("recordId") Long recordId, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

	void updateConfigCommitMessage(@Param("recordId")Long recordId, @Param("commitMessage")String commitMessage);
	
	List<OpsConfig> queryConfigRecordsByUser(@Param("userId") Long userId, @Param("proId") Long proId, @Param("envId") Long envId,
	        @Param("appId") Long appId);

	List<OpsConfig> queryConfigRecords(@Param("proId") Long proId, @Param("envId") Long envId, @Param("appId") Long appId);

	List<OpsConfig> queryCommitConfigRecords(@Param("proId") Long proId, @Param("envId") Long envId, @Param("appId") Long appId);
	
	OpsConfig getConfigRecord(@Param("recordId") Long recordId);

	OpsConfig getConfigRecordByAppBranch(@Param("appEnvId") Long appEnvId, @Param("branchName") String branchName);

    void deleteRecord(String branchName);
}