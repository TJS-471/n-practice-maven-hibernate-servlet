<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- JavaScript -->
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Login Page</title>
</head>

<body>

	<div>

		<div style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Login Form -->
					<form action="${pageContext.request.contextPath}/login"
						id="loginForm" method="POST" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>

									<!-- Check for login error -->
									<c:if test="${errorMessage != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${errorMessage}</div>
									</c:if>
									<!-- Check for logout -->
									<c:if test="${logoutMessage != null}">
										<div class="alert alert-success col-xs-offset-1 col-xs-10">
											${logoutMessage}</div>
									</c:if>
								</div>
							</div>
						</div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								name="login" autocomplete="off" id="login"
								placeholder="username">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								name="password" placeholder="password" id="password"
								autocomplete="off">
						</div>

						<!-- Login/Submit Button -->
						<div style="margin-top: 10px" class="input-group">
							<div class="form-inline">
								<button type="submit" class="btn btn-info col-2">Login</button>
								<a href="javascript:history.go(-1);" class="btn btn-light col-2">
									Cancel</a>

							</div>
						</div>

					</form>

				</div>

			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldLogin = document.getElementById('login');
		var fieldPassword = document.getElementById('password');
		if (fieldLogin.value.length == 0) {
			alert('Login is required');
			fieldLogin.focus();
			return false;
		}
		if (fieldPassword.value.length == 0) {
			alert('Password is required');
			fieldPassword.focus();
			return false;
		}
	}
</script>
</html>