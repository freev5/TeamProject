<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>검색 결과</h3>
<%
	String result = (String)request.getAttribute("result");
	if(result!=null){
		out.print(result+"<p>");
	} else {
%>
	<h3>${account.id} / ${account.pwd} / ${account.name} / ${account.email}</h3>
	<%} %>
	
<%@ include file="home.jsp" %>
</body>
</html>