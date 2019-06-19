<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.tp.vo.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>회원 검색</h3>
${error}
<form action="AccountSearch.do" method="post">
	ID : <input type="text" name="id"/>
	<input type="hidden" name="job" value="Delete"/>
	<input type="submit" value="검색"/>
</form>

<%
	Account account = (Account)request.getAttribute("account");
	if(account!=null){%>
	<h3>검색 정보 결과</h3>
	<h3>${account.id} / ${account.pwd} / ${account.name} / ${account.email}</h3>
	<form action="AccountDelete.do" method="post">
	<input type="hidden" name="id" value="${account.id }"/>
	<input type="submit" value="삭제"/>
	
</form>
	<%}else{ %>
	${result}<p>
	<%} %>
</body>
</html>