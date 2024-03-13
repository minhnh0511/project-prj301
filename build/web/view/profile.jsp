<%-- 
    Document   : profile
    Created on : Oct 18, 2023, 3:31:32 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css"/>
    </head>
    <body>
        <div class="profile container">
            <c:if test="${requestScope.notification != null && requestScope.notification.length() > 0}">
                <div class="alert alert-warning" role="alert">
                    ${requestScope.notification}
                </div>
            </c:if>

            <!--------------------------------------------------------------------------------------------------------->
            <!--for lecturer-->
            <c:if test="${lecturer.getLecturerID() != null}">
                <div class="profile__title">
                    <c:if test="${lecturer.getImage() != null}">
                        <img src="${pageContext.request.contextPath}/assets/images/${lecturer.getImage()}"/>
                    </c:if>
                    <c:if test="${lecturer.getImage() == null}">
                        <img src="${pageContext.request.contextPath}/assets/images/avtNull.jpg"/>
                    </c:if>
                    <h2>${lecturer.getLecturerName().toUpperCase()}</h2>

                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item"><a href="manageTest">Manage Test</a></li>
                            <li class="breadcrumb-item"><a href="manageResult?service=resultOfCourse">View Result</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Profile</li>
                        </ol>
                    </nav>

                    <!--update profile-->
                    <div class="updateProfile__button" data-toggle="collapse" data-target="#collapseUpdateProfile" aria-expanded="false" aria-controls="collapseUpdateProfile">
                        Update Profile
                    </div>
                    <div class="collapse" id="collapseUpdateProfile">
                        <div class="card card-body">
                            <form action="profile" method="post">
                                <input type="hidden" name="service" value="updateProfile"/>
                                <input type="hidden" name="lecturerID" value="${lecturer.getLecturerID()}"/>
                                Change Name<input type="text" name="lecturerName" value="${lecturer.getLecturerName()}" maxlength="30"/>
                                UserName<input type="text" name="userName" value="${requestScope.account.getUserName()}" maxlength="30" readonly style="color: grey"/>
                                Change Password<input type="password" name="password" maxlength="30" value="${requestScope.account.getPassword()}"/>
                                Checking password<input type="password" name="checkingPassword" maxlength="30" value="${requestScope.account.getPassword()}"/>
                                Change Email<input type="text" name="email" value="${lecturer.getEmail()}" maxlength="30"/>
                                Change Image<input type="text" name="imgage" value="${lecturer.getImage()}" maxlength="50"/>
                                Subject<input type="text" name="subjectID" value="${lecturer.getSubjectID()}" readonly style="color: grey"/>
                                <input type="submit" name="submit" value="Update"/>
                            </form>
                        </div>
                    </div>
                    <!--end update profile-->
                </div>

                <div class="profile__content">
                    <div class="detail__title">User details</div>
                    <div class="proflie__content--details">
                        <div class="detail__content">
                            <b>Email</b><br/>${lecturer.getEmail()}
                            <br/><br/><br/>
                            <b>Subject</b><br/>${requestScope.subjectName}
                        </div>
                        <c:if test="${listTest.size() > 0}">
                            <div class="detail__content">
                                <b>TestList</b><br/>
                                <c:forEach items="${listTest}" var="item">
                                    <a href="question?service=viewQuestion&testID=${item.getTestID()}">
                                        ${item.getTestID()} - ${item.getTestName()}
                                    </a><br/>
                                </c:forEach>
                            </div>
                        </c:if>
                        <p class="profile__description">
                            <b>Description</b><br/>
                            The university lecturer is an esteemed professional dedicated to imparting knowledge and shaping the minds of students. 
                            With a wealth of expertise in their respective field, they possess a deep understanding of the subject matter. Their lectures 
                            are characterized by a perfect blend of theoretical principles and practical applications, encouraging students to think 
                            critically and engage actively in the learning process. They create an inclusive and supportive environment, fostering open 
                            discussions and stimulating intellectual curiosity. The lecturer's passion for teaching is evident in their innovative teaching 
                            methods and commitment to student success. They serve as a mentor, guiding and inspiring students on their academic journey, 
                            leaving a lasting impact on their educational and personal development.
                        </p>
                    </div>
                </div>
            </c:if>
            <!--------------------------------------------------------------------------------------------------------->


            <!--------------------------------------------------------------------------------------------------------->
            <!--for student-->
            <c:if test="${student.getStudentID() != null}">
                <div class="profile__title">
                    <c:if test="${student.getImage() != null}">
                        <img src="${pageContext.request.contextPath}/assets/images/${student.getImage()}"/>
                    </c:if>
                    <c:if test="${student.getImage() == null}">
                        <img src="${pageContext.request.contextPath}/assets/images/avtNull.jpg"/>
                    </c:if>
                    <h2>${student.getStudentName().toUpperCase()}</h2>

                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item"><a href="manageCourse?service=viewAllEnrolledCourses">My Courses</a></li>
                            <li class="breadcrumb-item"><a href="manageResult?service=viewAllResults">View Result</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Profile</li>
                        </ol>
                    </nav>
                    <!--update profile-->
                    <div class="updateProfile__button" data-toggle="collapse" data-target="#collapseUpdateProfile" aria-expanded="false" aria-controls="collapseUpdateProfile">
                        Update Profile
                    </div>
                    <div class="collapse" id="collapseUpdateProfile">
                        <div class="card card-body">
                            <form action="profile" method="post">
                                <input type="hidden" name="service" value="updateProfile"/>
                                <input type="hidden" name="studentID" value="${student.getStudentID()}"/>
                                Change Name<input type="text" name="studentName" value="${student.getStudentName()}" maxlength="30"/>
                                UserName<input type="text" name="userName" value="${requestScope.account.getUserName()}" maxlength="30" readonly style="color: grey"/>
                                Change Password<input type="password" name="password" maxlength="30" value="${requestScope.account.getPassword()}"/>
                                Checking password<input type="password" name="checkingPassword" maxlength="30" value="${requestScope.account.getPassword()}"/>
                                Change Email<input type="text" name="email" value="${student.getEmail()}" maxlength="30"/>
                                Change Image<input type="text" name="imgage" value="${student.getImage()}" maxlength="50"/>
                                Subject<input type="text" name="className" value="${student.getClassName()}"/>
                                <input type="submit" name="submit" value="Update"/>
                            </form>
                        </div>
                    </div>
                    <!--end update profile-->
                </div>

                <div class="profile__content">
                    <div class="detail__title">User details</div>
                    <div class="proflie__content--details">
                        <div class="detail__content">
                            <b>Email</b><br/>${student.getEmail()}
                            <br/><br/><br/>
                            <b>Class</b><br/>${student.getClassName()}
                        </div>
                        <c:if test="${enrolledCourses.listCourse.size() > 0}">
                            <div class="detail__content">
                                <b>My Courses</b><br/>
                                <c:forEach items="${enrolledCourses.listCourse}" var="item">
                                    <a href="manageCourse?service=viewCourseDetail&lecturerID=${item.lecturer.lecturerID}">
                                        ${item.subject.getSubjectID()} - ${item.lecturer.getLecturerID()}
                                    </a><br/>
                                </c:forEach>
                            </div>
                        </c:if>
                        <p class="profile__description">
                            <b>Description</b><br/>
                            The university lecturer is an esteemed professional dedicated to imparting knowledge and shaping the minds of students. 
                            With a wealth of expertise in their respective field, they possess a deep understanding of the subject matter. Their lectures 
                            are characterized by a perfect blend of theoretical principles and practical applications, encouraging students to think 
                            critically and engage actively in the learning process. They create an inclusive and supportive environment, fostering open 
                            discussions and stimulating intellectual curiosity. The lecturer's passion for teaching is evident in their innovative teaching 
                            methods and commitment to student success. They serve as a mentor, guiding and inspiring students on their academic journey, 
                            leaving a lasting impact on their educational and personal development.
                        </p>
                    </div>
                </div>
            </c:if>
            <!--------------------------------------------------------------------------------------------------------->

        </div>
    </body>
</html>
