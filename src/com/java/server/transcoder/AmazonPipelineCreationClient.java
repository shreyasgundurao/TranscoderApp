/**
 * 
 */
package com.java.server.transcoder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoder;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoderClient;
import com.amazonaws.services.elastictranscoder.model.CreateJobOutput;
import com.amazonaws.services.elastictranscoder.model.CreatePipelineRequest;
import com.amazonaws.services.elastictranscoder.model.CreatePipelineResult;
import com.amazonaws.services.elastictranscoder.model.JobInput;
import com.amazonaws.services.elastictranscoder.model.Notifications;
import com.amazonaws.services.elastictranscoder.model.transform.AccessDeniedExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.CreatePipelineRequestMarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.CreatePipelineResultJsonUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.IncompatibleVersionExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.InternalServiceExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.LimitExceededExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.ResourceInUseExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.elastictranscoder.model.transform.ValidationExceptionUnmarshaller;
import com.amazonaws.services.importexport.model.CreateJobRequest;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import com.amazonaws.util.json.JSONObject;

/**
 * @author Shreyas
 * 
 */
public class AmazonPipelineCreationClient extends AmazonWebServiceClient {

	public AmazonPipelineCreationClient(ClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		// TODO Auto-generated constructor stub
	}

	public AmazonPipelineCreationClient(AWSCredentials awsCredentials,
			ClientConfiguration clientConfiguration) throws Exception {
		super(clientConfiguration);

	}

	/**
	 * @param args
	 */

	private AWSCredentialsProvider awsCredentialsProvider;

	private static AWSCredentials credentials;

	private static final Log log = LogFactory
			.getLog(AmazonPipelineCreationClient.class);

	static AmazonElasticTranscoder transcoder;

	/**
	 * List of exception unmarshallers for all AmazonElasticTranscoder
	 * exceptions.
	 */
	protected List<Unmarshaller<AmazonServiceException, JSONObject>> exceptionUnmarshallers;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String accessKey = "AAAA";
		String secretKey = "SSSS";
		credentials = new BasicAWSCredentials(accessKey, secretKey);

		init();
		CreatePipelineRequest pipelineRequest = new CreatePipelineRequest();

		Notifications notifications = new Notifications();
		notifications
				.setCompleted("arn:aws:sns:us-east-1:677293151830:topic-sample");
		notifications
				.setError("arn:aws:sns:us-east-1:677293151830:topic-sample");
		notifications
				.setProgressing("arn:aws:sns:us-east-1:677293151830:topic-sample");
		notifications
				.setWarning("arn:aws:sns:us-east-1:677293151830:topic-sample");

		pipelineRequest.setInputBucket("shrebuck5761");
		pipelineRequest.setName("transcoderPipeline");
		pipelineRequest.setOutputBucket("shrebuck5761");
		pipelineRequest.setRequestCredentials(credentials);
		pipelineRequest
				.setRole("arn:aws:iam::677293151830:role/Elastic_Transcoder_Default_Role");
		pipelineRequest.setNotifications(notifications);

		AmazonPipelineCreationClient p = new AmazonPipelineCreationClient(
				credentials, new ClientConfiguration());

		transcoder.createPipeline(pipelineRequest);
		
		
		// CreatePipelineResult result = p.createPipeline(pipelineRequest);

		// System.out.println("Result::: " + result);

	}

	private static void init() throws Exception {
		AWSCredentials credentials = new ClasspathPropertiesFileCredentialsProvider()
				.getCredentials();
		transcoder = new AmazonElasticTranscoderClient(credentials);
		transcoder
				.setEndpoint("https://elastictranscoder.us-east-1.amazonaws.com");
	}

}
