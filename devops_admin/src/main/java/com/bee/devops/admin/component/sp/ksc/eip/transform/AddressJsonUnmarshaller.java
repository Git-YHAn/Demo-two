package com.bee.devops.admin.component.sp.ksc.eip.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.eip.Address;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class AddressJsonUnmarshaller implements Unmarshaller<Address, JsonUnmarshallerContext> {
	@Override
	public Address unmarshall(JsonUnmarshallerContext context) throws Exception {
		Address result = new Address();
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
				if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					result.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("PublicIp", targetDepth)) {
					context.nextToken();
					result.setPublicIp(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("AllocationId", targetDepth)) {
					context.nextToken();
					result.setAllocationId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("State", targetDepth)) {
					context.nextToken();
					result.setState(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("LineId", targetDepth)) {
					context.nextToken();
					result.setLineId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("BandWidth", targetDepth)) {
					context.nextToken();
					result.setBandWidth(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("InstanceType", targetDepth)) {
					context.nextToken();
					result.setInstanceType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceId", targetDepth)) {
					context.nextToken();
					result.setInstanceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("NetworkInterfaceId", targetDepth)) {
					context.nextToken();
					result.setNetworkInterfaceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InternetGatewayId", targetDepth)) {
					context.nextToken();
					result.setInternetGatewayId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("BandWidthShareId", targetDepth)) {
					context.nextToken();
					result.setBandWidthShareId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("IsBandWidthShare", targetDepth)) {
					context.nextToken();
					result.setIsBandWidthShare(context.getUnmarshaller(Boolean.class).unmarshall(context));
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
		return result;
	}

	private static AddressJsonUnmarshaller instance;

	public static AddressJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new AddressJsonUnmarshaller();
		}
		return instance;
	}
}
