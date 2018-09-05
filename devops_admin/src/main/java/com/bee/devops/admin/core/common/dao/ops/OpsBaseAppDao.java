package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
@Mapper
public interface OpsBaseAppDao {
    int deleteByPrimaryKey(Long id);

    int insert(OpsBaseApp record);

    int insertSelective(OpsBaseApp record);

    List<OpsBaseApp> selectSelectiveApps(@Param("name")String searchName);
    
    List<OpsBaseApp> getAllApps();
    
    List<OpsBaseApp> searchAppsByPro(@Param("proId")Long proId,@Param("keyWord") String keyWord);
    
    List<OpsBaseApp> getProAllAppsByName(OpsBaseApp record);
    
    List<OpsBaseApp> selectNotSetApp(@Param("appIds") Long[] appIds,@Param("proId")Long proId);
    
    OpsBaseApp selectByPrimaryKey(@Param("appId")Long id);

    int updateByPrimaryKeySelective(OpsBaseApp record);

    int updateByPrimaryKey(OpsBaseApp record);
    
    int selectAppByCode(@Param("appCode")String appCode);
    
    List<OpsBaseApp> selectEnvInProApp(@Param("proId")Long proId,@Param("envId")Long envId);
    
    List<OpsBaseApp> selectAppVerInApp(@Param("proId")Long proId);
    
    int selectAppIsExist(@Param("appId") Long appId);

	Integer queryAppCount();
	
	List<OpsBaseAppType> queryAppTypes();

    OpsBaseApp getAppByGitUrl(String gitUrl);

    Integer getExistedGitUrlCount(@Param("gitUrl") String gitUrl, @Param("appId")Long appId);

    /**
     * 查询应用类型ID
     * @param appId 应用Id
     * @return 查询应用类型ID
     */
    Long getAppTypeId(@Param("appId") Long appId);
}