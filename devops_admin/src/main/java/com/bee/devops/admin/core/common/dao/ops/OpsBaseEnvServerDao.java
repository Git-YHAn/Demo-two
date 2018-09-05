package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnvServer;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OpsBaseEnvServerDao接口
 *
 * @author Roc created on 2018/8/13
 */
@Mapper
public interface OpsBaseEnvServerDao {

    /**
     * 查询所有应用状态为运行中或已停止的数据
     * 不包括正在发布中的数据
     *
     * @param deployingStatus 运行状态
     * @return 查询所有应用状态为运行中或已停止的数据
     */
    List<OpsBaseEnvServer> queryRunAndStopStatus(@Param("deployingStatus") int deployingStatus);

    /**
     * 更新应用运行状态接口
     *
     * @param hostAppEnvId 主键ID
     * @param status       待更新状态
     */
    void updateAppStatus(@Param("hostAppEnvId") Long hostAppEnvId, @Param("status") int status);

    /**
     * 更新应用运行状态接口
     *
     * @param hostId   服务器Id
     * @param appEnvId 应用环境Id
     * @param status   待更新状态
     */
    void updateAppStatusByHostAndAppEnvId(@Param("hostId") Long hostId, @Param("appEnvId") Long appEnvId, @Param("status") int status);

    /**
     * 记录工单成功发布的版本号
     *
     * @param hostId      服务器Id
     * @param appEnvId    应用环境Id
     * @param versionCode 成功发布的版本号
     */
    void updateVersionCodeByHostAndAppEnvId(@Param("hostId") Long hostId, @Param("appEnvId") Long appEnvId, @Param("versionCode") String versionCode);

    /**
     * 查询上一次发布成功的版本信息
     *
     * @param hostId   服务器Id
     * @param appEnvId 应用环境Id
     * @return 查询上一次发布成功的版本信息
     */
    String queryLastSuccessVersion(@Param("hostId") Long hostId, @Param("appEnvId") Long appEnvId);

    /**
     * 查询环境服务器列表
     *
     * @param appEnvId 应用环境ID
     * @return 查询环境服务器列表
     */
    List<OpsDepAppOrderInfoVo> queryEnvServersByAppEnvId(@Param("appEnvId") Long appEnvId);

    /**
     * 查询应用运行状态
     *
     * @param hostAppEnvId 主键ID
     * @return 应用运行状态
     */
    Integer getApplicationRunningStatus(@Param("hostAppEnvId") Long hostAppEnvId);
}
