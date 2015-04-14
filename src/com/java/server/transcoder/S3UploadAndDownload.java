package com.java.server.transcoder;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.StringUtils;

public class S3UploadAndDownload {

    
	public static void main(String[] args) throws Exception {
		
		//uploadVideoToS3(null);
		
	}
	
	
	public String[] uploadVideoToS3(String filePath, String objectId) {
		

        String existingBucketName = "";
        String keyName            = objectId;
        //String filePath           = "C:\\shreyas\\SJSU-Studies\\Semester3\\Cloud-281-Prof.Larkin\\Project\\data\\sample2.avi";  
        String accessKey = "AAAA";
        String secretKey = "SSSS";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 conn = new AmazonS3Client(credentials);
        
        try {
        Bucket bucket = conn.createBucket("shrebuck5761"); 
        existingBucketName = bucket.getName();
        
        TransferManager tm;
		
			tm = new TransferManager(new PropertiesCredentials(
					S3UploadAndDownload.class.getResourceAsStream(
							                   "AwsCredentials.properties")));
		    

        /*
         * ################ UPLOAD START ##################
         */
        // TransferManager processes all transfers asynchronously, 
        // so this call will return immediately.
        Upload upload = tm.upload(
        		existingBucketName, keyName, new File(filePath));

        try {
        	// Or you can block and wait for the upload to finish
        	upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
        	System.out.println("Unable to upload file, upload was aborted.");
        	amazonClientException.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Upload Done!");
        /*
         * ################ UPLOAD END ##################
         */
        
        
        /*
         * ################ LIST OBJECTS START ##################
         */
        
        //ObjectListing objects = conn.listObjects(bucket.getName());
        
        ObjectMetadata obj = conn.getObjectMetadata(bucket.getName(), keyName);
        
        obj.getContentType();
        
        Map<String, Object> map = obj.getRawMetadata();
        
        for (Entry<String, Object> entry : map.entrySet()) {
        	  String key = entry.getKey();
        	  String value = (String) entry.getValue().toString();
        	  System.out.println(key+" - " + value);
        	}

        String[] result = new String[2];
        result[0] = bucket.getName();
        result[1] = keyName;
        
        
        /*System.out.println("##########################################################");
        System.out.println("Here is your list of objects in "+ bucket.getName());
        for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                        System.out.println(objectSummary.getKey() + "\t" +
                                objectSummary.getSize() + "\t" +
                                StringUtils.fromDate(objectSummary.getLastModified()));
                }
        System.out.println("##########################################################");*/
        /*
         * ################ LIST OBJECTS END ##################
         */
        
        
        /*
         * ################ DOWNLOAD START ##################
         */
        
      /*  conn.getObject(
                new GetObjectRequest(bucket.getName(), "myObjKey"),
                new File("C:\\shreyas\\SJSU-Studies\\Semester3\\Cloud-281-Prof.Larkin\\Project\\output\\sampleeee.avi")
        );
        */
        
        System.out.println("Download Done!");
        /*
         * ################ DOWNLOAD START ##################
         */
        return result;
    
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return null;
	}
}