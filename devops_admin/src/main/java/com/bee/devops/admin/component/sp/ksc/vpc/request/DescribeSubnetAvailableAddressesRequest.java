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

public class DescribeSubnetAvailableAddressesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeSubnetAvailableAddressesRequest> {

	/**
	 * subnet-id，子网的ID
	 * 类型: Filter list
	 * 是否可缺省: 是
	 * 缺省值: 查询region下可用的IP信息
	 */
	List<Filter> filters;

	public DescribeSubnetAvailableAddressesRequest() {
		super();
	}

	public DescribeSubnetAvailableAddressesRequest(List<Filter> filters) {
		super();
		this.filters = filters;
	}

	public class DescribeSubnetAvailableAddressesMarshaller
	        implements Marshaller<Request<DescribeSubnetAvailableAddressesRequest>, DescribeSubnetAvailableAddressesRequest> {

		@Override
		public Request<DescribeSubnetAvailableAddressesRequest> marshall(DescribeSubnetAvailableAddressesRequest describeSubnetsRequest) throws Exception {
			if (describeSubnetsRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeSubnetAvailableAddressesRequest> request = new DefaultRequest<DescribeSubnetAvailableAddressesRequest>(describeSubnetsRequest,
			        KSCVPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeSubnetAvailableAddresses");
			String version = describeSubnetsRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

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
	public Request<DescribeSubnetAvailableAddressesRequest> getDryRunRequest() {
		Request<DescribeSubnetAvailableAddressesRequest> request = null;
		try {
			request = new DescribeSubnetAvailableAddressesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeSubnetAvailableAddressesMarshaller getMarshallerInstance() {
		return new DescribeSubnetAvailableAddressesMarshaller();
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
