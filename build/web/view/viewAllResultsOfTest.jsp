<%-- 
    Document   : viewAllResultsOfTest
    Created on : Oct 26, 2023, 8:13:51 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/viewAllResultsOfTest.css"/>
    </head>
    <body>
        <div class="allResultsOfTest container">
            <div class="allResultsOfTest__title">
                <!--title-->
                <h2>Result of ${requestScope.testName}</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageTest">Manage Test</a></li>
                        <li class="breadcrumb-item active" aria-current="page">View Result</li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&studentID=${student.getStudentID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>

            <div class="allResultsOfTest__content">
                <table>
                    <tr>
                        <th>No</th>
                        <th class="colorWhite">Class</th>
                        <th>Roll Number</th>
                        <th class="colorWhite">Student</th>
                        <th>Sumit date</th>
                        <th class="colorWhite">Grade</th>
                        <th>Re-attemp</th>
                    </tr>
                    <c:set var="i" value="${0}"/>
                    <c:forEach items="${requestScope.listStudentInResult}" var="item">
                        <tr>
                            <td>${i + 1}</td>
                            <td class="colorWhite">${item.className}</td>
                            <td>${item.studentID}</td>
                            <td class="colorWhite">${item.studentName}</td>
                            <td>${requestScope.listResultOfTest.get(i).date}</td>
                            <td class="colorWhite" style="color: blueviolet">${requestScope.listResultOfTest.get(i).grade}</td>
                            <td><a href="manageResult?service=reattemp&studentID=${item.studentID}&testID=${requestScope.listResultOfTest.get(i).testID}">Re-attemp</a></td>
                        </tr>
                        <c:set var="i" value="${i + 1}"/>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
