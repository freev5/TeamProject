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
<h3>���� ����</h3>
${error}
<form action="AccountSearch.do" method="post">
	ID : <input type="text" name="id"/>
	<input type="hidden" name="job" value="search"/>
	<input type="submit" value="����"/>
</form>

<%
	Account account = (Account)request.getAttribute("account");
	if(account!=null){%>
	<h3>ȸ������ ����</h3>
	<form action="AccountUpdate.do" method="post">
	ID : <input type="text" name="id"><br>
	��й�ȣ : <input type="text" name="pwd"><br>
	�̸� : <input type="text" name="name"><br>
	Email : <input type="text" name="email"><br>
	
	<input type="submit" value="����">
</form>
	<%}else{ %>
	${result}<p>
	<%} %>
</body>
</html>