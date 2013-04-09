<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Create Account Page</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<style>
			form { 
				width: 400px;
				position: absolute;
				left: 400px;
			 }
			label { float: left; width: 150px; }
			input[type=text] { float: left; width: 250px; }
			.clear { clear: both; height: 0; line-height: 0; }
			.floatright { float: right; }
			 #errorDiv{
		  	position: absolute;
		  	left: 32%;		  	
		  }
		  form{
		  	margin-top: 25px;
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
		
		<body>
		
		<!-- Codes by HTML.am -->

		<form method="post" action="edu.byu.isys413.cca.actions.CreateCustomer.action">
		
			<h2>Create New Account</h2>
			<h4>All fields are required</h4>
			<label for="firstname">First Name</label> 		
			<input type="text" name="firstname" class="input-block-level">
			<label for="lastname">Last Name</label> 	
			<input type="text" name="lastname" class="input-block-level">
			<label for="phone">Phone</label> 	
			<input type="text" name="phone" class="input-block-level">
			<label for="address">Address</label>		
			<input type="text" name="address" class="input-block-level">
			<label for="state">State</label>		
			<input type="text" name="state" class="input-block-level">
			<label for="city">City</label>		
			<input type="text" name="city" class="input-block-level">
			<label for="zip">Zip</label>		
			<input type="text" name="zip" class="input-block-level">
			<label for="email">Email</label>		
			<input type="text" name="email" class="input-block-level">
			<label for="password">Create Password</label>		
			<input type="password" name="password">
			<label for="password">Confirm Password</label>		
			<input type="password" name="passwordConfirm">
			
			<h3>Credit Card Information</h3>
			<label for="cc number">Credit Card Number</label>		
			<input type="text" name="ccnumber" class="input-block-level">
		
			<input type="submit" class="btn" value="Create">
			<a href="Login.jsp">Cancel</a>		
		<%
			if (request.getAttribute("error") != null){
				out.println("<div id=\"errorDiv\">");
				out.println(request.getAttribute("error"));
				out.println("</div>");
			}
		%>
		</form>		
		
		 <script>
			$('input[type=submit]').hover(function(){
				if ($('input[name="password"]').val() != $('input[name="passwordConfirm"]').val()){
					alert("Your passwords do not match. Type them again and be sure that your caps lock is not on!");
				}//if
			});			
		</script>
	</body>
</html>