<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>

<title>Home Page</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>

<body>

	<div align="center" class="h4 mb-4">

		<h1>Welcome to Home Page</h1>
		
		<!-- Add a login button -->
		<form action="${pageContext.request.contextPath}/login" method="GET">
			<input type="submit"
				value="Login" class="btn btn-outline-primary mt-2" />

		</form>
	</div>

</body>
</html>



