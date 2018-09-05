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

public class DescribeAvailabilityZonesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeAvailabilityZonesRequest> {

	public class DescribeAvailabilityZonesMarshaller implements Marshaller<Request<DescribeAvailabilityZonesRequest>, DescribeAvailabilityZonesRequest> {

		@Override
		public Request<DescribeAvailabilityZonesRequest> marshall(DescribeAvailabilityZonesRequest requestParam) throws Exception {
			if (requestParam == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeAvailabilityZonesRequest> request = new DefaultRequest<DescribeAvailabilityZonesRequest>(requestParam,
			        KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeAvailabilityZones");
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
	public Request<DescribeAvailabilityZonesRequest> getDryRunRequest() {
		Request<DescribeAvailabilityZonesRequest> request = null;
		try {
			request = new DescribeAvailabilityZonesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeAvailabilityZonesMarshaller getMarshallerInstance() {
		return new DescribeAvailabilityZonesMarshaller();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
