<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tp.vo.MD"%>
<%@ page import="com.tp.vo.Rate"%>
<%@ page import="com.tp.vo.WishList"%>
<%@ page import="com.tp.vo.MovieOutput"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<link rel="stylesheet" href="Film-Review-Movie-Database/css/style_m.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
<link rel="stylesheet" type="text/css" href="css/star-rating-svg.css">

</head>
<style>
#rateB:hover{color:#5fda5f;}
</style>
<body>
<%

int initialRate = 0;
int wishOn = 0;
request.setCharacterEncoding("UTF-8");

MD[] movies = (MD[])request.getServletContext().getAttribute("movies");
MovieOutput movieOp = (MovieOutput)request.getServletContext().getAttribute("movieOp");

ArrayList<Rate> rate  = (ArrayList<Rate>)request.getSession().getAttribute("rlist");
ArrayList<WishList> wish  = (ArrayList<WishList>)request.getSession().getAttribute("wlist");
ArrayList<MD> md  = (ArrayList<MD>)request.getSession().getAttribute("mlist");

String reqMtitle = request.getParameter("Mtitle");
System.out.println(reqMtitle);

for(int i=1;i<md.size();i++){
if(reqMtitle.equals(md.get(i).getMtitle())){
	
	
String Mtitle = md.get(i).getMtitle();
String image = md.get(i).getImage();
int MovieNo = md.get(i).getMcode();
String pubDate = md.get(i).getPubDate();
String subTitle = md.get(i).getSubtitle();
String director = md.get(i).getDirector();
String actor = md.get(i).getActor();
String link = md.get(i).getLink();


if(rate!=null){
	for(int jj=0;jj<rate.size();jj++){
		if(rate.get(jj).getMtitle().equals(reqMtitle)){
			initialRate=Integer.parseInt(rate.get(jj).getRating());
			break;
		}
		initialRate=0;
	}
	}
	if(wish!=null){
	for(int ii=0;ii<wish.size();ii++){
		if(wish.get(ii).getMtitle().equals(reqMtitle)){
			wishOn = 1;
			break;
		}
		wishOn = 0;
	}
	}
%>


<jsp:include page="nav.jsp"></jsp:include>
<input type="hidden" id="initialRate" value="<%=initialRate%>">


		<div class="buster-light">
<div class="hero mv-single-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- <h1> movie listing - list</h1>
				<ul class="breadcumb">
					<li class="active"><a href="#">Home</a></li>
					<li> <span class="ion-ios-arrow-right"></span> movie listing</li>
				</ul> -->
			</div>
		</div>
	</div>
</div>
<div class="page-single movie-single movie_single">
	<div class="container">
		<div class="row ipad-width2">
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="movie-img sticky-sbd">
					<img src="<%=image %>" alt="">
					
				</div>
			</div>
			<div class="col-md-8 col-sm-12 col-xs-12">
				<div class="movie-single-ct main-content">
					<h1 class="bd-hd"><%=reqMtitle %> <span><%=pubDate %></span><br/><span><%=subTitle %></span></h1>
					
					<div style="color:#4f5b68;">감독 : <%=director %></div>
					<div style="color:#4f5b68;">배우 : <%=actor %></div>
					<div class="social-btn">
						<%if(wishOn == 0){ %>
		<form action="WishInsert.do" method="post">
			<input type="hidden" name="Mtitle" value="<%=reqMtitle%>">
			<input type="hidden" name="Mcode" value="<%=MovieNo%>">
			<button type="submit" style="border:0; outline: none;    background-color: transparent;"><i class="fa fa-heart" style="font-size:25px;"></i>
			</button>
		</form>
		<%}else{ %>
		<form action="WishDelete.do" method="post">
			<input type="hidden" name="Mtitle" value="<%=reqMtitle%>">
			<input type="hidden" name="Mcode" value="<%=MovieNo%>">
			<br/>
			<button type="submit" style="border:0;outline: none;background-color: transparent;"><i class="fa fa-heart" style="color: red; font-size:25px;"></i>
			</button>
		</form>
		<%}%>
		
								
					</div>
					<div class="movie-rate">
						<div class="rate">
							<i class="ion-android-star"></i>
						</div>
						<div class="rate-star">
							<p>Rate This Movie:  </p>
							<%if(initialRate==0){
			%><form action="RateInsert.do" id="rateForm" method="post">
			<input type="hidden" name="Mtitle" value="<%=reqMtitle%>">
			<input type="hidden" id="rating" name="rating" value="">
				<span class="my-rating"></span>
				
				<button class="rate_button" type="submit" style="border:0; outline: none;    background-color: transparent;"><i id="rateB"class="fa fa-check"></i>
			</button>
			</form>
			<%
		}
		else{%>
			 <form action="RateUpdate.do" method="post">
		<input type="hidden" name="Mtitle" value="<%=reqMtitle%>">
		<input type="hidden" id="rating" name="rating" value="">
			
			<span class="my-rating"></span>
			<button class="rate_button" type="submit" style="border:0; outline: none;    background-color: transparent;"><i id="rateB"class="fa fa-check"></i>
			</button>
		</form>
		<%
		}
		%>
						</div>
					</div>
					
					<div id="wrap" style="position:block;margin:100px 0 0 -300px;">
 						 <iframe src="https://movie.naver.com/movie/bi/mi/basic.nhn?code=<%=MovieNo%>"
 						  width="1080" height="860" name=naver onload="naver.scrollTo(100,500); scrolling="no" frameborder="0"></iframe>
 					</div>

					
					
				</div>
			</div>
		</div>
	</div>
</div>
		</div>
		
		
		
		<%
		
		break;	
	}

}
%>



<script>
		$(function() {
		
		  $(".my-rating").starRating({
		    initialRating: (document.getElementById("initialRate").value) / 2,
		    disableAfterRate: false,
		    onHover: function(currentIndex, currentRating, $el){
		      console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
		      $('.live-rating').text(currentIndex*2);
		      
		    },
		    onLeave: function(currentIndex, currentRating, $el){
		      console.log('index: ', currentIndex, 'currentRating: ', currentRating, ' DOM element ', $el);
		      $('.live-rating').text(currentRating*2);
		      $('#rating').val(currentRating*2);
		    }
		  });
		});
	</script>
	<script src="Film-Review-Movie-Database/js/jquery.js"></script>
<script src="Film-Review-Movie-Database/js/plugins.js"></script>
<script src="Film-Review-Movie-Database/js/plugins2.js"></script>
<script src="Film-Review-Movie-Database/js/custom.js"></script>
<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>