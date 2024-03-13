<%-- 
    Document   : viewAllEnrolledCourses
    Created on : Oct 23, 2023, 9:51:17 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/viewAllEnrolledCourses.css"/>
    </head>
    <body>
        <div class="viewAllEnrolledCourses container">
            <div class="viewAllEnrolledCourses__title">
                <!--title-->
                <h2>My Courses</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Manage Courses</li>
                        <li class="breadcrumb-item"><a href="manageResult?service=viewAllResults">View Result</a></li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&studentID=${student.getStudentID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <div class="viewAllEnrolledCourses__content">
                <c:if test="${sessionScope.enrolledCourses.listCourse.size() > 0}">
                    <c:forEach items="${sessionScope.enrolledCourses.listCourse}" var="item">
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
                                        <a hrefmanageCourse?service=viewCourseDetail&lecturerID=${item.lecturer.lecturerID}">${item.lecturer.lecturerName}</a>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${sessionScope.enrolledCourses.listCourse.size() == 0}">
                    No courses enrolled.
                </c:if>
            </div>        
        </div>
    </body>
</html>
