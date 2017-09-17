<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
    
    <body>
        <h1 class="main">REGISTRATION</h1><br/>
        <form:form method="post" autocomplete="false" modelAttribute="userForm" action="createuser">
            <label class="registration_label">Login:</label><form:input type="text" path = "login"/><br/>
            <form:errors path="login" /><br/>
            <label class="registration_label">Password:</label><form:input type="password" path = "password"/><br/>
            <form:errors path="password" /><br/>
            <label class="registration_label">Email:</label><form:input type="text" path = "email"/><br/>
            <form:errors path="email" /><br/>
            <input type = "submit">
            <!-- TODO CAPTCHA!!!! -->
        </form:form>
    </body>
</html>