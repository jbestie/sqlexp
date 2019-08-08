<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Editor page</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.css"/>" rel="stylesheet" data-name="vs/editor/editor.main" />
    <script src="<c:url value="/resources/js/tinymce/tinymce.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
    <script>var require = { paths: { 'vs': '<c:url value="/resources/js/monaco/min/vs"/>' } };</script>
    <script src="<c:url value="/resources/js/monaco/min/vs/loader.js"/>"></script>
    <script src="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.nls.js"/>"></script>
    <script src="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.js"/>"></script>
    <script>
        tinymce.init({
            selector: '#description',
            theme: 'modern',
            plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern help',
            toolbar1: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat',
            image_advtab: true,
            height: "170",
            resize: false
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var editor = monaco.editor.create(document.getElementById('queryToExecute'), {
                value: [
                    '--- correct query here ', '', '', ''
                ].join('\n'),
                language: 'sql',
                minimap: {
                    enabled: false
                },
                contentWidth: 700
            });

            $('#createTask').click(function (e) {
                $("#query").text(editor.getValue());
                $("#task").submit();
            });

            $('#executeQuery').click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "GET",
                    url: "randomQuery",
                    data: {
                        query: editor.getValue()
                    },
                    success: function (result) {

                        if (!result.correct) {
                            $("#queryResult").html(result.message);
                            return;
                        }

                        var resultString = "<table class=\"result_table\">";

                        var columnsResult = result.queryResult.columnNames;
                        var columnsLength = columnsResult.length;

                        // head
                        resultString += "<tr>";
                        for (var i = 0; i < columnsLength; i++) {
                            resultString += "<th class=\"result_table\">" + columnsResult[i] + "</th>";
                        }
                        resultString += "</tr>";
                        // end head

                        // content
                        var resultRows = result.queryResult.resultSet;
                        var rowsCount = resultRows.length;

                        for (i = 0; i < rowsCount; i++) {
                            var resultColumns = resultRows[i];
                            var colsCount = resultColumns.length;
                            resultString += "<tr>";

                            for (var j = 0; j < colsCount; j++) {
                                resultString += "<td class=\"result_table\">";
                                resultString += resultColumns[j];
                                resultString += "</td>";
                            }

                            resultString += "</tr>";
                        }
                        // end content

                        $("#queryResult").html(resultString);
                    },
                    error: function (result) {
                        alert('error');
                    }
                });
            })
        });
    </script>
    <title></title>
</head>

<body>
<h1 class="main">SqlExp questions editor</h1>
<form:form method="post" autocomplete="false" modelAttribute="task" action="createTask">
    <label class="registration_label">Category:</label><form:select cssClass="editor_category" path="category" multiple="false">
    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
</form:select>
    <label class="registration_label">Task name:</label><form:input type="text" path="name" class="task_name"/><br/>
    <form:errors path="category"/><form:errors path="name"/>
    <fieldset class="editor_task_description">
        <legend class="question_title">Question description</legend>
        <form:textarea path="description"/><br/>
    </fieldset>
    <div class="editor_two_panel_query">
        <div id="queryToExecute" class="editor_query"></div>
        <div class="button_to_execute_container">
            <input type="button" class="editor_run_query" value="Test query" id="executeQuery"/><br/>
        </div>
        <div class="editor_result_side">
            <div id="queryResult" class="editor_query_result query_result"></div>
        </div>
    </div>
    <form:hidden path="query"/>
    <form:errors path="query"/><br/>
    <div class="create_task_button">
        <input type="button" value="Create task" id="createTask" class="create_task_button">
    </div>
</form:form>
</body>
</html>