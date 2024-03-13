<%-- 
    Document   : sider
    Created on : Oct 16, 2023, 9:07:17 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sider.css"/>
    </head>
    <body>
    <sider>
        <a href="${pageContext.request.contextPath}/home">
            <i class="fa-solid fa-house"></i>Home
        </a>

        <!--for lecturer-->
        <c:if test="${lecturer.getLecturerID() != null}">
            <a href="manageTest">
                <i class="fa-solid fa-list"></i></i>Manage Test
            </a>
            <a href="manageResult?service=resultOfCourse">
                <i class="fa-solid fa-square-poll-vertical"></i>View Result
            </a>
        </c:if>

        <!--for student-->
        <c:if test="${student.getStudentID() != null}">
            <a href="manageCourse?service=viewAllEnrolledCourses">
                <i class="fa-solid fa-list"></i></i>My Courses
            </a>
            <a href="manageResult?service=viewAllResults">
                <i class="fa-solid fa-square-poll-vertical"></i>View Result
            </a>
        </c:if>
            
    </sider>
</body>
</html>
