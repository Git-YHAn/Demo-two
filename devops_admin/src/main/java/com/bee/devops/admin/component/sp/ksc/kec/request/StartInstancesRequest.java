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

public class StartInstancesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<StartInstancesRequest> {
	/**
	 * 待启动实例ID列表
	 * 类型: String 列表
	 */
	List<String> instanceIds;

	public StartInstancesRequest() {
		super();
	}

	public StartInstancesRequest(List<String> instanceIds) {
		super();
		this.instanceIds = instanceIds;
	}

	public class StartInstancesMarshaller implements Marshaller<Request<StartInstancesRequest>, StartInstancesRequest> {

		@Override
		public Request<StartInstancesRequest> marshall(StartInstancesRequest startInstancesRequest) throws Exception {
			if (startInstancesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<StartInstancesRequest> request = new DefaultRequest<StartInstancesRequest>(startInstancesRequest, KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "StartInstances");
			String version = startInstancesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (startInstancesRequest.getInstanceIds() != null) {
				List<String> instanceIds = startInstancesRequest.getInstanceIds();
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
	public Request<StartInstancesRequest> getDryRunRequest() {
		Request<StartInstancesRequest> request = null;
		try {
			request = new StartInstancesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public StartInstancesMarshaller getMarshallerInstance() {
		return new StartInstancesMarshaller();
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
