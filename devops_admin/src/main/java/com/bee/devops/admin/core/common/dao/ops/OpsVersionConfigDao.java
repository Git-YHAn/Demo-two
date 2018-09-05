package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsVersionConfig;
import com.bee.devops.admin.core.vo.response.CodeVo;
import com.bee.devops.admin.core.vo.response.ConfigVersionVo;
@Mapper
public interface OpsVersionConfigDao {
    int deleteByPrimaryKey(Long configVerId);

    int insert(OpsVersionConfig opsVersionConfig);

    int insertSelective(OpsVersionConfig opsVersionConfig);

    OpsVersionConfig selectByPrimaryKey(Long configVerId);

    int updateByPrimaryKeySelective(OpsVersionConfig opsVersionConfig);

    int updateByPrimaryKey(OpsVersionConfig opsVersionConfig);
    
    CodeVo getCode(@Param("envId") Long envId, @Param("appId") Long appId, @Param("proId") Long proId);
   
    List<ConfigVersionVo> queryConfigVersionByPageData(@Param("envId") Long envId, @Param("proId") Long proId, @Param("appName") String appName);

    List<ConfigVersionVo> queryConfigVersionByid(@Param("envId") Long envId, @Param("proId") Long proId, @Param("appId") Long appId);
    
    Long getMaxConfigVersionId(@Param("appEnvId") Long appEnvId);
    
    String getMaxConfigVersion(@Param("appEnvId") Long appEnvId, @Param("todayTime")String todayTime);
    
    ConfigVersionVo getDataByVersionCode(@Param("versionCode") String versionCode);
    
    OpsVersionConfig getDataByConfigVerId(@Param("configVerId") Long configVerId);
    
    List<OpsVersionConfig> queryConfigVersionsByAppEnvId(@Param("appEnvId") Long appEnvId);

    /**
     * 获取最新版本号
     *
     * @param appEnvId 应用id
     * @return
     */
    String getNewestVersion(Long appEnvId);

    /**
     * 根据环境id和项目id获取配置版本未使用数量
     *
     *
     * @param envId 环境id
     * @param proId 项目id
     * @return
     */
    Integer getConfigVersionNotUsedCount(@Param("envId") Long envId,@Param("proId") Long proId);

}

	