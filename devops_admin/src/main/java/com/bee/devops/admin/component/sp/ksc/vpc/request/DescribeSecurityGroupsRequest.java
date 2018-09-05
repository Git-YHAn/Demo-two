package com.bee.devops.admin.component.sp.ksc.vpc.request;

import java.beans.Transient;
import java.util.ArrayList;
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

public class DescribeSecurityGroupsRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeSecurityGroupsRequest> {
	/**
	 * 一个或多个安全组的ID
	 * 类型: String List
	 * 是否可缺省: 是
	 * 缺省值: 查询region下所有的安全组信息
	 */
	List<String> securityGroupIds = new ArrayList<>();

	/**
	 * vpc-id，VPC的ID
	 * 类型: Filter list
	 * 是否可缺省: 是
	 */
	List<Filter> filters = new ArrayList<>();

	public DescribeSecurityGroupsRequest() {
		super();
	}

	public DescribeSecurityGroupsRequest(List<String> securityGroupIds, List<Filter> filters) {
		super();
		this.securityGroupIds = securityGroupIds;
		this.filters = filters;
	}

	public class DescribeSecurityGroupsMarshaller implements Marshaller<Request<DescribeSecurityGroupsRequest>, DescribeSecurityGroupsRequest> {

		@Override
		public Request<DescribeSecurityGroupsRequest> marshall(DescribeSecurityGroupsRequest describeRequest) throws Exception {
			if (describeRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeSecurityGroupsRequest> request = new DefaultRequest<DescribeSecurityGroupsRequest>(describeRequest,
			        KSCVPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeSecurityGroups");
			String version = describeRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);
			if (describeRequest.getSecurityGroupIds() != null) {
				List<String> groupIds = describeRequest.getSecurityGroupIds();
				int i = 1;
				for (String groupId : groupIds) {
					request.addParameter("SecurityGroupId." + i, groupId);
					i++;
				}
			}
			if (describeRequest.getFilters() != null) {
				List<Filter> filters = describeRequest.getFilters();
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
	public Request<DescribeSecurityGroupsRequest> getDryRunRequest() {
		Request<DescribeSecurityGroupsRequest> request = null;
		try {
			request = new DescribeSecurityGroupsMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeSecurityGroupsMarshaller getMarshallerInstance() {
		return new DescribeSecurityGroupsMarshaller();
	}

	public List<String> getSecurityGroupIds() {
		return securityGroupIds;
	}

	public void setSecurityGroupIds(List<String> securityGroupIds) {
		this.securityGroupIds = securityGroupIds;
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
