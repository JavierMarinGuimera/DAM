<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="sections/head.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="sections/header.jsp" />

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<spring:message code="login.give.credentials" />
				</h3>
			</div>
			<div class="panel-body">
				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="login.credentials.failure" />
					</div>
				</c:if>
				<form action="<c:url value= "/j_spring_security_check"> </c:url>"
					method="post">
					<fieldset>
						<div class="form-group">
							<spring:message code="login.username" var="username" />
							<input class="form-control" placeholder="${username}"
								name="j_username" type="text">
						</div>
						<div class="form-group">
							<spring:message code="login.password" var="password" />
							<input class="form-control" placeholder="${password}"
								name="j_password" type="password">
						</div>
						<spring:message code="login.login" var="login" />
						<input class="btn btn-lg btn-success btn-block" type="submit"
							value="${login}">
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>