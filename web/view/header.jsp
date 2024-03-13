<%-- 
    Document   : header
    Created on : Oct 9, 2023, 5:03:56 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Vector" %>
<%@page import="model.Lecturer" %>
<%@page import="model.Test" %>
<%@page import="model.Subject" %>
<!DOCTYPE html>
<html>
    <c:set var="lecturer" value="${sessionScope.lecturer}"/>
    <c:set var="listTest" value="${sessionScope.listTest}"/>
    <c:set var="student" value="${sessionScope.student}"/>
    <c:set var="enrolledCourses" value="${sessionScope.enrolledCourses}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" 
              referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css"/>
    </head>
    <body>
        <header class="container-fluid">
            <div class="header__navigator">
                <i class="fa-solid fa-bars"></i>
                <a href="${sessionScope.role == 3 ? 'admin' : 'home'}">
                    <img src="${pageContext.request.contextPath}/assets/images/setup/Logo.png"/>
                </a>
                <!--------------------------------------------------------------------------------------------------------->
                <!--for lecturer-->
                <c:if test="${lecturer.getLecturerID() != null}">
                    <div class="dropdown">
                        <button class="dropdown-toggle list-dropdown" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa-solid fa-list"></i></i>MY TESTS
                        </button>
                        <div class="dropdown-menu">
                            <c:if test="${listTest != null}">
                                <c:forEach items="${listTest}" var="item">
                                    <a class="dropdown-item" href="question?service=viewQuestion&testID=${item.getTestID()}">
                                        ${item.getTestName()}
                                    </a>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <!--------------------------------------------------------------------------------------------------------->

                <!--------------------------------------------------------------------------------------------------------->
                <!--for student-->
                <c:if test="${student.getStudentID() != null}">
                    <div class="dropdown">
                        <button class="dropdown-toggle list-dropdown" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa-solid fa-list"></i></i>MY COURSES
                        </button>
                        <div class="dropdown-menu">
                            <c:if test="${enrolledCourses.listCourse != null}">
                                <c:forEach items="${enrolledCourses.listCourse}" var="item">
                                    <a class="dropdown-item" href="manageCourse?service=viewCourseDetail&lecturerID=${item.lecturer.lecturerID}">
                                        ${item.subject.subjectID.toUpperCase()} - ${item.lecturer.lecturerID.toUpperCase()}
                                    </a>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <!--------------------------------------------------------------------------------------------------------->
            </div>

            <div class="header__account">
                <!--------------------------------------------------------------------------------------------------------->
                <!--for lecturer-->
                <c:if test="${lecturer.getLecturerID() != null}">
                    <div class="dropdown account__dropdown">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            ${lecturer.getLecturerName().toUpperCase()}
                            <c:if test="${lecturer.getImage() != null}">
                                <img src="${pageContext.request.contextPath}/assets/images/${lecturer.getImage()}"/>
                            </c:if>
                            <c:if test="${lecturer.getImage() == null}">
                                <img src="${pageContext.request.contextPath}/assets/images/avtNull.jpg"/>
                            </c:if>
                        </div>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="profile?service=viewProfile&lecturerID=${lecturer.getLecturerID()}">
                                <i class="fa-solid fa-user"></i>PROFILE
                            </a>
                            <a class="dropdown-item" href="logout"><i class="fa-solid fa-right-from-bracket"></i>LOG OUT</a>
                        </div>
                    </div>
                </c:if>
                <!--------------------------------------------------------------------------------------------------------->

                <!--------------------------------------------------------------------------------------------------------->
                <!--for student-->   
                <c:if test="${student.getStudentID() != null}">
                    <div class="dropdown account__dropdown">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            ${student.getStudentName().toUpperCase()}
                            <c:if test="${student.getImage() != null}">
                                <img src="${pageContext.request.contextPath}/assets/images/${student.getImage()}"/>
                            </c:if>
                            <c:if test="${student.getImage() == null}">
                                <img src="${pageContext.request.contextPath}/assets/images/avtNull.jpg"/>
                            </c:if>
                        </div>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="profile?service=viewProfile&studentID=${student.studentID}">
                                <i class="fa-solid fa-user"></i>PROFILE
                            </a>
                            <a class="dropdown-item" href="logout"><i class="fa-solid fa-right-from-bracket"></i>LOG OUT</a>
                        </div>
                    </div>
                </c:if>
                <!--------------------------------------------------------------------------------------------------------->

                <!--------------------------------------------------------------------------------------------------------->
                <!--for admin--> 
                <c:if test="${sessionScope.role == 3}">
                    <div class="dropdown account__dropdown">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            ADMIN
                            <img src="${pageContext.request.contextPath}/assets/images/admin.png"/>
                        </div>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="admin">
                                <i class="fa-solid fa-graduation-cap"></i>MANAGE COURSES
                            </a>
                            <a class="dropdown-item" href="logout"><i class="fa-solid fa-right-from-bracket"></i>LOG OUT</a>
                        </div>
                    </div>
                </c:if>
                <!--------------------------------------------------------------------------------------------------------->


                <!--------------------------------------------------------------------------------------------------------->
                <c:if test="${sessionScope.role == null}">
                    <a href="/quizTerm4/login">LOG IN</a>
                    <button data-toggle="modal" data-target="#registerModal">SIGN UP</button>
                </c:if>


            </div>

            <!-- Modal sign up -->

        </header>
        <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="registerModalLabel">You are:</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="register" method="POST">
                            <input type="radio" name="role" value="1" required/>Lecturer
                            <br />
                            <input type="radio" name="role" value="2" required/>Student
                            <br/>
                            <input type="submit" value="SIGN UP"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.role != 3}">
            <script src="${pageContext.request.contextPath}/assets/js/header.js"></script>
        </c:if>
    </body>
</html>
