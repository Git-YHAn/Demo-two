package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDep;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDepHis;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppImg;
import com.bee.devops.admin.core.vo.response.DeployAppVersionVo;
import com.bee.devops.admin.core.vo.response.PublishVersionResponse;
@Mapper
public interface OpsVersionAppDepDao {
	/**
	 * 通过id删除
	 * @param depAppVerId
	 * @return
	 */
    int deleteByPrimaryKey(Long depAppVerId);

    /**
     * 完整添加
     * @param record
     * @return
     */
    int insert(OpsVersionAppDep record);

    /**
     * 模糊添加
     * @param record
     * @return
     */
    int insertSelective(OpsVersionAppDep record);

    /**
     * 根据id查询
     * @param depAppVerId
     * @return
     */
    OpsVersionAppDep selectByPrimaryKey(Long depAppVerId);

    /**
     * 根据id模糊修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OpsVersionAppDep record);

    /**
     * 根据id修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(OpsVersionAppDep record);

	List<DeployAppVersionVo> searchAppVersion(@Param("envId")Long envId, @Param("proId")Long proId,@Param("appName") String appName);
    
	String getMaxDeployVersion(@Param("appEnvId") Long appEnvId, @Param("todayTime")String todayTime);
	
	String getMaxDeployVersionHis(@Param("appEnvId") Long appEnvId, @Param("todayTime")String todayTime);
	
    DeployAppVersionVo getDataByVersionCode(@Param("versionCode") String versionCode);
    
    List<OpsVersionAppImg> queryAllImagesByPageData(@Param("envid") Long envid, @Param("proid") Long proid, @Param("appid") Long appid);

    /**
     * 获取最新版本号
     *
     * @param appEnvId 应用id
     * @return
     */
    String getNewestVersion(Long appEnvId);
    
    String getNewestVersionHis(Long appEnvId);
    
    OpsVersionAppDep getMaxDeployVersion(@Param("appEnvId") Long appEnvId);
    
    Long getMaxVersionId(@Param("appEnvId") Long appEnvId);
    
    List<OpsVersionAppDep> queryDeployAppVersionsByAppEnvId(@Param("appEnvId") Long appEnvId);
    
    List<PublishVersionResponse> queryWaitEnactApps(@Param("proId") Long proId, @Param("envId") Long envId);

    /**
     * 根据环境id和项目id获取发布版本未使用数量
     *
     *
     * @param envId 环境id
     * @param proId 项目id
     * @return
     */
    Integer getReleaseVersionNotUsedCount(@Param("envId") Long envId,@Param("proId") Long proId);
    
    Integer insertDeeAppVerHis(OpsVersionAppDepHis opsVersionAppDepHis);
    
    Integer updateDeeAppVerHis(OpsVersionAppDepHis opsVersionAppDepHis);
    
    List<OpsVersionAppDepHis> queryMakingVersion(@Param("proId") Long proId, @Param("envId") Long envId);
    
    Integer changeVersionStatus(@Param("appDepVerHisId")Long appDepVerHisId);
    
    OpsVersionAppDepHis getVerHisByStatusId(@Param("appEnvId")Long appEnvId);
}