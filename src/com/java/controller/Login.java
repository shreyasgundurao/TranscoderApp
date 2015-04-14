package com.java.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Request Processing");
		
		String email=request.getParameter("usermail");
		System.out.println(email);
		HttpSession session =request.getSession();
		session.setAttribute("email", email);
		String pwd=request.getParameter("password");
		System.out.println(pwd);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://mydb.cb5n3gqn8vyr.us-east-1.rds.amazonaws.com:3306/mydb","root","root12345");
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery("select * from user where email='"+email+"'");
		
				
			
			if(rs.next())
			{System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
			if(rs.getString(4).equals(pwd))
			{
			response.sendRedirect("Welcome.jsp");
			}
			else
			{
				System.out.println("Invalid password try again");
				response.sendRedirect("Error.jsp");

			}
			}
			else
				{
				System.out.println("Invalid Username try again");
				response.sendRedirect("Error.jsp");

				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
