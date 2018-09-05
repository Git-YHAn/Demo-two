package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OpsBaseServerTempDao{
	/**
	 * 添加shell模板
	 * @param opsBaseServerTemp
	 */
	void inserTpl(OpsBaseServerTemp opsBaseServerTemp);
	
	/**
	 * 根据id删除模板
	 * @param tplId
	 */
	void deleteTplById(long tplId);
	
	/**
	 * 根据类型删除模板
	 * @param tpType
	 */
	void deleteTplByType(Integer tpType);
	
	/**
	 * 查询所有shell模板
	 * @return
	 */
	List<OpsBaseServerTemp> selectAll(@Param("tplName") String tplName, @Param("tplType") String tplType);
	
	/**
	 * 根据id查询shell模板
	 * @return
	 */
	List selectById(Integer[] tplIds);
	
	/**
	 * 根据类型查询shell模板
	 * @param tpType
	 * @return
	 */
	List selectByType(Integer tpType);
	
	/**
	 * 根据id修改shell模板信息
	 * @param opsBaseServerTemp
	 */
	public void upDate(OpsBaseServerTemp opsBaseServerTemp);

	OpsBaseServerTemp getById(long tempId);
}