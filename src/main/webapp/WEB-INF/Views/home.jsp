<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company</title>
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
			<li><a style="color:yellow;"><u>${sessionScope.loggedUser.getUsername()}</u></a></li>
		</menu>
	</nav>
	<main class="home">
		<h2>Our products</h2>
		<section>
			<c:forEach var="product" items="${top3}">
				<div class="product-div" onClick='window.location.assign("products/${product.getProductId()}")'>
					<h3>${product.getProductName()}</h3>
					<img src="" alt="" />
					<p>Price : ${product.getPrice()}</p>
				</div>
			</c:forEach>
		</section>
		<div style="text-align:center;"><a href="<c:url value='/products' />"><button class="act-btn">View All</button></a></div>
	</main>
</body>
</html>