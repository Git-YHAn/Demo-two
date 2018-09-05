package com.bee.devops.admin.core.vo.request;

import java.io.Serializable;

public class OpsDepAppOrderInfosRequest implements Serializable {
	
	private static final long serialVersionUID = -5760291373791376017L;

    private Long[] orderInfosId;

	public Long[] getOrderInfosId() {
		return orderInfosId;
	}

	public void setOrderInfosId(Long[] orderInfosId) {
		this.orderInfosId = orderInfosId;
	}
   
}
