<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
    <form action="<spring:url value="/admin/studentPage/${student.id}/edit" />" method="POST">
        <input type="text" name="firstName" value="${student.firstName}" placeholder="First name" required />
        <input type="text" name="lastName" value="${student.lastName}" placeholder="Last name" required />
        <input type="text" name="username" value="${student.username}" placeholder="Username" required/>
        <input type="submit" class="btn" value="Edit"/>
    </form>
</body>
</html>