package com.java.server.rds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EstablishConnection {
		String url = "jdbc:mysql://mydb.cb5n3gqn8vyr.us-east-1.rds.amazonaws.com:3306/mydb";
	
		//String url = "jdbc:mysql://localhost:3306/cmpe281";
		String uname = "root";
		String pwd = "root12345";
		
		public Connection getConnection()
		{
			Connection conn=null;
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(url, uname, pwd);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return conn;
			
		}
		
		public void endConnection(Connection conn)
		{
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		

	

}
