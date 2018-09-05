package com.bee.devops.admin.component.sp.ksc.epc;

import com.bee.devops.admin.component.sp.ksc.KSCBaseClient;
import com.bee.devops.admin.component.sp.ksc.epc.response.DescribeEPCImagesResponse;
import com.bee.devops.admin.component.sp.ksc.epc.transform.DescribeEPCImagesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.epc.request.DescribeEPCImagesRequest;
import com.ksc.auth.AWSCredentials;

public class KSCEPCClient extends KSCBaseClient {
	/** Default signing name for the service. */
	public static final String DEFAULT_SIGNING_NAME = "epc";

	/** The region metadata service name for computing region endpoints. */
	private static final String DEFAULT_ENDPOINT_PREFIX = "epc";

	public KSCEPCClient(AWSCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void init() {
		setServiceNameIntern(DEFAULT_SIGNING_NAME);
		setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
	}

	/**
	 * 查询镜像
	 */
	public DescribeEPCImagesResponse describeImages(DescribeEPCImagesRequest request) throws Exception {
		return invokRequest(request, new DescribeEPCImagesJsonUnmarshaller(), request.getMarshallerInstance());
	}

}
