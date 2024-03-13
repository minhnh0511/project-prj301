<%-- 
    Document   : resultOfCourse
    Created on : Oct 26, 2023, 5:19:20 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/resultOfCourse.css"/>
    </head>
    <body>
        <div class="resultOfCourse container">
            <div class="resultOfCourse__title">
                <!--title-->
                <h2>Student's result</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="manageTest">Manage Test</a></li>
                        <li class="breadcrumb-item active" aria-current="page">View Result</li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&lecturerID=${lecturer.getLecturerID()}">Profile</a></li>
                    </ol>
                </nav>
            </div>
                    
            <div class="resultOfCourse__content">
                <h2>Test list</h2>
                <p>(Select a test to view result)</p>
                <c:if test="${requestScope.resultOfCourse.size() != 0}">
                    <ul>
                        <c:forEach items="${requestScope.resultOfCourse}" var="item">
                            <li>
                                <i class="fa-regular fa-file-lines"></i>
                                <a class="dropdown-item" href="manageResult?service=viewAllResultsOfTest&testID=${item.getTestID()}">
                                    ${item.getTestName()}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.listTest.size() == 0}">
                    You do not have any test yet.
                </c:if>

            </div> 
        </div>
    </body>
</html>
