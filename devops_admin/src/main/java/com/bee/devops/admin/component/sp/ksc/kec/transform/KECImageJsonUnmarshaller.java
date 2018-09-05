package com.bee.devops.admin.component.sp.ksc.kec.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.kec.KECImage;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class KECImageJsonUnmarshaller implements Unmarshaller<KECImage, JsonUnmarshallerContext> {
	private static KECImageJsonUnmarshaller instance;

	public static KECImageJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new KECImageJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public KECImage unmarshall(JsonUnmarshallerContext context) throws Exception {
		KECImage image = new KECImage();
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
				} else if (context.testExpression("Name", targetDepth)) {
					context.nextToken();
					image.setName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("IsPublic", targetDepth)) {
					context.nextToken();
					image.setIsPublic(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreationDate", targetDepth)) {
					context.nextToken();
					image.setCreationDate(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ImageState", targetDepth)) {
					context.nextToken();
					image.setImageState(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("InstanceId", targetDepth)) {
					context.nextToken();
					image.setInstanceId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Platform", targetDepth)) {
					context.nextToken();
					image.setPlatform(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("IsNpe", targetDepth)) {
					context.nextToken();
					image.setIsNpe(context.getUnmarshaller(Boolean.class).unmarshall(context));
				} else if (context.testExpression("UserCategory", targetDepth)) {
					context.nextToken();
					image.setUserCategory(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("SysDisk", targetDepth)) {
					context.nextToken();
					image.setSysDisk(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Progress", targetDepth)) {
					context.nextToken();
					image.setProgress(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ImageSource", targetDepth)) {
					context.nextToken();
					image.setImageSource(context.getUnmarshaller(String.class).unmarshall(context));
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
