/**
 * 
 */
package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.elastictranscoder.model.CreateJobOutput;
import com.java.dao.Video;
import com.java.server.rds.RdsSample;
import com.java.server.transcoder.AmazonJobCreationClient;
import com.java.server.transcoder.S3UploadAndDownload;

// TODO: This code needs to be copied to a servlet. 

/**
 * @author Shreyas
 * 
 */
public class AppServlet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
	}
	
	public static void transcodeVideo(String filePath) {
		


		/*
		 * 1. Get the video details and populate video object with everything except objectId and bucketId 
		 * 2. Store the video details in S3 
		 * 3. Return objectId and bucketId from S3 
		 * 4. Populate the video object with objectId and bucketId 
		 * 5. Insert video details into RDS 
		 * 6. Call AmazonJobCreationClient by passing S3 ObjectId. Return outputkeys from S3. 
		 * 7. Insert new rows (for all 5 output formats) into RDS 
		 * 8. Return JSON response. Really???
		 */
		
		try {
		
			//System.out.println("upload");
			
			//String filePath = "C:\\shreyas\\SJSU-Studies\\Semester3\\Cloud-281-Prof.Larkin\\Project\\data\\sample.avi";

		String format = filePath.substring(filePath.length() - 3,
				filePath.length());
		
		String objectId = "transcodedFile"; // get it from user or filename
		
		HashMap<String, String> videoFormatMapper = populateMap();

		
		Video videoDetails = new Video();

		videoDetails.setOwnerId("a@a.com"); // get from session
		videoDetails.setFormat(format);
		videoDetails.setSourceFormat(format);

		S3UploadAndDownload uploadObj = new S3UploadAndDownload();

		String[] result = uploadObj.uploadVideoToS3(filePath, objectId);

		System.out.println("Video uploaded to S3");
		
		videoDetails.setBucketId(result[0]);
		videoDetails.setObjectId(result[1]);

		// Insert video into RDS here - CODE Pls!!

		// ///////////////// new column
		videoDetails.setSourceObjectId(result[1].toString());

		RdsSample.storeVideoDetails(videoDetails);

		System.out.println("Video details stored in RDS");
		
		AmazonJobCreationClient transcoderJob = new AmazonJobCreationClient(
				new ClientConfiguration());

		ArrayList<CreateJobOutput> outputs = transcoderJob.createJob(result[1]
				.toString());

		System.out.println("Transcoder job done");
		
		for (int i = 0; i < outputs.size(); i++) {

			videoDetails.setObjectId(outputs.get(i).getKey());
			videoDetails.setFormat(videoFormatMapper.get(
					outputs.get(i).getPresetId()).toString());

			RdsSample.storeVideoDetails(videoDetails);
		}

		System.out.println("Finally done!");
		
		// Create a map which maps presetId to file formats. Need this to save
		// the format in RDS (after transcoding)

		// Insert new row to video table in RDS with outputkey

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}

	public static HashMap<String, String> populateMap() {

		HashMap<String, String> videoFormatMapper = new HashMap<String, String>();

		videoFormatMapper.put("1351620000001-100110", "Audio AAC - 256k");
		videoFormatMapper.put("1351620000001-300010", "Audio MP3 - 320k");
		videoFormatMapper.put("1351620000001-100070",
				"Web: Facebook, SmugMug, Vimeo, YouTube");
		videoFormatMapper.put("1351620000001-100090",
				"Amazon Kindle Fire HD 8.9");
		videoFormatMapper.put("1351620000001-100080", "Amazon Kindle Fire HD");
		videoFormatMapper.put("1351620000001-100060",
				"Apple TV 3G, Roku HD/2 XD");
		videoFormatMapper.put("1351620000001-000001", "Generic 1080p");
		videoFormatMapper.put("1351620000001-200010",
				"HLS (Apple HTTP Live Streaming), 2 megabits/second");
		videoFormatMapper.put("1351620000001-200060", "HLS Audio, 160k");
		videoFormatMapper.put("1351620000001-100010",
				"iPhone 4, iPod touch 5G and 4G, iPad 2G and 1G");
		videoFormatMapper
				.put("1351620000001-100020",
						"iPhone 5, iPhone 4S, iPad 4G and 3G, iPad mini, Samsung Galaxy S2/S3/Tab 2");
		videoFormatMapper.put("1351620000001-100040",
				"iPod touch, iPhone 3 and 1, iPod classic");

		return videoFormatMapper;

	}
}
