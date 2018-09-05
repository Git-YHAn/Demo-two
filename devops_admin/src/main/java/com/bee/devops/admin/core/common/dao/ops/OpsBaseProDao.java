package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
@Mapper
public interface OpsBaseProDao {
    int deleteByPrimaryKey(Long id);

    int insert(OpsBasePro record);
    
    int insertSelective(OpsBasePro record);

    List<OpsBasePro> selectSelectiveProjects(@Param("name")String searchName);
    
    List<OpsBasePro>	getAllProjects();
    
    OpsBasePro selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpsBasePro record);

    int updateByPrimaryKey(OpsBasePro record);
    
    int selectByProjectCode(@Param("proCode")String proCode);
    
	List<OpsBasePro> selectProByEnvid(Long envId);
	
	List<OpsBasePro> selectByEnvid(Long envId);

	Integer queryProjectCount();

	List<Map<String, Object>> getEnvNameList(Long proId);

	List<Map<String,Object>> getAppService(Map<String,Object> param);

	List<Map<String, Object>> getServiceInfo(Long appEvnId);
	
	String getWebHookByProId(Long ProId);
}