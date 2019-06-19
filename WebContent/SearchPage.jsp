
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="com.tp.vo.MD"%>
<%@ page import="com.tp.vo.Rate"%>
<%@ page import="com.tp.vo.WishList"%>
<%@ page import="com.tp.vo.MovieOutput"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="com.tp.controller.FrontController"%>
<%@page import="com.tp.controller.MovieSearchController"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
<link rel="stylesheet" type="text/css" href="css/star-rating-svg.css">

<link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />

<link rel="stylesheet" href="Film-Review-Movie-Database/css/plugins.css">
	<link rel="stylesheet" href="Film-Review-Movie-Database/css/style.css">
</head>


<body>
<%
//별 개수 설정
	int initialRate = 0;
	//보고싶어요 설정
	int wishOn = 0;
	
	//인코딩
	request.setCharacterEncoding("UTF-8");
	
	//로그인 상태 받아오기 : 비로그인 시 로그인 페이지로
	String Loging = (String)request.getSession().getAttribute("Loging");
	if(Loging==null){
		response.sendRedirect("Login.jsp");
	}
	
	//api로 받아온 영화리스트
	MD[] movies = (MD[])request.getServletContext().getAttribute("movies");
	//api로 받아온 영화리스트 종합정보
	MovieOutput movieOp = (MovieOutput)request.getServletContext().getAttribute("movieOp");
%>
<script>
//현재 위치값을 받아옴
$(function () {
                $(window).scroll(function () {
                    var num = $(this).scrollTop();
                    sessionStorage.setItem('scroll', num);
                });
            });
//처음 위치값 설정
window.onload = function() {
	let data = sessionStorage.getItem('scroll');
	window.scrollTo(0, data);
	};

</script>
<jsp:include page="nav.jsp"></jsp:include>
<div class="buster-light">
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1> 검색결과 : <%=request.getParameter("MovieSearch") %></h1>
					<ul class="breadcumb">
						<li class="active"><a href="Main.jsp">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Search Movie</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="topbar-filter">
				</div>
				<div class="flex-wrap-movielist">
						<%
	if(movieOp.getDisplay()==null){
		movieOp.setDisplay("0");
	}
	if(movies!=null){
	for(int i=0;i<Integer.parseInt(movieOp.getDisplay());i++){
		request.getServletContext().setAttribute("i",i);
		%>
		<jsp:include page="MovieBox.jsp"></jsp:include>
		
		<script>
		$(function() {
		
		  $(".my-rating-<%=i%>").starRating({
		    initialRating: (document.getElementById("initialRate-<%=i%>").value) / 2,
		    disableAfterRate: false,
		    onHover: function(currentIndex, currentRating, $el){
		      console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
		      $('.live-rating-<%=i%>').text(currentIndex*2);
		      
		    },
		    onLeave: function(currentIndex, currentRating, $el){
		      console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
		      $('.live-rating-<%=i%>').text(currentRating*2);
		      $('#rating-<%=i %>').val(currentRating*2);
		    }
		  });
		});
	</script>
		<%
		
	}
	}
%>
					
					
						
				</div>		
				
			</div>
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="sidebar">
					
					
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

<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>