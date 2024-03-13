<%-- 
    Document   : siderAdmin
    Created on : Oct 30, 2023, 5:11:06 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminSider.css"/>
    </head>
    <body>
    <sider>
        <a href="admin">
            <i class="fa-solid fa-graduation-cap"></i>Manage Courses
        </a>
        
        <a href="logout">
            <i class="fa-solid fa-right-from-bracket"></i> Sign out
        </a>
    </sider>
</body>
</html>
