package com.bee.devops.admin.component.sp;

import java.util.List;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.sp.ksc.epc.KSCEPCClient;
import com.bee.devops.admin.component.sp.ksc.epc.request.DescribeEPCImagesRequest;
import com.bee.devops.admin.component.sp.ksc.epc.response.DescribeEPCImagesResponse;
import com.bee.devops.admin.component.sp.model.epc.EPCImage;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.BasicAWSCredentials;

/**
 * 服务器供应商云服务器接口代理类
 * 
 * @description KECServerProviderAPIProxy
 * @author TurnerXi
 * @date 2018年7月19日
 */
public class EPCServerProviderAPIProxy {
	private AWSCredentials credentials;
	private KSCEPCClient epcClient;

	public EPCServerProviderAPIProxy() {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.epcClient = new KSCEPCClient(credentials);
		this.epcClient.setEndpoint(epcClient.getEndpointPrefix() + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public EPCServerProviderAPIProxy(String region) {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.epcClient = new KSCEPCClient(credentials);
		this.epcClient.setEndpoint(epcClient.getEndpointPrefix() + "." + region + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public static EPCServerProviderAPIProxy getInstance(String region) {
		return new EPCServerProviderAPIProxy(region);
	}

	public static EPCServerProviderAPIProxy getInstance() {
		return new EPCServerProviderAPIProxy();
	}
	/**
	 * 查询镜像列表
	 * 
	 * @param imageType
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EPCImage> queryImages() throws Exception {
		DescribeEPCImagesResponse describeImages = epcClient.describeImages(new DescribeEPCImagesRequest());
		return describeImages.getImageSet();
	}

}
