<%-- 
    Document   : viewQuestion
    Created on : Oct 19, 2023, 1:55:15 PM
    Author     : admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/viewQuestion.css"/>
    </head>
    <body>
        <c:if test="${requestScope.listQuestion.size() > 0}">
            <ul class="listQuestion container">
                <div class="listQuestion__title">
                    <h2>Test ID: ${requestScope.listQuestion.get(0).getTestID()}</h2>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item"><a href="manageTest">Manage Test</a></li>
                            <li class="breadcrumb-item"><a href="manageResult?service=resultOfCourse">View Result</a></li>
                            <li class="breadcrumb-item"><a href="profile">Profile</a></li>
                        </ol>
                    </nav>
                </div>
                <c:set var="i" value="${0}"/>
                <c:forEach items="${requestScope.listQuestion}" var="item">
                    <li class="listQuestion__item">
                        <c:set var="i" value="${i + 1}"/>
                        <form>
                            <p><b>Question ${i}:</b> ${item.getContent()}</p>
                            <input 
                                name="${item.getQuestionID()}" 
                                type="radio" 
                                ${item.getQuestionKey() == 1 ? 'checked' : ''} 
                                disabled
                                /><span>A. ${item.getChoice1()}</span>
                            <br/>
                            <input 
                                name="${item.getQuestionID()}" 
                                type="radio" ${item.getQuestionKey() == 2 ? 'checked' : ''} 
                                disabled
                                /><span>B. ${item.getChoice2()}</span>
                            <br/>
                            <input 
                                name="${item.getQuestionID()}" 
                                type="radio" ${item.getQuestionKey() == 3 ? 'checked' : ''} 
                                disabled
                                /><span>C. ${item.getChoice3()}</span>
                            <br/>
                            <input 
                                name="${item.getQuestionID()}" 
                                type="radio" ${item.getQuestionKey() == 4 ? 'checked' : ''} 
                                disabled
                                /><span>D. ${item.getChoice4()}</span>
                            <br/>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <c:if test="${requestScope.listQuestion.size() == 0}">
            No questions in the test.
        </c:if>
    </body>
</html>
