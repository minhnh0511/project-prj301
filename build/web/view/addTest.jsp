<%-- 
    Document   : addTest
    Created on : Oct 19, 2023, 5:21:35 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/addTest.css"/>
    </head>
    <body>
        <div class="addTest container">
            <div class="addTest__title">
                <!--title-->
                <h2>Subject ID: ${sessionScope.lecturer.getSubjectID()}</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageTest">Manage Test</a></li>
                        <li class="breadcrumb-item"><a href="manageResult?service=resultOfCourse">View Result</a></li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&lecturerID=${lecturer.getLecturerID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <!--form-->
            
            <div class="addTest__content">
                <form action="manageTest">
                    <input type="hidden" name="service" value="addTestWithQuestions"/>
                    <input type="hidden" name="testID" value="${sessionScope.lecturer.getSubjectID()}${requestScope.newOrder}"/>
                    <input type="text" name="testName" maxlength="30" placeholder="Enter test name" required/>
                    <c:forEach begin="${1}" end="${requestScope.numberOfQuestion}" var="i">
                        <div class="addTest__content--form">
                            <c:if test="${i < 10}">
                                <c:set var="orderQuestion" value="0${i}"/>
                            </c:if>
                            <c:if test="${i >= 10}">
                                <c:set var="orderQuestion" value="${i}"/>
                            </c:if>
                            <c:set 
                                var="questionID" 
                                value="${sessionScope.lecturer.getSubjectID().substring(sessionScope.lecturer.getSubjectID().length() - 3)}${requestScope.newOrder.substring(requestScope.newOrder.length() - 2)}${orderQuestion}"/>
                            <b>Question ${i}: </b><input type="text" name="content${String.format("%02d", i)}" placeholder="Enter question" required/>
                            <br/>
                            <input type="radio" name="questionKey${String.format("%02d", i)}" value="1" required/>
                            <span>A:</span><input type="text" name="choice1${String.format("%02d", i)}" required/>
                            <br/>
                            <input type="radio" name="questionKey${String.format("%02d", i)}" value="2" required/>
                            <span>B:</span><input type="text" name="choice2${String.format("%02d", i)}" required/>
                            <br/>
                            <input type="radio" name="questionKey${String.format("%02d", i)}" value="3" required/>
                            <span>C:</span><input type="text" name="choice3${String.format("%02d", i)}" required/>
                            <br/>
                            <input type="radio" name="questionKey${String.format("%02d", i)}" value="4" required/>
                            <span>D:</span><input type="text" name="choice4${String.format("%02d", i)}" required/>
                            <br/>
                            <input type="hidden" name="questionID${String.format("%02d", i)}" value="${questionID}"/>
                        </div>
                    </c:forEach>
                    <input type="hidden" name="numberOfQuestion" value="${requestScope.numberOfQuestion}"/>
                    <input type="submit" name="submit" value="Add Test"/>
                </form>
            </div>

        </div>
    </body>
</html>
