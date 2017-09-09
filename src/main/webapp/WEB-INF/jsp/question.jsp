<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>

<html>
    <head>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
        <script type="text/javascript">
            $(document).ready(function(){              
                // вешаем на клик по элементу с id = example-1
                $('#executeQuery').click(function(e){
                    e.preventDefault();
                    $.ajax({
                        type: "GET",
                        url: "submit",
                        data: { 
                            questionId: $("#questionId").val(),
                            query: $("#query").val() 
                        },
                        success: function(result) {
                            
                            if (result.correct) {
                                $("#nextQuestion").show();
                                $("#success_message").text(result.message);
                                $("#error_message").text("");
                            } else {
                                $("#success_message").text("");
                                $("#error_message").text(result.message);
                            }
                            
                            $("#queryResult").text("result");
                        },
                        error: function(result) {
                            alert('error');
                        }
                    });
                }) 
            }); 
        </script>
    </head>
    <body>
        <div>
            <div class = "question_left">
                <h5>Questions</h5>
                <ul>
                    <li>Question 1</li>
                    <li>Question 2</li>
                    <li>Question 3</li>
                    <li>Question 4</li>
                    <li>Question 5</li>
                    <li>Question 6</li>
                </ul>
            </div>
            <div class = "question_right">
                <fieldset>
                    <legend class="question_title">Question 1: Get all employees</legend>
                    ${description}<br/>
                        <input type = "hidden" id = "questionId" name = "questionId" value = "1"/>
                        <textarea id = "query" class="query" name = "query"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;<textarea id="queryResult" readonly class="query_result"></textarea><br/>
                        <label id="error_message" class="error_message"></label><label id="success_message" class="success_message"></label><br/>
                        <input type="button" value="Run query" id = "executeQuery"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="next_question" value="Next question" id = "nextQuestion"/>
                </fieldset>
            </div>
        </div>
    </body>
</html>
