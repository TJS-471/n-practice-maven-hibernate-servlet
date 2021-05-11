<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- JavaScript -->
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Save User</title>
</head>

<body>

	<div class="container">

		<h3>User Directory</h3>
		<hr>
		<p class="h4 mb-4">
			<c:if test="${user == null}">Add new user</c:if>
			<c:if test="${user != null}">Edit user</c:if>
		</p>


		<c:if test="${user != null}">
			<form action="${pageContext.request.contextPath}/admin/update_user" 
			id="userForm"
				method="post">
		</c:if>

		<c:if test="${user == null}">
			<form action="${pageContext.request.contextPath}/admin/create_user" 
			id="userForm"
				method="post">
		</c:if>

		<!-- Add a hidden form field to handle update -->
		<input type="hidden" name="id" value="${user.id}"> 
		<c:if test="${user == null}">
		<input
			type="text" name="login" id="login" size="30"
			class="form-control mb-4 col-4" placeholder="Login"> 
			</c:if>
			<c:if test="${user != null}">
		<input
			type="text" name="login" value="${user.login}" id="login" size="30"
			class="form-control mb-4 col-4" readonly="readonly"> 
			</c:if>
		<input
			type="text" name="email" value="${user.email}"
			class="form-control mb-4 col-4" id="email" placeholder="Email">
			<input
			type="password" name="password" 
			id="password" autocomplete="off" minlength="6" maxlength="12"
			class="form-control mb-4 col-4" placeholder="Password">
			<input
			type="password" name="confirmPassword" 
			class="form-control mb-4 col-4" id="confirmPassword" autocomplete="off" 
			 minlength="6" maxlength="12" placeholder="Password again">
		<input
			type="text" name="firstName" value="${user.firstName}" size="30"
			class="form-control mb-4 col-4" id="firstName" placeholder="First Name"> 
		<input
			type="text" name="lastName" value="${user.lastName}" size="30"
			class="form-control mb-4 col-4" id="lastName" placeholder="Last Name"> 
		<input
			type="text" name="birthDate"
			value="<fmt:formatDate pattern='yyyy-MM-dd' value ='${user.birthDate}'/>"
			class="form-control mb-4 col-4" id="birthDate" placeholder="Birth Date"> 
			
			<select name="name" id="roles" class="form-control mb-4 col-4" required="required">
			<c:if test="${user == null}">
			<c:forEach items="${rolesList}" var="role">
			    <option value="${role.name}">${role.name}</option>
			</c:forEach>
			</c:if>
			<c:if test="${user != null}">
                   			<c:forEach items="${rolesList}" var="role">
                   			<c:forEach items="${user.roles}" var="userRole">
                   				<option value="${role.name}" <c:if test="${userRole eq role.name}"> <c:out value= "selected=selected"/></c:if>>${role.name}</option>
                                  </c:forEach>
                   			</c:forEach>
                   			</c:if>
		</select> 
		<button type="submit" class="btn btn-info col-2" style="margin-top: 15px">Save</button>
		<button id="cancelbutton" class="btn btn-light col-2" style="margin-top: 15px">Cancel</button>
		</form>
		<hr>
		<a href="${pageContext.request.contextPath}/admin/list_users">Back
			to Users List</a>

	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#userForm").validate({
			rules : {
				login : "required",
				email : {
					required : true,
					email : true
				},
				<c:if test="${user == null}">
				password : "required",
				</c:if>
				<c:if test="${user == null}">
				confirmPassword : {
					required : true,
					equalTo : "#password",
					</c:if>
				},
				firstName : "required",
				lastName : "required",
				birthDate : "required",
				roles : "required"
			},
			messages : {
				login : "Please, enter a login.",
				email : {
					required : "Please, enter an email.",
					email : "Please, enter a valid email adress"
				},
				<c:if test="${user == null}">
				password : "Please, enter a password.",
				</c:if>
				confirmPassword : {
					<c:if test="${user == null}">
					required : "Please, enter a password once again.",
					</c:if>
					equalTo : "The confirmed password should match with a password."
				},

				firstName : "Please, enter a first name.",
				lastName : "Please, enter an last name.",
				birthDate : "Please, enter a birth date.",
				roles : "Please, choose a roles."
			}
		});
		$("#cancelbutton").click(function() {
			history.go(-1);
		});
	});
	</script>
</html>










