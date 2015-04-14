package com.java.server.rds;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.java.dao.Video;

public class RdsSample {
	static Connection connection;
	static EstablishConnection con;
	public static void main(String[] args) {
		
		//registerUser("somesh.sb@gmail.com", "somesh", "benchalli", "abcd");
		//storeVideoDetails("laptop","somesh.sb@gmail.com","objectId","bucketId","laptop");
	}
	
	
	public static void registerUser(String email, String fname, String lname, String pwd) {
		
		try {
			con = new EstablishConnection();
			connection = con.getConnection();
			connection.setAutoCommit(false);
			String query = "insert into user values (?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, pwd);
			pstmt.execute();

			con.endConnection(connection);
			System.out.println("User details inserted into DB");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/*public static void storeVideoDetails (String format, String ownerId, String ObjectId, String bucketId, String source_format) {
	
		try {
			con = new EstablishConnection();
			connection = con.getConnection();
			connection.setAutoCommit(false);
			String query = "insert into video (format,ownerId,objectId,bucketId,source_format) values(?,?,?,?,?) ";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, format);
			pstmt.setString(2, ownerId);
			pstmt.setString(3, ObjectId);
			pstmt.setString(4, bucketId);
			pstmt.setString(5, source_format);
			pstmt.execute();

			con.endConnection(connection);
			System.out.println("Video details inserted into DB");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void storeVideoDetails (Video videoDetails) {
		
		try {
			con = new EstablishConnection();
			connection = con.getConnection();
			connection.setAutoCommit(false);
			String query = "insert into video (format,ownerId,objectId,bucketId,source_format,source_objectId) values(?,?,?,?,?,?) ";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, videoDetails.getFormat());
			pstmt.setString(2, videoDetails.getOwnerId());
			pstmt.setString(3, videoDetails.getObjectId());
			pstmt.setString(4, videoDetails.getBucketId());
			pstmt.setString(5, videoDetails.getSourceFormat());
			pstmt.setString(6, videoDetails.getSourceObjectId());
			pstmt.execute();

			con.endConnection(connection);
			System.out.println("Video details inserted into DB");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
