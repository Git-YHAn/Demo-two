package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.NetworkInterface;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class NetworkInterfaceJsonUnmarshaller implements Unmarshaller<NetworkInterface, JsonUnmarshallerContext> {
	private static NetworkInterfaceJsonUnmarshaller instance;

	public static NetworkInterfaceJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new NetworkInterfaceJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public NetworkInterface unmarshall(JsonUnmarshallerContext context) throws Exception {
		NetworkInterface interfac = new NetworkInterface();
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
				if (context.testExpression("NetworkInterfaceId", targetDepth)) {
					context.nextToken();
					interfac.setNetworkInterfaceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("NetworkInterfaceType", targetDepth)) {
					context.nextToken();
					interfac.setNetworkInterfaceType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("PrivateIpAddress", targetDepth)) {
					context.nextToken();
					interfac.setPrivateIpAddress(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SubnetId", targetDepth)) {
					context.nextToken();
					interfac.setSubnetId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("MacAddress", targetDepth)) {
					context.nextToken();
					interfac.setMacAddress(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("VpcId", targetDepth)) {
					context.nextToken();
					interfac.setVpcId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SecurityGroupSet", targetDepth)) {
					context.nextToken();
					interfac.setSecurityGroupSet(new ListUnmarshaller<>(GroupIdentifierJsonUnmarshaller.getInstance()).unmarshall(context));
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
		return interfac;
	}

}
