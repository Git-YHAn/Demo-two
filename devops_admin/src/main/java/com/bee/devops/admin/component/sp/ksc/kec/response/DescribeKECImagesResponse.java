package com.bee.devops.admin.component.sp.ksc.kec.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.kec.KECImage;

/**
 * 描述镜像信息
 * 
 * @description DescribeImagesResponse
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class DescribeKECImagesResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String RequestId;
	/**
	 * 镜像集合
	 * 类型: 镜像（Image）列表
	 */
	List<KECImage> imagesSet;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public List<KECImage> getImagesSet() {
		return imagesSet;
	}

	public void setImagesSet(List<KECImage> imagesSet) {
		this.imagesSet = imagesSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
