<%-- 
    Document   : resultOfTest
    Created on : Oct 24, 2023, 8:22:35 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/resultOfTest.css"/>
    </head>
    <body>
        <div class="resultOfTest container">
            <div class="resultOfTest__title">
                <!--title-->
                <h2>${requestScope.subjectInfo.subjectName} - ${requestScope.subjectInfo.subjectID}</h2>

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

            <div class="resultOfTest__content">
                <div>${requestScope.testInfo.testName}</div>
                <ul>
                    <li><b>State:</b> Submitted on ${requestScope.resultOfTest.date}</li>
                    <li><b>Grade:</b> ${requestScope.resultOfTest.grade}</li>
                    <li><b>Review:</b> <a href="manageResult?service=reviewTest&studentID=${student.getStudentID()}&testID=${requestScope.testInfo.getTestID()}">Review</a></li>
                </ul>
            </div>
        </div>
    </body>
</html>
