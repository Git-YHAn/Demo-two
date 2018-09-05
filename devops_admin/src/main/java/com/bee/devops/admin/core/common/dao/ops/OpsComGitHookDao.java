package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.common.OpsComGitHook;
@Mapper
public interface OpsComGitHookDao {
    int deleteByPrimaryKey(Long gitMsgId);

    int insert(OpsComGitHook record);

    int insertSelective(OpsComGitHook record);

    OpsComGitHook selectByPrimaryKey(Long gitMsgId);

    int updateByPrimaryKeySelective(OpsComGitHook record);

    int updateByPrimaryKeyWithBLOBs(OpsComGitHook record);

    int updateByPrimaryKey(OpsComGitHook record);
    
    List<OpsComGitHook> queryByPageData(@Param("envId")Long envId, @Param("appId")Long appId, @Param("proId")Long proId);
}