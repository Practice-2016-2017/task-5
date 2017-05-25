<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <form:form action="/admin/teacherPage/add" modelAttribute="user" method="POST">
        <input type="text" name="firstName" placeholder="First name" required />
        <input type="text" name="lastName"  placeholder="Last name" required />
        <input type="text" name="username"  placeholder="Username" required/>
        <input type="text" name="password" placeholder="Password" required/>
        <input type="submit" class="btn" value="Add"/>
    </form:form>
</body>
</html>