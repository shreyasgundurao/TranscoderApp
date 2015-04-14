/**
 * 
 */
package com.java.server.transcoder;

import java.util.ArrayList;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoder;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoderClient;
import com.amazonaws.services.elastictranscoder.model.CreateJobOutput;
import com.amazonaws.services.elastictranscoder.model.CreateJobRequest;
import com.amazonaws.services.elastictranscoder.model.CreateJobResult;
import com.amazonaws.services.elastictranscoder.model.JobInput;

/**
 * @author Shreyas
 * 
 */
public class AmazonJobCreationClient extends AmazonWebServiceClient {

	public AmazonJobCreationClient(ClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		// TODO Auto-generated constructor stub
	}

	public AmazonJobCreationClient(AWSCredentials credentials2,
			ClientConfiguration clientConfiguration) {
		// TODO Auto-generated constructor stub

		super(clientConfiguration);
		/*
		 * this.awsCredentialsProvider = new StaticCredentialsProvider(
		 * awsCredentials);
		 * 
		 * init();
		 */
	}

	/**
	 * @param args
	 */

	private static AWSCredentials credentials;
	static AmazonElasticTranscoder transcoder;

	public static void main(String[] args) throws Exception {
		//createJob("");
	}

	public ArrayList<CreateJobOutput> createJob(String inputKey) {

		// TODO Auto-generated method stub

		String accessKey = "AAAA";
		String secretKey = "SSSS";
		credentials = new BasicAWSCredentials(accessKey, secretKey);

		try {
			AmazonJobCreationClient j = new AmazonJobCreationClient(
					credentials, new ClientConfiguration());

			init();

			CreateJobRequest createJobRequest = new CreateJobRequest();

			JobInput input = new JobInput();
			input.setKey(inputKey);
			input.setFrameRate("auto");
			input.setResolution("auto");
			input.setAspectRatio("auto");
			input.setInterlaced("auto");
			input.setContainer("auto");

			// Amazon Kindle Fire HD 8.9,,, iPhone 4, iPod touch 5G and 4G, iPad
			// 2G and 1G,,, iPhone 5, iPhone 4S, iPad 4G and 3G, iPad mini,
			// Samsung Galaxy S2/S3/Tab 2,,, Web: Facebook, SmugMug, Vimeo,
			// YouTube,,, Generic 1080p - In the same order.

			String[] presetIds = { "1351620000001-100090",
					"1351620000001-100010", "1351620000001-100020",
					"1351620000001-100070", "1351620000001-000001" };

			String[] formats = {
					"Amazon Kindle Fire HD 8.9",
					"iPhone 4, iPod touch 5G and 4G, iPad 2G and 1G",
					"iPhone 5, iPhone 4S, iPad 4G and 3G, iPad mini, Samsung Galaxy S2/S3/Tab 2",
					"Web: Facebook, SmugMug, Vimeo, YouTube", "Generic 1080p" };

			ArrayList<CreateJobOutput> outputs = new ArrayList<CreateJobOutput>();

			CreateJobOutput outputVideo = null;

			for (int i = 0; i < 5; i++) {

				outputVideo = new CreateJobOutput();
				outputVideo.setKey(inputKey + "-" + formats[i]);
				outputVideo.setRotate("0");
				outputVideo.setPresetId(presetIds[i].toString());

				outputs.add(outputVideo);

			}


			createJobRequest.withInput(input);
			createJobRequest.setOutputKeyPrefix("transcoderApplication/");
			createJobRequest.setOutputs(outputs);
			createJobRequest.setPipelineId("1387423544843-l9mzff"); // My Pipeline - TranscoderPipeline

			CreateJobResult res = transcoder.createJob(createJobRequest);

			System.out.println("Result:  " + res.toString());

			return outputs;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void init() throws Exception {
		AWSCredentials credentials = new ClasspathPropertiesFileCredentialsProvider()
				.getCredentials();
		transcoder = new AmazonElasticTranscoderClient(credentials);
		transcoder
				.setEndpoint("https://elastictranscoder.us-east-1.amazonaws.com");
	}

}
