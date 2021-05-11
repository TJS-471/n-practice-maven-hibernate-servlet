<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
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

<title>Role Directory</title>
</head>

<body>

	<div class="container">

		<h3>Role Directory</h3>
		<hr>
	<div align="right">

		<div align="right">

			<!-- Add a logout button -->
			<form action="${pageContext.request.contextPath}/logout" method="GET">
			${role} ${lastName} 
				<input type="submit"
					value="Logout" class="btn btn-outline-primary mt-2" />
			</form>

		</div>

		</div>
		<form class="form-inline">
			<a href="${pageContext.request.contextPath}/admin/create_role"
				class="btn btn-primary btn-sm mr-5 mb-3"> Add Role </a>
		</form>


		<table class="table table-bordered table-striped">
			<thead class="thead-dark" align="center">
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Name</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody align="center">
				<c:forEach var="tempRole" items="${roleList}" varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td>${tempRole.id}</td>
					<td>${tempRole.name}</td>
					<td>
						<!-- Add "update" button/link --> 
						<a href="${pageContext.request.contextPath}/admin/update_role?id=${tempRole.id}&name=${tempRole.name}"
						class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
						<a href="${pageContext.request.contextPath}/admin/delete_role?id=${tempRole.id}&name=${tempRole.name}"
						class="btn btn-danger btn-sm"
						onclick="if (!(confirm('Are you sure you want to delete this role?'))) return false">
							Delete </a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
				<!-- Add a redirect to users list button -->
		<form action="${pageContext.request.contextPath}/admin/list_users" method="GET">
			 <input type="submit"
				value="To Users List" class="btn btn-outline-primary mt-2" />

		</form>
	</div>
</body>
</html>