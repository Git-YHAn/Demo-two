package com.bee.devops.admin.component.sp.ksc.kec.request;

import java.beans.Transient;
import java.util.List;

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

public class TerminateInstancesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<TerminateInstancesRequest> {
	/**
	 * 待销毁实例ID列表
	 */
	List<String> instanceIds;

	public TerminateInstancesRequest(List<String> instanceIds) {
		super();
		this.instanceIds = instanceIds;
	}

	public TerminateInstancesRequest() {
		super();
	}

	public class TerminateInstancesMarshaller implements Marshaller<Request<TerminateInstancesRequest>, TerminateInstancesRequest> {

		@Override
		public Request<TerminateInstancesRequest> marshall(TerminateInstancesRequest terminateInstancesRequest) throws Exception {
			if (terminateInstancesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<TerminateInstancesRequest> request = new DefaultRequest<TerminateInstancesRequest>(terminateInstancesRequest,
			        KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "TerminateInstances");
			String version = terminateInstancesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);
			if (terminateInstancesRequest.getInstanceIds() != null) {
				List<String> instanceIds = terminateInstancesRequest.getInstanceIds();
				int i = 1;
				for (String instanceId : instanceIds) {
					request.addParameter("InstanceId." + i, instanceId);
					i++;
				}
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<TerminateInstancesRequest> getDryRunRequest() {
		Request<TerminateInstancesRequest> request = null;
		try {
			request = new TerminateInstancesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public TerminateInstancesMarshaller getMarshallerInstance() {
		return new TerminateInstancesMarshaller();
	}

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
