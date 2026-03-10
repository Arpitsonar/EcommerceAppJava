<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My orders</title>
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
			<li><a href="#">MyOrders</a></li>
			<li><a href="<c:url value='/user/logout' />">Logout</a></li>
		</menu>
</nav>
<br />
<main class="myorders">
<h1>Here are all your Orders Mr./Miss ${sessionScope.loggedUser.getUsername()}</h1>
<section>
	<c:forEach var="order" items="${orders}">
		<div>
			<h2>${order.getProductName()}</h2>
			<div>Price: &#8377;${order.getAmount()}</div>
			<a href="<c:url value='/order/cancel/${order.getOrderId()}' />"><button class="act-btn">Cancel</button></a>
		</div>
	</c:forEach>
</section>
</main>
</body>
</html>