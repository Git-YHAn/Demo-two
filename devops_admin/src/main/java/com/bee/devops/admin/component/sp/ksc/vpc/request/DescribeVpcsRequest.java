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
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class DescribeVpcsRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeVpcsRequest> {

	/**
	 * 一个或多个VPC的ID
	 * 默认: 展示所有的Vpcs信息
	 * 类型: String List
	 * 是否可缺省: 是
	 * 缺省值: 查询region下所有的VPC信息
	 */
	List<String> vpcIds;

	public DescribeVpcsRequest() {
		super();
	}

	public DescribeVpcsRequest(List<String> vpcIds) {
		super();
		this.vpcIds = vpcIds;
	}

	public class DescribeVpcsMarshaller implements Marshaller<Request<DescribeVpcsRequest>, DescribeVpcsRequest> {

		@Override
		public Request<DescribeVpcsRequest> marshall(DescribeVpcsRequest describeVpcsRequest) throws Exception {
			if (describeVpcsRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeVpcsRequest> request = new DefaultRequest<DescribeVpcsRequest>(describeVpcsRequest, KSCVPCClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeVpcs");
			String version = describeVpcsRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (describeVpcsRequest.getVpcIds() != null) {
				List<String> vpcIds = describeVpcsRequest.getVpcIds();
				int i = 1;
				for (String vpcId : vpcIds) {
					request.addParameter("VpcId." + i, StringUtils.fromString(vpcId));
					i++;
				}
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<DescribeVpcsRequest> getDryRunRequest() {
		Request<DescribeVpcsRequest> request = null;
		try {
			request = new DescribeVpcsMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeVpcsMarshaller getMarshallerInstance() {
		return new DescribeVpcsMarshaller();
	}

	public List<String> getVpcIds() {
		return vpcIds;
	}

	public void setVpcIds(List<String> vpcIds) {
		this.vpcIds = vpcIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
