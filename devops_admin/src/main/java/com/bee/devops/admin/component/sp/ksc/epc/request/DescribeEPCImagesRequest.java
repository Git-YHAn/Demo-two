package com.bee.devops.admin.component.sp.ksc.epc.request;

import java.beans.Transient;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.epc.KSCEPCClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.model.Filter;
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class DescribeEPCImagesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeEPCImagesRequest> {
	/**
	 * 可查询多个实例信息，查看详细ID.N使用方式
	 * 镜像ID
	 * 类型：String
	 * 是否必填：否
	 * 缺省值：查询region下所有的镜像
	 */
	List<String> imageIds;

	/**
	 * 筛选器，查看详细Filter.N使用方式
	 * image-type：镜像的类型
	 * 类型：Filter
	 * 是否必填：否
	 */
	List<Filter> filters;

	/**
	 * 单次调用可返回的最大条目数量. 传入返回的 NextToken 值可以获取剩余的其它条目.这个值可以允许的范围是 5-1000.
	 * 类型: Integer
	 * 是否必填： 否
	 */
	Integer maxResults;

	/**
	 * 获取另一页返回结果的 token.类型:
	 * String 是否必填：否
	 */
	String nextToken;

	public DescribeEPCImagesRequest() {
		super();
	}

	public DescribeEPCImagesRequest(List<Filter> filters) {
		super();
		this.filters = filters;
	}

	public DescribeEPCImagesRequest(List<String> imageIds, List<Filter> filters, Integer maxResults, String nextToken) {
		super();
		this.imageIds = imageIds;
		this.filters = filters;
		this.maxResults = maxResults;
		this.nextToken = nextToken;
	}

	public class DescribeImagesMarshaller implements Marshaller<Request<DescribeEPCImagesRequest>, DescribeEPCImagesRequest> {

		@Override
		public Request<DescribeEPCImagesRequest> marshall(DescribeEPCImagesRequest describeImagesRequest) throws Exception {
			if (describeImagesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeEPCImagesRequest> request = new DefaultRequest<DescribeEPCImagesRequest>(describeImagesRequest, KSCEPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeImages");
			String version = describeImagesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2015-11-01";
			}
			request.addParameter("Version", version);

			if (describeImagesRequest.getImageIds() != null) {
				List<String> imageIds = describeImagesRequest.getImageIds();
				int i = 1;
				for (String imageId : imageIds) {
					request.addParameter("ImageId." + i, StringUtils.fromString(imageId));
					i++;
				}
			}
			if (describeImagesRequest.getMaxResults() != null) {
				request.addParameter("MaxResults", StringUtils.fromInteger(describeImagesRequest.getMaxResults()));
			}
			if (!StringUtils.isNullOrEmpty(describeImagesRequest.getNextToken())) {
				request.addParameter("NextToken", StringUtils.fromString(describeImagesRequest.getNextToken()));
			}
			if (describeImagesRequest.getFilters() != null) {
				List<Filter> filters = describeImagesRequest.getFilters();
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
	public Request<DescribeEPCImagesRequest> getDryRunRequest() {
		Request<DescribeEPCImagesRequest> request = null;
		try {
			request = new DescribeImagesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeImagesMarshaller getMarshallerInstance() {
		return new DescribeImagesMarshaller();
	}

	public List<String> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
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

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
