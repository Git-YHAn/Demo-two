package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrder;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderDetailVo;
@Mapper
public interface OpsDepAppOrderDao {
	
	long insertDepAppOrder(OpsDepAppOrder opsdepapporder);
	
	OpsDepAppOrderDetailVo queryByOrderId(@Param("orderId") Long orderId);

	List<OpsDepAppOrderDetailVo> queryOrders(@Param("proId") Long proId, @Param("envId") Long envId, @Param("title") String title);

	List<Long> queryDeployingServersByOrderId(@Param("orderId") Long orderId);

	void updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") Integer orderStatus);
	
	int deleteOrderById(Long orderId);
	
	OpsDepAppOrder queryByOrderKey(@Param("orderId") Long orderId);
}