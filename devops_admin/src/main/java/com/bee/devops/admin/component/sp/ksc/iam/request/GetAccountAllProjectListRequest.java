package com.bee.devops.admin.component.sp.ksc.iam.request;

import java.beans.Transient;

import com.bee.devops.admin.component.sp.ksc.iam.KSCIAMClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.transform.Marshaller;

public class GetAccountAllProjectListRequest extends KscWebServiceRequest implements DryRunSupportedRequest<GetAccountAllProjectListRequest> {

	@Transient
	public Request<GetAccountAllProjectListRequest> getDryRunRequest() {
		Request<GetAccountAllProjectListRequest> request = null;
		try {
			request = new GetUserRequestMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public GetUserRequestMarshaller getMarshallerInstance() {
		return new GetUserRequestMarshaller();
	}

	public class GetUserRequestMarshaller implements Marshaller<Request<GetAccountAllProjectListRequest>, GetAccountAllProjectListRequest> {

		@Override
		public Request<GetAccountAllProjectListRequest> marshall(GetAccountAllProjectListRequest getUserRequest) throws Exception {
			if (getUserRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<GetAccountAllProjectListRequest> request = new DefaultRequest<GetAccountAllProjectListRequest>(getUserRequest,
			        KSCIAMClient.DEFAULT_ENDPOINT_PREFIX);
			request.addParameter("Action", "GetAccountAllProjectList");
			String version = getUserRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2015-11-01";
			}
			request.addParameter("Version", version);
			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

}
