<%@page import="com.tp.controller.MD.MDListController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.tp.vo.MD"%>
<%@ page import="com.tp.service.*"%>
<%@ page import="com.tp.vo.Rate"%>
<%@ page import="com.tp.vo.WishList"%>
<%@ page import="com.tp.vo.MovieOutput"%>
<%@ page import="java.util.ArrayList"%>
<style>
#rateB:hover{color:#5fda5f;}
</style>
	<%
	while(true){
		int initialRate = 0;
		int i = (int)request.getServletContext().getAttribute("i");
		int wishOn = 0;
		request.setCharacterEncoding("UTF-8");
		
		String id = (String)request.getSession().getAttribute("Loginid");
		
		
		
		MD[] movies = (MD[])request.getServletContext().getAttribute("movies");
		MovieOutput movieOp = (MovieOutput)request.getServletContext().getAttribute("movieOp");
		ArrayList<Rate> rate  = (ArrayList<Rate>)request.getSession().getAttribute("rlist");
		ArrayList<WishList> wish  = (ArrayList<WishList>)request.getSession().getAttribute("wlist");
		
		
		String Mtitle = movies[i].getMtitle();
		String image = movies[i].getImage();
		int MovieNo = movies[i].getMcode();
		String pubDate = movies[i].getPubDate();
		
		if(rate!=null){
		for(int jj=0;jj<rate.size();jj++){
			if(rate.get(jj).getMtitle().equals(Mtitle)){
				initialRate=Integer.parseInt(rate.get(jj).getRating());
				break;
			}
			initialRate=0;
		}
		}
		if(wish!=null){
		for(int ii=0;ii<wish.size();ii++){
			if(wish.get(ii).getMtitle().equals(Mtitle)){
				wishOn = 1;
				break;
			}
			wishOn = 0;
		}
		}
		
		%>
		
		
		<div class="movie-item-style-2 movie-item-style-1">
		<input type="hidden" id="initialRate-<%=i %>" value="<%=initialRate%>">
	            			
	            			<div class="hvr-inner">
	            				<form action="MoviePage.jsp" method="get"> 
	            				<input type="hidden" value="<%=Mtitle%>" name="Mtitle">
	            				<button style="border:0;line:0;background-color:transparent;color:#fff;outline: none;" type="submit">상세정보 <i class="ion-android-arrow-dropright"></i></button>
	            				</form>
	            			</div>
	            			
	            			<img src="<%=image%>" alt="" onerror="images/content_not.jpg">
	            			<div class="mv-item-infor">
	            			
	            			
	            			<h6><a style="display: block;" href="#"><%=Mtitle %>(<%=pubDate%>)</a>
	            			
		</h6>
	            				
	            				
	            				<p class="rate"><%if(initialRate==0){
			%><form action="RateInsert.do" id="rateForm" method="post">
			<input type="hidden" name="Mtitle" value="<%=Mtitle%>">
			<input type="hidden" name="Mcode" value="<%=MovieNo%>">
			<input type="hidden" id="rating-<%=i %>" name="rating" value="">
				<span class="my-rating-<%=i%>" style="display: inline-block;"></span>
				
				<button class="rate_button" type="submit" style="border:0;     background-color: transparent;outline: none;"><i id="rateB"class="fa fa-check" style="font-size:25px;"></i>
			</button>
			</form>
			<%
		}
		else{%>
			 <form action="RateUpdate.do" method="post">
		<input type="hidden" name="Mtitle" value="<%=Mtitle%>">
		<input type="hidden" id="rating-<%=i %>" name="rating" value="" >
			
			<span class="my-rating-<%=i%>" style="display: inline-block;"></span>
			<button class="rate_button" type="submit" style="border:0;     background-color: transparent;outline: none;"><i id="rateB"class="fa fa-check" style="font-size:25px;"></i>
			</button>
		</form>
		<%
		}
		%></p>
		<%if(wishOn == 0){ %>
		<form action="WishInsert.do" method="post">
			<input type="hidden" name="Mtitle" value="<%=Mtitle%>">
			<input type="hidden" name="Mcode" value="<%=MovieNo%>">
			<button class="wishbutton"type="submit" style="border:0;     background-color: transparent;outline: none;"><i class="fa fa-heart" style="font-size:25px;    margin: 0 0 0 80px;"></i>
			</button>
		</form>
		<%}else{ %>
		<form action="WishDelete.do" method="post">
			<input type="hidden" name="Mtitle" value="<%=Mtitle%>">
			<input type="hidden" name="Mcode" value="<%=MovieNo%>">
			<button class="wishbutton"type="submit" style="border:0;background-color: transparent;outline: none;"><i class="fa fa-heart" style="color: pink;    margin: 0 0 0 80px; font-size:25px;"></i>
			</button>
		</form>
		<%} %>
		
		</div>
	            			</div>

		
		<form action="MoviePage.jsp" method="get">
		<input type="hidden" value="<%=Mtitle%>" name="Mtitle">
		</form>
		
		
		
		
		
		
	
	
	<%break;}%>