<%-- 
    Document   : questionsToDo
    Created on : Oct 24, 2023, 11:41:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/questionsToDo.css"/>
    </head>
    <body>
        <ul class="questionsToDo container">
            <div class="questionsToDo__title">
                <h2>${requestScope.testInfo.testName} - ${requestScope.testInfo.lecturerID.toUpperCase()}</h2>
            </div>
            <c:set var="i" value="${0}"/>
            <form action="doTest" method="post">
                <input type="hidden" name="service" value="submitTest"/>
                <input type="hidden" name="studentID" value="${student.studentID}"/>
                <c:forEach items="${requestScope.listQuestionToDo}" var="item">
                    <li class="questionsToDo__item">
                        <input type="hidden" name="questionID${String.format('%02d', i + 1)}" value="${requestScope.listQuestionToDo.get(i).getQuestionID()}"/>
                    <c:set var="i" value="${i + 1}"/>
                    <p><b>Question ${i}:</b> ${item.getContent()}</p>
                    <input 
                        name="answer${String.format('%02d', i)}" 
                        type="radio" 
                        value="1"
                        /><span>A. ${item.getChoice1()}</span>
                    <br/>
                    <input 
                        name="answer${String.format('%02d', i)}" 
                        type="radio" 
                        value="2"
                        /><span>B. ${item.getChoice2()}</span>
                    <br/>
                    <input 
                        name="answer${String.format('%02d', i)}" 
                        type="radio"
                        value="3"
                        /><span>C. ${item.getChoice3()}</span>
                    <br/>
                    <input 
                        name="answer${String.format('%02d', i)}" 
                        type="radio"
                        value="4"
                        /><span>D. ${item.getChoice4()}</span>
                    <br/>
                    </li>
                </c:forEach>
                <input type="hidden" name="testID" value="${requestScope.testInfo.testID}"/>    
                <input type="hidden" name="numberOfQuestion" value="${requestScope.listQuestionToDo.size()}"/>
                <input type="submit" value="SUBMIT"/>
            </form>
        </ul>
    </body>
</html>
