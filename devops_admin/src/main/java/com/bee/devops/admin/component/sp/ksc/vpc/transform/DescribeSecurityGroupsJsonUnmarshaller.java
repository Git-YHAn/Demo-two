package com.bee.devops.admin.component.sp.ksc.vpc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.ksc.vpc.response.DescribeSecurityGroupsResponse;
import com.bee.devops.admin.component.sp.model.vpc.SecurityGroup;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class DescribeSecurityGroupsJsonUnmarshaller implements Unmarshaller<DescribeSecurityGroupsResponse, JsonUnmarshallerContext> {
	@Override
	public DescribeSecurityGroupsResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
		DescribeSecurityGroupsResponse result = new DescribeSecurityGroupsResponse();
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
				if (context.testExpression("SecurityGroupSet", targetDepth)) {
					context.nextToken();
					result.setSecurityGroupSet(new ListUnmarshaller<SecurityGroup>(SecurityGroupJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("RequestId", targetDepth)) {
					context.nextToken();
					result.setRequestId(context.getUnmarshaller(String.class).unmarshall(context));
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

	private static DescribeSecurityGroupsJsonUnmarshaller instance;

	public static DescribeSecurityGroupsJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new DescribeSecurityGroupsJsonUnmarshaller();
		}
		return instance;
	}
}
