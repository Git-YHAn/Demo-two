package com.bee.devops.admin.component.sp.ksc.kec.request;

import java.beans.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.kec.KSCKECClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.transform.Marshaller;

public class DescribeInstanceTypeConfigsRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeInstanceTypeConfigsRequest> {
	public class DescribeInstanceTypeConfigsMarshaller implements Marshaller<Request<DescribeInstanceTypeConfigsRequest>, DescribeInstanceTypeConfigsRequest> {

		@Override
		public Request<DescribeInstanceTypeConfigsRequest> marshall(DescribeInstanceTypeConfigsRequest requestParam) throws Exception {
			if (requestParam == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeInstanceTypeConfigsRequest> request = new DefaultRequest<DescribeInstanceTypeConfigsRequest>(requestParam,
			        KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeInstanceTypeConfigs");
			String version = requestParam.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<DescribeInstanceTypeConfigsRequest> getDryRunRequest() {
		Request<DescribeInstanceTypeConfigsRequest> request = null;
		try {
			request = new DescribeInstanceTypeConfigsMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeInstanceTypeConfigsMarshaller getMarshallerInstance() {
		return new DescribeInstanceTypeConfigsMarshaller();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
