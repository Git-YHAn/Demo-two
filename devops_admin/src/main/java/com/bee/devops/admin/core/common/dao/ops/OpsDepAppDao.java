package com.bee.devops.admin.core.common.dao.ops;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;
import com.bee.devops.admin.core.vo.response.OpsDepAppResponse;

@Mapper
public interface OpsDepAppDao {

	List<OpsDepAppResponse> queryPublishApps(@Param("proId") Long proId, @Param("envId") Long envId);
	
	List<OpsDepApp> queryWaitPublishAppsByCon(@Param("proId") Long proId, @Param("envId") Long envId);
	
	List<OpsDepApp> queryPublishingApps(@Param("proId") Long proId, @Param("envId") Long envId);
	
	List<OpsDepApp> queryPhyPublishingApps(@Param("proId") Long proId, @Param("envId") Long envId);
	
	List<OpsDepApp> queryConPublishingApps(@Param("proId") Long proId, @Param("envId") Long envId);
	
	void save(OpsDepApp opsDepApp);

	OpsDepApp getAppByAppEnv(@Param("appEnvId")Long appEnvId,@Param("serverId") Long serverId);

	void updateStatus(@Param("oldStatus")Integer oldStatus,@Param("newStatus") Integer newStatus,@Param("publishId") Long publishId);

	void updateDeployAppVersion(@Param("versionId")Long versionId,@Param("versionCode")  String versionCode,@Param("publishId")  Long publishId);

	OpsDepApp getByPrimaryKey(@Param("publishId")long publishId);

	void delete(@Param("publishId") Long publishId);
	
	void updatePublishDate(@Param("publishDate")Date publishDate, @Param("publishId")Long publishId);
}
