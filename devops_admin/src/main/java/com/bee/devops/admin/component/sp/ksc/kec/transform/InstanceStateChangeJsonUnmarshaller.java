package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.InstanceStateChange;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class InstanceStateChangeJsonUnmarshaller implements Unmarshaller<InstanceStateChange, JsonUnmarshallerContext> {
	private static InstanceStateChangeJsonUnmarshaller instance;

	public static InstanceStateChangeJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new InstanceStateChangeJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public InstanceStateChange unmarshall(JsonUnmarshallerContext context) throws Exception {
		InstanceStateChange instanceStateChange = new InstanceStateChange();
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
					instanceStateChange.setInstanceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Return", targetDepth)) {
					context.nextToken();
					instanceStateChange.setReturn(context.getUnmarshaller(String.class).unmarshall(context));
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
		return instanceStateChange;
	}

}
