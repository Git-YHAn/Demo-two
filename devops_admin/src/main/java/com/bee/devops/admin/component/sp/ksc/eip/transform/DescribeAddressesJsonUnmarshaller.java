package com.bee.devops.admin.component.sp.ksc.eip.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.ksc.eip.response.DescribeAddressesResponse;
import com.bee.devops.admin.component.sp.model.eip.Address;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class DescribeAddressesJsonUnmarshaller implements Unmarshaller<DescribeAddressesResponse, JsonUnmarshallerContext> {
	@Override
	public DescribeAddressesResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
		DescribeAddressesResponse result = new DescribeAddressesResponse();
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
				if (context.testExpression("AddressesSet", targetDepth)) {
					context.nextToken();
					result.setAddressesSet(new ListUnmarshaller<Address>(AddressJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("NextToken", targetDepth)) {
					context.nextToken();
					result.setNextToken(context.getUnmarshaller(String.class).unmarshall(context));
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

}
