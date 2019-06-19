<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<input type="hidden" name="job" value="search"/>
	<input type="submit" value="검색"/>
</form>
</body>
</html>