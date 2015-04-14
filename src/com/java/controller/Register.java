package com.java.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Register() {
        super();
        
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("reuqets from register");

String pwd=request.getParameter("password");
String fname=request.getParameter("fname");
String lname=request.getParameter("lname");
String email=request.getParameter("email");
System.out.println(pwd+fname+lname+email);

try {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://mydb.cb5n3gqn8vyr.us-east-1.rds.amazonaws.com:3306/mydb",
			"root","root12345");
			Statement st= con.createStatement();
			ResultSet rs;
			int i=st.executeUpdate("insert into user values ('"+email+"','"+fname+"','"+lname+"','"+pwd+"')");
			
			if(i==1)
			{
				response.sendRedirect("Home.jsp");
			}
			else
			{
				response.sendRedirect("Registration.jsp");
			}

} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}

}
