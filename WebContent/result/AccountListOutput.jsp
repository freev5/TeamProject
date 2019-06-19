<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tp.vo.Account" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Account> list = (ArrayList<Account>) request.getAttribute("AccountList"); 

	if(!list.isEmpty()){%>
	<table border="1">
	<tr><th>ID</th><th>비밀번호</th><th>이름</th><th>이메일</th>
	<%
		for(int i=0;i<list.size();i++){
			Account account = list.get(i);
			%>
			<tr>
			<td><%=account.getId()%></td>
			<td><%=account.getPwd()%></td>
			<td><%=account.getName()%></td>
			<td><%=account.getEmail()%></td>
			<td>
			<form action="AccountDelete.do" method="post">
			<input type="hidden" name="id" value="<%=account.getId()%>">
			<input type="submit" value="삭제">
			</form>
			</td>
			</tr>
		<%}
	}else{
		out.print("<h3>등록된 회원정보가 없습니다.</h3>");
	}
	%>
	</table>
</body>
</html>