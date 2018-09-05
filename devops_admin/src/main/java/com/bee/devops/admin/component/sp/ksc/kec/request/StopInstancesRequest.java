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
import com.ksc.util.StringUtils;

public class StopInstancesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<StopInstancesRequest> {
	/**
	 * 待启动实例ID列表
	 * 类型: String 列表
	 */
	List<String> instanceIds;
	/**
	 * 强制关闭
	 * 类型：Boolean
	 */
	Boolean forceStop;

	public StopInstancesRequest() {
		super();
	}

	public StopInstancesRequest(List<String> instanceIds, Boolean forceStop) {
		super();
		this.instanceIds = instanceIds;
		this.forceStop = forceStop;
	}

	public class StartInstancesMarshaller implements Marshaller<Request<StopInstancesRequest>, StopInstancesRequest> {

		@Override
		public Request<StopInstancesRequest> marshall(StopInstancesRequest startInstancesRequest) throws Exception {
			if (startInstancesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<StopInstancesRequest> request = new DefaultRequest<StopInstancesRequest>(startInstancesRequest, KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "StopInstances");
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
			if (startInstancesRequest.getForceStop() != null) {
				request.addParameter("ForceStop", StringUtils.fromBoolean(startInstancesRequest.getForceStop()));
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<StopInstancesRequest> getDryRunRequest() {
		Request<StopInstancesRequest> request = null;
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

	public Boolean getForceStop() {
		return forceStop;
	}

	public void setForceStop(Boolean forceStop) {
		this.forceStop = forceStop;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
