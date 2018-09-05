package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.model.iam.Project;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

public class ProjectJsonUnmarshaller implements Unmarshaller<Project, JsonUnmarshallerContext> {
	private static ProjectJsonUnmarshaller instance;

	public static ProjectJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new ProjectJsonUnmarshaller();
		}
		return instance;
	}

	@Override
	public Project unmarshall(JsonUnmarshallerContext context) throws Exception {
		Project user = new Project();
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
				if (context.testExpression("ProjectId", targetDepth)) {
					context.nextToken();
					user.setProjectId(context.getUnmarshaller(Long.class).unmarshall(context));
				} else if (context.testExpression("AccountId", targetDepth)) {
					context.nextToken();
					user.setAccountId(context.getUnmarshaller(Long.class).unmarshall(context));
				} else if (context.testExpression("ProjectName", targetDepth)) {
					context.nextToken();
					user.setProjectName(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("ProjectDesc", targetDepth)) {
					context.nextToken();
					user.setProjectDesc(context.getUnmarshaller(String.class).unmarshall(context));
				} else if (context.testExpression("CreateTime", targetDepth)) {
					context.nextToken();
					user.setCreateTime(context.getUnmarshaller(String.class).unmarshall(context));
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
