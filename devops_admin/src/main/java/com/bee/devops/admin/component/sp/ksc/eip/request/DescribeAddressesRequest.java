package com.bee.devops.admin.component.sp.ksc.eip.request;

import java.beans.Transient;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.vpc.KSCVPCClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.model.Filter;
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class DescribeAddressesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeAddressesRequest> {
	/**
	 * 一个或多个弹性IP的ID
	 * 默认: 查询region下所有的弹性IP的ID
	 * 类型: String
	 */
	List<String> allocationIds;

	/**
	 * EIP所属项目的ID
	 * 默认: 查询region下的默认项目
	 * 类型: String
	 */
	List<String> projectIds;

	/**
	 * network-interface-id，主机的网卡信息
	 * instance-type，弹性IP绑定的实例类型
	 * internet-gateway-id，互联网网关的ID
	 * band-width-share-id，共享带宽ID
	 * line-id，线路的ID
	 * public-ip，弹性IP的IP
	 * 类型: Filter list
	 */
	List<Filter> filters;

	/**
	 * 单次调用可返回的最大条目数量. 传入返回的 NextToken 值可以获取剩余的其它条目. 这个值可以允许的范围是 5 - 1000
	 * 类型: Integer
	 */
	Integer maxResults;

	/**
	 * 获取另一页返回结果的 token.
	 * 类型: String
	 */
	Integer nextToken;

	public DescribeAddressesRequest() {
		super();
	}

	public DescribeAddressesRequest(List<String> allocationIds, List<String> projectIds, List<Filter> filters, Integer maxResults, Integer nextToken) {
		super();
		this.allocationIds = allocationIds;
		this.projectIds = projectIds;
		this.filters = filters;
		this.maxResults = maxResults;
		this.nextToken = nextToken;
	}

	public class DescribeAddressesMarshaller implements Marshaller<Request<DescribeAddressesRequest>, DescribeAddressesRequest> {

		@Override
		public Request<DescribeAddressesRequest> marshall(DescribeAddressesRequest describeAddressesRequest) throws Exception {
			if (describeAddressesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeAddressesRequest> request = new DefaultRequest<DescribeAddressesRequest>(describeAddressesRequest,
			        KSCVPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeAddresses");
			String version = describeAddressesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (describeAddressesRequest.getMaxResults() != null) {
				request.addParameter("MaxResults", StringUtils.fromInteger(describeAddressesRequest.getMaxResults()));
			}
			if (describeAddressesRequest.getNextToken() != null) {
				request.addParameter("NextToken", StringUtils.fromInteger(describeAddressesRequest.getNextToken()));
			}
			if (describeAddressesRequest.getAllocationIds() != null) {
				List<String> allocationIds = describeAddressesRequest.getAllocationIds();
				int i = 1;
				for (String allocationId : allocationIds) {
					request.addParameter("AllocationId." + i, StringUtils.fromString(allocationId));
					i++;
				}
			}
			if (describeAddressesRequest.getProjectIds() != null) {
				List<String> projectIds = describeAddressesRequest.getProjectIds();
				int i = 1;
				for (String projectId : projectIds) {
					request.addParameter("ProjectId." + i, projectId);
					i++;
				}
			}
			if (describeAddressesRequest.getFilters() != null) {
				List<Filter> filters = describeAddressesRequest.getFilters();
				int filtersListIndex = 1;
				for (Filter filter : filters) {
					request.addParameter("Filter." + filtersListIndex + ".Name", filter.getName());
					com.ksc.internal.SdkInternalList<String> valuesList = (com.ksc.internal.SdkInternalList<String>) filter.getValues();
					if (!valuesList.isEmpty() || !valuesList.isAutoConstruct()) {
						int valuesListIndex = 1;
						for (String valuesListValue : valuesList) {
							if (valuesListValue != null) {
								request.addParameter("Filter." + filtersListIndex + ".Value." + valuesListIndex, valuesListValue);
							}
							valuesListIndex++;
						}
					}

					filtersListIndex++;
				}
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<DescribeAddressesRequest> getDryRunRequest() {
		Request<DescribeAddressesRequest> request = null;
		try {
			request = new DescribeAddressesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeAddressesMarshaller getMarshallerInstance() {
		return new DescribeAddressesMarshaller();
	}

	public List<String> getAllocationIds() {
		return allocationIds;
	}

	public void setAllocationIds(List<String> allocationIds) {
		this.allocationIds = allocationIds;
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getNextToken() {
		return nextToken;
	}

	public void setNextToken(Integer nextToken) {
		this.nextToken = nextToken;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
