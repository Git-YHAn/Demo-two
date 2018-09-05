package com.bee.devops.admin.component.sp.ksc.kec.request;

import java.beans.Transient;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.common.Sort;
import com.bee.devops.admin.component.sp.ksc.kec.KSCKECClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.model.Filter;
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class DescribeInstancesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeInstancesRequest> {

	/**
	 * 单次调用所返回的最大实例数目，取值为5~1000，超过1000记为1000
	 * 类型: Integer
	 * 缺省: 10
	 */
	Integer maxResults;

	/**
	 * 分页标识，单次调用未返回全部实例时，标记下次调用的返回值的起点，默认值是0
	 * 类型: Integer
	 */
	Integer marker;

	/**
	 * 待返回描述信息的实例ID列表
	 * 类型: String 列表
	 * 缺省: 返回全部实例描述信息
	 */
	List<String> instanceIds;

	/**
	 * 待返回实例信息的项目ID列表
	 * 类型：Long列表
	 * 是否可缺省：是
	 */
	List<Long> projectIds;

	/**
	 * 一个或者多个过滤器
	 * 类型: 过滤器（Filter）列表
	 * 有效值：支持如下过滤器名称
	 * instance-id 实例ID
	 * subnet-id 子网ID
	 * vpc-id vpc ID
	 * network-interface.subnet-id 网络接口关联的子网ID
	 * network-interface.network-interface-id 网络接口的ID
	 * network-interface.group-id 网络接口关联的安全组ID
	 * instance-state.name-实例状态
	 * availability-zone.name 可用区（AvailabilityZone）名字(规划中)
	 */
	List<Filter> filters;

	/**
	 * 筛选器
	 * 类型: 筛选器（Sort）列表
	 * 有效值：支持如下筛选器名称
	 * InstanceName –主机名称
	 * CreationDate –创建时间
	 * PrivateIpAddress - 主机内网IP（主网卡）
	 */
	List<Sort> sorts;

	/**
	 * 搜索条件，模糊匹配，可搜索字段如下：
	 * 类型：string
	 * 支持字段：实例名（InstanceName）、主网卡私有IP地址（PrivateIpAddress）
	 */
	String search;

	public DescribeInstancesRequest() {
		super();
	}

	public DescribeInstancesRequest(Integer maxResults, Integer marker, List<String> instanceIds, List<Long> projectIds, List<Filter> filters, List<Sort> sorts,
	        String search) {
		super();
		this.maxResults = maxResults;
		this.marker = marker;
		this.instanceIds = instanceIds;
		this.projectIds = projectIds;
		this.filters = filters;
		this.sorts = sorts;
		this.search = search;
	}

	public class DescribeInstancesMarshaller implements Marshaller<Request<DescribeInstancesRequest>, DescribeInstancesRequest> {

		@Override
		public Request<DescribeInstancesRequest> marshall(DescribeInstancesRequest terminateInstancesRequest) throws Exception {
			if (terminateInstancesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeInstancesRequest> request = new DefaultRequest<DescribeInstancesRequest>(terminateInstancesRequest,
			        KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeInstances");
			String version = terminateInstancesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (terminateInstancesRequest.getMaxResults() != null) {
				request.addParameter("MaxResults", StringUtils.fromInteger(terminateInstancesRequest.getMaxResults()));
			}
			if (terminateInstancesRequest.getMarker() != null) {
				request.addParameter("Marker", StringUtils.fromInteger(terminateInstancesRequest.getMarker()));
			}
			if (!StringUtils.isNullOrEmpty(terminateInstancesRequest.getSearch())) {
				request.addParameter("Search", StringUtils.fromString(terminateInstancesRequest.getSearch()));
			}
			if (terminateInstancesRequest.getInstanceIds() != null) {
				List<String> instanceIds = terminateInstancesRequest.getInstanceIds();
				int i = 1;
				for (String instanceId : instanceIds) {
					request.addParameter("InstanceId." + i, StringUtils.fromString(instanceId));
					i++;
				}
			}
			if (terminateInstancesRequest.getProjectIds() != null) {
				List<Long> projectIds = terminateInstancesRequest.getProjectIds();
				int i = 1;
				for (Long projectId : projectIds) {
					request.addParameter("ProjectId." + i, StringUtils.fromLong(projectId));
					i++;
				}
			}
			if (terminateInstancesRequest.getFilters() != null) {
				List<Filter> filters = terminateInstancesRequest.getFilters();
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
			if (terminateInstancesRequest.getSorts() != null) {
				List<Sort> sorts = terminateInstancesRequest.getSorts();
				for (Sort sort : sorts) {
					request.addParameter("Sort." + sort.getName(), sort.getValue());
				}
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<DescribeInstancesRequest> getDryRunRequest() {
		Request<DescribeInstancesRequest> request = null;
		try {
			request = new DescribeInstancesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeInstancesMarshaller getMarshallerInstance() {
		return new DescribeInstancesMarshaller();
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getMarker() {
		return marker;
	}

	public void setMarker(Integer marker) {
		this.marker = marker;
	}

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
