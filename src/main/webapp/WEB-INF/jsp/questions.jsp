<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Questions</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#backToList').click(function () {
                window.location.href = '<c:url value="/"/>';
            });
        });
    </script>
    <title></title>
</head>
<body>
<div class="questions_container">
    <div class="question_left">
    </div>
    <div class="question_right">
        <h5>Questions</h5>
        <ul>
            <c:forEach items="${tasks}" var="task">
                <li><a href="<c:url value="/question?questionId=${task.id}"/>"><c:out value="${task.name}"/></a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="editor_bottom_nav_buttons">
        <div class="left_nav_button">
            <input type="button" value="Back" id="backToList" class="create_task_button">
        </div>
    </div>
</div>
</body>
</html>
