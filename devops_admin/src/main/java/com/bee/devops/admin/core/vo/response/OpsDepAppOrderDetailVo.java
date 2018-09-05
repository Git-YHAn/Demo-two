package com.bee.devops.admin.core.vo.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OpsDepAppOrderDetailVo {
	private Long orderId; 
	private String orderTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    private String orderDetails;
    private Integer orderStatus;
	private String operateUserName;	
	private List<OpsDepAppOrderInfoResponse> deployOrderInfos;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}

	public List<OpsDepAppOrderInfoResponse> getDeployOrderInfos() {
		return deployOrderInfos;
	}
	public void setDeployOrderInfos(List<OpsDepAppOrderInfoResponse> deployOrderInfos) {
		this.deployOrderInfos = deployOrderInfos;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
