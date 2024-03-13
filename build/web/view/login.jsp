<%-- 
    Document   : login
    Created on : Oct 10, 2023, 9:47:44 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/login.css"/>
    </head>
    <body>
        <%
            String notification = (String)request.getAttribute("notification");
            if(notification == null) {
                notification = "";
            }
        %>
        <div class="login">
            <form action="login" method="post">
                <p>QT4 - SOFTWARE ENGINEERING TERM 4 REVIEW</p>
                <input type="text" name="userName" placeholder="Enter userName" required/><br/>
                <input type="password" name="password" placeholder="Enter password" required/><br/>
                <div>You are:
                    <input type="radio" name="role" value="1" required/> Lecturer
                    <input type="radio" name="role" value="2" required/> Student
                    <input type="radio" name="role" value="3" required/> Admin
                </div>
                <p style="color: red; font-size: 13px"><%=notification%></p>
                <input type="submit" name="submit" value="LOGIN"/>
            </form>
        </div>
    </body>
</html>
