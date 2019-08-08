<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Create SQL question category page</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <title></title>
    </head>

    <body>
        <h1 class="main">SqlExp questions editor</h1>
        <form:form method="post" autocomplete="false" modelAttribute="category" action="createCategory">
            <label class="registration_label">Category name:</label><form:input type="text" path="name"/>
            <form:errors path="name"/><br/>
            <input type="submit">
        </form:form>
    </body>
</html>