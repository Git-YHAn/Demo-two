package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerExecHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpsBaseServerExecHisDao {
    /**
     * 添加shell执行记录
     *
     * @param exec
     */
    Integer insertExecHis(OpsBaseServerExecHis exec);

    /**
     * 根据id删除执行记录
     *
     * @param execHisId 执行记录Id
     */
    int deleteExecHisById(Long execHisId);

    /**
     * 查询shell执行记录
     * @param sshAddress    主机SSH地址
     * @param tplName       执行模板名称
     * @param execTime      执行时间
     * @return
     */
    List<OpsBaseServerExecHis> selectAll(@Param("sshAddress") String sshAddress, @Param("tplName") String tplName, @Param("execTime") String execTime);
}