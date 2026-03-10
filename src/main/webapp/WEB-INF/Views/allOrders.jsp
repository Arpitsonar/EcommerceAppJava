<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Orders</title>
<link href="<c:url value='/public/styles.css' />" rel="stylesheet"/>
</head>
<body>
<nav>
		<span>Company</span>
		<label for="ch">
			<div></div>
			<div></div>
			<div></div>
		</label><input type="checkbox" id="ch" />
		<menu>
			<li><a href="<c:url value='/' />">Home</a></li>
			<li><a href="<c:url value='/products' />">Products</a></li>
			<c:if test="${sessionScope.loggedUser == null}">
				<li><a href="<c:url value='/user/login' />">Login</a></li>
				<li><a href="<c:url value='/user/register' />">Register</a></li>
			</c:if>
			<c:choose>
				<c:when test='${sessionScope.level.equals("CUSTOMER")}'>
					<li><a href="<c:url value='/order/myorders' />">MyOrders</a></li>
					<li><a href="<c:url value='/user/logout' />">Logout</a></li>
				</c:when>
				<c:when test='${sessionScope.level.equals("ADMIN")}'>
					<li><a href="<c:url value='/user/all' />" >Users</a></li>
					<li><a href="<c:url value='/order/all' />" >Orders</a></li>
					<li><a href="<c:url value='/user/logout' />">Logout</a></li>
				</c:when>
				<c:when test='${sessionScope.level.equals("MANAGER")}'>
					<li><a href="<c:url value='/user/all' />" >Users</a></li>
					<li><a href="<c:url value='/order/all' />" >Orders</a></li>
					<li><a href="<c:url value='/user/logout' />">Logout</a></li>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
			<li style="color:blue">${sessionScope.loggedUser.getUsername()}</li>
		</menu>
	</nav>
<main class="myorders">
<h1>Here are all orders till date</h1>
<section>
	<c:set var="total" value="0" />
	<c:forEach var="order" items="${orders}">
		<div>
			<h2>${order.getProductName()} - <i><u>${order.getUser().getUsername()}</u></i></h2><br />
			<div>Ordered on : ${order.getDate()}</div>
			<div>Price: &#8377; ${order.getAmount()} /-</div>
			
		</div>
		<c:set var="total" value="${total + order.getAmount()}" />
	</c:forEach>
</section>
<h2>Total Revenue : &#8377; ${total}/-</h2>
</main>
</body>
</html>