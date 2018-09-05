package com.bee.devops.admin.core.common.dao.ops;

import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrderInfo;
import com.bee.devops.admin.core.vo.response.AppReleaseInfo;
import com.bee.devops.admin.core.vo.response.DeployAppVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoAppEnvVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoResponse;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpsDepAppOrderInfoDao {

	long insertDepAppOrderInfo(OpsDepAppOrderInfo opsdepapporderinfo);

	List<OpsDepAppOrderInfo> queryOrderInfosByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据工单信息Id查询
     * @param orderInfoId 工单信息Id
     * @return 根据工单信息Id查询
     */
	OpsDepAppOrderInfoResponse queryByOrderInfoId(@Param("orderInfoId")Long orderInfoId);

    /**
     * 根据应用环境Id查询应用实例信息
     *
     * @param appEnvId 应用环境Id
     * @param serverId 服务器Id
     * @return 根据应用环境Id查询应用实例信息
     */
    OpsDepAppOrderInfoVo queryOrderInfoByAppEnvAndServerId(@Param("appEnvId") Long appEnvId, @Param("serverId") Long serverId);

    /**
     * 根据工单信息ID查询应用环境、服务器和发布版本
     *
     * @param orderInfoId 工单信息ID
     * @return 根据应用环境Id查询应用实例信息
     */
    OpsDepAppOrderInfoAppEnvVo queryAppEnvAndDepAppVerAndServer(@Param("orderInfoId") Long orderInfoId);

    /**
     * 更新发布工单信息
     * @param orderInfo 更新工单信息
     */
    void updateByOrderAndServerIdSelective(OpsDepAppOrderInfoResponse orderInfo);

    List<Long> queryDeployingServersByOrderId(@Param("orderId") Long orderId, @Param("deployStatus") Integer deployStatus);

    List<OpsDepAppOrderInfoResponse> queryOrderInfos(@Param("orderinfos") Long[] orderinfos);
    
    int deleteOrderInfosByOrderId(Long orderId);

    AppReleaseInfo getReleaseInfo(@Param("envId")Long envId, @Param("proId")Long proId);

	List<DeployAppVo> getAppDeployData(@Param("time")String time, @Param("proId")Long proId, @Param("envId")Long envId);

	DeployAppVo getAppPublishStatus();
}