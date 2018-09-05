package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.bee.devops.admin.common.enums.OrderStatusEnums;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsDepAppOrderRequest  implements Serializable {
	
	private static final long serialVersionUID = -5760291373791376016L;

	private Long orderId;

	@NotNull(message = "{publish.order.title.notnull}")
	private String orderTitle;
	
	@NotNull(message = "{publish.order.info.notnull}")
    private String orderDetails;
    
	@NotNull(message = "{publish.order.envId.notnull}")
    private Long envId;

	@NotNull(message = "{publish.order.proId.notnull}")
    private Long proId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createDate = new Date();
    
    private Integer orderStatus = OrderStatusEnums.UNDISPOSED.getValue();
    
	private List<OpsDepAppOrderInfoRequest> deployOrderInfos;

	public OpsDepAppOrder transEntity() {
		OpsDepAppOrder opsdepapporder = new OpsDepAppOrder();
		opsdepapporder.setEnvId(envId);
		opsdepapporder.setCreateDate(createDate);
		opsdepapporder.setOrderDetails(orderDetails);
		opsdepapporder.setOrderStatus(orderStatus);
		opsdepapporder.setOrderTitle(orderTitle);
		opsdepapporder.setProId(proId);
        return opsdepapporder;
    }
	
	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OpsDepAppOrderInfoRequest> getDeployOrderInfos() {
		return deployOrderInfos;
	}

	public void setDeployOrderInfos(List<OpsDepAppOrderInfoRequest> deployOrderInfos) {
		this.deployOrderInfos = deployOrderInfos;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
