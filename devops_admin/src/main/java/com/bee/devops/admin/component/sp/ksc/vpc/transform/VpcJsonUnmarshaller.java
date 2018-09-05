package com.bee.devops.admin.component.sp.ksc.vpc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.vpc.Vpc;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class VpcJsonUnmarshaller implements Unmarshaller<Vpc, JsonUnmarshallerContext> {
	private static VpcJsonUnmarshaller instance;

	public static VpcJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new VpcJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public Vpc unmarshall(JsonUnmarshallerContext context) throws Exception {
		Vpc vpc = new Vpc();
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
				if (context.testExpression("VpcId", targetDepth)) {
					context.nextToken();
					vpc.setVpcId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("VpcName", targetDepth)) {
					context.nextToken();
					vpc.setVpcName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CidrBlock", targetDepth)) {
					context.nextToken();
					vpc.setCidrBlock(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					vpc.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("IsDefault", targetDepth)) {
					context.nextToken();
					vpc.setIsDefault(context.getUnmarshaller(Boolean.class).unmarshall(context));
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
		return vpc;
	}

}
