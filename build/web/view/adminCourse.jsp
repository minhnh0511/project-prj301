<%-- 
    Document   : adminCourse
    Created on : Oct 30, 2023, 8:52:07 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminCourse.css"/>
    </head>
    <body>
        <div class="adminCourse container">
            <h1>All Courses</h1>
            <ul>
                <c:forEach items="${sessionScope.subjectList}" var="subjectItem">
                    <li>
                        <h5>${subjectItem.subjectID} - ${subjectItem.subjectName}</h5>
                        <c:forEach items="${sessionScope.courseList}" var="courseItem">
                            <c:if test="${courseItem.subjectID == subjectItem.subjectID}">
                                <a href="admin?service=manageCourse&lecturerID=${courseItem.lecturerID}">
                                    <i class="fa-solid fa-graduation-cap"></i>
                                    ${courseItem.lecturerID} - ${courseItem.lecturerName}
                                </a>
                            </c:if>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
