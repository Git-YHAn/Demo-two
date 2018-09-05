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

public class RebootInstancesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<RebootInstancesRequest> {
	/**
	 * 待启动实例ID列表
	 * 类型: String 列表
	 */
	List<String> instanceIds;

	public RebootInstancesRequest() {
		super();
	}

	public RebootInstancesRequest(List<String> instanceIds) {
		super();
		this.instanceIds = instanceIds;
	}

	public class RebootInstancesMarshaller implements Marshaller<Request<RebootInstancesRequest>, RebootInstancesRequest> {

		@Override
		public Request<RebootInstancesRequest> marshall(RebootInstancesRequest rebootInstancesRequest) throws Exception {
			if (rebootInstancesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<RebootInstancesRequest> request = new DefaultRequest<RebootInstancesRequest>(rebootInstancesRequest, KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "RebootInstances");
			String version = rebootInstancesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (rebootInstancesRequest.getInstanceIds() != null) {
				List<String> instanceIds = rebootInstancesRequest.getInstanceIds();
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
	public Request<RebootInstancesRequest> getDryRunRequest() {
		Request<RebootInstancesRequest> request = null;
		try {
			request = new RebootInstancesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public RebootInstancesMarshaller getMarshallerInstance() {
		return new RebootInstancesMarshaller();
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
