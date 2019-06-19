<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V12</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="Login&RegTemplate/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/css/util.css">
	<link rel="stylesheet" type="text/css" href="Login&RegTemplate/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('Login&RegTemplate/images/img-01.jpg');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form action="AccountRegist.do" method="post" class="login100-form validate-form">
					<div class="login100-form-avatar">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">
						Register
					</span>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "ID is required">
						<input class="input100" type="text" name="id" placeholder="ID">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
						<input class="input100" type="password" name="pwd" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input m-b-10" data-validate = "Name is required">
						<input class="input100" type="text" name="name" placeholder="Name">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>
					<div class="wrap-input100 validate-input m-b-10" data-validate = "Birthday is required">
						<input class="input100" type="text" name="birth" placeholder="Birthday">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-10" data-validate = "Email is required">
						<input class="input100" type="text" name="email" placeholder="E-mail">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>
					

					<div class="container-login100-form-btn p-t-10">
						<button class="login100-form-btn">
							Signup
						</button>
					</div>

					
					<div class="text-center w-full">
						<div class="txt1" href="#">
							Create new account				
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="Login&RegTemplate/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="Login&RegTemplate/vendor/bootstrap/js/popper.js"></script>
	<script src="Login&RegTemplate/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="Login&RegTemplate/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="Login&RegTemplate/js/main.js"></script>

</body>
</html>