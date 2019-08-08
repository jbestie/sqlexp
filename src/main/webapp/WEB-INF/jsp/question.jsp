<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Question page</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.css"/>" rel="stylesheet" data-name="vs/editor/editor.main" />
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
    <script>var require = { paths: { 'vs': '<c:url value="/resources/js/monaco/min/vs"/>' } };</script>
    <script src="<c:url value="/resources/js/monaco/min/vs/loader.js"/>"></script>
    <script src="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.nls.js"/>"></script>
    <script src="<c:url value="/resources/js/monaco/min/vs/editor/editor.main.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var editor = monaco.editor.create(document.getElementById('query'), {
                value: [
                    "", "", "", ""
                ].join('\n'),
                language: 'sql',
                minimap: {
                    enabled: false
                }
            });


            $('#executeQuery').click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "GET",
                    url: "submit",
                    data: {
                        id: $("#id").val(),
                        query: editor.getValue()
                    },
                    success: function (result) {

                        if (result.correct) {
                            $("#nextQuestion").show();
                            $("#success_message").text(result.message);
                            $("#error_message").text("");
                        } else {
                            $("#success_message").text("");
                            $("#error_message").text(result.message);
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
                        alert('error ' + result.message);
                    }
                });
            })
        });
    </script>
    <title></title>
</head>
<body>
<div>
    <div class="question_left">
        <h5>Questions</h5>
        <ul>
            <c:forEach items="${questionList}" var="item" varStatus="loop">
                <li><a href="<c:url value="/question?questionId=${loop.index + 1}"/>"><c:out value="${item}"/></a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="question_right">
        <fieldset>
            <legend class="question_title">${task.name}</legend>
            ${task.description}<br/>
            <input type="hidden" id="id" name="id" value="1"/>
            <div>
                <div class="inner_question_left">
                    <label for="query"></label><div id="query" class="query"></div>
                </div>
                <div id="queryResult" class="inner_question_right query_result"></div>
                <br/>
                <div class="spacer" style="clear: both;"></div>
            </div>
            <label id="error_message" class="error_message"></label><label id="success_message"
                                                                           class="success_message"></label><br/>
            <input type="button" value="Run query" id="executeQuery"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
                                                                                                     class="next_question"
                                                                                                     value="Next question"
                                                                                                     id="nextQuestion"/>
        </fieldset>
    </div>
</div>
</body>
</html>
