package com.bee.devops.admin.component.sp.ksc.vpc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.vpc.SecurityGroupEntry;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class SecurityGroupEntryJsonUnmarshaller implements Unmarshaller<SecurityGroupEntry, JsonUnmarshallerContext> {
	private static SecurityGroupEntryJsonUnmarshaller instance;

	public static SecurityGroupEntryJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new SecurityGroupEntryJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public SecurityGroupEntry unmarshall(JsonUnmarshallerContext context) throws Exception {
		SecurityGroupEntry securityGroupEntry = new SecurityGroupEntry();
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
				if (context.testExpression("SecurityGroupEntryId", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setSecurityGroupEntryId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Protocol", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setProtocol(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("PortRangeTo", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setPortRangeTo(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("PortRangeFrom", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setPortRangeFrom(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("IcmpType", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setIcmpType(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("IcmpCode", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setIcmpCode(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("Direction", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setDirection(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Description", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setDescription(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CidrBlock", targetDepth)) {
					context.nextToken();
					securityGroupEntry.setCidrBlock(context.getUnmarshaller(String.class).unmarshall(context));
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
		return securityGroupEntry;
	}

}
