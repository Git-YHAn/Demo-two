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

public class DescribeKECImagesRequest extends KscWebServiceRequest implements DryRunSupportedRequest<DescribeKECImagesRequest> {
	/**
	 * 镜像ID
	 * 类型: String
	 */
	String imageId;
	/**
	 * 镜像类型
	 * 类型:String
	 */
	String imageType;

	public DescribeKECImagesRequest(String imageId, String imageType) {
		super();
		this.imageId = imageId;
		this.imageType = imageType;
	}

	public class DescribeImagesMarshaller implements Marshaller<Request<DescribeKECImagesRequest>, DescribeKECImagesRequest> {

		@Override
		public Request<DescribeKECImagesRequest> marshall(DescribeKECImagesRequest describeImagesRequest) throws Exception {
			if (describeImagesRequest == null) {
				throw new KscClientException("Invalid argument passed to marshall(...)");
			}

			Request<DescribeKECImagesRequest> request = new DefaultRequest<DescribeKECImagesRequest>(describeImagesRequest, KSCKECClient.DEFAULT_SIGNING_NAME);
			request.addParameter("Action", "DescribeImages");
			String version = describeImagesRequest.getVersion();
			if (org.apache.commons.lang.StringUtils.isBlank(version)) {
				version = "2016-03-04";
			}
			request.addParameter("Version", version);

			if (!StringUtils.isNullOrEmpty(describeImagesRequest.getImageId())) {
				request.addParameter("ImageId", StringUtils.fromString(describeImagesRequest.getImageId()));
			}
			if (!StringUtils.isNullOrEmpty(describeImagesRequest.getImageType())) {
				request.addParameter("ImageType", StringUtils.fromString(describeImagesRequest.getImageType()));
			}
			request.setHttpMethod(HttpMethodName.GET);

			return request;
		}

	}

	@Transient
	public Request<DescribeKECImagesRequest> getDryRunRequest() {
		Request<DescribeKECImagesRequest> request = null;
		try {
			request = new DescribeImagesMarshaller().marshall(this);
			request.addParameter("DryRun", Boolean.toString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@Transient
	public DescribeImagesMarshaller getMarshallerInstance() {
		return new DescribeImagesMarshaller();
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

}
