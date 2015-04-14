package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;

/**
 * Servlet implementation class Download
 */

public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection;
	static EstablishConnection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sourcefile = request.getParameter("sfilename");
		String format = request.getParameter("format");
		String username = request.getParameter("username");
		System.out.println(sourcefile + " " + format);
		username = "a@a.com";
		con = new EstablishConnection();
		connection = con.getConnection();
		HttpSession session = request.getSession();

		try {
			Statement st = connection.createStatement();

			String query = "select objectid from video where ownerid= 'a@a.com' and source_objectid ='"
					+ sourcefile + "' and format='" + format + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				System.out.println(rs.getString(1));
				session.setAttribute("convertedfilename", rs.getString(1));
			} else {
				session.removeAttribute("convertedfilename");
			}

			rs.close();

			/*
			 * ################ DOWNLOAD START ##################
			 */
			String accessKey = "AAAA";
			String secretKey = "SSSS";
			AWSCredentials credentials = new BasicAWSCredentials(accessKey,
					secretKey);

			AmazonS3 conn = new AmazonS3Client(credentials);
			Bucket bucket = conn.listBuckets().get(0);
			//Bucket bucket = conn.createBucket(buck);
			String existingBucketName = bucket.getName();
			conn.getObject(
					new GetObjectRequest(bucket.getName(), "myObjKey"),
					new File(
							"C:\\shreyas\\SJSU-Studies\\Semester3\\Cloud-281-Prof.Larkin\\Project\\output\\sampleeee.avi"));

			System.out.println("Download Done!");
			/*
			 * ################ DOWNLOAD END ##################
			 */

			response.sendRedirect("NewFile.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
