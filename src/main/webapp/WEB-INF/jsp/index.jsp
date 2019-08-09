<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sql Exp</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <title></title>
</head>

<body>
<c:set value='${sessionScope["username"]}' var="username"/>
<div class = "top_index_login_register">
    <c:if test="${empty username}">
        <a href="login">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="registration">Registration</a><br/>
    </c:if>
    <c:if test="${not empty username}">
        <label>You're logged in as <b><c:out value="username"/></b></label> &nbsp; <a href="logout">Logout</a><br/>
        <h3>Select SQL quiz category</h3>
    </c:if>
</div>
<c:if test="${not empty username}">
    <div class = "index_categories_container">
        <c:forEach items="${categories}" var="item">
            <div class = "index_categories_item">
                <a href="<c:url value="/questions?categoryId=${item.id}"/>"><c:out value="${item.name}"/></a>
            </div>
        </c:forEach>
    </div>
</c:if>
</body>
</html>