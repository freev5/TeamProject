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
<h3>정보 수정</h3>
${error}
<form action="AccountSearch.do" method="post">
	ID : <input type="text" name="id"/>
	<input type="hidden" name="job" value="search"/>
	<input type="submit" value="가입"/>
</form>

<%
	Account account = (Account)request.getAttribute("account");
	if(account!=null){%>
	<h3>회원정보 수정</h3>
	<form action="AccountUpdate.do" method="post">
	ID : <input type="text" name="id"><br>
	비밀번호 : <input type="text" name="pwd"><br>
	이름 : <input type="text" name="name"><br>
	Email : <input type="text" name="email"><br>
	
	<input type="submit" value="수정">
</form>
	<%}else{ %>
	${result}<p>
	<%} %>
</body>
</html>