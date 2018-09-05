package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.AvailabilityZone;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class AvailabilityZoneJsonUnmarshaller implements Unmarshaller<AvailabilityZone, JsonUnmarshallerContext> {

	@Override
	public AvailabilityZone unmarshall(JsonUnmarshallerContext context) throws Exception {
		AvailabilityZone result = new AvailabilityZone();
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
				if (context.testExpression("AvailabilityZone", targetDepth)) {
					context.nextToken();
					result.setAvailabilityZone(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Region", targetDepth)) {
					context.nextToken();
					result.setRegion(context.getUnmarshaller(String.class).unmarshall(context));
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

	private static AvailabilityZoneJsonUnmarshaller instance;

	public static AvailabilityZoneJsonUnmarshaller getInstance() {
		if (instance == null)
			instance = new AvailabilityZoneJsonUnmarshaller();
		return instance;
	}

}
