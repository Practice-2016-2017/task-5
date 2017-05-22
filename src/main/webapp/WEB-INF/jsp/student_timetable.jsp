<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
    <div align="center">
        <table border="1" cellpadding="5">
                    <caption><h2>Student timetable</h2></caption>
                    <tr>
                        <th>Id</th>
                        <th>TimeStart</th>
                        <th>TimeEnd</th>
                        <th>DayOfWeek</th>
                        <th>Teacher name</>
                    </tr>
                    <c:forEach var="studentClass" items="${studentClasses}">
                        <tr>
                            <td><c:out value="${studentClass.id}" /></td>
                            <td><c:out value="${studentClass.timeStart}" /></td>
                            <td><c:out value="${studentClass.timeEnd}" /></td>
                            <td><c:out value="${studentClass.dayOfWeek}" /></td>
                            <td><c:out value="${studentClass.teacher.username}" /></td>
                        </tr>
                    </c:forEach>
                </table>
    </div>
</body>
</html>