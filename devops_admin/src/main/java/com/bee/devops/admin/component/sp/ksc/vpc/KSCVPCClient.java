package com.bee.devops.admin.component.sp.ksc.vpc;

import com.bee.devops.admin.component.sp.ksc.KSCBaseClient;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSecurityGroupsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSubnetAvailableAddressesRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSubnetsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeVpcsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSecurityGroupsResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSubnetAvailableAddressesResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSubnetsResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeVpcsResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.transform.DescribeSecurityGroupsJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.vpc.transform.DescribeSubnetAvailableAddressesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.vpc.transform.DescribeSubnetsJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.vpc.transform.DescribeVpcsJsonUnmarshaller;
import com.ksc.auth.AWSCredentials;

public class KSCVPCClient extends KSCBaseClient {
	/** Default signing name for the service. */
	public static final String DEFAULT_SIGNING_NAME = "vpc";

	/** The region metadata service name for computing region endpoints. */
	private static final String DEFAULT_ENDPOINT_PREFIX = "vpc";

	public KSCVPCClient(AWSCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void init() {
		setServiceNameIntern(DEFAULT_SIGNING_NAME);
		setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
	}

	/**
	 * 描述VPC信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DescribeVpcsResponse describeVpcs(DescribeVpcsRequest request) throws Exception {
		return invokRequest(request, new DescribeVpcsJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 描述安全组信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DescribeSecurityGroupsResponse describeSecurityGroups(DescribeSecurityGroupsRequest request) throws Exception {
		return invokRequest(request, new DescribeSecurityGroupsJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 描述子网信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DescribeSubnetsResponse describeSubnets(DescribeSubnetsRequest request) throws Exception {
		return invokRequest(request, new DescribeSubnetsJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 描述子网可用IP信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DescribeSubnetAvailableAddressesResponse describeSubnetAvailableAddresses(DescribeSubnetAvailableAddressesRequest request) throws Exception {
		return invokRequest(request, new DescribeSubnetAvailableAddressesJsonUnmarshaller(), request.getMarshallerInstance());
	}
}
