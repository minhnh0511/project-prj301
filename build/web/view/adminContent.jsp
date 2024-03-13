<%-- 
    Document   : contentAdmin
    Created on : Oct 30, 2023, 5:23:09 PM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminContent.css"/>
    </head>
    <body>
    <content>
        <c:choose>
            <c:when test="${requestScope.studentsOfCourse != null}">
                <%@include file="adminManageCourse.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="adminCourse.jsp" %>
            </c:otherwise>
        </c:choose>
    </content>
    </body>
</html>
