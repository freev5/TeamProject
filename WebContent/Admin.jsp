<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tp.vo.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Basic need -->
	<title>Open Pediatrics</title>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">
	<link rel="profile" href="#">

    <!--Google Font-->
    <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
	<!-- Mobile specific meta -->
	<meta name=viewport content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone-no">

	<!-- CSS files -->
	<link rel="stylesheet" href="Film-Review-Movie-Database/css/plugins.css">
	<link rel="stylesheet" href="Film-Review-Movie-Database/css/style.css">
</head>
<body>
<%
String Loging = (String)request.getSession().getAttribute("Loging");
Account account = (Account)request.getSession().getAttribute("account");
if(Loging==null){
	response.sendRedirect("Login.jsp");
}
%>
<jsp:include page="nav_Admin.jsp"></jsp:include>

<div class="hero user-hero">
		<div class="container">
			<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1 style="margin-left: -10px;"> 관리자 페이지</h1>
					<ul class="breadcumb" style="margin-left: 42%;">
						<li class="active"><a href="Main.jsp">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Admin Page</li>
					</ul>
				</div>
			</div>
		</div>
		</div>
	</div>
	<div class="buster-light">
		<div class="page-single">
			<div class="container">
				<div class="row ipad-width">
					<div class="col-md-3 col-sm-12 col-xs-12">
						<div class="user-information"></div>
					</div>
					
					<div class="col-md-9 col-sm-12 col-xs-12" style="margin: 0 0 0 12%;">
						<div class="form-style-1 user-pro" action="#" style="background-color:#fff;">
							<form action="AccountUpdate.do" method="post" class="user">
								<h4>Admin Info</h4>
								<div class="row">
									<div class="col-md-6 form-it">
										<label>ID</label> <input type="text" name="id"
											placeholder="아이디" value="<%=account.getId()%>">
									</div>
									<div class="col-md-6 form-it">
										<label>PassWord</label> <input type="password"
											placeholder="비밀번호" value="<%=account.getPwd()%>">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 form-it">
										<label>Name</label> <input type="text" name="name" value="<%=account.getName()%>" placeholder="이름">
									</div>
									<div class="col-md-6 form-it">
										<label>BirthDay</label> <input type="text" name="birth"
											placeholder="생년월일" value="<%=account.getBirth()%>">
									</div>

								</div>
								<div class="row">
									<div class="col-md-6 form-it">
										<label>Email</label> <input type="text" name="email" placeholder="이메일" value="<%=account.getEmail()%>">
									</div>
								</div>

								<div class="row">
									<div class="col-md-2">
										<input class="submit" type="submit" value="save">
									</div>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		
		<script src="Film-Review-Movie-Database/js/jquery.js"></script>
<script src="Film-Review-Movie-Database/js/plugins.js"></script>
<script src="Film-Review-Movie-Database/js/plugins2.js"></script>
<script src="Film-Review-Movie-Database/js/custom.js"></script>
</body>
</html>