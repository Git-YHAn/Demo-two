package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.InstanceConfigure;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class InstanceConfigureJsonUnmarshaller implements Unmarshaller<InstanceConfigure, JsonUnmarshallerContext> {
	private static InstanceConfigureJsonUnmarshaller instance;

	public static InstanceConfigureJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new InstanceConfigureJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public InstanceConfigure unmarshall(JsonUnmarshallerContext context) throws Exception {
		InstanceConfigure config = new InstanceConfigure();
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
				if (context.testExpression("DataDiskGb", targetDepth)) {
					context.nextToken();
					config.setDataDiskGb(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("DataDiskType", targetDepth)) {
					context.nextToken();
					config.setDataDiskType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("RootDiskGb", targetDepth)) {
					context.nextToken();
					config.setRootDiskGb(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("GPU", targetDepth)) {
					context.nextToken();
					config.setGPU(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("MemoryGb", targetDepth)) {
					context.nextToken();
					config.setMemoryGb(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("VCPU", targetDepth)) {
					context.nextToken();
					config.setVCPU(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("SPEC", targetDepth)) {
					context.nextToken();
					config.setSpec(context.getUnmarshaller(String.class).unmarshall(context));
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
		return config;
	}

}
