package com.bee.devops.admin.core.controller.ops.ksyun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.annotation.LogAnnotation;
import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.component.sp.EIPServerProviderAPIProxy;
import com.bee.devops.admin.component.sp.IAMServerProviderAPIProxy;
import com.bee.devops.admin.component.sp.KECServerProviderAPIProxy;
import com.bee.devops.admin.component.sp.VPCServerProviderAPIProxy;
import com.bee.devops.admin.component.sp.model.eip.Address;
import com.bee.devops.admin.component.sp.model.iam.Project;
import com.bee.devops.admin.component.sp.model.kec.AvailabilityZone;
import com.bee.devops.admin.component.sp.model.kec.Instance;
import com.bee.devops.admin.component.sp.model.kec.InstanceFamily;
import com.bee.devops.admin.component.sp.model.kec.InstanceStateChange;
import com.bee.devops.admin.component.sp.model.kec.InstanceTypeConfig;
import com.bee.devops.admin.component.sp.model.kec.KECImage;
import com.bee.devops.admin.component.sp.model.kec.NetworkInterface;
import com.bee.devops.admin.component.sp.model.kec.Region;
import com.bee.devops.admin.component.sp.model.vpc.SecurityGroup;
import com.bee.devops.admin.component.sp.model.vpc.Subnet;
import com.bee.devops.admin.component.sp.model.vpc.Vpc;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecBaseRequest;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecModifyInstanceImageRequest;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecRebootInstanceRequest;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecRunInstanceRequest;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecStartInstanceRequest;
import com.bee.devops.admin.core.controller.ops.ksyun.request.KecStopInstanceRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/ksyun/kec")
@LogAnnotation(module = "金山云KEC")
public class OpsKsyunKecController extends BaseController {
	final static Logger log = Logger.getLogger(OpsKsyunKecController.class);

	@ApiOperation(value = "查询云服务器实例", notes = "查询云服务器实例")
	@RequestMapping(value = { "/search/instance/{pageNum}/{pageSize}/{region}/{keywords}",
	        "/search/instance/{pageNum}/{pageSize}/{region}" }, method = RequestMethod.GET)
	public ResultHandler<PageBean<Instance>> queryInstances(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable String region,
	        @PathVariable(required = false) String keywords) throws Exception {
		Map<String, String> sorts = new HashMap<>();
		sorts.put("CreationDate", "DESC");
		sorts.put("InstanceName", "ASC");
		PageBean<Instance> results = KECServerProviderAPIProxy.getInstance(region).queryInstances(pageNum, pageSize, sorts, keywords);
		List<KECImage> images = KECServerProviderAPIProxy.getInstance(region).queryAllImages();
		List<Address> addresses = EIPServerProviderAPIProxy.getInstance(region).queryAllAddresses();
		List<Subnet> subnets = VPCServerProviderAPIProxy.getInstance(region).queryAllSubnets();
		List<Vpc> vpcs = VPCServerProviderAPIProxy.getInstance(region).queryAllVpcs();
		List<Instance> list = results.getResults();
		for (Instance instance : list) {
			for (KECImage image : images) {
				if (image.getImageId().equals(instance.getImageId())) {
					instance.setImage(image);
					break;
				}
			}

			List<NetworkInterface> networkInterfaceSet = instance.getNetworkInterfaceSet();
			for (NetworkInterface networkInterface : networkInterfaceSet) {
				for (Address address : addresses) {
					if (networkInterface.getNetworkInterfaceId().equals(address.getNetworkInterfaceId())) {
						networkInterface.setAddress(address);
						break;
					}
				}
				for (Vpc vpc : vpcs) {
					if (networkInterface.getVpcId().equals(vpc.getVpcId())) {
						networkInterface.setVpc(vpc);
						break;
					}
				}
				for (Subnet subnet : subnets) {
					if (networkInterface.getSubnetId().equals(subnet.getSubnetId())) {
						networkInterface.setSubNet(subnet);
						break;
					}
				}
			}
		}
		return ResultHandler.success(results);
	}

	@ApiOperation(value = "查询机型配置信息", notes = "查询机型配置信息")
	@RequestMapping(value = "/search/instance/familys", method = RequestMethod.POST)
	public ResultHandler<List<InstanceFamily>> describeInstanceFamilys(@RequestBody KecBaseRequest request) throws Exception {
		return ResultHandler.success(KECServerProviderAPIProxy.getInstance(request.getRegion()).describeInstanceFamilys());
	}

	@ApiOperation(value = "创建云服务器实例", notes = "创建云服务器实例")
	@RequestMapping(value = "/run/instance", method = RequestMethod.POST)
	public ResultHandler<List<Instance>> runInstance(@RequestBody KecRunInstanceRequest request) throws Exception {
		List<Instance> list = KECServerProviderAPIProxy.getInstance(request.getRegion()).runImageInstance(request.transRequest());
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "开启云服务器实例", notes = "开启云服务器实例")
	@RequestMapping(value = "/start/instance", method = RequestMethod.POST)
	public ResultHandler<List<InstanceStateChange>> startInstance(@RequestBody KecStartInstanceRequest request) throws Exception {
		String[] arr = new String[request.getInstanceIds().size()];
		List<InstanceStateChange> list = KECServerProviderAPIProxy.getInstance().startInstances(request.getInstanceIds().toArray(arr));
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "关闭云服务器实例", notes = "关闭云服务器实例")
	@RequestMapping(value = "/stop/instance", method = RequestMethod.POST)
	public ResultHandler<List<InstanceStateChange>> stopInstance(@RequestBody KecStopInstanceRequest request) throws Exception {
		String[] arr = new String[request.getInstanceIds().size()];
		List<InstanceStateChange> list = KECServerProviderAPIProxy.getInstance().stopInstances(request.getForceStop(), request.getInstanceIds().toArray(arr));
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "重启云服务器实例", notes = "重启云服务器实例")
	@RequestMapping(value = "/reboot/instance", method = RequestMethod.POST)
	public ResultHandler<List<InstanceStateChange>> rebootInstance(@RequestBody KecRebootInstanceRequest request) throws Exception {
		String[] arr = new String[request.getInstanceIds().size()];
		List<InstanceStateChange> list = KECServerProviderAPIProxy.getInstance().rebootInstances(request.getInstanceIds().toArray(arr));
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "云服务器实例重装系统", notes = "云服务器实例重装系统")
	@RequestMapping(value = "/modify/instance/image", method = RequestMethod.POST)
	public ResultHandler<List<Map<String, String>>> modifyInstanceImage(@RequestBody KecModifyInstanceImageRequest request) throws Exception {
		List<String> instanceIds = request.getInstanceIds();
		List<Map<String, String>> resultMap = new ArrayList<>();
		for (String instanceId : instanceIds) {
			Map<String, String> params = new HashMap<>();
			params.put("instanceId", instanceId);
			try {
				String result = KECServerProviderAPIProxy.getInstance().modifyInstanceImage(instanceId, request.getImageId(), request.getInstancePassword(),
				        request.getKeepImageLogin());
				params.put("result", result);
			} catch (Exception e) {
				params.put("result", e.getMessage());
				log.error("实例[" + instanceId + "]重装系统失败：" + e.getMessage());
				e.printStackTrace();
			}
			resultMap.add(params);
		}
		return ResultHandler.success(resultMap);
	}

	@ApiOperation(value = "根据类型查询镜像信息", notes = "根据类型查询镜像信息")
	@RequestMapping(value = { "/search/image", "/search/image/{imageType}" }, method = RequestMethod.GET)
	public ResultHandler<List<KECImage>> queryImagesByType(@PathVariable(required = false) String imageType) throws Exception {
		List<KECImage> images = KECServerProviderAPIProxy.getInstance().queryImagesByType(imageType);
		return ResultHandler.success(images);
	}

	@ApiOperation(value = "查询机型套餐配置信息", notes = "查询机型套餐配置信息")
	@RequestMapping(value = "/search/instance/type/config", method = RequestMethod.GET)
	public ResultHandler<List<InstanceTypeConfig>> queryInstanceTypeConfigs() throws Exception {
		List<InstanceTypeConfig> configs = KECServerProviderAPIProxy.getInstance().describeInstanceTypeConfigs();
		return ResultHandler.success(configs);
	}

	@ApiOperation(value = "查询地域列表", notes = "查询地域列表")
	@RequestMapping(value = "/search/regions", method = RequestMethod.GET)
	public ResultHandler<List<Region>> queryRegions() throws Exception {
		List<Region> regions = KECServerProviderAPIProxy.getInstance().describeRegions();
		return ResultHandler.success(regions);
	}

	@ApiOperation(value = "查询可用区列表", notes = "查询可用区列表")
	@RequestMapping(value = "/search/available/zones", method = RequestMethod.POST)
	public ResultHandler<List<AvailabilityZone>> queryAvailabilityZones(@RequestBody KecBaseRequest request) throws Exception {
		List<AvailabilityZone> zones = KECServerProviderAPIProxy.getInstance(request.getRegion()).describeAvailabilityZones();
		return ResultHandler.success(zones);
	}

	@ApiOperation(value = "查询vpc列表", notes = "查询vpc列表")
	@RequestMapping(value = "/search/vpcs", method = RequestMethod.POST)
	public ResultHandler<List<Vpc>> queryAllVpcs(@RequestBody KecBaseRequest request) throws Exception {
		List<Vpc> vpcs = VPCServerProviderAPIProxy.getInstance(request.getRegion()).queryAllVpcs();
		return ResultHandler.success(vpcs);
	}

	@ApiOperation(value = "通过vpcId查询子网列表", notes = "通过vpcId查询子网列表")
	@RequestMapping(value = "/search/subnets/{vpcId}", method = RequestMethod.GET)
	public ResultHandler<List<Subnet>> querySubnetsByVpcId(@PathVariable String vpcId) throws Exception {
		List<Subnet> subnets = VPCServerProviderAPIProxy.getInstance().querySubnetsByVpcId(vpcId);
		return ResultHandler.success(subnets);
	}

	@ApiOperation(value = "通过子网ID查询可用IP", notes = "通过子网ID查询可用IP")
	@RequestMapping(value = "/search/subnets/ips/{subnetId}", method = RequestMethod.GET)
	public ResultHandler<List<String>> querySubnetAvailableAddresses(@PathVariable String subnetId) throws Exception {
		List<String> list = VPCServerProviderAPIProxy.getInstance().querySubnetAvailableAddresses(subnetId);
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "通过vpcId查询安全组信息", notes = "通过vpcId查询安全组信息")
	@RequestMapping(value = "/search/security/groups/{vpcId}", method = RequestMethod.GET)
	public ResultHandler<List<SecurityGroup>> querySecurityGroupsByVpcId(@PathVariable String vpcId) throws Exception {
		List<SecurityGroup> list = VPCServerProviderAPIProxy.getInstance().querySecurityGroupsByVpcId(vpcId);
		return ResultHandler.success(list);
	}

	@ApiOperation(value = "查询账号下所有项目", notes = "查询账号下所有项目")
	@RequestMapping(value = "/search/projects", method = RequestMethod.GET)
	public ResultHandler<List<Project>> queryAllProjectList() throws Exception {
		List<Project> list = IAMServerProviderAPIProxy.getInstance().queryAllProjectList().getProjectList();
		return ResultHandler.success(list);
	}
}
