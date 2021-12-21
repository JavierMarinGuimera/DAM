<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	<div class="container"></div>
	<div class="container">
		<jsp:include page="sections/header.jsp" />

		<%-- TODO Internacionalizar --%>
		<%-- TODO new order view. Show a list of the items in the order passed as param and a list of available items passed as param --%>

		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<span class="glyphicon glyphicon-shopping-cart"></span>
							<spring:message code="newOrder.selected.items" />
							<spring:url var="finishOrderUrl"
								value='/users/orders/newOrder/clearItems' />
							<a href="${finishOrderUrl}"
								class="btn btn-default btn-sm"> <span
								class="glyphicon glyphicon-remove"></span> <spring:message
									code="newOrder.clear" />
							</a>
						</h4>
					</div>
					<div class="panel-body">
						<c:forEach items="${sessionScope.order.items}" var="order">
							<div class="media">
								<div class="media-left media-top">
									<img src='<spring:url value="/images/${order.key.image}"/>'
										class="media-object" style="width: 60px">
								</div>
								<div class="media-body">
									<h4 class="media-heading">
										${order.key.name} <small> x ${order.value} </small> <small>
											${order.value * order.key.price} <spring:message
												code="currency.symbol" />
										</small>
									</h4>
									<p>${order.key.description}</p>
								</div>
								<div class="media-right media-top">
									<a
										href="/orders/users/orders/newOrder/increaseItem?referenciaItem=${order.key.reference}">
										<span class="glyphicon glyphicon-plus-sign"></span>
									</a> <a
										href="/orders/users/orders/newOrder/decreaseItem?referenciaItem=${order.key.reference}">
										<span class="glyphicon glyphicon-minus-sign"></span>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="panel-footer">
						<c:if test="${!empty sessionScope.order.items}">
							<spring:url var="finishOrderUrl"
								value='/users/orders/newOrder/finishOrder' />
							<a href="${finishOrderUrl}"
								class="btn btn-default btn-sm btn-block"> <span
								class="glyphicon glyphicon-check"></span> <spring:message
									code="header.navbar.finish.order" />
							</a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<span class="glyphicon glyphicon-list"></span>
							<spring:message code="newOrder.items" />
						</h4>
					</div>
					<div class="panel-body">

						<c:forEach items="${itemsDisponibles}" var="item">
							<div class="media">
								<div class="media-left media-top">
									<img src='<spring:url value="/images/${item.image}"/>'
										class="media-object" style="width: 60px">
								</div>
								<div class="media-body">
									<h4 class="media-heading">
										${item.name} <small> ${item.price} <spring:message
												code="currency.symbol" />
										</small>
									</h4>
									<p>${item.description}</p>
								</div>
								<div class="media-right media-top">
									<a
										href="/orders/users/orders/newOrder/increaseItem?referenciaItem=${item.reference}">
										<span class="glyphicon glyphicon-plus-sign"></span>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>