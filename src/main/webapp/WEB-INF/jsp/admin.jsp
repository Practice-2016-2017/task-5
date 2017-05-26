<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
<div class="container">
<div class="row">
<ul class="nav navbar-nav">
    <li><a href="<spring:url value="/admin/studentPage" />">Student page</a></li>
    <li><a href="<spring:url value="/admin/teacherPage" />">Teacher page</a></li>
    <li><a href="<spring:url value="/admin/studentClassPage" />">Student class page</a></li>
    </ul>
</div>
</div>

    <div class="right">
        <a href="<c:url value="/logout" />">Logout</a>
    </div>
</body>
</html>