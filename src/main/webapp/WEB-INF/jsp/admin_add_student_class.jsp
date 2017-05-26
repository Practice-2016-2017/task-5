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
    <form:form action="/admin/studentClassPage/add" modelAttribute="studentClass" method="POST">
        <div class="form-group">
            <form:label path="dayOfWeek">Day of week</form:label>
            <form:select path="dayOfWeek"
                items="${dayOfWeekList}" class="form-control" itemValue="id" itemLabel="name"/>
        </div>
        <div class="form-group">
            <form:label path="studentClassTime">Class number</form:label>
            <form:select path="studentClassTime" class="form-control"
                items="${studentClassTimeList}" itemValue="id" itemLabel="id" />
        </div>
        <div class="form-group">
            <form:label path="teacher">Teacher name</form:label>
            <form:select path="teacher" class="form-control"
                items="${teacherList}" itemValue="id" itemLabel="lastName" />
        </div>
        <div class="form-group">
            <form:label path="studentGroup">Student group</form:label>
            <form:select path="studentGroup" class="form-control"
             items="${studentGroupList}" itemValue="id" itemLabel="name" />
        </div>

        <input type="submit" class="btn btn-primary" value="Add"/>
    </form:form>

    <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>