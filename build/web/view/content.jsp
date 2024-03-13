<%-- 
    Document   : content
    Created on : Oct 9, 2023, 9:17:55 PM
    Author     : admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/content.css"/>
    </head>
    <body>
    <content>
        <c:choose>
            <c:when test="${requestScope.account != null && requestScope.subjectName != null}">
                <%@include file="profile.jsp" %>
            </c:when>
            <c:when test="${requestScope.listQuestion != null}">
                <%@include file="viewQuestion.jsp" %>
            </c:when>
            <c:when test="${requestScope.testList != null}">
                <%@include file="manageTest.jsp" %>
            </c:when>
            <c:when test="${requestScope.numberOfQuestion != null}">
                <%@include file="addTest.jsp" %>
            </c:when>
            <c:when test="${requestScope.resultOfCourse != null}">
                <%@include file="resultOfCourse.jsp" %>
            </c:when>
            <c:when test="${requestScope.listResultOfTest != null}">
                <%@include file="viewAllResultsOfTest.jsp" %>
            </c:when>
            
            
            <c:when test="${requestScope.searchedCourses != null}">
                <%@include file="viewSearchedCourses.jsp" %>
            </c:when>
            <c:when test="${requestScope.account != null}">
                <%@include file="profile.jsp" %>
            </c:when>
            <c:when test="${requestScope.request.equals('viewAllEnrolledCourses')}">
                <%@include file="viewAllEnrolledCourses.jsp" %>
            </c:when>
            <c:when test="${requestScope.listTestOfCourse != null}">
                <%@include file="detailCourse.jsp" %>
            </c:when>
            <c:when test="${requestScope.listQuestionToDo != null}">
                <%@include file="questionsToDo.jsp" %>
            </c:when>
            <c:when test="${requestScope.resultOfTest != null}">
                <%@include file="resultOfTest.jsp" %>
            </c:when>
            <c:when test="${requestScope.listReviewResult != null}">
                <%@include file="viewAllResults.jsp" %>
            </c:when>
            <c:when test="${requestScope.listQuestionReview != null && requestScope.checkedAnswer != null}">
                <%@include file="reviewTest.jsp" %>
            </c:when>
            
            <c:otherwise>
                <%@include file="intro.jsp" %>
            </c:otherwise>
        </c:choose>
    </content>
</body>
</html>
