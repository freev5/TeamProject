<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ page import="com.tp.util.tpUtil" %>
    <%@ page import="com.tp.vo.Account" %>
    <!--preloading-->
<div id="preloader">
    <img class="logo" src="Film-Review-Movie-Database/images/logo1.png" alt="" width="119" height="58">
    <div id="status">
        <span></span>
        <span></span>
    </div>
</div>
<!--end of preloading-->
<!--login form popup-->
<%
	String Loging = (String)request.getSession().getAttribute("Loging");
	Account account = (Account)request.getSession().getAttribute("account");
	
%>
<div class="login-wrapper" id="login-content">
    <div class="login-content">
        <a href="#" class="close">x</a>
        <h3>Login</h3>
        <form method="post" action="AccountLogin.do">
        	<div class="row">
        		 <label for="username">
                    ID
                    <input type="text" name="id" id="username" placeholder="id" required="required" />
                </label>
        	</div>
           
            <div class="row">
            	<label for="password">
                    PASSWORD
                    <input type="password" name="pwd" id="password" placeholder="password" required="required" />
                </label>
            </div>
           <div class="row">
           	 <button type="submit">Login</button>
           </div>
        </form>
        <div class="row" style="margin:5% 0 0 0; width:100%;">
           	 <button style="border-radius:20px; background-color:#ccc;" type="button" onclick="location.href='Register.jsp'">회원가입</button>
           </div>
    </div>
</div>
<!--end of login form popup-->
<!--signup form popup-->
<div class="login-wrapper"  id="signup-content">
    <div class="login-content">
        <a href="#" class="close">x</a>
        <h3>sign up</h3>
        <form method="post" action="#">
            <div class="row">
                 <label for="username-2">
                    ID
                    <input type="text" name="username" id="username-2" placeholder="Hugh Jackman" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required="required" />
                </label>
            </div>
           
            <div class="row">
                <label for="email-2">
                    your email:
                    <input type="password" name="email" id="email-2" placeholder="" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" />
                </label>
            </div>
             <div class="row">
                <label for="password-2">
                    Password:
                    <input type="password" name="password" id="password-2" placeholder="" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" />
                </label>
            </div>
             <div class="row">
                <label for="repassword-2">
                    re-type Password:
                    <input type="password" name="password" id="repassword-2" placeholder="" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required" />
                </label>
            </div>
           <div class="row">
             <button type="submit">sign up</button>
           </div>
        </form>
    </div>
</div>
<!--end of signup form popup-->

<!-- BEGIN | Header -->
<header class="ht-header">
	<div class="container">
		<nav class="navbar navbar-default navbar-custom">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header logo">
				    <div class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					    <span class="sr-only">Toggle navigation</span>
					    <div id="nav-icon1">
							<span></span>
							<span></span>
							<span></span>
						</div>
				    </div>
				    <a href="Main.jsp"><img class="logo" src="Film-Review-Movie-Database/images/logo1.png" alt="" width="119" height="58"></a>
			    </div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse flex-parent" id="bs-example-navbar-collapse-1">
					
<ul class="nav navbar-nav flex-child-menu menu-left">
						<li class="hidden">
							<a href="#page-top"></a>
						</li>
						<%
							%>
								
								<li><a href="Admin.jsp">관리자 정보</a></li>
								<li><a href="AdminAccountList.jsp">회원관리</a></li>
								<li><a href="AdminMovieList.jsp">영화관리</a></li>
								
							<%
					%>  
						</ul>
					<ul class="nav navbar-nav flex-child-menu menu-right">
					<li>
								<form action="MovieSearch.do" method="get">
                    		<%
                    			tpUtil tp = new tpUtil();
                    			String rn = tp.randomQuery();
                    		%>
                    			<input type="hidden" name="MovieSearch" value="<%=rn%>">
                    			<input type="hidden" name="MovieDisplay" value="30">
                            	<button type="submit" style="border:0;background-color: transparent;outline: none;color:#abb7c4;font-weight: bold;font-size:14px;">평가하기</button>
                            </form>
								</li>
					             <li><a href="Logout.jsp">로그아웃</a></li>
						
						
					</ul>
				</div>
			<!-- /.navbar-collapse -->
	    </nav>
	    
	    <!-- top search form -->
	    <div class="top-search">
	    <form action="MovieSearch.do" method="get" style="width:100%;">
			<input name="MovieSearch" autofocus type="text" placeholder="영화의 이름으로 검색해 주세요." autocomplete=off>
			<input type="hidden" name="MovieDisplay" value="100">
			</form>
	    </div>
	</div>
</header>
<!-- END | Header -->