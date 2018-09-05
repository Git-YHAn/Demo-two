package com.bee.devops.admin.common.request;

import java.io.Serializable;

/**
 * 所有MVC接口的入参都必须使用该对象的子类进行封装
 * @author created by liyong on 2018年3月2日 上午10:41:02
 */
public class RequestDtoBase implements Serializable {
	private static final long serialVersionUID = -6741387694066040777L;
	private String clientIp;
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getClientIp() {
		return clientIp;
	}
}
