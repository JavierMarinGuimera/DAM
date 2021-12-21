<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Users</title>
</head>
<body>
	<div class="container">
		<h1>Users</h1>
		<table class="table">
			<tr>
				<th>Username</th>
				<th>Name</th>
				<th>Email</th>
				<th>Rank</th>
			</tr>
			<c:forEach var="user" items="${requestScope.users}">
				<tr>
					<td>${user.username}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.rank}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
