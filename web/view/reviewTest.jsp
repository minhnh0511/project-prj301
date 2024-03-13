<%-- 
    Document   : reviewTest
    Created on : Oct 25, 2023, 1:04:53 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reviewTest.css"/>
    </head>
    <body>
        <div class="reviewTest container">
            <div class="reviewTest__title">
                <!--title-->
                <h2>${requestScope.resultReview.getTestID().substring(0, 6)} - ${requestScope.testName}</h2>
                
                <div class="reviewTest__result">
                    Grade: ${requestScope.resultReview.getGrade()}
                </div>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageCourse?service=viewAllEnrolledCourses">My Courses</a></li>
                        <li class="breadcrumb-item"><a href="manageResult?service=viewAllResults">View Result</a></li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&studentID=${student.getStudentID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <div class="reviewTest__content">
                <c:set var="i" value="${0}"/>
                <form>
                    <ul>
                        <c:forEach items="${requestScope.listQuestionReview}" var="item">
                            <c:set var="i" value="${i + 1}"/>
                            <li>
                                <p><b>Question ${i}:</b> ${item.getContent()}</p>
                                <input 
                                    name="${item.getQuestionID()}" 
                                    type="radio" 
                                    disabled
                                    ${(requestScope.checkedAnswer.get(i - 1) == 1) ? 'checked' : ''}
                                    /><span class="${item.getQuestionKey() == 1 ? 'correctAnswer ' : ''}" >A. ${item.getChoice1()}</span>
                                <br/>
                                <input 
                                    name="${item.getQuestionID()}" 
                                    type="radio" 
                                    disabled
                                    ${(requestScope.checkedAnswer.get(i - 1) == 2) ? 'checked' : ''}
                                    /><span class="${item.getQuestionKey() == 2 ? 'correctAnswer ' : ''}" >B. ${item.getChoice2()}</span>
                                <br/>
                                <input 
                                    name="${item.getQuestionID()}" 
                                    type="radio" 
                                    disabled
                                    ${(requestScope.checkedAnswer.get(i - 1) == 3) ? 'checked' : ''}
                                    /><span class="${item.getQuestionKey() == 3 ? 'correctAnswer ' : ''}" >C. ${item.getChoice3()}</span>
                                <br/>
                                <input 
                                    name="${item.getQuestionID()}" 
                                    type="radio" 
                                    disabled
                                    ${(requestScope.checkedAnswer.get(i - 1) == 4) ? 'checked' : ''}
                                    /><span class="${item.getQuestionKey() == 4 ? 'correctAnswer ' : ''}">D. ${item.getChoice4()}</span>
                                <br/>
                            </li>
                        </c:forEach>
                    </ul>
                </form>
            </div>    
        </div>
    </body>
</html>
