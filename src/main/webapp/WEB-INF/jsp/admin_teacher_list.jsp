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
    <div align="center">
        <table class="table table-bordered table-striped">
            <caption><h2>Teachers list</h2></caption>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Enabled</th>
                <th colspan=2>Action</th>
            </tr>
            <c:forEach var="teacher" items="${teachers}" varStatus="status">
                <tr>
                    <td><c:out value="${teacher.id}" /></td>
                    <td><c:out value="${teacher.username}" /></td>
                    <td><c:out value="${teacher.firstName}" /></td>
                    <td><c:out value="${teacher.lastName}" /></td>
                    <td><c:out value="${teacher.enabled}" /></td>
                    <td><a class="btn btn-info" href="<spring:url value="/admin/teacherPage/${teacher.id}/edit" />">Edit</a> </td>
                    <td>
                        <form action="<spring:url value="/admin/teacherPage/${teacher.id}/delete" />" method="POST">
                            <button type="submit" class="btn btn-danger">Del</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a class="btn btn-primary" href="<spring:url value="/admin/teacherPage/add" />">Add teacher</a>
    </div>
</body>
</html>