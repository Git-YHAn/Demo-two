package com.bee.devops.admin.component.sp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.sp.ksc.eip.KSCEIPClient;
import com.bee.devops.admin.component.sp.ksc.eip.request.DescribeAddressesRequest;
import com.bee.devops.admin.component.sp.ksc.eip.response.DescribeAddressesResponse;
import com.bee.devops.admin.component.sp.model.eip.Address;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.BasicAWSCredentials;
import com.ksc.model.Filter;

/**
 * 服务器供应商网络服务接口代理类
 * 
 * @description EIPServerProviderAPIProxy
 * @author TurnerXi
 * @date 2018年7月19日
 */
public class EIPServerProviderAPIProxy {
	private AWSCredentials credentials;
	private KSCEIPClient kscEIPClient;

	public EIPServerProviderAPIProxy() {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kscEIPClient = new KSCEIPClient(credentials);
		this.kscEIPClient.setEndpoint(kscEIPClient.getEndpointPrefix() + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public EIPServerProviderAPIProxy(String region) {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kscEIPClient = new KSCEIPClient(credentials);
		this.kscEIPClient.setEndpoint(kscEIPClient.getEndpointPrefix() + "." + region + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public static EIPServerProviderAPIProxy getInstance(String region) {
		return new EIPServerProviderAPIProxy(region);
	}

	public static EIPServerProviderAPIProxy getInstance() {
		return new EIPServerProviderAPIProxy();
	}

	/**
	 * 查询弹性IP
	 * 
	 * @param allocationIds
	 * @param projectIds
	 * @param filters
	 * @param maxResults
	 * @param nextToken
	 * @return
	 * @throws Exception
	 */
	public DescribeAddressesResponse queryEIPAddresses(List<String> allocationIds, List<String> projectIds, List<Filter> filters, Integer maxResults,
	        Integer nextToken) throws Exception {
		DescribeAddressesResponse response = kscEIPClient
		        .describeAddresses(new DescribeAddressesRequest(allocationIds, projectIds, filters, maxResults, nextToken));
		return response;
	}

	public List<Address> queryAddressesByInterfaceIds(String... interfaceId) throws Exception {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("network-interface-id", Arrays.asList(interfaceId)));
		DescribeAddressesResponse result = this.queryEIPAddresses(null, null, filters, KSCEIPClient.MAX_RESULTS_MAX, null);
		return result.getAddressesSet();
	}

	public List<Address> queryAllAddresses() throws Exception {
		DescribeAddressesResponse result = this.queryEIPAddresses(null, null, null, KSCEIPClient.MAX_RESULTS_MAX, null);
		return result.getAddressesSet();
	}

}
