package com.bee.devops.admin.component.sp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.bee.devops.admin.common.page.Meta;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.sp.ksc.common.Sort;
import com.bee.devops.admin.component.sp.ksc.kec.KSCKECClient;
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
import com.bee.devops.admin.component.sp.model.kec.AvailabilityZone;
import com.bee.devops.admin.component.sp.model.kec.Instance;
import com.bee.devops.admin.component.sp.model.kec.InstanceFamily;
import com.bee.devops.admin.component.sp.model.kec.InstanceStateChange;
import com.bee.devops.admin.component.sp.model.kec.InstanceTypeConfig;
import com.bee.devops.admin.component.sp.model.kec.KECImage;
import com.bee.devops.admin.component.sp.model.kec.Region;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.BasicAWSCredentials;
import com.ksc.model.Filter;

/**
 * 服务器供应商云服务器接口代理类
 * 
 * @description KECServerProviderAPIProxy
 * @author TurnerXi
 * @date 2018年7月19日
 */
public class KECServerProviderAPIProxy {
	private AWSCredentials credentials;
	private KSCKECClient kecClient;

	public KECServerProviderAPIProxy() {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kecClient = new KSCKECClient(credentials);
		this.kecClient.setEndpoint(kecClient.getEndpointPrefix() + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public KECServerProviderAPIProxy(String region) {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.kecClient = new KSCKECClient(credentials);
		this.kecClient.setEndpoint(kecClient.getEndpointPrefix() + "." + region + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public static KECServerProviderAPIProxy getInstance() {
		return new KECServerProviderAPIProxy();
	}

	public static KECServerProviderAPIProxy getInstance(String region) {
		return new KECServerProviderAPIProxy(region);
	}

	/**
	 * 查询镜像列表
	 * 
	 * @param imageType
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<KECImage> queryImages(String imageId, String imageType) throws Exception {
		DescribeKECImagesResponse describeImages = kecClient.describeImages(new DescribeKECImagesRequest(imageId, imageType));
		return describeImages.getImagesSet();
	}

	public KECImage getImageById(String imageId) throws Exception {
		List<KECImage> images = queryImages(imageId, null);
		return images == null || images.size() == 0 ? null : images.get(0);
	}

	public List<KECImage> queryImagesByType(String imageType) throws Exception {
		return queryImages(null, imageType);
	}

	public List<KECImage> queryAllImages() throws Exception {
		return queryImages(null, null);
	}

	/**
	 * 查询实例
	 * 
	 * @param maxResults
	 *            最大条数
	 * @param marker
	 *            当前页起点
	 * @param instanceIds
	 *            实例ID
	 * @param projectIds
	 *            项目ID
	 * @param filters
	 *            过滤器 key:value1,value2,value3<br/>
	 *            instance-id 实例ID<br/>
	 *            subnet-id 子网ID<br/>
	 *            vpc-id vpc ID<br/>
	 *            network-interface.subnet-id 网络接口关联的子网ID<br/>
	 *            network-interface.network-interface-id 网络接口的ID<br/>
	 *            network-interface.group-id 网络接口关联的安全组ID<br/>
	 *            instance-state.name-实例状态<br/>
	 *            availability-zone.name 可用区（AvailabilityZone）名字(规划中)
	 * @param sorts
	 *            排序 ASC|DESC<br/>
	 *            InstanceName –主机名称<br/>
	 *            CreationDate –创建时间<br/>
	 *            PrivateIpAddress - 主机内网IP（主网卡）<br/>
	 * @param search
	 *            模糊查询<br/>
	 *            实例名（InstanceName）、主网卡私有IP地址（PrivateIpAddress）
	 * @return
	 * @throws Exception
	 */
	private DescribeInstancesResponse queryInstances(Integer maxResults, Integer marker, List<String> instanceIds, List<Long> projectIds,
	        Map<String, String> filters, Map<String, String> sorts, String search) throws Exception {
		List<Filter> filterList = new LinkedList<>();
		if (filters != null) {
			Set<Entry<String, String>> filterEntries = filters.entrySet();
			for (Entry<String, String> entry : filterEntries) {
				filterList.add(new Filter(entry.getKey(), Arrays.asList(entry.getValue().split(","))));
			}
		}

		List<Sort> sortList = new LinkedList<>();
		if (sorts != null) {
			Set<Entry<String, String>> sortEntries = sorts.entrySet();
			for (Entry<String, String> entry : sortEntries) {
				sortList.add(new Sort(entry.getKey(), entry.getValue()));
			}
		}

		DescribeInstancesRequest request = new DescribeInstancesRequest(maxResults, marker, instanceIds, projectIds, filterList, sortList, search);
		DescribeInstancesResponse response = kecClient.describeInstances(request);
		return response;
	}

	/**
	 * 
	 * @param pageSize
	 * @param pageNum
	 * @param sorts
	 *            排序 ASC|DESC<br/>
	 *            InstanceName –主机名称<br/>
	 *            CreationDate –创建时间<br/>
	 *            PrivateIpAddress - 主机内网IP（主网卡）<br/>
	 * @param search
	 *            模糊查询<br/>
	 *            实例名（InstanceName）、主网卡私有IP地址（PrivateIpAddress）
	 * @return
	 * @throws Exception
	 */
	public PageBean<Instance> queryInstances(Integer pageNum, Integer pageSize, Map<String, String> sorts, String search) throws Exception {
		DescribeInstancesResponse result = this.queryInstances(pageSize, (pageNum - 1) * pageSize, null, null, null, sorts, search);
		int pages = (result.getInstanceCount() % pageSize > 0 ? 1 : 0) + result.getInstanceCount() / pageSize;
		Meta meta = new Meta(result.getInstanceCount(), pageNum, pageSize, pages, result.getInstancesSet().size());
		return new PageBean<>(meta, result.getInstancesSet());
	}

	/**
	 * 创建云服务器实例
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<Instance> runImageInstance(RunInstancesRequest request) throws Exception {
		RunInstancesResponse response = kecClient.runInstances(request);
		return response.getInstancesSet();
	}

	/**
	 * 启动实例
	 * 
	 * @param instanceIds
	 * @return
	 * @throws Exception
	 */
	public List<InstanceStateChange> startInstances(String... instanceIds) throws Exception {
		StartInstancesRequest request = new StartInstancesRequest(Arrays.asList(instanceIds));
		StartInstancesResponse response = kecClient.startInstances(request);
		return response.getInstancesSet();
	}

	/**
	 * 停止实例
	 * 
	 * @param forceStop
	 *            强制关闭
	 * @param instanceIds
	 * @return
	 * @throws Exception
	 */
	public List<InstanceStateChange> stopInstances(Boolean forceStop, String... instanceIds) throws Exception {
		StopInstancesRequest request = new StopInstancesRequest(Arrays.asList(instanceIds), forceStop);
		StopInstancesResponse response = kecClient.stopInstances(request);
		return response.getInstancesSet();
	}

	/**
	 * 重启实例
	 * 
	 * @param forceStop
	 * @param instanceIds
	 * @return
	 * @throws Exception
	 */
	public List<InstanceStateChange> rebootInstances(String... instanceIds) throws Exception {
		RebootInstancesRequest request = new RebootInstancesRequest(Arrays.asList(instanceIds));
		RebootInstancesResponse response = kecClient.rebootInstances(request);
		return response.getInstancesSet();
	}

	/**
	 * 销毁实例
	 * 
	 * @param instanceIds
	 * @return
	 * @throws Exception
	 */
	public List<InstanceStateChange> terminateInstances(String... instanceIds) throws Exception {
		TerminateInstancesRequest request = new TerminateInstancesRequest(Arrays.asList(instanceIds));
		TerminateInstancesResponse response = kecClient.terminateInstances(request);
		return response.getInstancesSet();
	}

	/**
	 * 重装系统
	 * 
	 * @param instanceId
	 * @param imageId
	 * @param instancePassword
	 * @param keepImageLogin
	 * @return
	 * @throws Exception
	 */
	public String modifyInstanceImage(String instanceId, String imageId, String instancePassword, Boolean keepImageLogin) throws Exception {
		ModifyInstanceImageRequest request = new ModifyInstanceImageRequest(instanceId, imageId, instancePassword, keepImageLogin);
		ModifyInstanceImageResponse response = kecClient.modifyInstanceImage(request);
		return response.getReturn();
	}

	/**
	 * 查询机型配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<InstanceFamily> describeInstanceFamilys() throws Exception {
		DescribeInstanceFamilysRequest request = new DescribeInstanceFamilysRequest();
		DescribeInstanceFamilysResponse response = kecClient.describeInstanceFamilys(request);
		return response.getInstanceFamilySet();
	}

	/**
	 * 查询机型套餐配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<InstanceTypeConfig> describeInstanceTypeConfigs() throws Exception {
		DescribeInstanceTypeConfigsRequest request = new DescribeInstanceTypeConfigsRequest();
		DescribeInstanceTypeConfigsResponse response = kecClient.describeInstanceTypeConfigs(request);
		return response.getInstanceTypeConfigSet();
	}

	/**
	 * 查询地域列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Region> describeRegions() throws Exception {
		DescribeRegionsRequest request = new DescribeRegionsRequest();
		DescribeRegionsResponse response = kecClient.describeRegions(request);
		return response.getRegionSet();
	}

	/**
	 * 查询可用区列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AvailabilityZone> describeAvailabilityZones() throws Exception {
		DescribeAvailabilityZonesRequest request = new DescribeAvailabilityZonesRequest();
		DescribeAvailabilityZonesResponse response = kecClient.DescribeAvailabilityZones(request);
		return response.getAvailabilityZoneSet();

	}

}
