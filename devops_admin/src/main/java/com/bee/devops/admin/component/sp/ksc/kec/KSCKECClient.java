package com.bee.devops.admin.component.sp.ksc.kec;

import com.bee.devops.admin.component.sp.ksc.KSCBaseClient;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeAvailabilityZonesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeInstanceFamilysRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeInstanceTypeConfigsRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeKECImagesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.DescribeRegionsRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.ModifyInstanceImageRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.RebootInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.RunInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.StartInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.StopInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.request.TerminateInstancesRequest;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeAvailabilityZonesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeInstanceFamilysResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeInstanceTypeConfigsResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeKECImagesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.DescribeRegionsResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.ModifyInstanceImageResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.RebootInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.RunInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.StartInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.StopInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.response.TerminateInstancesResponse;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeAvailabilityZonesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeImagesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeInstanceFamilysJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeInstanceTypeConfigsJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeInstancesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.DescribeRegionsJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.ModifyInstanceImageJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.RebootInstancesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.RunInstancesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.StartInstancesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.StopInstancesJsonUnmarshaller;
import com.bee.devops.admin.component.sp.ksc.kec.transform.TerminateInstancesJsonUnmarshaller;
import com.ksc.auth.AWSCredentials;

public class KSCKECClient extends KSCBaseClient {
	/** Default signing name for the service. */
	public static final String DEFAULT_SIGNING_NAME = "kec";

	/** The region metadata service name for computing region endpoints. */
	private static final String DEFAULT_ENDPOINT_PREFIX = "kec";

	public KSCKECClient(AWSCredentials credentials) {
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
	public DescribeKECImagesResponse describeImages(DescribeKECImagesRequest request) throws Exception {
		return invokRequest(request, new DescribeImagesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 查询实例
	 */
	public DescribeInstancesResponse describeInstances(DescribeInstancesRequest request) throws Exception {
		return invokRequest(request, new DescribeInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 创建实例
	 */
	public RunInstancesResponse runInstances(RunInstancesRequest request) throws Exception {
		return invokRequest(request, new RunInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 启动实例
	 */
	public StartInstancesResponse startInstances(StartInstancesRequest request) throws Exception {
		return invokRequest(request, new StartInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 关闭实例
	 */
	public StopInstancesResponse stopInstances(StopInstancesRequest request) throws Exception {
		return invokRequest(request, new StopInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 重启实例
	 */
	public RebootInstancesResponse rebootInstances(RebootInstancesRequest request) throws Exception {
		return invokRequest(request, new RebootInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 删除实例
	 */
	public TerminateInstancesResponse terminateInstances(TerminateInstancesRequest request) throws Exception {
		return invokRequest(request, new TerminateInstancesJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 重装系统
	 */
	public ModifyInstanceImageResponse modifyInstanceImage(ModifyInstanceImageRequest request) throws Exception {
		return invokRequest(request, new ModifyInstanceImageJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 查询机型配置信息
	 */
	public DescribeInstanceFamilysResponse describeInstanceFamilys(DescribeInstanceFamilysRequest request) throws Exception {
		return invokRequest(request, new DescribeInstanceFamilysJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 查询机型套餐配置信息
	 */
	public DescribeInstanceTypeConfigsResponse describeInstanceTypeConfigs(DescribeInstanceTypeConfigsRequest request) throws Exception {
		return invokRequest(request, new DescribeInstanceTypeConfigsJsonUnmarshaller(), request.getMarshallerInstance());
	}

	/**
	 * 查询地域列表
	 */
	public DescribeRegionsResponse describeRegions(DescribeRegionsRequest request) throws Exception {
		return invokRequest(request, new DescribeRegionsJsonUnmarshaller(), request.getMarshallerInstance());
	}
	
	/**
	 * 查询可用区列表
	 */
	public DescribeAvailabilityZonesResponse DescribeAvailabilityZones(DescribeAvailabilityZonesRequest request) throws Exception {
		return invokRequest(request, new DescribeAvailabilityZonesJsonUnmarshaller(), request.getMarshallerInstance());
	}
}
