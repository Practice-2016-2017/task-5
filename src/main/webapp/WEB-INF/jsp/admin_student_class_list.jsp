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
            <caption><h2>Student class list</h2></caption>
            <tr>
                <th>Id</th>
                <th>Day of week</th>
                <th>Time start</th>
                <th>Time end</th>
                <th>Teacher name</th>
                <th>Student group</th>
                <th colspan=2>Action</th>
            </tr>
            <c:forEach var="studentClass" items="${studentClasses}" varStatus="status">
                <tr>
                    <td><c:out value="${studentClass.id}" /></td>
                    <td><c:out value="${studentClass.dayOfWeek.name}" /></td>
                    <td><c:out value="${studentClass.studentClassTime.timeStart}" /></td>
                    <td><c:out value="${studentClass.studentClassTime.timeEnd}" /></td>
                    <td><c:out value="${studentClass.teacher.lastName}" /></td>
                    <td><c:out value="${studentClass.studentGroup.name}" /></td>
                    <td><a href="<spring:url value="/admin/studentClassPage/${studentClass.id}/edit" />">Edit</a> </td>
                    <td>
                        <form action="<spring:url value="/admin/studentClassPage/${studentClass.id}/delete" />" method="POST">
                            <input type="submit" class="btn" value="Delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="<spring:url value="/admin/studentClassPage/add" />">Add student class</a>
    </div>
</body>
</html>