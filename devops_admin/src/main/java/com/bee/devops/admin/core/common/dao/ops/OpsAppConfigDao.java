package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsAppConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpsAppConfigDao {

    void addAppConfigRecord(OpsAppConfig record);

    void updateAppConfigRecord(OpsAppConfig record);

    void updateAuditInfo(OpsAppConfig record);

    void updateAppConfigRecordStatus(@Param("recordId") Long recordId, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

    List<OpsAppConfig> queryAppConfigRecordsByUser(@Param("userId") Long userId, @Param("proId") Long proId, @Param("appId") Long appId);

    List<OpsAppConfig> queryCommitAppConfigRecords(@Param("proId") Long proId, @Param("appId") Long appId);

    OpsAppConfig getAppConfigRecord(@Param("recordId") Long recordId);

    List<OpsAppConfig> getRecord(@Param("proId") Long proId, @Param("appId") Long appId, @Param("branch") String branch);
}