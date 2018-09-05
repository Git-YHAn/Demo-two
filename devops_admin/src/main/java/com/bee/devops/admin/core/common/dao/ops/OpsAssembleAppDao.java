package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import com.bee.devops.admin.core.vo.response.AppServiceManagerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
@Mapper
public interface OpsAssembleAppDao {
    int deleteByPrimaryKey(Long appEnvId);

    int insert(OpsAssembleApp record);

    int insertSelective(OpsAssembleApp record);

    AppServiceManagerVo selectByPrimaryKey(Long appEnvId);

    int updateByPrimaryKeySelective(OpsAssembleApp record);

    int updateByPrimaryKey(OpsAssembleApp record);
    
    int updateAppEnvByType(@Param("currentVersion")String currentVersion, @Param("deployType")String deployType,@Param("appEnvId")Long appEnvId);
    
	int selectByEnvid(Long envId);

	OpsAssembleApp getAppEnv(@Param("envId")Long envId, @Param("appId")Long appId);
	
	int selectByEnvidAppid(@Param("envId")Long envId, @Param("appId")Long appId);
	
	int selectByAppid(@Param("appId")Long appId);
	
	OpsAssembleApp selectAppEnvByAppid(@Param("appId")Long appId);
	
	List<AppServiceManagerVo> searchAppEnvs(@Param("envId")Long envId, @Param("proId")Long proId, @Param("appName")String appName);
	
	List<OpsAssembleApp> selectAllAppEnvs();
	
	Long[] selectAppidByEnvid(@Param("envId")Long envId);
	
	OpsAssembleApp getByappEnvId(@Param("appEnvId")Long appEnvId);
	
	OpsAssembleApp getBygiturl(@Param("giturl")String giturl);

	AppEnvProCodeVo getCodes(@Param("appEnvId") Long appEnvId);
}