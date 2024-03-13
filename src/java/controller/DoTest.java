/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOAnswer;
import dal.DAOResult;
import dal.DBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Vector;
import model.Answer;
import model.Question;
import model.Result;
import model.Subject;
import model.Test;

/**
 *
 * @author admin
 */
@WebServlet(name = "DoTest", urlPatterns = {"/doTest"})
public class DoTest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBContext dao = new DBContext();
        String service = request.getParameter("service");
        //view question
        if (service.equals("viewQuestion")) {
            String isEnrolled = request.getParameter("isEnrolled");
            String testID = request.getParameter("testID");
            //if the course is already enrolled
            if (isEnrolled.equals("true")) {
                //set list question
                ResultSet rs = dao.getData("select * from Question where TestID = '" + testID + "'");
                Vector<Question> listQuestionToDo = new Vector<>();
                try {
                    while (rs.next()) {
                        Question question = new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                        listQuestionToDo.add(question);
                    }
                } catch (SQLException ex) {
                }
                //set test info
                ResultSet rsT = dao.getData("select * from Test where TestID = '" + testID + "'");
                Test test = new Test();
                try {
                    if (rsT.next()) {
                        test.setTestID(rsT.getString(1));
                        test.setTestName(rsT.getString(2));
                        test.setLecturerID(rsT.getString(3));
                    }
                } catch (SQLException ex) {
                }
                //set request
                request.setAttribute("testInfo", test);
                request.setAttribute("listQuestionToDo", listQuestionToDo);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
            //if the course is not enrolled yet.
            else {
                ResultSet rs = dao.getData("select LecturerID from Test where TestID = '" + testID + "'");
                String lecturerId = "";
                try {
                    if(rs.next()) {
                        lecturerId = rs.getString(1);
                    }
                } catch (SQLException ex) {
                }
                String notification = "Must enroll course to do test";
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("manageCourse?service=viewCourseDetail&lecturerID=" + lecturerId).forward(request, response);
            }
        }

        //submit test
        if (service.equals("submitTest")) {
            DAOAnswer daoAnswer = new DAOAnswer();
            String testID = request.getParameter("testID");
            String studentID = request.getParameter("studentID");
            int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
            //initialize variable to check mark
            int mark = 0;
            //mark the test
            for (int i = 0; i < numberOfQuestion; i++) {
                //get data
                String order = String.format("%02d", i + 1);
                String questionID = request.getParameter("questionID" + order);
                int answerOrder = 0;
                try {
                    answerOrder = Integer.parseInt(request.getParameter("answer" + order));
                } catch (NumberFormatException | NullPointerException e) {
                }
                //insert answer
                Answer answer = new Answer(questionID, studentID, answerOrder);
                //check if the answer is correct
                ResultSet questionKey = dao.getData("select QuestionKey from Question where QuestionID = '" + questionID + "'");
                try {
                    if (questionKey.next()) {
                        //increase mark if correct
                        if (answerOrder == questionKey.getInt(1)) {
                            mark++;
                        }
                    }
                } catch (SQLException ex) {
                }
                daoAnswer.insertAnswer(answer);
            }
            //get test info
            ResultSet resultInfo = dao.getData("select t.*, s.*\n"
                    + "from Test t join Lecturer l on t.LecturerID = l.LecturerID\n"
                    + "	join Subject s on l.SubjectID = s.SubjectID\n"
                    + "where TestID = '" + testID + "'");
            Test testInfo = new Test();
            Subject subjectInfo = new Subject();
            try {
                if(resultInfo.next()) {
                    //set test info
                    testInfo.setTestID(resultInfo.getString(1));
                    testInfo.setTestName(resultInfo.getString(2));
                    testInfo.setLecturerID(resultInfo.getString(3));
                    //set subject info
                    subjectInfo.setSubjectID(resultInfo.getString(4));
                    subjectInfo.setSubjectName(resultInfo.getString(5));
                }
            } catch (SQLException ex) {
            }
            //solve data to go to result
            LocalDate currentDate = LocalDate.now();
            DAOResult daoResult = new DAOResult();
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String formattedNumber = decimalFormat.format(10.0 * mark /numberOfQuestion );
            double grade = Double.parseDouble(formattedNumber);
            Result result = new Result(studentID, testID, grade, currentDate.toString());
            daoResult.insertResult(result);
            //set request
            request.setAttribute("numberOfQuestionStu", numberOfQuestion);
            request.setAttribute("mark", mark);
            request.setAttribute("testInfo", testInfo);
            request.setAttribute("subjectInfo", subjectInfo);
            request.setAttribute("resultOfTest", result);
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
