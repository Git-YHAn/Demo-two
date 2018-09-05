package com.bee.devops.admin.component.sp.ksc.vpc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.vpc.SecurityGroup;
import com.bee.devops.admin.component.sp.model.vpc.SecurityGroupEntry;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class SecurityGroupJsonUnmarshaller implements Unmarshaller<SecurityGroup, JsonUnmarshallerContext> {
	private static SecurityGroupJsonUnmarshaller instance;

	public static SecurityGroupJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new SecurityGroupJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public SecurityGroup unmarshall(JsonUnmarshallerContext context) throws Exception {
		SecurityGroup securityGroup = new SecurityGroup();
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

				if (context.testExpression("SecurityGroupId", targetDepth)) {
					context.nextToken();
					securityGroup.setSecurityGroupId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SecurityGroupName", targetDepth)) {
					context.nextToken();
					securityGroup.setSecurityGroupName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SecurityGroupType", targetDepth)) {
					context.nextToken();
					securityGroup.setSecurityGroupType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("VpcId", targetDepth)) {
					context.nextToken();
					securityGroup.setVpcId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					securityGroup.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SecurityGroupEntrySet", targetDepth)) {
					context.nextToken();
					securityGroup.setSecurityGroupEntrySet(
					        new ListUnmarshaller<SecurityGroupEntry>(SecurityGroupEntryJsonUnmarshaller.getInstance()).unmarshall(context));
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
		return securityGroup;
	}

}
