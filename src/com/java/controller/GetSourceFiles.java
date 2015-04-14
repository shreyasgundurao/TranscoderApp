package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Servlet implementation class GetSourceFiles
 */

public class GetSourceFiles extends HttpServlet {
	static Connection connection;
	static EstablishConnection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSourceFiles() {
        super();
     
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		con = new EstablishConnection();
		connection = con.getConnection();
		HttpSession session = request.getSession();
		try{
		 Statement st = connection.createStatement();
		 ArrayList<String> sourcefiles = new ArrayList<String>();
		 String query = "select distinct(source_objectid) from video where ownerid= 'a@a.com'";
		 ResultSet rs =st.executeQuery(query);
		 while(rs.next())
		 {
			 sourcefiles.add(rs.getString(1));
		 }
		 session.setAttribute("sourcefiles", sourcefiles);
		 response.sendRedirect("NewFile.jsp");
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
}
