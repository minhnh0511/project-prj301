<%-- 
    Document   : footer
    Created on : Oct 9, 2023, 8:59:51 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css"/>
    </head>
    <body>
        <footer>
            <c:if test="${sessionScope.role == null}">
                YOU ARE NOT LOGGED IN.(<a href="/quizTerm4/login">LOG IN</a>)
            </c:if>
                
            <!--------------------------------------------------------------------------------------------------------->
            <!--for lecturer-->
            <c:if test="${lecturer.getLecturerID() != null}">
                YOU ARE LOGGED IN AS 
                <u>${lecturer.getLecturerName().toUpperCase()}</u>.(<a href="${pageContext.request.contextPath}/logout">LOG OUT</a>)
            </c:if>
            
            <!--------------------------------------------------------------------------------------------------------->
            <!--for student-->
            <c:if test="${student.getStudentID() != null}">
                YOU ARE LOGGED IN AS 
                <u>${student.getStudentName().toUpperCase()}</u>.(<a href="${pageContext.request.contextPath}/logout">LOG OUT</a>)
            </c:if>
            
            <!--------------------------------------------------------------------------------------------------------->
            <!--for admin-->
            <c:if test="${sessionScope.role == 3}">
                YOU ARE LOGGED IN AS 
                <u>${'Admin'.toUpperCase()}</u>.(<a href="${pageContext.request.contextPath}/logout">LOG OUT</a>)
            </c:if>
            <br />
            <div class="footer__info">
                @Copyright 2023 by MinhNHHE170924
                <br />
            </div>
        </footer>
    </body>
</html>
