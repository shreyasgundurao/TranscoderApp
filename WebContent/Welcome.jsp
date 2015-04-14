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
		$("#filename").val($(":file").val());
	    	
	 });
});




</script>
<title>Insert title here</title>
</head>
<body>


  <% if(session.getAttribute("email")==null)
	  {
	  	response.sendRedirect("Home.jsp");
	  	return;
	  }
	  %>
Hi Welcome!!!  <p><a href="Logout.jsp">Logout</a>

<form id="id" action="uploadServlet">
<input type="text" id="filename" name="filename">
<input type="submit" name="Upload" value="Upload">
</form>

<br/>

</body>
</html>
