<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login Error</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="loginform cf">
<center><p style="color:red">Invalid Username/Password</p></center>
</div>
<%

getServletContext().getRequestDispatcher("/Home.jsp").include(request,response);

%>

</body>

</html>