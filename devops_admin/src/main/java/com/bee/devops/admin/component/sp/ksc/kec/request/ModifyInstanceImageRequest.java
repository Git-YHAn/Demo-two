package com.bee.devops.admin.component.sp.ksc.kec.request;

import java.beans.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.ksc.kec.KSCKECClient;
import com.ksc.DefaultRequest;
import com.ksc.KscClientException;
import com.ksc.KscWebServiceRequest;
import com.ksc.Request;
import com.ksc.http.HttpMethodName;
import com.ksc.model.DryRunSupportedRequest;
import com.ksc.transform.Marshaller;
import com.ksc.util.StringUtils;

public class ModifyInstanceImageRequest extends KscWebServiceRequest implements DryRunSupportedRequest<ModifyInstanceImageRequest> {
	/**
	 * 
	 * 待更换或者重新安装操作系统的实例ID
	 * 类型: String
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 * 是否可缺省: 否
	 */
	String instanceId;
	/**
	 * 待更换的镜像ID；如果缺省，表明无需改变镜像，只需重新安装实例的操作系统。
	 * 类型: String
	 * 有效值：标准UUID格式，形如^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$
	 * 是否可缺省: 是
	 */
	String imageId;

	/**
	 * 实例开机密码
	 * 类型: String
	 * 有效值：最短8字符，最长32字符，必须包含大小写英文字符和数字，支持其他可见字符
	 * 是否可缺省: 是
	 */
	String instancePassword;

	/**
	 * 保留镜像设置登录。该参数只对使用自定义镜像有效。当该值填写为true，默认InstancePassword参数无效。该参数与InstancePassword必须填写一个。
	 * 类型：Boolean
	 * 有效值：true | false
	 * 默认值：false
	 * 是否可以缺省：是
	 */
	Boolean keepImageLogin;

	public ModifyInstanceImageRequest() {
		super();
	}

	public ModifyInstanceImageRequest(String instanceId, String imageId, String instancePassword, Boolean keepImageLogin) {
		super();
		this.instanceId = instanceId;
		this.imageId = imageId;
		this.instancePassword = instancePassword;
		this.keepImageLogin = keepImageLogin;
	}

	public class ModifyInstanceImageMarshaller implements Marshaller<Request<ModifyInstanceImageRequest>, ModifyInstanceImageRequest> {

		@Override
		public Request<ModifyInstanceImageRequest> marshall(ModifyInstanceImageRequest modifyInstanceImageRequest) throws Exception {
			if (modifyInstanceImageRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<ModifyInstanceImageRequest> request = new DefaultRequest<ModifyInstanceImageRequest>(modifyInstanceImageRequest,
			        KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "ModifyInstanceImage");
			String version = modifyInstanceImageRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (modifyInstanceImageRequest.getKeepImageLogin() != null) {
				request.addParameter("KeepImageLogin", StringUtils.fromBoolean(modifyInstanceImageRequest.getKeepImageLogin()));
			}
			if (!StringUtils.isNullOrEmpty(modifyInstanceImageRequest.getInstanceId())) {
				request.addParameter("InstanceId", StringUtils.fromString(modifyInstanceImageRequest.getInstanceId()));
			}
			if (!StringUtils.isNullOrEmpty(modifyInstanceImageRequest.getImageId())) {
				request.addParameter("ImageId", StringUtils.fromString(modifyInstanceImageRequest.getImageId()));
			}
			if (!StringUtils.isNullOrEmpty(modifyInstanceImageRequest.getInstancePassword())) {
				request.addParameter("InstancePassword", StringUtils.fromString(modifyInstanceImageRequest.getInstancePassword()));
			}

			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<ModifyInstanceImageRequest> getDryRunRequest() {
		Request<ModifyInstanceImageRequest> request = null;
		try {
			request = new ModifyInstanceImageMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public ModifyInstanceImageMarshaller getMarshallerInstance() {
		return new ModifyInstanceImageMarshaller();
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getInstancePassword() {
		return instancePassword;
	}

	public void setInstancePassword(String instancePassword) {
		this.instancePassword = instancePassword;
	}

	public Boolean getKeepImageLogin() {
		return keepImageLogin;
	}

	public void setKeepImageLogin(Boolean keepImageLogin) {
		this.keepImageLogin = keepImageLogin;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}
}
