
<%@page import="com.tp.controller.FrontController"%>
<%@page import="com.tp.controller.MovieSearchController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.tp.vo.MD"%>
<%@ page import="com.tp.vo.Rate"%>
<%@ page import="com.tp.vo.MovieOutput"%>
<%@page import="com.tp.vo.WishList"%>
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
<style>
.wishbutton{border:0;outline:0;background-color:transparent;font-size:20px;}
</style>
<body>
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
<%
	//별 개수 설정
	int initialRate = 0;
	//보고싶어요 설정
	int wishOn = 0;
	
	request.setCharacterEncoding("UTF-8");
	MovieOutput movieOp = (MovieOutput)request.getServletContext().getAttribute("movieOp");
	ArrayList<Rate> rate  = (ArrayList<Rate>)request.getSession().getAttribute("rlist");
	ArrayList<WishList> wish  = (ArrayList<WishList>)request.getSession().getAttribute("wlist");
	ArrayList<WishList> movies  = (ArrayList<WishList>)request.getSession().getAttribute("mlist");
	%>
	
	<jsp:include page="nav_myPage.jsp"></jsp:include>
	<div class="buster-light">
<div class="hero common-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1> 보고싶은 영화 </h1>
					<ul class="breadcumb">
						<li class="active"><a href="Main.jsp">Home</a></li>
						<li> <span class="ion-ios-arrow-right"></span> Wish Movie</li>
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
	if(movies!=null){
	for(int i=0;i<wish.size();i++){
		request.getServletContext().setAttribute("i",i);
		%>
		<jsp:include page="MovieBox_Wish.jsp"></jsp:include>
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

		<script src="Film-Review-Movie-Database/js/jquery.js"></script>
<script src="Film-Review-Movie-Database/js/plugins.js"></script>
<script src="Film-Review-Movie-Database/js/plugins2.js"></script>
<script src="Film-Review-Movie-Database/js/custom.js"></script>

<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>