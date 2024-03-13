<%-- 
    Document   : register
    Created on : Oct 10, 2023, 11:31:17 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css"/>
    </head>
    <body>
        <%
            int role = Integer.parseInt((String)request.getAttribute("role"));
            ResultSet subjectList = (ResultSet)request.getAttribute("data");
            String notification = (String)request.getAttribute("notification");
            if(notification == null) {
                notification = "";
            }
        %>
        <div class="register">
            <form action="register" method="post">
                <input type="hidden" name="role" value="<%=role%>"/>
                <%if(role == 1) {%>
                <p>SIGN UP AS LECTURER</p>
                <input type="text" name="lecturerID" placeholder="Enter lecturerID" maxlength="10" required/>
                <input type="text" name="lecturerName" placeholder="Enter lecturerName" maxlength="30" required/>
                <%}else if(role == 2) {%>
                <p>SIGN UP AS STUDENT</p>
                <input type="text" name="studentID" placeholder="Enter studentID" maxlength="10" required/>
                <input type="text" name="studentName" placeholder="Enter studentName" maxlength="30" required/>
                <%}%>
                <input type="text" name="userName" placeholder="Enter userName" maxlength="30" required/>
                <input type="password" name="password" placeholder="Enter password" maxlength="30" required/>
                <input type="password" name="checkingPassword" placeholder="Enter password again" maxlength="30" required/>
                <input type="text" name="email" placeholder="Enter email" maxlength="30" required/>
                <%if(role == 1) {%>
                <select name="subjectID">
                    <%while(subjectList.next()){%>
                    <option value="<%=subjectList.getString(1)%>"><%=subjectList.getString(2)%></option> 
                    <%}%>
                </select>
                <%}else if(role == 2) {%>
                <input type="text" name="class" placeholder="Enter Class" maxlength="10" required/>
                <%}%>
                <input type="text" name="image" placeholder="Enter image path" maxlength="50"/>
                <p style="color: red; font-size: 13px"><%=notification%></p>
                <input type="submit" name="submit" value="SIGN UP"/>
            </form>
        </div>
    </body>
</html>
