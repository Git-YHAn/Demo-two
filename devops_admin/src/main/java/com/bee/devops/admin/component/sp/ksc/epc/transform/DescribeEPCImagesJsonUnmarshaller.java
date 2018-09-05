package com.bee.devops.admin.component.sp.ksc.epc.transform;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.VALUE_NULL;

import com.bee.devops.admin.component.sp.ksc.epc.response.DescribeEPCImagesResponse;
import com.bee.devops.admin.component.sp.model.epc.EPCImage;
import com.fasterxml.jackson.core.JsonToken;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.ListUnmarshaller;
import com.ksc.transform.Unmarshaller;

public class DescribeEPCImagesJsonUnmarshaller implements Unmarshaller<DescribeEPCImagesResponse, JsonUnmarshallerContext> {

	@Override
	public DescribeEPCImagesResponse unmarshall(JsonUnmarshallerContext context) throws Exception {
		DescribeEPCImagesResponse result = new DescribeEPCImagesResponse();
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
				if (context.testExpression("ImageSet", targetDepth)) {
					context.nextToken();
					result.setImageSet(new ListUnmarshaller<EPCImage>(EPCImageJsonUnmarshaller.getInstance()).unmarshall(context));
				} else if (context.testExpression("TotalCount", targetDepth)) {
					context.nextToken();
					result.setTotalCount(context.getUnmarshaller(Integer.class).unmarshall(context));
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
