<%-- 
    Document   : detailCourse
    Created on : Oct 24, 2023, 12:34:35 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detailCourse.css"/>
    </head>
    <body>
        <div class="detailCourse container">
            <c:if test="${requestScope.notification != null}">
                <div class="alert alert-dark" role="alert">
                    ${requestScope.notification}
                </div>
            </c:if>
            <div class="detailCourse__title">
                <!--title-->
                <h2>${requestScope.subject.getSubjectID()} - ${requestScope.listTestOfCourse.get(0).getLecturerID()}</h2>

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

            <div class="detailCourse__content">
                <h2>Test list</h2>
                <c:if test="${requestScope.listTestOfCourse.size() != 0}">
                    <ul>
                        <c:forEach items="${requestScope.listTestOfCourse}" var="item">
                            <li>
                                <i class="fa-regular fa-file-lines"></i>
                            <c:if test="${!requestScope.testIsDone.contains(item.getTestID())}">
                                <a class="dropdown-item" href="doTest?service=viewQuestion&isEnrolled=${requestScope.isEnrolled}&testID=${item.getTestID()}">
                                    ${item.getTestName()}
                                </a>
                            </c:if>
                            <c:if test="${requestScope.testIsDone.contains(item.getTestID())}">
                                <a href="manageResult?service=reviewTest&studentID=${student.getStudentID()}&testID=${item.getTestID()}">
                                    ${item.getTestName()} (Test done. Review)
                                </a>
                            </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.listTestOfCourse.size() == 0}">
                    You do not have any test yet.
                </c:if>

                <c:if test="${requestScope.isEnrolled == true}">
                    <a href="manageCourse?service=unenroll&course=${requestScope.listTestOfCourse.get(0).getLecturerID()}" class="enroll-button">UNENROLL</a>
                </c:if>

                <c:if test="${requestScope.isEnrolled == false}">
                    <a href="manageCourse?service=enroll&course=${requestScope.listTestOfCourse.get(0).getLecturerID()}" class="enroll-button">ENROLL</a>
                </c:if>
            </div>
        </div>
    </body>
</html>
