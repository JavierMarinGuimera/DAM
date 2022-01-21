<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message var="currencySymbol" code="currency.symbol" scope="session" />
<fmt:setLocale value="${pageContext.response.locale}" />
<div class="jumbotron">
	<h1>
		<spring:message code="header.title" />
	</h1>
	<p>
		<spring:message code="header.subtitle" />
	</p>
</div>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<sec:authorize access="isAuthenticated()">
				<sec:authentication var="user" property="principal" />
				<a class="navbar-brand" href="#"> ${user.firstName} ${user.lastName} </a>
			</sec:authorize>
		</div>

		<ul class="nav navbar-nav">
			<sec:authorize access="hasAnyRole('ROLE_USER')">
				<spring:url var="ordersUrl" value='/users/orders/' />
				<li
					class="${requestScope['javax.servlet.forward.request_uri'] eq ordersUrl?'active':''}">
					<a href="${ordersUrl}">
						<span class="glyphicon glyphicon-th-list"></span>
						<spring:message code="header.navbar.my.orders" />
					</a>
				</li>
				<spring:url var="newOrderUrl" value='/users/orders/newOrder' />
				<li
					class="${requestScope['javax.servlet.forward.request_uri'] eq newOrderUrl?'active':''}">
					<a href="${newOrderUrl}">
						<span class="glyphicon glyphicon-plus"></span>
						<spring:message code="header.navbar.new.order" />
					</a>
				</li>
				<c:if test="${!empty sessionScope.order.items}">
					<spring:url var="finishOrderUrl" value='/users/orders/newOrder/finishOrder' />
					<li
						class="${requestScope['javax.servlet.forward.request_uri'] eq finishOrderUrl?'active':''}">
						<a href="${finishOrderUrl}">
							<span class="glyphicon glyphicon-th-large"></span>
							<spring:message code="header.navbar.finish.order" />
						</a>
					</li>
				</c:if>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
				<spring:url var="ordersUrl" value='/admin/orders/' />
				<li
					class="${requestScope['javax.servlet.forward.request_uri'] eq ordersUrl?'active':''}">
					<a href="${ordersUrl}">
						<span class="glyphicon glyphicon-th-list"></span>
						<spring:message code="header.navbar.orders" />
					</a>
				</li>
			</sec:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<a href="#" data-toggle="dropdown">
					<span class="glyphicon glyphicon-flag"></span>
					<spring:message code="header.navbar.language" />
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li class="${pageContext.response.locale.language eq 'es'?'disabled':''}">
						<a href="?language=es">
							<spring:message code="header.navbar.language.spanish" />
						</a>
					</li>
					<li class="${pageContext.response.locale.language eq 'en'?'disabled':''}">
						<a href="?language=en">
							<spring:message code="header.navbar.language.english" />
						</a>
					</li>
				</ul>
			</li>
			<sec:authorize access="isAuthenticated()">
				<li>
					<a href="<spring:url value='/logout'/>">
						<span class="glyphicon glyphicon-log-out"></span>
						<spring:message code="header.navbar.logout" />
					</a>
				</li>
			</sec:authorize>
		</ul>
	</div>
</nav>

