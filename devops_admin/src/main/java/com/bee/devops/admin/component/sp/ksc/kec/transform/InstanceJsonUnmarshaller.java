package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.Instance;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class InstanceJsonUnmarshaller implements Unmarshaller<Instance, JsonUnmarshallerContext> {
	private static InstanceJsonUnmarshaller instance;

	public static InstanceJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new InstanceJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public Instance unmarshall(JsonUnmarshallerContext context) throws Exception {
		Instance instance = new Instance();
		int originalDepth = context.getCurrentDepth();
		String currentParentElement = context.getCurrentParentElement();
		int targetDepth = originalDepth + 1;

		JsonToken token = context.getCurrentToken();
		if (token == null) {
			token = context.nextToken();
		}
		if (token == VALUE_NULL) {
			return null;
		}
		while (true) {
			if (token == null) {
				break;
			}

			if (token == FIELD_NAME || token == START_OBJECT) {
				if (context.testExpression("InstanceId", targetDepth)) {
					context.nextToken();
					instance.setInstanceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceName", targetDepth)) {
					context.nextToken();
					instance.setInstanceName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ChargeType", targetDepth)) {
					context.nextToken();
					instance.setChargeType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreationDate", targetDepth)) {
					context.nextToken();
					instance.setCreationDate(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ImageId", targetDepth)) {
					context.nextToken();
					instance.setImageId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceConfigure", targetDepth)) {
					context.nextToken();
					instance.setInstanceConfigure(InstanceConfigureJsonUnmarshaller.getInstance().unmarshall(context));
				} else if (context.testExpression("InstanceState", targetDepth)) {
					context.nextToken();
					instance.setInstanceState(InstanceStateJsonUnmarshaller.getInstance().unmarshall(context));
				} else if (context.testExpression("InstanceType", targetDepth)) {
					context.nextToken();
					instance.setInstanceType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Monitoring", targetDepth)) {
					context.nextToken();
					instance.setMonitoring(MonitoringJsonUnmarshaller.getInstance().unmarshall(context));
				} else if (context.testExpression("NetworkInterfaceSet", targetDepth)) {
					context.nextToken();
					instance.setNetworkInterfaceSet(new ListUnmarshaller<>(NetworkInterfaceJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("PrivateIpAddress", targetDepth)) {
					context.nextToken();
					instance.setPrivateIpAddress(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ProjectId", targetDepth)) {
					context.nextToken();
					instance.setProjectId(context.getUnmarshaller(Long.class).unmarshall(context));
				} else if (context.testExpression("SriovNetSupport", targetDepth)) {
					context.nextToken();
					instance.setSriovNetSupport(context.getUnmarshaller(Boolean.class).unmarshall(context));
				} else if (context.testExpression("SubnetId", targetDepth)) {
					context.nextToken();
					instance.setSubnetId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("AvailabilityZoneName", targetDepth)) {
					context.nextToken();
					instance.setAvailabilityZoneName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ProductType", targetDepth)) {
					context.nextToken();
					instance.setProductType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ProductWhat", targetDepth)) {
					context.nextToken();
					instance.setProductWhat(context.getUnmarshaller(String.class).unmarshall(context));
				}
			} else if (token == END_ARRAY || token == END_OBJECT) {
				if (context.getLastParsedParentElement() == null || context.getLastParsedParentElement().equals(currentParentElement)) {
					if (context.getCurrentDepth() <= originalDepth) {
						break;
					}
				}
			}
			token = context.nextToken();
		}
		return instance;
	}

}
