package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.ApplicationVersionResponse;

@Mapper
public interface OpsVersionAppDao {
	int deleteByPrimaryKey(Long appVerId);

	int insert(OpsVersionApp record);

	int insertSelective(OpsVersionApp record);

	OpsVersionApp selectByPrimaryKey(Long appVerId);

	int updateByPrimaryKeySelective(OpsVersionApp record);

	int updateByPrimaryKey(OpsVersionApp record);

	AppEnvProCodeVo getCodes(@Param("envId") Long envId, @Param("appId") Long appId, @Param("proId") Long proId);

	List<OpsAssembleApp> selectByEnvId(Long envId);

	Long getMaxAppVersionId(@Param("appId") Long appId);

	String getMaxVersionCode(@Param("appId") Long appId, @Param("todayTime") String todayTime);

	ApplicationVersionResponse getDataByVersionCode(@Param("versionCode") String versionCode);

	OpsVersionApp getDataByAppVerId(@Param("appVerId") Long appVerId);

	List<OpsVersionApp> searchAppVersion(@Param("envId") Long envId, @Param("proId") Long proId, @Param("appName") String appName);

	List<OpsVersionApp> searchAppVersionByid(@Param("envId") Long envId, @Param("proId") Long proId, @Param("appId") Long appId);

	/**
	 * 根据应用id获取最新版本号
	 *
	 * @param appId
	 *            应用id
	 * @return
	 */
	String getNewestVersion(Long appId);

	List<OpsVersionApp> queryAppVersionsByAppEnvId(@Param("appId") Long appId);

	/**
	 * 根据项目id获取应用版本未使用数量
	 *
	 * @param proId
	 *            项目id
	 * @return
	 */
	Integer getAppVersionNotUsedCount(Long proId);

	/**
	 * 根据项目id和应用id获取对应的项目及应用编码
	 *
	 * @param appId
	 * @param proId
	 * @return
	 */
	AppEnvProCodeVo getProCodeAndAppCode(@Param("appId") Long appId, @Param("proId") Long proId);

	/**
	 * 根据提交记录ID查询应用版本
	 * 
	 * @param commitId
	 * @return
	 */
	List<OpsVersionApp> queryVersionByCommitId(@Param("commitId") String commitId);
}