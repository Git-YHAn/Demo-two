package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

/**
 * 
 * @description ListProjectResultJsonUnmarshaller
 * @author TurnerXi
 * @date 2018年7月20日
 */
public class ListProjectResultResponseJsonUnmarshaller implements Unmarshaller<ListProjectResult, JsonUnmarshallerContext> {

	@Override
	public ListProjectResult unmarshall(JsonUnmarshallerContext context) throws Exception {
		ListProjectResult result = new ListProjectResult();
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
				if (context.testExpression("Total", targetDepth)) {
					context.nextToken();
					result.setTotal(context.getUnmarshaller(Integer.class).unmarshall(context));
				} else if (context.testExpression("ProjectList", targetDepth)) {
					context.nextToken();
					result.setProjectList(new ListUnmarshaller<>(ProjectJsonUnmarshaller.getInstance()).unmarshall(context));
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

	private static ListProjectResultResponseJsonUnmarshaller instance;

	public static ListProjectResultResponseJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new ListProjectResultResponseJsonUnmarshaller();
		}
		return instance;
	}
}
