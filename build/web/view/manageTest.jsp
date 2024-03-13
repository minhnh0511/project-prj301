<%-- 
    Document   : manageTest
    Created on : Oct 17, 2023, 11:18:29 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/manageTest.css"/>
    </head>
    <body>
        <div class="manageTest container">
            <c:if test="${requestScope.notification != null}">
                <div class="alert alert-dark" role="alert">
                    ${requestScope.notification}
                </div>
            </c:if>
            <div class="manageTest__title">
                <!--title-->
                <h2>${requestScope.subject.getSubjectName()} - ${requestScope.subject.getSubjectID()}</h2>

                <!--breadcrumb-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Manage Test</li>
                        <li class="breadcrumb-item"><a href="manageResult?service=resultOfCourse">View Result</a></li>
                        <li class="breadcrumb-item"><a href="profile?service=viewProfile&lecturerID=${lecturer.getLecturerID()}">Profile</a></li>
                    </ol>
                </nav>
                <!--add more test-->
                <div class="manageTest__button--add" 
                     data-toggle="collapse" data-target="#collapseWidthAddTest" aria-expanded="false" aria-controls="collapseWidthAddTest">
                    <i class="fa-solid fa-plus"></i>Add more test
                </div>
                <div style="height: 50px; margin-left: 630px; margin-top: -50px">
                    <div class="collapse width" id="collapseWidthAddTest">
                        <div class="card card-body" style="width: 330px;">
                            <form action="manageTest">
                                <input type="hidden" name="service" value="addTest"/>
                                Number of question:<input type="number" name="numberOfQuestion" min="1" max="99"/>
                                <input type="submit" name="submit" value="Add"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="manageTest__content">
                <h2>Test list</h2>
                <c:if test="${requestScope.listTest.size() != 0}">
                    <ul>
                        <c:forEach items="${requestScope.testList}" var="item">
                            <li>
                                <i class="fa-regular fa-file-lines"></i>
                                <a class="dropdown-item" href="question?service=viewQuestion&testID=${item.getTestID()}">
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
