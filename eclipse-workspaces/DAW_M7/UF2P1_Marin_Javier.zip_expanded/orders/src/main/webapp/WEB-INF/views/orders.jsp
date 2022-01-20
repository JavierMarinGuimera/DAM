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
<script>
	
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="sections/header.jsp" />

		<%-- TODO Internacionalizar --%>
		<%-- TODO if user has ROLE_ADMIN -> Show all orders of all users and let change state and delivery date --%>
		<%-- <c:if test="${}">
			<div class="alert alert-danger">Invalid credentials </div>
		</c:if> --%>
		<%-- TODO if user has ROLE_USER -> Show all orders of the user --%>

		<table class="table table-hover">
			<thead>
				<tr>
					<td><spring:message code="orders.client" /></td>
					<td><spring:message code="orders.reference" /></td>
					<td><spring:message code="orders.deliveryAddress" /></td>
					<td><spring:message code="orders.startDate" /></td>
					<td><spring:message code="orders.state" /></td>
					<td><spring:message code="orders.deliveryDate" /></td>
					<td><spring:message code="orders.details" /></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td>${order.client.firstName}${order.client.lastName}</td>
						<td>${order.reference}</td>
						<td>${order.deliveryAddress.recipientName}<br>
							${order.deliveryAddress.address} <br>
							${order.deliveryAddress.zipCode} ${order.deliveryAddress.city} <br>
							${order.deliveryAddress.state} <br>
							${order.deliveryAddress.country}
						</td>
						<td>${order.startDate}</td>
						<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
							<td style="width: 15%;">
								<form class="form-horizontal" action="/orders/admin/orders/setState"
									method="get">
									<select name="state"
										onchange="$(this).closest('form').submit();">
										<c:forEach items="${states}" var="state" varStatus="status">
											<option value="${status.index}" ${order.state == status.index? 'selected="selected"':''}><spring:message
													code="${state}" /></option>
										</c:forEach>
									</select> <input type="text" name="referenciaItem"
										value="${order.reference}" class="hidden"/>
								</form>
							</td>
							<td>
								<form class="form-horizontal"
									action="/orders/admin/orders/setDeliveryDate" method="get">
									<input type="date" name="deliveryDate" onchange="$(this).closest('form').submit();" />
									<input type="text" name="referenciaItem" value="${order.reference}" class="hidden"/>
								</form>
							</td>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROLE_USER')">
							<td><c:choose>
									<c:when test="${order.state == 0}">
										<td style="width: 15%;"><spring:message
												code="${states[0]}" /></td>
									</c:when>
									<c:when test="${order.state == 1}">
										<td style="width: 15%;"><spring:message
												code="${states[1]}" /></td>
									</c:when>
									<c:when test="${order.state == 2}">
										<td style="width: 15%;"><spring:message
												code="${states[2]}" /></td>
									</c:when>
									<c:when test="${order.state == 3}">
										<td style="width: 15%;"><spring:message
												code="${states[3]}" /></td>
									</c:when>
									<c:when test="${order.state == 4}">
										<td style="width: 15%;"><spring:message
												code="${states[4]}" /></td>
									</c:when>
									<c:otherwise>
										<td style="width: 15%;"><spring:message
												code="${states[5]}" /></td>
									</c:otherwise>
								</c:choose></td>
							<td>${order.deliveryDate}</td>
						</sec:authorize>
						<td>
							<table class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th><spring:message code="finishOrder.reference" /></th>
										<th><spring:message code="finishOrder.item" /></th>
										<th><spring:message code="finishOrder.price" /></th>
										<th><label for="totalQuantity"><spring:message
													code="finishOrder.quantity" /> </label></th>
										<th><spring:message code="finishOrder.amount" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order.items}" var="item">
										<tr>
											<td>${item.key.reference}</td>
											<td>${item.key.name}</td>
											<td>${item.key.price}<spring:message
													code="currency.symbol" /></td>
											<td>${item.value}</td>
											<td>${item.value * item.key.price}<spring:message
													code="currency.symbol" /></td>
										</tr>
									</c:forEach>

									<tr>
										<th colspan="3" style="text-align: right"><spring:message
												code="orders.total" /></th>
										<th><label for="totalQuantity">
												${order.totalQuantity} </label></th>
										<th>${order.totalAmount}<spring:message
												code="currency.symbol" /></th>
									</tr>

								</tbody>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
