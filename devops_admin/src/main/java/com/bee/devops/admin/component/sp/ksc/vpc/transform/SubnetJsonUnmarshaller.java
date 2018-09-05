package com.bee.devops.admin.component.sp.ksc.vpc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.vpc.Subnet;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class SubnetJsonUnmarshaller implements Unmarshaller<Subnet, JsonUnmarshallerContext> {
	private static SubnetJsonUnmarshaller instance;

	public static SubnetJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new SubnetJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public Subnet unmarshall(JsonUnmarshallerContext context) throws Exception {
		Subnet subnet = new Subnet();
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
				if (context.testExpression("SubnetId", targetDepth)) {
					context.nextToken();
					subnet.setSubnetId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SubnetName", targetDepth)) {
					context.nextToken();
					subnet.setSubnetName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("VpcId", targetDepth)) {
					context.nextToken();
					subnet.setVpcId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SubnetType", targetDepth)) {
					context.nextToken();
					subnet.setSubnetType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("NetworkAclId", targetDepth)) {
					context.nextToken();
					subnet.setNetworkAclId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("NatId", targetDepth)) {
					context.nextToken();
					subnet.setNatId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("GatewayIp", targetDepth)) {
					context.nextToken();
					subnet.setGatewayIp(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Dns2", targetDepth)) {
					context.nextToken();
					subnet.setDns2(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Dns1", targetDepth)) {
					context.nextToken();
					subnet.setDns1(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("DhcpIpTo", targetDepth)) {
					context.nextToken();
					subnet.setDhcpIpTo(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("DhcpIpFrom", targetDepth)) {
					context.nextToken();
					subnet.setDhcpIpFrom(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					subnet.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CidrBlock", targetDepth)) {
					context.nextToken();
					subnet.setCidrBlock(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("AvailbleIPNumber", targetDepth)) {
					context.nextToken();
					subnet.setAvailbleIPNumber(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("AvailabilityZoneName", targetDepth)) {
					context.nextToken();
					subnet.setAvailabilityZoneName(context.getUnmarshaller(String.class).unmarshall(context));
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
		return subnet;
	}

}
