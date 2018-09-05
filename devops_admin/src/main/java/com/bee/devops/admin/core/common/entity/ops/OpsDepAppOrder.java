package com.bee.devops.admin.core.common.entity.ops;

import java.io.Serializable;
import java.util.Date;

public class OpsDepAppOrder implements Serializable {

	private static final long serialVersionUID = -4984598924914723032L;

	private Long orderId;

    private Long envId;

    private Long proId;

    private String orderTitle;

    private long operateUserId;

    private Date createDate;

    private Integer orderStatus;

    private String orderDetails;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails == null ? null : orderDetails.trim();
    }

    
}