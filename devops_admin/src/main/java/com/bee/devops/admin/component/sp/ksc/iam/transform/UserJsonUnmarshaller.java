package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import org.apache.commons.lang3.time.DateUtils;

import com.bee.devops.admin.component.sp.model.iam.User;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class UserJsonUnmarshaller implements Unmarshaller<User, JsonUnmarshallerContext> {
	private static UserJsonUnmarshaller instance;

	public static UserJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new UserJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public User unmarshall(JsonUnmarshallerContext context) throws Exception {
		User user = new User();
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
				if (context.testExpression("UserId", targetDepth)) {
					context.nextToken();
					user.setUserId(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Path", targetDepth)) {
					context.nextToken();
					user.setPath(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("UserName", targetDepth)) {
					context.nextToken();
					user.setUserName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("Krn", targetDepth)) {
					context.nextToken();
					user.setKrn(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateDate", targetDepth)) {
					context.nextToken();
					user.setCreateDate(DateUtils.parseDate(context.getUnmarshaller(String.class).unmarshall(context),"yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
		return user;
	}

}
