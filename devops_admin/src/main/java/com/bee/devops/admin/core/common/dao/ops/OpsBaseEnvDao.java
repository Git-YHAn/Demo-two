package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
@Mapper
public interface OpsBaseEnvDao {
	/**
	 *根据ID 删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id);
    /**
     * 添加所有环境信息
     * @param opsBaseEnv
     * @return
     */
    int insert(OpsBaseEnv opsBaseEnv);
    
    /**
     * 选择添加环境信息
     * @param opsBaseEnv
     * @return
     */
    int insertSelective(OpsBaseEnv opsBaseEnv);
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    OpsBaseEnv selectByPrimaryKey(Long id);
    
    /**
     * 根据ID选择修改所有信息
     * @param opsBaseEnv
     * @return
     */
    int updateByPrimaryKeySelective(OpsBaseEnv opsBaseEnv);
    
    /**
     * 根据ID选择修改信息
     * @param opsBaseEnv
     * @return
     */
    int updateByPrimaryKey(OpsBaseEnv opsBaseEnv);
    
    /**
     * 查询所有
     * @return
     */
    List<OpsBaseEnv>  selectAll();
    
    /**
     * 根据code查询数量
     * @param code
     * @return
     */
	int selectByCode(String code);
	
	/**
	 * 根据priority查询数量
	 * @param priority
	 * @return
	 */
	int selectByPriority(long priority);
	
	/**
	 * 根据名称分页查询
	 * @param deployEnvironment
	 * @return
	 */
	List<OpsBaseEnv> selectAllByName(@Param("envName")String envName);
	
	List<OpsBaseEnv> selectEnvByPriority(@Param("priority")Integer priority);
	
	List<OpsBaseEnv> selectByAppVer();
	
	/**
	 * 根据ID查找优先级
	 * @param envId
	 * @return
	 */
	int selectPriorityById(Long envId);
	
}