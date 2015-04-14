<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
	$(":file").change(function(){
		alert ($(":file").val());
	    	
	 });
});




</script>
<title>Insert title here</title>
</head>
<body>


 
Hi! Welcome!!!  <p><a href="Logout.jsp">Logout</a>


<br/>

<form action="ViewConverted" method="post">

<input type="submit" value="View Converted Files"> 
</form>
<%
int i=0;
ArrayList<String> sourcefiles = (ArrayList<String>)session.getAttribute("sourcefiles");
if(sourcefiles!=null)

{
	%>
	<form action="Download" method="post">	
	<select id="combos" name="sfilename" required>
	<option value="">Please Select the File</option>
	<%
	for(i=0; i < sourcefiles.size(); i++)
{
	out.println(sourcefiles.get(i));
	%>
	<option value= "<%= sourcefiles.get(i) %>" ><%=sourcefiles.get(i) %></option>
	<%
}
%>
</select>
<select name="format" required>
<option value="">Please Select the Format</option> 	
  <option value="Amazon Kindle Fire HD 8.9">Amazon Kindle Fire HD 8.9</option>
  <option value="iPhone 4, iPod touch 5G and 4G, iPad 2G and 1G"">iPhone 4, iPod touch 5G and 4G, iPad 2G and 1G</option>
  <option value="iPhone 5, iPhone 4S, iPad 4G and 3G, iPad mini, Samsung Galaxy S2/S3/Tab 2">iPhone 5, iPhone 4S, iPad 4G and 3G, iPad mini, Samsung Galaxy S2/S3/Tab 2</option>
  <option value="Web: Facebook, SmugMug, Vimeo, YouTube" >Web: Facebook, SmugMug, Vimeo, YouTube</option>
  <option value="Generic 1080p" >Generic 1080p</option>  
</select>  
<input type=submit value="Download this File">
</form>
<%if(session.getAttribute("convertedfilename")!=null)
{
	%><span name="filesuccess">Your file is Downloaded at the sourcefile location</span>
<% }
else
{
%>
<script>
$(function(){
	  $('span.filesuccess').hide()
	})
</script>	
<%}

} %>






</body>
</html>
