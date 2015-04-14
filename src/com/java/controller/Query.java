package com.java.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Query {
	 public static void main(String[] args){
		 String url = "jdbc:mysql://mydb.cb5n3gqn8vyr.us-east-1.rds.amazonaws.com:3306/mydb";
		 Connection conn=null;
			try {
			//String url = "jdbc:mysql://localhost:3306/cmpe281";
			String uname = "root";
			String pwd = "root12345";
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(url, uname, pwd);
			 
			 Statement st = conn.createStatement();
			 ArrayList<String> sourcefiles = new ArrayList<String>();
			 String query = "select distinct(source_objectid) from video where ownerid= 'a@a.com'";
			 ResultSet rs =st.executeQuery(query);
			 while(rs.next())
			 {
				 sourcefiles.add(rs.getString(1));
			 }
			 
			 for (String string : sourcefiles) {
				System.out.println(string);
			}
				
			

				
				
			}
			 catch (Exception e) {
					
					e.printStackTrace();
				}
}
}
