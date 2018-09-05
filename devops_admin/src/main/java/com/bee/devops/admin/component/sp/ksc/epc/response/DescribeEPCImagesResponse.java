package com.bee.devops.admin.component.sp.ksc.epc.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.epc.EPCImage;

/**
 * 描述镜像信息
 * 
 * @description DescribeImagesResponse
 * @author TurnerXi
 * @date 2018年7月18日
 */
public class DescribeEPCImagesResponse {
	/**
	 * 请求ID
	 * 类型: String
	 */
	String RequestId;
	/**
	 * 镜像集合
	 * 类型: 镜像（Image）列表
	 */
	List<EPCImage> imageSet;
	/**
	 * 满足条件的镜像总数
	 * 类型：Integer
	 */
	Integer totalCount;

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public List<EPCImage> getImageSet() {
		return imageSet;
	}

	public void setImageSet(List<EPCImage> imageSet) {
		this.imageSet = imageSet;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
