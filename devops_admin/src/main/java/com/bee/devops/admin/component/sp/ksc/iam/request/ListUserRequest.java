package com.bee.devops.admin.component.sp.ksc.iam.request;

import java.beans.Transient;

import org.apache.commons.lang.StringUtils;

import com.bee.devops.admin.component.sp.ksc.iam.KSCIAMClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.transform.Marshaller;

public class ListUserRequest extends KscWebServiceRequest implements DryRunSupportedRequest<ListUserRequest> {

	@Transient
	public Request<ListUserRequest> getDryRunRequest() {
		Request<ListUserRequest> request = null;
		try {
			request = new ListUserRequestMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public ListUserRequestMarshaller getMarshallerInstance() {
		return new ListUserRequestMarshaller();
	}

	public class ListUserRequestMarshaller implements Marshaller<Request<ListUserRequest>, ListUserRequest> {

		@Override
		public Request<ListUserRequest> marshall(ListUserRequest listUserRequest) throws Exception {
			if (listUserRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<ListUserRequest> request = new DefaultRequest<ListUserRequest>(listUserRequest, KSCIAMClient.DEFAULT_ENDPOINT_PREFIX);
			request.addParameter("Action", "ListUsers");
			String version = listUserRequest.getVersion();
			if (StringUtils.isBlank(version)) {
				version = "2015-11-01";
			}
			request.addParameter("Version", version);
			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

}
