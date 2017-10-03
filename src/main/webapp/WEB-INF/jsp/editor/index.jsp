<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
    
    <body>
        <h1 class="main">Task editor</h1>
        <ul>
            <c:forEach items="${taskList}" var="item" varStatus="loop">
              <li><a href="<c:url value="/editor/edit?id=${item.id}"/>"><c:out value="${item.name}"/></a></li>
            </c:forEach>
        </ul>
    </body>
</html>