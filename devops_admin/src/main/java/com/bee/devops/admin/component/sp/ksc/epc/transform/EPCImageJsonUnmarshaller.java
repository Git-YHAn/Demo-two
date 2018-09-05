package com.bee.devops.admin.component.sp.ksc.epc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.epc.EPCImage;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class EPCImageJsonUnmarshaller implements Unmarshaller<EPCImage, JsonUnmarshallerContext> {
	private static EPCImageJsonUnmarshaller instance;

	public static EPCImageJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new EPCImageJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public EPCImage unmarshall(JsonUnmarshallerContext context) throws Exception {
		EPCImage image = new EPCImage();
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
				if (context.testExpression("ImageId", targetDepth)) {
					context.nextToken();
					image.setImageId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ImageName", targetDepth)) {
					context.nextToken();
					image.setImageName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ImageType", targetDepth)) {
					context.nextToken();
					image.setImageType(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					image.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("OsName", targetDepth)) {
					context.nextToken();
					image.setOsName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("OsType", targetDepth)) {
					context.nextToken();
					image.setOsType(context.getUnmarshaller(String.class).unmarshall(context));
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
		return image;
	}

}
