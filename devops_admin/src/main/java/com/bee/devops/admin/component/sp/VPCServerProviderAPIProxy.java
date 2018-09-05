package com.bee.devops.admin.component.sp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.sp.ksc.vpc.KSCVPCClient;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSecurityGroupsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSubnetAvailableAddressesRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeSubnetsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.request.DescribeVpcsRequest;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSecurityGroupsResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSubnetAvailableAddressesResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSubnetsResponse;
import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeVpcsResponse;
import com.bee.devops.admin.component.sp.model.vpc.SecurityGroup;
import com.bee.devops.admin.component.sp.model.vpc.Subnet;
import com.bee.devops.admin.component.sp.model.vpc.Vpc;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.BasicAWSCredentials;
import com.ksc.model.Filter;

/**
 * 服务器供应商网络服务接口代理类
 * 
 * @description NetworkServerProviderAPIProxy
 * @author TurnerXi
 * @date 2018年7月19日
 */
public class VPCServerProviderAPIProxy {
	private AWSCredentials credentials;
	private KSCVPCClient kscVPCClient;

	public VPCServerProviderAPIProxy() {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kscVPCClient = new KSCVPCClient(credentials);
		this.kscVPCClient.setEndpoint(kscVPCClient.getEndpointPrefix() + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public VPCServerProviderAPIProxy(String region) {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kscVPCClient = new KSCVPCClient(credentials);
		this.kscVPCClient.setEndpoint(kscVPCClient.getEndpointPrefix() + "." + region + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public static VPCServerProviderAPIProxy getInstance(String region) {
		return new VPCServerProviderAPIProxy(region);
	}

	public static VPCServerProviderAPIProxy getInstance() {
		return new VPCServerProviderAPIProxy();
	}

	/**
	 * 查询vpc列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Vpc> queryVpcs(String... vpcIds) throws Exception {
		DescribeVpcsResponse response = kscVPCClient.describeVpcs(new DescribeVpcsRequest(Arrays.asList(vpcIds)));
		return response.getVpcSet();
	}

	public List<Vpc> queryAllVpcs() throws Exception {
		DescribeVpcsResponse response = kscVPCClient.describeVpcs(new DescribeVpcsRequest());
		return response.getVpcSet();
	}

	/**
	 * 查询安全组
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SecurityGroup> querySecurityGroups() throws Exception {
		DescribeSecurityGroupsResponse response = kscVPCClient.describeSecurityGroups(new DescribeSecurityGroupsRequest());
		return response.getSecurityGroupSet();
	}

	/**
	 * 通过vpcId查询安全组
	 * 
	 * @param vpcId
	 * @return
	 * @throws Exception
	 */
	public List<SecurityGroup> querySecurityGroupsByVpcId(String vpcId) throws Exception {
		List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter();
		filter.setName("vpc-id");
		List<String> values = new ArrayList<>();
		values.add(vpcId);
		filter.setValues(values);
		filters.add(filter);
		DescribeSecurityGroupsResponse response = kscVPCClient.describeSecurityGroups(new DescribeSecurityGroupsRequest(null, filters));
		return response.getSecurityGroupSet();
	}

	/**
	 * 查询子网
	 * 
	 * @param subnetIds
	 * @param filters
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Subnet> querySubnetsByIds(String... subnetIds) throws Exception {
		DescribeSubnetsResponse response = kscVPCClient.describeSubnets(new DescribeSubnetsRequest(Arrays.asList(subnetIds), null));
		return response.getSubnetSet();
	}

	/**
	 * 查询所有子网
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Subnet> queryAllSubnets() throws Exception {
		DescribeSubnetsResponse response = kscVPCClient.describeSubnets(new DescribeSubnetsRequest());
		return response.getSubnetSet();
	}

	/**
	 * 通过vpcId查询子网
	 * 
	 * @param vpcId
	 * @return
	 * @throws Exception
	 */
	public List<Subnet> querySubnetsByVpcId(String vpcId) throws Exception {
		List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter();
		filter.setName("vpc-id");
		List<String> values = new ArrayList<>();
		values.add(vpcId);
		filter.setValues(values);
		filters.add(filter);
		DescribeSubnetsResponse response = kscVPCClient.describeSubnets(new DescribeSubnetsRequest(null, filters));
		return response.getSubnetSet();
	}

	/**
	 * 查询子网可用IP
	 * 
	 * @param subnetId
	 * @return
	 * @throws Exception
	 */
	public List<String> querySubnetAvailableAddresses(String subnetId) throws Exception {
		List<Filter> filters = new ArrayList<>();
		Filter filter = new Filter();
		filter.setName("subnet-id");
		List<String> values = new ArrayList<>();
		values.add(subnetId);
		filter.setValues(values);
		filters.add(filter);
		DescribeSubnetAvailableAddressesResponse response = kscVPCClient.describeSubnetAvailableAddresses(new DescribeSubnetAvailableAddressesRequest(filters));
		return response.getAvailableIpAddresses();
	}

}
