/**
 * @author heping
 * @date2018年6月11日
 */
package com.bee.devops.admin.core.vo.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.bee.devops.admin.common.request.RestRequest;

public class SysResourceRequest extends RestRequest{
	private static final long serialVersionUID = 3040975938181673061L;

	private Long resourceId;
	
	@NotBlank(message = "{sys.resource.resourceName.notnull}")
//	@Pattern(regexp = "^[\u4e00-\u9fa5]{4,10}$",message = "{sys.resource.resourceName.format}")
	private String resourceName;

	private String resourceCode;

	private String resourceUrl;

	private String resourceIcon;

	private Integer resourceOrder;

	@NotNull(message = "{sys.resource.type.notnull}")
	private Integer resourceType;

	private Long parentId;

	private Long moduleId;

	private Integer isEnable;

	private Integer isShow;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourceIcon() {
		return resourceIcon;
	}

	public void setResourceIcon(String resourceIcon) {
		this.resourceIcon = resourceIcon;
	}

	public Integer getResourceOrder() {
		return resourceOrder;
	}

	public void setResourceOrder(Integer resourceOrder) {
		this.resourceOrder = resourceOrder;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}
