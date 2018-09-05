package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.iam.User;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class ListUsersResultJsonUnmarshaller implements Unmarshaller<ListUserResult, JsonUnmarshallerContext> {

	@Override
	public ListUserResult unmarshall(JsonUnmarshallerContext context) throws Exception {
		ListUserResult result = new ListUserResult();
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
				if (context.testExpression("Users", targetDepth)) {
					context.nextToken();
					result.setUsers(new ListUnmarshaller<User>(UserJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("Marker", targetDepth)) {
					context.nextToken();
					result.setMarker(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("IsTruncated", targetDepth)) {
					context.nextToken();
					result.setIsTruncated(context.getUnmarshaller(Boolean.class).unmarshall(context));
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

	private static ListUsersResultJsonUnmarshaller instance;

	public static ListUsersResultJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new ListUsersResultJsonUnmarshaller();
		}
		return instance;
	}
}
