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
            <caption><h2>Student timetable</h2></caption>
            <tr>
                <th>Day of week</th>
                <th>TimeStart</th>
                <th>TimeEnd</th>
                <th>Teacher name</th>
                <th>Student group</th>
            </tr>
            <c:forEach var="studentClass" items="${studentClasses}">
                <tr>
                    <td><c:out value="${studentClass.dayOfWeek.name}" /></td>
                    <td><c:out value="${studentClass.studentClassTime.timeStart}" /></td>
                    <td><c:out value="${studentClass.studentClassTime.timeEnd}" /></td>
                    <td><c:out value="${studentClass.teacher.lastName}" /></td>
                    <td><c:out value="${studentClass.studentGroup.name}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>