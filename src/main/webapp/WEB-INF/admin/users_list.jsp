<!DOCTYPE HTML>

<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="main.java.com.mycompany.dto.UserDto"%>

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

<title>Admin Directory</title>
</head>

<body>

	<div class="container">

		<h3>Admin Directory</h3>
		<hr>

		<div align="right">

			<!-- Add a logout button -->
			<form action="${pageContext.request.contextPath}/logout" method="GET">
				${role} ${lastName} <input type="submit" value="Logout"
					class="btn btn-outline-primary mt-2" />
			</form>

		</div>

		<form class="form-inline">
			<a href="${pageContext.request.contextPath}/admin/create_user"
				class="btn btn-primary mr-sm-2 mb-3"> Add new user </a> <input
				class="form-control ml-5 mr-sm-2 mb-3" type="search" name="userName"
				placeholder="Search by name" />

			<button class="btn btn-primary mb-3" type="submit">Search</button>
		</form>

		<table class="table table-bordered table-striped">
			<thead class="thead-dark" align="center">
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Login</th>
					<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Role(s)</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody align="center">
				<c:forEach var="tempUser" items="${usersList}" varStatus="status">
					<tr>
						<td>${status.index +1}</td>
						<td>${tempUser.id}</td>
						<td>${tempUser.login}</td>
						<td>${tempUser.email}</td>
						<td>${tempUser.firstName}</td>
						<td>${tempUser.lastName}</td>
						<%!%>
						<td><c:forEach items="${ages}" var="age">
								<c:if test="${age.key ==  tempUser.id}">
						${age.value}
						</c:if>
							</c:forEach></td>
						<td><c:forEach var="role" items="${tempUser.roles}">
					      ${role} 
					   </c:forEach></td>
						<td>
							<!-- Add "update" button/link --> <a
							href="${pageContext.request.contextPath}/admin/update_user?login=${tempUser.login}"
							class="btn btn-info btn-sm"> Update </a>
							<!-- Add "delete" button/link -->
							<a
							href="${pageContext.request.contextPath}/admin/delete_user?id=${tempUser.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">
								Delete </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- Add a redirect to roles button -->
		<form action="${pageContext.request.contextPath}/admin/list_roles"
			method="GET">
			<input type="submit" value="To Roles List"
				class="btn btn-outline-primary mt-2" />

		</form>
	</div>
</body>
</html>



