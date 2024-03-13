/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOResult;
import dal.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Question;
import model.Result;
import model.Student;
import model.Subject;
import model.Test;

/**
 *
 * @author admin
 */
@WebServlet(name = "ManageResult", urlPatterns = {"/manageResult"})
public class ManageResult extends HttpServlet {

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
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute("role"));
        String service = request.getParameter("service");

        //----------------------------------------------------------------------------------------------------------------------
        //for lecturer
        if (role.equals("1")) {
            Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
            //view result of course
            if (service.equals("resultOfCourse")) {
                ResultSet rs = dao.getData("select * from Test where LecturerID = '" + lecturer.getLecturerID() + "'");
                Vector<Test> resultOfCourse = new Vector<>();
                try {
                    while (rs.next()) {
                        resultOfCourse.add(new Test(rs.getString(1), rs.getString(2), rs.getString(3)));
                    }
                } catch (SQLException ex) {
                }
                request.setAttribute("resultOfCourse", resultOfCourse);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

            //view all result of a single test
            if (service.equals("viewAllResultsOfTest")) {
                String testID = request.getParameter("testID");
                ResultSet rs = dao.getData("select r.*, s.*, t.TestName\n"
                        + "from Result r join Test t on r.TestID = t.TestID\n"
                        + "    join Student s on r.StudentID = s.StudentID\n"
                        + "where r.TestID = '" + testID + "'\n"
                        + "order by r.TestID, s.Class, r.StudentID, r.Grade");
                Vector<Result> listResultOfTest = new Vector<>();
                Vector<Student> listStudentInResult = new Vector<>();
                String testName = "";
                try {
                    while (rs.next()) {
                        listResultOfTest.add(new Result(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
                        listStudentInResult.add(new Student(rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                        testName = rs.getString(10);
                    }
                } catch (SQLException ex) {
                }
                request.setAttribute("listResultOfTest", listResultOfTest);
                request.setAttribute("listStudentInResult", listStudentInResult);
                request.setAttribute("testName", testName);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }
            
            if(service.equals("reattemp")) {
                String studentID = request.getParameter("studentID");
                String testID = request.getParameter("testID");
                DAOResult daoResult = new DAOResult();
                daoResult.deleteResult(studentID, testID);
                daoResult.deleteAnswer(studentID, testID);
                request.getRequestDispatcher("manageResult?service=viewAllResultsOfTest&testID=" + testID).forward(request, response);
            }
        }

        //----------------------------------------------------------------------------------------------------------------------
        //for student
        if (role.equals("2")) {
            Student student = (Student) session.getAttribute("student");
            //view all results
            if (service.equals("viewAllResults")) {
                //
                ResultSet rs = dao.getData("select r.*, t.TestName, l.LecturerID , s.*\n"
                        + "from Result r join Test t on r.TestID = t.TestID\n"
                        + "	join Lecturer l on l.LecturerID = t.LecturerID\n"
                        + "	join Subject s on l.SubjectID = s.SubjectID\n"
                        + "where StudentID = '" + student.getStudentID() + "'\n"
                        + "order by s.SubjectID, l.LecturerID");
                Vector<Result> listReviewResult = new Vector<>();
                Vector<Test> listReviewTest = new Vector<>();
                Vector<Subject> listReviewSubject = new Vector<>();
                try {
                    while (rs.next()) {
                        Result result = new Result(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
                        Test test = new Test(rs.getString(2), rs.getString(5), rs.getString(6));
                        Subject subject = new Subject(rs.getString(7), rs.getString(8));
                        listReviewResult.add(result);
                        listReviewTest.add(test);
                        listReviewSubject.add(subject);
                    }
                } catch (SQLException ex) {
                }
                //set request
                request.setAttribute("listReviewResult", listReviewResult);
                request.setAttribute("listReviewTest", listReviewTest);
                request.setAttribute("listReviewSubject", listReviewSubject);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

            //review a single test
            if (service.equals("reviewTest")) {
                String testID = request.getParameter("testID");
                ResultSet rs = dao.getData("select r.StudentID, r.Grade, r.Date, q.*, t.TestName, a.Answer\n"
                        + "from Result r join Student s on r.StudentID = s.StudentID\n"
                        + "	join Test t on r.TestID = t.TestID\n"
                        + "	join Question q on q.TestID = t.TestID\n"
                        + "	join Answer a on a.QuestionID = q.QuestionID\n"
                        + "where r.TestID = '" + testID + "' \n"
                        + "	and r.StudentID = '" + student.getStudentID() + "'");
                Vector<Integer> checkedAnswer = new Vector<>();
                Vector<Question> listQuestionReview = new Vector<>();
                Result resultReview = new Result();
                String testName = "";
                try {
                    while (rs.next()) {
                        //set result info
                        resultReview.setStudentID(rs.getString(1));
                        resultReview.setTestID(rs.getString(11));
                        resultReview.setGrade(rs.getDouble(2));
                        resultReview.setDate(rs.getString(3));
                        //set test name
                        testName = rs.getString(12);
                        //add question into list
                        Question question = new Question(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                                rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11));
                        listQuestionReview.add(question);
                        //get the answer
                        checkedAnswer.add(rs.getInt(13));
                    }
                } catch (SQLException ex) {
                }
                //set request
                request.setAttribute("resultReview", resultReview);
                request.setAttribute("testName", testName);
                request.setAttribute("listQuestionReview", listQuestionReview);
                request.setAttribute("checkedAnswer", checkedAnswer);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

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
