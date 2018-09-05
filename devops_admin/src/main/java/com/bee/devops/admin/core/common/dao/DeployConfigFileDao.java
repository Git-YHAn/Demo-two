//package com.bee.devops.admin.core.common.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import com.bee.devops.admin.core.common.entity.DeployConfigFile;
//import com.bee.devops.admin.core.common.entity.DeployConfigFileRecord;
//@Mapper
//public interface DeployConfigFileDao {
//    int deleteByPrimaryKey(Long configFileId);
//
//    int insert(DeployConfigFile record);
//
//    int insertSelective(DeployConfigFile record);
//
//    DeployConfigFile selectByPrimaryKey(Long configFileId);
//
//    int updateByPrimaryKeySelective(DeployConfigFile record);
//
//    int updateByPrimaryKey(DeployConfigFile record);
//
//	Integer addRecord(DeployConfigFile deployConfigFile);
//	Integer addRecordByMap(Map<String,Object> param);
//
//	List<DeployConfigFileRecord> queryAduitFile(Map<String, Object> param);
//
//	List<String> queryBranch(Map<String, Object> param);
//
//	List<String> queryUserBranch(Map<String, Object> param);
//
//	Integer aduitFile(Map<String, Object> param);
//}