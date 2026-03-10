<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
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
	</menu>
</nav>
<main>
	<section class="product-form">
	<c:if test="${error != null}">
		<div class="error">${error}</div>
	</c:if>
		<form method="POST">
			<label>Product</label><br />
			<input name="productName" type="text" maxlength="30" value="${product.getProductName()}" required /><br/>
			<label>Price</label><br />
			<input name="price" type="number" value="${product.getPrice()}" required /><br />
			<label>Stock</label><br />
			<input name="stock" type="number" value="${product.getStock()}" required /><br />
			<label>Pieces Sold</label><br />
			<input name="sales" type="number" value="${product.getSales()}" readOnly /><br />
			<label>Description</label><br />
			<textarea rows="5" cols="25" name="description" required>${product.getDescription()}</textarea><br />
			<button class="act-btn">Done !</button>
		</form>
			
	</section>
</main>
</body>
</html>