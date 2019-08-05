<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Question page</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#executeQuery').click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "GET",
                    url: "submit",
                    data: {
                        id: $("#id").val(),
                        query: $("#query").val()
                    },
                    success: function (result) {

                        if (result.correct) {
                            $("#nextQuestion").show();
                            $("#success_message").text(result.message);
                            $("#error_message").text("");
                        } else {
                            $("#success_message").text("");
                            $("#error_message").text(result.message);
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
                    <label for="query"></label><textarea id="query" class="query" name="query"></textarea>
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
