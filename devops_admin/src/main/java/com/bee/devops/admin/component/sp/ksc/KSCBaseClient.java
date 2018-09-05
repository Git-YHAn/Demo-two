package com.bee.devops.admin.component.sp.ksc;

import org.apache.log4j.Logger;

import com.ksc.ClientConfiguration;
import com.ksc.ClientConfigurationFactory;
import com.ksc.KscServiceException;
import com.ksc.KscWebServiceClient;
import com.ksc.KscWebServiceRequest;
import com.ksc.KscWebServiceResponse;
import com.ksc.Request;
import com.ksc.Response;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.AWSCredentialsProvider;
import com.ksc.auth.DefaultAWSCredentialsProviderChain;
import com.ksc.http.ExecutionContext;
import com.ksc.http.HttpResponseHandler;
import com.ksc.internal.StaticCredentialsProvider;
import com.ksc.metrics.RequestMetricCollector;
import com.ksc.protocol.json.JsonClientMetadata;
import com.ksc.protocol.json.JsonErrorResponseMetadata;
import com.ksc.protocol.json.JsonOperationMetadata;
import com.ksc.protocol.json.SdkJsonProtocolFactory;
import com.ksc.transform.JsonUnmarshallerContext;
import com.ksc.transform.Marshaller;
import com.ksc.transform.Unmarshaller;
import com.ksc.util.CredentialUtils;
import com.ksc.util.KscRequestMetrics;
import com.ksc.util.KscRequestMetrics.Field;

public abstract class KSCBaseClient extends KscWebServiceClient {

	final static Logger log = Logger.getLogger(KSCBaseClient.class);

	public static final int MAX_RESULTS_MIN = 5;// 最小分页条数
	public static final int MAX_RESULTS_MAX = 1000;// 最大分页条数

	/** Provider for AWS credentials. */
	private AWSCredentialsProvider kscCredentialsProvider;

	public KSCBaseClient() {
		this(new DefaultAWSCredentialsProviderChain(), configFactory.getConfig());
	}

	public KSCBaseClient(ClientConfiguration clientConfiguration) {
		this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
	}

	public KSCBaseClient(AWSCredentials awsCredentials) {
		this(awsCredentials, configFactory.getConfig());
	}

	public KSCBaseClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		this.kscCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
		init();
	}

	public KSCBaseClient(AWSCredentialsProvider awsCredentialsProvider) {
		this(awsCredentialsProvider, configFactory.getConfig());
	}

	public KSCBaseClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
		this(awsCredentialsProvider, clientConfiguration, null);
	}

	public KSCBaseClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration,
	        RequestMetricCollector requestMetricCollector) {
		super(clientConfiguration, requestMetricCollector);
		this.kscCredentialsProvider = awsCredentialsProvider;
		init();
	}

	protected abstract void init();

	/**
	 * Client configuration factory providing ClientConfigurations tailored to this
	 * client
	 */
	protected static final ClientConfigurationFactory configFactory = new ClientConfigurationFactory();
	protected final SdkJsonProtocolFactory protocolFactory = new SdkJsonProtocolFactory(new JsonClientMetadata().withSupportsCbor(false));

	/**
	 * Normal invoke with authentication. Credentials are required and may be
	 * overriden at the request level.
	 **/
	protected <X, Y extends KscWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<KscWebServiceResponse<X>> responseHandler,
	        ExecutionContext executionContext) {

		executionContext.setCredentialsProvider(CredentialUtils.getCredentialsProvider(request.getOriginalRequest(), kscCredentialsProvider));

		return doInvoke(request, responseHandler, executionContext);
	}

	/**
	 * Invoke the request using the http client. Assumes credentials (or lack
	 * thereof) have been configured in the ExecutionContext beforehand.
	 **/
	private <X, Y extends KscWebServiceRequest> Response<X> doInvoke(Request<Y> request, HttpResponseHandler<KscWebServiceResponse<X>> responseHandler,
	        ExecutionContext executionContext) {
		request.setEndpoint(endpoint);
		request.setTimeOffset(timeOffset);

		HttpResponseHandler<KscServiceException> errorResponseHandler = protocolFactory.createErrorResponseHandler(new JsonErrorResponseMetadata());
		return client.execute(request, responseHandler, errorResponseHandler, executionContext);
	}

	protected <T extends KscWebServiceRequest, R> R invokRequest(T parseRequest, Unmarshaller<R, JsonUnmarshallerContext> responseJsonUnmarshaller,
	        Marshaller<Request<T>, T> requestMarshaller) throws Exception {
		ExecutionContext executionContext = createExecutionContext(parseRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<T> request = null;
		Response<R> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				T beforeMarshalling = super.beforeMarshalling(parseRequest);
				request = requestMarshaller.marshall(beforeMarshalling);
				request.addHeader("Accept", "application/json");
				// Binds the request metrics to the current request.
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			log.info("金山云服务发送请求： ENDPOINT-" + endpoint + ", " + "request: " + request);
			HttpResponseHandler<KscWebServiceResponse<R>> responseHandler = protocolFactory
			        .createResponseHandler(new JsonOperationMetadata().withPayloadJson(true).withHasStreamingSuccessResponse(false), responseJsonUnmarshaller);
			response = invoke(request, responseHandler, executionContext);
			log.info("金山云服务发送请求： ENDPOINT-" + endpoint + ", " + "response: " + response.getKscResponse());
			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}
}
