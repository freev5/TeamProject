<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.tp.util.tpUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화</title>



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

<script>$(document).ready(function() { $('body').bootstrapMaterialDesign(); });</script>


</head>
<body>
<script>
window.onload = function() {
	window.scrollTo(0, 0);
	};
</script>

<jsp:include page="nav.jsp"></jsp:include>

<div class="slider movie-items">
	<div class="container">
		<div class="row">
			
	    	
	    </div>
	</div>
</div>
		<div class="buster-light">

<div class="trailers">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-12">
				<div class="title-hd">
					<h2>Movie Trailer</h2>
				</div>
				<div class="videos">
				 	<div class="slider-for-2 video-ft">
				 		<div>
				 			<iframe class="item-video" src="#" data-src="https://www.youtube.com/embed/1Q8fG0TtVAY"></iframe>
					    </div>
					    <div>
					    	<iframe class="item-video" src="#" data-src="https://www.youtube.com/embed/w0qQkSuWOS8"></iframe>
					    </div>
					    <div>
					    	<iframe class="item-video" src="#" data-src="https://www.youtube.com/embed/44LdLqgOpjo"></iframe>
					    </div>
						
						
					</div>
					<div class="slider-nav-2 thumb-ft">
						<div class="item">
							<div class="trailer-img">
								<img src="Film-Review-Movie-Database/images/uploads/trailer7.jpg"  alt="photo by Barn Images" width="4096" height="2737">
							</div>
							<div class="trailer-infor">
	                        	<h4 class="desc">Wonder Woman</h4>
	                        	<p>2:30</p>
	                        </div>
						</div>
						<div class="item">
							<div class="trailer-img">
								<img src="Film-Review-Movie-Database/images/uploads/trailer2.jpg"  alt="photo by Barn Images" width="350" height="200">
							</div>
							<div class="trailer-infor">
	                        	<h4 class="desc">Oblivion: Official Teaser Trailer</h4>
	                        	<p>2:37</p>
	                        </div>
						</div>
						<div class="item">
							<div class="trailer-img">
								<img src="Film-Review-Movie-Database/images/uploads/trailer6.jpg" alt="photo by Joshua Earle">
							</div>
							<div class="trailer-infor">
	                        	<h4 class="desc">Exclusive Interview:  Skull Island</h4>
	                        	<p>2:44</p>
	                        </div>
						</div>
						
					
					</div>
				</div>   
			</div>
		</div>
	</div>
</div>
<!-- latest new v1 section-->
<div class="latestnew">
	<div class="container">
		<div class="row ipad-width">
			<div class="col-md-8" style="width: 100%;">
				<div class="title-hd">
					<h2>Movie news</h2>
				</div>
				<div class="tabs">
				    <div class="tab-content">
				        <div id="tab31" class="tab active">
				            <div class="row">
				            	<div class="blog-item-style-1">
				            		<img src="images/bong.jpg" alt="" style="width:170px; height:250px;">
				            		<div class="blog-it-infor">
				            			<h3><a href="https://www.bbc.com/news/world-europe-48409063" target="_blank">Cannes: Palme d'Or goes to Bong Joon-ho's Parasite</a></h3>
				            			<span class="time">BBC News</span>
				            			<p>South Korean director <span>Bong Joon-ho</span> has won the Cannes film festival's most prestigious award. The Palme d'Or was awarded for his film Parasite, a dark comedy thriller exploring social class dynamics. The festival came to a close this evening after 11 days of previews of new films and documentaries.
				            		</div>
				            	</div>
				            </div>
				        </div>
				        
				        
				    </div>
				</div>
				
			</div>
			
		</div>
	</div>
</div>
<!--end of latest new v1 section-->
		</div>
<jsp:include page="footer.jsp"></jsp:include>

<script src="Film-Review-Movie-Database/js/jquery.js"></script>
<script src="Film-Review-Movie-Database/js/plugins.js"></script>
<script src="Film-Review-Movie-Database/js/plugins2.js"></script>
<script src="Film-Review-Movie-Database/js/custom.js"></script>
</body>
</html>