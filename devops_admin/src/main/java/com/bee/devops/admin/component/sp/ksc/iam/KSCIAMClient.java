package com.bee.devops.admin.component.sp.ksc.iam;

import com.bee.devops.admin.component.sp.ksc.KSCBaseClient;
import com.bee.devops.admin.component.sp.ksc.iam.request.GetAccountAllProjectListRequest;
import com.bee.devops.admin.component.sp.ksc.iam.request.GetUserRequest;
import com.bee.devops.admin.component.sp.ksc.iam.request.ListUserRequest;
import com.bee.devops.admin.component.sp.ksc.iam.response.GetAccountAllProjectListResponse;
import com.bee.devops.admin.component.sp.ksc.iam.response.GetUserResponse;
import com.bee.devops.admin.component.sp.ksc.iam.response.ListUsersResponse;
import com.bee.devops.admin.component.sp.ksc.iam.transform.GetAccountAllProjectListResponseJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.iam.transform.GetUserResponseJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.iam.transform.ListUsersResponseJsonUnmarshaller;
import com.ksc.auth.AWSCredentials;

public class KSCIAMClient extends KSCBaseClient {
	/** Default signing name for the service. */
	private static final String DEFAULT_SIGNING_NAME = "iam";

	/** The region metadata service name for computing region endpoints. */
	public static final String DEFAULT_ENDPOINT_PREFIX = "iam";

	public KSCIAMClient(AWSCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void init() {
		setServiceNameIntern(DEFAULT_SIGNING_NAME);
		setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
	}

	public GetUserResponse getUser(GetUserRequest request) throws Exception {
		return invokRequest(request, new GetUserResponseJsonUnmarshaller(), request.getMarshallerInstance());
	}

	public ListUsersResponse ListUsers(ListUserRequest request) throws Exception {
		return invokRequest(request, new ListUsersResponseJsonUnmarshaller(), request.getMarshallerInstance());
	}

	public GetAccountAllProjectListResponse getAccountAllProjectList(GetAccountAllProjectListRequest request) throws Exception {
		return invokRequest(request, new GetAccountAllProjectListResponseJsonUnmarshaller(), request.getMarshallerInstance());
	}

}
