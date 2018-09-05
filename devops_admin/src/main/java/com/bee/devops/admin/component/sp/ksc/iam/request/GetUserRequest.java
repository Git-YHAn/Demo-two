package com.bee.devops.admin.component.sp.ksc.iam.request;

import java.beans.Transient;

import org.apache.commons.lang3.StringUtils;

import com.bee.devops.admin.component.sp.ksc.iam.KSCIAMClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.transform.Marshaller;

public class GetUserRequest extends KscWebServiceRequest implements DryRunSupportedRequest<GetUserRequest> {
	/**
	 * 待查询的IAM用户名，未指定调用时会依据AKId推测用户名
	 * 最短1,最长64 [\w+=,.@-]+
	 */
	String userName;

	@Transient
	public Request<GetUserRequest> getDryRunRequest() {
		Request<GetUserRequest> request = null;
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

	public class GetUserRequestMarshaller implements Marshaller<Request<GetUserRequest>, GetUserRequest> {

		@Override
		public Request<GetUserRequest> marshall(GetUserRequest getUserRequest) throws Exception {
			if (getUserRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<GetUserRequest> request = new DefaultRequest<GetUserRequest>(getUserRequest, KSCIAMClient.DEFAULT_ENDPOINT_PREFIX);
			request.addParameter("Action", "GetUser");
			String version = getUserRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2015-11-01";
			}
			request.addParameter("Version", version);
			if (StringUtils.isNotBlank(getUserRequest.getUserName())) {
				request.addParameter("UserName", getUserRequest.getUserName());
			}
			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
