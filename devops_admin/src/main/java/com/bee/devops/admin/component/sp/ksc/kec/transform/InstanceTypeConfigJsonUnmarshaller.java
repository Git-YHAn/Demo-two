package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.AvailabilityZoneInfo;
import com.bee.devops.admin.component.sp.model.kec.InstanceTypeConfig;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class InstanceTypeConfigJsonUnmarshaller implements Unmarshaller<InstanceTypeConfig, JsonUnmarshallerContext> {

	@Override
	public InstanceTypeConfig unmarshall(JsonUnmarshallerContext context) throws Exception {
		InstanceTypeConfig result = new InstanceTypeConfig();
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
				} else if (context.testExpression("InstanceType", targetDepth)) {
					context.nextToken();
					result.setInstanceType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceFamily", targetDepth)) {
					context.nextToken();
					result.setInstanceFamily(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SriovNetSupport", targetDepth)) {
					context.nextToken();
					result.setSriovNetSupport(context.getUnmarshaller(Boolean.class).unmarshall(context));
				} else if (context.testExpression("GPU", targetDepth)) {
					context.nextToken();
					result.setGpu(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("CPU", targetDepth)) {
					context.nextToken();
					result.setCpu(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("Memory", targetDepth)) {
					context.nextToken();
					result.setMemory(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("DataDiskMax", targetDepth)) {
					context.nextToken();
					result.setDataDiskMax(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("DataDiskMin", targetDepth)) {
					context.nextToken();
					result.setDataDiskMin(context.getUnmarshaller(Integer.class).unmarshall(context));
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

	private static InstanceTypeConfigJsonUnmarshaller instance;

	public static InstanceTypeConfigJsonUnmarshaller getInstance() {
		if (instance == null)
			instance = new InstanceTypeConfigJsonUnmarshaller();
		return instance;
	}

}
