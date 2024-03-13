<%-- 
    Document   : viewSearchedCourses
    Created on : Oct 23, 2023, 4:38:46 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/viewSearchedCourses.css"/>
    </head>
    <body>
        <div class="viewSearchedCourses container">
            <div class="viewSearchedCourses__title">
                <!--title-->
                <h2>Search results: ${requestScope.searchedCourses.size()}</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageCourse?service=viewAllEnrolledCourses">Manage Courses</a></li>
                        <li class="breadcrumb-item"><a href="manageResult?service=viewAllResults">View Result</a></li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&studentID=${student.getStudentID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <div class="viewSearchedCourses__content">
                <c:if test="${requestScope.searchedCourses.size() > 0}">
                    <c:forEach items="${requestScope.searchedCourses}" var="item">
                        <div class="card" style="width: 18rem;">
                            <img src="${pageContext.request.contextPath}/assets/images/setup/searchedCourse.jpg" class="card-img-top" alt="...">
                            <hr/>
                            <ul class="card-body">
                                <li>
                                    <p>Subject: 
                                        <a href="manageCourse?service=viewCourseDetail&lecturerID=${item.lecturer.lecturerID}">${item.subject.subjectName}</a>
                                    </p>
                                </li>
                                <li>
                                    <p>Lecturer: 
                                        <a href="manageCourse?service=viewCourseDetail&lecturerID=${item.lecturer.lecturerID}">${item.lecturer.lecturerName}</a>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.searchedCourses.size() == 0}">
                    No courses found.
                </c:if>
            </div>        
        </div>
    </body>
</html>
