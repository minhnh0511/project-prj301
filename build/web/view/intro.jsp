<%-- 
    Document   : intro
    Created on : Oct 17, 2023, 9:49:30 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/intro.css"/>
    </head>
    <body>
        <div class="intro">
            <div class="content__above">
                <div class="content__intro">
                    <p class="intro__large">FPT EDUCATION</p>
                    <hr/>
                    <p class="intro__small">SOFTWARE ENGINEERING TERM 4 REVIEW</p>
                    <a href="#listSubjectIntro">LEARN MORE</a>
                </div>
            </div>
            <div class="content__under container">
                <div id="listSubjectIntro" class="under__title">
                    Subject Review:
                </div>
                <c:if test="${student.getStudentID() != null}">
                    <form action="manageCourse" class="courseSearchForm">
                        <input type="hidden" name="service" value="searchCourse"/>
                        <input type="text" name="course" placeholder="Enter course"/>
                        <input type="submit" value="Search"/>
                    </form>
                </c:if>
                <ul class="under__list">
                    <li class="under__subject" data-toggle="collapse" href="#collapsePRJ301" role="button" aria-expanded="false" aria-controls="collapsePRJ301">
                        <div class="subject__title">
                            <div>PRJ301</div>
                            <p>Java Web Application Development</p>
                            <div class="collapse" id="collapsePRJ301">
                                <p>
                                    By the end of this course Students will be able to:<br/>
                                    a) Knowledge: (what will students know?)<br/>
                                    • Understand the core technologies of Java web programming:<br/>
                                    - Servlet and JSP<br/>
                                    - Scope of sharing state (session, application, request,page)<br/>
                                    - JSP Tags, JSTL, Customtags<br/>
                                    - Filtering<br/>
                                    • Know how to develop and deploy your own websites using Java<br/>
                                    • Understand and be able to apply MVC architecture for the web<br/>
                                    b) Skills: (what will students be able to do?)<br/>
                                    • Basic Web application development applying MVC Design Pattern using Servlet/Filter as Controller
                                </p>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/images/setup/PRJ301.jpg"/>
                    </li>

                    <li class="under__subject" data-toggle="collapse" href="#collapseMAS291" role="button" aria-expanded="false" aria-controls="collapseMAS291">
                        <div class="subject__title">
                            <div>MAS291</div>
                            <p>Statistics and Probability</p>
                            <div class="collapse" id="collapseMAS291">
                                <p>
                                    Upon finishing the course, students must acquire:<br/>
                                    1. the following knowledge:(ABET a1)<br/>
                                    • The fundamental principles of probability and their applications<br/>
                                    • The frequently used probability distributions.<br/>
                                    • The basics of descriptive statistics<br/>
                                    • The inferences of statistics: parameter estimations, hypothesis testing, regressions & correlations.<br/>
                                    2. the following skills: (ABET a2)<br/>
                                    • Recognize simple statistical models and applied them to solve engineering problems.<br/>
                                    • Use at least one statistical software (Excel, Maxima) for problem solving.<br/>
                                    • Self-study skill. (ABET i)<br/>
                                </p>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/images/setup/MAS291.png"/>
                    </li>

                    <li class="under__subject" data-toggle="collapse" href="#collapseJPD123" role="button" aria-expanded="false" aria-controls="collapseJPD123">
                        <div class="subject__title">
                            <div>JPD123</div>
                            <p>Elementary Japanese 1-A1.2</p>
                            <div class="collapse" id="collapseJPD123">
                                <p>
                                    I. Yêu cầu định hướng triển khai của môn học: Môn học cung cấp kiến thức, kỹ năng cơ bản của tiếng Nhật ở trình độ sơ cấp 1 (tương đương với trình độ A1.2) cho đối tượng sinh viên học tiếng Nhật là ngoại ngữ 2 tại trường Đại học. Môn học là học phần tiếp theo môn JPD113.<br/>
                                    II. Mục tiêu của môn học:<br/>
                                    Sau khi hoàn thành xong môn học này, sinh viên có thể:<br/>
                                    1. Giới thiệu được về nơi xuất thân của mình : như đặc điểm của quê hương mình, món ăn, khí hậu, những nơi nổi tiếng của quê mình.<br/>
                                    2. Nói chuyện được với bạn bè mình về những dự định hay công việc thường làm.<br/>
                                    3. Có thể mời, rủ bạn tham gia 1 hoạt động nào đó; trao đổi thông tin và thảo luận để thống nhất được địa điểm và lịch hẹn.<br/>
                                    4. Truyền đạt đến người khác 1 cách đơn giản tình hình xung quanh. Ngoài ra có thể nhờ vả, đề xuất cùng nhau làm việc gì đó.<br/>
                                    5. Có thể nắm được khoảng gần 300 từ vựng cơ bản, cách viết và đọc của khoảng hơn 40 chữ Hán, khoảng gần 30 cấu trúc ngữ pháp cơ bản dùng trong hội thoại sơ cấp.<br/>
                                    6. Rèn luyện kỹ năng làm việc nhóm, phát triển bản thân thông qua các hoạt động nhóm trong lớp (role play..) và các hoạt động ngoại khóa.<br/>
                                </p>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/images/setup/JPD123.png"/>
                    </li>

                    <li class="under__subject" data-toggle="collapse" href="#collapseIOT102" role="button" aria-expanded="false" aria-controls="collapseIOT102">
                        <div class="subject__title">
                            <div>IOT102</div>
                            <p>Internet of Things</p>
                            <div class="collapse" id="collapseIOT102">
                                <p>
                                    This is a 3-credit course. This course has two parts online and offline.<br/>
                                    The content includes basic concepts and applications of IoT, practical exercises on the learning KIT.<br/>
                                    Students are taught how to learn online, and practice some parts at home. Q & A sessions, the Guidance for important issues, as well as performance assessments, will be conducted in the classroom.<br/>
                                </p>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/images/setup/IOT102.png"/>
                    </li>

                    <li class="under__subject" data-toggle="collapse" href="#collapseSWE201c" role="button" aria-expanded="false" aria-controls="collapseSWE201c">
                        <div class="subject__title">
                            <div>SWE201c</div>
                            <p>Introduction to Software Engineering</p>
                            <div class="collapse" id="collapseSWE201c">
                                <p>
                                    SWE201c is for people who are new to software engineering. It's also for those who have already developed software, but wish to gain a deeper understanding of the underlying context and theory of software development practices.<br/>
                                    At the end of this course, we expect learners to be able to:<br/>
                                    1.) Build high-quality and secure software using SDLC methodologies such as agile, lean, and traditional/waterfall.<br/>
                                    2.) Analyze a software development team's SDLC methodology and make recommendations for improvements.<br/>
                                    3.) Compare and contrast software development methodologies with respect to environmental, organizational, and product constraints.<br/>
                                </p>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/images/setup/SWE201c.png"/>
                    </li>
                </ul>
        </div>
    </body>
</html>
