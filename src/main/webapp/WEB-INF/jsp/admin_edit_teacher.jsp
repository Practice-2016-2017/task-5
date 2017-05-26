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
    <form:form action="/admin/teacherPage/${user.id}/edit" modelAttribute="user" method="POST">
        <div class="form-group">
            <label for="firstName">First name</label>
            <input type="text" class="form-control" name="firstName" value="${user.firstName}"
             placeholder="First name" required />
        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <input type="text" class="form-control" name="lastName" value="${user.lastName}"
             placeholder="Last name" required />
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" value="${user.username}"
              placeholder="Username" required/>
        </div>
        <input type="submit" class="btn btn-primary" value="Edit"/>
    </form:form>

    <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>