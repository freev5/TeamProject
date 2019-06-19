<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tp.vo.Account" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--Google Font-->
    <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />

	
	<link rel="stylesheet" href="css/table.css">
</head>
<body>
<h1 style="float:left; padding: 0 0 0 130px;">회원관리</h1>	<h3><a href="Admin.jsp" style="text-decoration:none;color:#444;margin:50px 0 0 1000px;">뒤로가기</a></h3>
<%
		ArrayList<Account> list = (ArrayList<Account>) request.getSession().getAttribute("AccountList");

		if (!list.isEmpty()) {
	%>
	<table class="rwd-table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>가입일시</th>
			</tr>
		</thead>
		<%
			for (int i = 0; i < list.size(); i++) {
					Account account = list.get(i);
		%>
		<tbody>
			<tr>
				<td><%=account.getId()%></td>
				<td><%=account.getPwd()%></td>
				<td><%=account.getName()%></td>
				<td><%=account.getBirth()%></td>
				<td><%=account.getEmail()%></td>
				<td><%=account.getRegdate()%></td>
				<td>
					<form action="AccountDelete.do" method="post">
						<input type="hidden" name="id" value="<%=account.getId()%>">
						<input type="submit" value="삭제" style="border:0;background-color: transparent; width:100%;height:100%;">
					</form>
				</td>
			</tr>
		</tbody>
		<%
			}
			} else {
				out.print("<h3>등록된 회원정보가 없습니다.</h3>");
			}
		%>
	</table>
	
</body>
</html>