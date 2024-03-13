<%-- 
    Document   : adminManageCourse
    Created on : Oct 31, 2023, 12:03:29 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminManageCourse.css"/>
    </head>
    <body>
        <div class="manageCourse container">
            <div class="manageCourse__title">
                <h2>${requestScope.course.subjectID} - ${requestScope.course.lecturerID}</h2>
            </div>
            <div class="manageCourse__content">
                <h3>Student List</h3>
                <table>
                    <tr>
                        <th>Class</th>
                        <th class="colorWhite">Roll Number</th>
                        <th>Name</th>
                        <th class="colorWhite">Email</th>
                        <th>Unenroll</th>
                    </tr>
                    <c:forEach items="${requestScope.studentsOfCourse}" var="item">
                        <tr>
                            <td>${item.className}</td>
                            <td class="colorWhite">${item.studentID}</td>
                            <td>${item.studentName}</td>
                            <td class="colorWhite">${item.email}</td>
                            <td><a href="admin?service=unenroll&studentID=${item.studentID}&lecturerID=${requestScope.course.lecturerID}">Unenroll</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <div>
                    <a href="admin?service=unenrollAll&lecturerID=${requestScope.course.lecturerID}">UNENROLL ALL</a>
                </div>
            </div>
        </div>
    </body>
</html>
