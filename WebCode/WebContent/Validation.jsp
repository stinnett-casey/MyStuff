<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Validation</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<style>
			#message{
				position: absolute;
				top: 70px;
				text-align: center;
				left: 10%;
			}
		</style>
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
					<a class="brand" href="#">MyStuff</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="#">Home</a></li>
						</ul>
					</div><!--/.nav-collapse -->
				</div>				
			</div>
		</div>
<div id="message">
	<h2>Thank you!</h2>
	<p>Thanks for signing up with MyStuff! You will be receiving an <b>email</b> shortly with a <u>validation link</u>. 
	Click on the link to validate your account, login, and then have fun on our site!</p>
	<a href="Login.jsp">Login Page</a>
</div>
	</body>
</html>