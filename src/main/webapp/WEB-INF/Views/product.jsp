<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.getProductName()}</title>
<link href='<c:url value="/public/styles.css" />' rel="stylesheet" />
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
	</menu>
</nav>

<main>
<section class="one-product">
	<h2>${product.getProductName()}</h2><br />
	<p>${product.getDescription()}, this product is being delivered and shipped by Company.org</p><br />
	<img src="" alt="" />
	<c:if test="${product.getStock() > 2}">
		<div>${product.getSales()} pieces already Sold, what are you waiting for</div>
		<div style="color: green;">In Stock</div>
		<div><a href="<c:url value='/order/${product.getProductId()}' />"><button class="act-btn">Buy Now !</button></a></div>
	</c:if>
	<c:if test="${product.getStock() < 2}">
		<div style="color: red">Out of Stock (Please visit again after some time)</div>
	</c:if>
	<c:if test='${level == "ADMIN" || level == "MANAGER"}'>
		<div>
			<a href="<c:url value='/products/edit/${product.getProductId()}' />" ><button class="edit-btn">Edit</button></a>
			<a href="<c:url value='/products/delete/${product.getProductId()}' />" ><button class="remove-btn">Remove</button></a>
		</div>
	</c:if>
</section>
</main>
</body>
</html>