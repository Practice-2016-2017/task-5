<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <form:form action="/teacher/timetable/add" modelAttribute="studentClass" method="POST">
        <table>
            <tr>
                <td><form:label path="dayOfWeek">Day of week</form:label></td>
                <td><form:select path="dayOfWeek"
                    items="${dayOfWeekList}" itemValue="id" itemLabel="name"/></td>
            </tr>
            <tr>
                <td><form:label path="studentClassTime">Class number</form:label></td>
                <td><form:select path="studentClassTime"
                    items="${studentClassTimeList}" itemValue="id" itemLabel="id" /> </td>
            </tr>
            <tr>
                <td><form:label path="studentGroup">Student group</form:label></td>
                <td><form:select path="studentGroup"
                 items="${studentGroupList}" itemValue="id" itemLabel="name" /> </td>
            </tr>
        </table>
        <input type="submit" class="btn btn-primary" value="Add"/>
    </form:form>
</body>
</html>