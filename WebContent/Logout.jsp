<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/wc.1.css">
</head>
<body>
<%
	session.setAttribute("Loginid",null);
	session.setAttribute("Loginname",null);
	session.setAttribute("isAdmin",null);
	session.setAttribute("Loging",null);
	
	session.setAttribute("movies", null);
	session.setAttribute("MD", null);
	session.setAttribute("movieOp", null);
	session.setAttribute("rlist", null);
	session.setAttribute("wlist", null);

	response.sendRedirect("Main.jsp");
%>
</body>
</html>