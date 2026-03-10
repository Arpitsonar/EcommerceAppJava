<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Users</title>
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
<main class="all-users">
<h1>All Users...</h1>
<section>
	<c:forEach var="user" items="${users}">
		<div class="user">
			<h2>${user.getUsername()}</h2>
			<div>This user is a ${user.getUserlevel()}</div>
			<c:if test='${user.getUserlevel().equals("CUSTOMER")}'>
			<div>Orders: ${user.getOrders()}</div>
			<div>Income: ${user.getIncome()}</div>
			</c:if>
			<a href="<c:url value='/user/delete/${user.getUserId()}' />"><button class="act-btn">Delete this user</button></a>
		</div>
	</c:forEach>
</section>
</main>
</body>
</html>