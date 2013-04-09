<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Change the color with the button!
	<input type="button" id="clicker" value="Change Color" />
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>
		
	<script>
		$(function(){
			$('#clicker').click(function(){
				$(this).attr('value', 'Switched');
			});//end click
		});//Ready
	</script>

</body>
</html>