<%@page import="com.tp.controller.FrontController"%>
<%@page import="com.tp.controller.MovieSearchController"%>
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
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
<link rel="stylesheet" type="text/css" href="css/star-rating-svg.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body>
<h3>검색 결과</h3>
<%
	int initialRate = 0;
	String action="";
	int wishOn = 0;
	request.setCharacterEncoding("UTF-8");
	
	String Loging = (String)request.getSession().getAttribute("Loging");
	if(Loging==null){
		response.sendRedirect("Login.jsp");
	}
	
	MD[] movies = (MD[])request.getServletContext().getAttribute("movies");
	MovieOutput movieOp = (MovieOutput)request.getServletContext().getAttribute("movieOp");

	
	%>
	<div>검색 결과 : <%=movieOp.getDisplay() %>건</div>
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






<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>