<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company-login</title>
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
			<li><a href="<c:url value='/user/register' />">Register</a></li>
		</menu>
	</nav>
	<main class="login">
		<section>
		<h2>Login Form</h2>
		<c:if test="${error != null}">
			<div class="error">${error}</div>
		</c:if>
			<form method="POST">
				<input type="text" name="username" placeholder="Enter Username" /><br />
				<input type="password" name="password" placeholder="Enter Password" /><br />
				<div style="text-align:center;"><button class="act-btn">Login</button></div>
			</form>
			<br />
			<br />
			<a href="register">New User? Register now !</a>
		</section>
	</main>
</body>
</html>