package com.bee.devops.admin.component.sp.ksc.kec.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 更换或者重新安装实例操作系统
 * 重新安装/更换实例的操作系统；
 * 
 * 约束条件1： 联网增强主机重装系统，只能安装联网增强型的标准系统镜像，暂不支持自定义镜像安装；
 * 
 * @description StartInstancesResponse
 * @author TurnerXi
 * @date 2018年8月22日
 */
public class ModifyInstanceImageResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String requestId;

	/**
	 * 更换或者重装实例操作系统成功与否
	 * 类型: String
	 * 有效值：true（成功）| false（失败）
	 * 是否可缺省: 否
	 */
	String Return;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getReturn() {
		return Return;
	}

	public void setReturn(String return1) {
		Return = return1;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
