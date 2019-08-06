<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Sql Exp</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <title></title>
    </head>
    
    <body>
        <h1 class="main">Welcome to Sql Experience site!</h1>
        <c:set value='${sessionScope["username"]}' var="username"/>
        <div class = "top_index_login_register">
            <c:if test="${empty username}">
                <a href="login">Login</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="registration">Registration</a><br/>
            </c:if>
            <c:if test="${not empty username}">
                <label>You're logged in as <b><c:out value="username"/></b></label> &nbsp; <a href="logout">Logout</a><br/>
            </c:if>
        </div>
        <c:if test="${not empty username}">
            <a href="question?questionId=1">SQL Simple SELECT</a><br/>
        </c:if>
    </body>
</html>