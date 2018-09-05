package com.bee.devops.admin.component.sp.ksc.vpc.request;

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

public class DescribeSubnetsRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeSubnetsRequest> {

	/**
	 * 一个或多个（一期只支持一个）子网的ID
	 * 类型: String
	 * 是否可缺省: 是
	 * 缺省值: 查询region下所有的子网信息
	 */
	List<String> subnetIds;
	/**
	 * vpc-id，VPC的ID
	 * nat-id，NAT的ID
	 * network-acl-id，ACL的ID。subnet-type，子网的类型
	 * 类型: Filter list
	 * 是否可缺省:是
	 */
	List<Filter> filters;

	public DescribeSubnetsRequest() {
		super();
	}

	public DescribeSubnetsRequest(List<String> subnetIds, List<Filter> filters) {
		super();
		this.subnetIds = subnetIds;
		this.filters = filters;
	}

	public class DescribeSubnetsMarshaller implements Marshaller<Request<DescribeSubnetsRequest>, DescribeSubnetsRequest> {

		@Override
		public Request<DescribeSubnetsRequest> marshall(DescribeSubnetsRequest describeSubnetsRequest) throws Exception {
			if (describeSubnetsRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeSubnetsRequest> request = new DefaultRequest<DescribeSubnetsRequest>(describeSubnetsRequest, KSCVPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeSubnets");
			String version = describeSubnetsRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (describeSubnetsRequest.getSubnetIds() != null) {
				List<String> projectIds = describeSubnetsRequest.getSubnetIds();
				int i = 1;
				for (String projectId : projectIds) {
					request.addParameter("SubnetId." + i, projectId);
					i++;
				}
			}
			if (describeSubnetsRequest.getFilters() != null) {
				List<Filter> filters = describeSubnetsRequest.getFilters();
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
	public Request<DescribeSubnetsRequest> getDryRunRequest() {
		Request<DescribeSubnetsRequest> request = null;
		try {
			request = new DescribeSubnetsMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeSubnetsMarshaller getMarshallerInstance() {
		return new DescribeSubnetsMarshaller();
	}

	public List<String> getSubnetIds() {
		return subnetIds;
	}

	public void setSubnetIds(List<String> subnetIds) {
		this.subnetIds = subnetIds;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
