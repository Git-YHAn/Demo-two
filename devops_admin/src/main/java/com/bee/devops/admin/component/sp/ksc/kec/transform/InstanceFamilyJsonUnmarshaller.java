package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.AvailabilityZoneInfo;
import com.bee.devops.admin.component.sp.model.kec.InstanceFamily;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class InstanceFamilyJsonUnmarshaller implements Unmarshaller<InstanceFamily, JsonUnmarshallerContext> {

	@Override
	public InstanceFamily unmarshall(JsonUnmarshallerContext context) throws Exception {
		InstanceFamily result = new InstanceFamily();
		int originalDepth = context.getCurrentDepth();
		String currentParentElement = context.getCurrentParentElement();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

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
				if (context.testExpression("AvailabilityZoneSet", targetDepth)) {
					context.nextToken();
					result.setAvailabilityZoneSet(
					        new ListUnmarshaller<AvailabilityZoneInfo>(AvailabilityZoneInfoJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("InstanceFamilyName", targetDepth)) {
					context.nextToken();
					result.setInstanceFamilyName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceFamily", targetDepth)) {
					context.nextToken();
					result.setInstanceFamily(context.getUnmarshaller(String.class).unmarshall(context));
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

	private static InstanceFamilyJsonUnmarshaller instance;

	public static InstanceFamilyJsonUnmarshaller getInstance() {
		if (instance == null)
			instance = new InstanceFamilyJsonUnmarshaller();
		return instance;
	}

}
