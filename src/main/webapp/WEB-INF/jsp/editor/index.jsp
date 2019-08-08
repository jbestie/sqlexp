<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Task editor</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/editor_table.css" />" rel="stylesheet">
    <title></title>
</head>

<body>
<h1 class="main">Task editor</h1>
<div class="creation_links">
    <a href="create_category">Create category</a>
    <a href="create">Create new task</a>
</div>

<table class="blueTable">
    <thead>
    <tr>
        <th>Task name</th>
        <th>Task category</th>
        <th>Action</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <td colspan="3">
            <div class="links"><a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a></div>
        </td>
    </tr>
    </tfoot>
    <tbody>
    <tr>
        <c:forEach items="${taskList}" var="item" varStatus="loop">
            <td><b><c:out value="${item.name}"/></b></td>
            <td><b><c:out value="${categories[item.category].name}"/></b></td>
            <td><a href="<c:url value="/editor/edit?id=${item.id}"/>">Edit</a></td>
        </c:forEach>
    </tr>
    </tbody>
</table>
</body>
</html>