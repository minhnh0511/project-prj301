<%-- 
    Document   : viewAllResults
    Created on : Oct 25, 2023, 2:56:50 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/viewAllResults.css"/>
    </head>
    <body>
        <div class="viewAllResults container">
            <div class="viewAllResults__title">
                <!--title-->
                <h2>MY RESULTS</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageCourse?service=viewAllEnrolledCourses">My Courses</a></li>
                        <li class="breadcrumb-item active" aria-current="page">View Result</li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&studentID=${student.getStudentID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <div class="viewAllResults__content">
                <table>
                    <tr>
                        <th>Lecturer</th>
                        <th class="colorWhite">Subject</th>
                        <th>Test</th>
                        <th class="colorWhite">Submit date</th>
                        <th>Grade</th>
                        <th class="colorWhite">Review</th>
                    </tr>
                    <c:forEach begin="${0}" end="${requestScope.listReviewResult.size() - 1}" var="i">
                        <tr>
                            <td>${requestScope.listReviewTest.get(i).getLecturerID().toUpperCase()}</td>
                            <td class="colorWhite">${requestScope.listReviewSubject.get(i).getSubjectName()}</td>
                            <td>${requestScope.listReviewTest.get(i).getTestName()}</td>
                            <td class="colorWhite">${requestScope.listReviewResult.get(i).getDate()}</td>
                            <td>${requestScope.listReviewResult.get(i).getGrade()}</td>
                            <td class="colorWhite"><a href="manageResult?service=reviewTest&studentID=${student.getStudentID()}&testID=${requestScope.listReviewTest.get(i).getTestID()}">Review</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:forEach items="${requestScope.testQues}" var="item">
                    ${item.key} - ${item.value}
                </c:forEach>
            </div>
        </div>
    </body>
</html>
