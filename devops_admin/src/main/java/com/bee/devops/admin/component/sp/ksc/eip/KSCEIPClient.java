package com.bee.devops.admin.component.sp.ksc.eip;

import com.bee.devops.admin.component.sp.ksc.KSCBaseClient;
import com.bee.devops.admin.component.sp.ksc.eip.request.DescribeAddressesRequest;
import com.bee.devops.admin.component.sp.ksc.eip.response.DescribeAddressesResponse;
import com.bee.devops.admin.component.sp.ksc.eip.transform.DescribeAddressesJsonUnmarshaller;
import com.ksc.auth.AWSCredentials;

public class KSCEIPClient extends KSCBaseClient {
	/** Default signing name for the service. */
	public static final String DEFAULT_SIGNING_NAME = "eip";

	/** The region metadata service name for computing region endpoints. */
	private static final String DEFAULT_ENDPOINT_PREFIX = "eip";

	public KSCEIPClient(AWSCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void init() {
		setServiceNameIntern(DEFAULT_SIGNING_NAME);
		setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
	}

	public DescribeAddressesResponse describeAddresses(DescribeAddressesRequest request) throws Exception {
		return invokRequest(request, new DescribeAddressesJsonUnmarshaller(), request.getMarshallerInstance());
	}
}
