package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.ksc.iam.response.ListUsersResponse;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class ListUsersResponseJsonUnmarshaller implements Unmarshaller<ListUsersResponse, JsonUnmarshallerContext> {

	@Override
	public ListUsersResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
		ListUsersResponse result = new ListUsersResponse();
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
				if (context.testExpression("ListUserResult", targetDepth)) {
					context.nextToken();
					result.setListUserResult(ListUsersResultJsonUnmarshaller.getInstance().unmarshall(context));
				} else if (context.testExpression("ResponseMetadata", targetDepth)) {
					context.nextToken();
					result.setResponseMetadata(ResponseMetadataJsonUnmarshaller.getInstance().unmarshall(context));
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

	private static ListUsersResponseJsonUnmarshaller instance;

	public static ListUsersResponseJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new ListUsersResponseJsonUnmarshaller();
		}
		return instance;
	}
}
