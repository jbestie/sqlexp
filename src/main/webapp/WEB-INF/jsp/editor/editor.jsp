<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/tinymce/tinymce.min.js" />"></script>
        <script>
            tinymce.init({
                selector: '#questionName',
				theme: 'modern',
				plugins: 'print preview fullpage searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern help',
				toolbar1: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat',
				image_advtab: true
            });
    </script>
    </head>

    <body>
        <h1 class="main">SqlExp questions editor</h1>
        <form:form method="post" autocomplete="false" modelAttribute="task" action="editor/createtask">
            <label class="registration_label">Category:</label><form:select path="category" items="${categories}" /><br/>
            <form:errors path="category" /><br/>
            <label class="registration_label">Task name:</label><form:input type="text" path = "name"/><br/>
			<form:errors path="name" /><br/>
            <label class="registration_label">Correct query:</label><form:input type="text" path = "query"/><br/>
			<form:errors path="query" /><br/>
			<fieldset>
				<legend class="question_title">Question description</legend>
				<form:textarea path="description">Put description here</form:textarea><br/>
			</fieldset>
			<input type = "submit">
        </form:form>
    </body>
</html>