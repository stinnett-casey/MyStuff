<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MyStuff Home Page</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				
				
				$('#conceptual').on('keyup', function(){					
					$.post(
						"edu.byu.isys413.cca.actions.GetConceptuals.action",
						{
							'letters': $('#conceptual').val(),
							'condition': $('#condition option:selected').val()
						}).done(function(names){
							console.log("In done function");
							var obj = jQuery.parseJSON(names);
							console.log(obj);
							$.each(obj, function(){
								$('#results > dl').append('<dt>' + obj.price + '</dt>');
							});
						});
				});
				
				//$('.carousel').carousel();
				
			
			});
		</script>
	</head>

	<body>

		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="brand" style="color:CCFF99;">MyStuff</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="#">Home</a></li>
							<% 
								out.println("<li>");
								out.println(session.getAttribute("name"));
								out.println("</li>");
							%>
						</ul>
										
						
					</div><!--/.nav-collapse -->
				</div>				
			</div>
		</div>

		<div class="container" style="margin-top: 100px; background-image: url('img/leaves.jpg');">
			<div id="myCarousel" class="carousel">
				<center>
					<div class="carousel-inner">
						<div class="item active" style="height:300px;">
						<img src="img/canon.gif" width="546px" height="370px" alt=""/>
							<div class="carousel-caption">
								<h4>Canon EOS 5D Mark III</h4>
							</div>
						</div>
						<div class="item" style="height:300px;">
							<img src="img/FujifilmFinePix S9100 - new.jpg" width="300px" height="300px" alt=""/>
							<div class="carousel-caption">
								<h4>Fujifilm FinePix S9100</h4>
							</div>
						</div>
							<div class="item" style="height:300px;">
								<img src="img/SnowCameraGoggles.jpeg" width="300px" height="300px" alt=""/>
								<div class="carousel-caption">
									<h4>Snow Camera Goggles</h4>
								</div>
							</div>
						<div class="item" style="height:300px;">
							<img src="img/DigitalVideoCamera - new.jpg" width="300px" height="300px" alt=""/>
							<div class="carousel-caption">
								<h4>Digital Video Camera</h4>
							</div>
						</div>
					</div><!--Carousel Inner-->
				</center>
				<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<script type="text/javascript" src="js/carousel.js"></script>

		<div id="search space" class="container" style="background-color: silver; margin-bottom: 10px;">
			<div id="searchBox">
				<form method="post">
				<select id="condition" style="position: relative; left: 20%; ">
						<option value="new" name="new">New</option>
						<option value="used" name="used">Used</option>
				</select>
					<input id="conceptual" type="search" style="position:relative;
					left:40%;
					top: 80%;
					width: 300px;
					height: 26px;
					margin-left: -150px;
					margin-top: 10px;">
					
				</form>
			</div>


			<div id="results" class="container" style="margin-left:25px; margin-right:25px;">
				<dl>
				</dl>
				<div id="purchase button" style="position:relative; margin-right:40px; padding-bottom:40px;">
					<button class="btn btn-success" type="submit" style="float: right;">Purchase Product</button>
				</div>
			</div>
		</div>
		
		

	</body>
</html>