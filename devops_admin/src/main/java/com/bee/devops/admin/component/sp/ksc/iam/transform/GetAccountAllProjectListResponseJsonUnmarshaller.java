package com.bee.devops.admin.component.sp.ksc.iam.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.ksc.iam.response.GetAccountAllProjectListResponse;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Unmarshaller;

/**
 * 
 * @description GetAccountAllProjectListResponseJsonUnmarshaller
 * @author TurnerXi
 * @date 2018年7月20日
 */
public class GetAccountAllProjectListResponseJsonUnmarshaller implements Unmarshaller<GetAccountAllProjectListResponse, JsonUnmarshallerContext> {

	@Override
	public GetAccountAllProjectListResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
		GetAccountAllProjectListResponse result = new GetAccountAllProjectListResponse();
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
				if (context.testExpression("ListProjectResult", targetDepth)) {
					context.nextToken();
					result.setListProjectResult(ListProjectResultResponseJsonUnmarshaller.getInstance().unmarshall(context));
				} else if (context.testExpression("RequestId", targetDepth)) {
					context.nextToken();
					result.setRequestId(context.getUnmarshaller(String.class).unmarshall(context));
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

	private static GetAccountAllProjectListResponseJsonUnmarshaller instance;

	public static GetAccountAllProjectListResponseJsonUnmarshaller getInstance() {
		if (instance == null) {
			instance = new GetAccountAllProjectListResponseJsonUnmarshaller();
		}
		return instance;
	}
}
