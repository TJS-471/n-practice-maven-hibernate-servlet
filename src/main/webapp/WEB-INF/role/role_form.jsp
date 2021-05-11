<html lang="en" xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<title>Role Directory</title>

</head>
<body>
	<div class="container">
		<h3>Role Directory</h3>
		<hr>
		<p class="h4 mb-4">
		<c:if test="${role == null}">Save Role</c:if>
		<c:if test="${role != null}">Edit Role</c:if>
		</p>
		
		<c:if test="${role != null}">
			<form action="${pageContext.request.contextPath}/admin/update_role"
				method="post">
		</c:if>
		
		<c:if test="${role == null}">
			<form action="${pageContext.request.contextPath}/admin/create_role"
				method="post">
		</c:if>
		
		<!-- Add a hidden form field to handle update -->
		<input type="hidden" name="id" value="${role.id}"> 
		<input type="text" name="name" value="${role.name}"
		class="form-control mb-4 col-4" placeholder="Role Name" required="required">
		<button type="submit" class="btn btn-info col-2">Save</button>
		</form>
		<hr>
		<a href="${pageContext.request.contextPath}/admin/list_roles">Back
			to Role LIst</a>
	</div>
</body>
</html>