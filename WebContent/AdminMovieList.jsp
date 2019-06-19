<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tp.vo.MD"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/table.css">
<title>Insert title here</title>

<!--Google Font-->
    <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
	
</head>
<body>
	<h1 style="float:left; padding: 0 0 0 130px;">영화관리</h1>	<h3><a href="Admin.jsp" style="text-decoration:none;color:#444;margin:50px 0 0 1000px;">뒤로가기</a></h3>

	<%
		request.setCharacterEncoding("UTF-8");
		ArrayList<MD> md = (ArrayList<MD>) request.getSession().getAttribute("mlist");

		if (!md.isEmpty()) {
	%>
	<table class="rwd-table">
	<thead>
		<tr>
			<th>번호</th>
			<th>썸네일</th>
			<th>제목</th>
			<th>부제</th>
			<th>개봉년도</th>
			<th>감독</th>
			<th>배우</th>
			<th>영화코드</th>
			<th>유저평점</th>
			<th><a href="Admin.jsp" style="text-decoration:none;color:#fff;"></a></th>
			</tr></thead>
			<%
				for (int i = 0; i < md.size(); i++) {
						MD movie = md.get(i);
			%>
			<tbody>
				<tr>
					<td><%=movie.getMid()%></td>
					<td><img style="width:50px;height:70px;" src="<%=movie.getImage()%>"></td>
					<td><%=movie.getMtitle()%></td>
					<td><%=movie.getSubtitle()%></td>
					<td><%=movie.getPubDate()%></td>
					<td><%=movie.getDirector()%></td>
					<td><%=movie.getActor()%></td>
					<td><%=movie.getMcode()%></td>
					<td><%=movie.getUserRating()%></td>
					<td>
						<form action="MDDelete.do" method="post">
							<input type="hidden" name="Mtitle" value="<%=movie.getMtitle()%>">
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