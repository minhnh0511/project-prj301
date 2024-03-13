/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOAccountLecturer;
import dal.DAOAccountStudent;
import dal.DAOLecturer;
import dal.DAOStudent;
import dal.DBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AccountLecturer;
import model.Lecturer;
import model.AccountStudent;
import model.Student;

/**
 *
 * @author admin
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

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
        if (service == null) {
            service = "viewProfile";
        }
        String notification = request.getParameter("notification");
        if (notification == null) {
            notification = "";
        }
        //----------------------------------------------------------------------------------------------------------------------
        //for lecturer
        if (role.equals("1")) {
            //view profile
            if (service.equals("viewProfile")) {
                String lecturerID = request.getParameter("lecturerID");
                ResultSet rs = dao.getData("select s.SubjectName, al.*\n"
                        + "from Lecturer l join Subject s on l.SubjectID = s.SubjectID\n"
                        + "	join AccountLecturer al on al.LecturerID = l.LecturerID\n"
                        + "where l.LecturerID = '" + lecturerID + "'");
                String subjectName = "";
                AccountLecturer account = new AccountLecturer();
                try {
                    if (rs.next()) {
                        subjectName = rs.getString(1);
                        account.setUserName(rs.getString(2));
                        account.setPassword(rs.getString(3));
                        account.setLecturerID(rs.getString(4));
                    }
                } catch (SQLException | NullPointerException ex) {
                }
                request.setAttribute("subjectName", subjectName);
                request.setAttribute("account", account);
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

            //update profile
            if (service.equals("updateProfile")) {
                String lecturerID = request.getParameter("lecturerID");
                String lecturerName = request.getParameter("lecturerName");
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String checkingPassword = request.getParameter("checkingPassword");
                String email = request.getParameter("email");
                String imgage = request.getParameter("imgage");
                String subjectID = request.getParameter("subjectID");
                //correct checking password
                if (!password.equals(checkingPassword)) {
                    notification = "Incorrect password. Please try again.";
                    response.sendRedirect("profile?service=viewProfile&lecturerID=" + lecturerID
                            + "&notification=" + notification);
                } //correct checking password
                else {
                    //update lecturer
                    Lecturer lecturer = new Lecturer(lecturerID, lecturerName, email, imgage, subjectID);
                    DAOLecturer daoLecturer = new DAOLecturer();
                    int updateLecturer = daoLecturer.updateLecturer(lecturer);
                    //update account lecturer
                    AccountLecturer accLec = new AccountLecturer(userName, password, lecturerID);
                    DAOAccountLecturer daoAccLec = new DAOAccountLecturer();
                    int updateAccLec = daoAccLec.updateAccountLecturer(accLec);
                    notification = (updateLecturer > 0 || updateAccLec > 0) ? "Update successfully" : "Something wrong happen. Please try later.";
                    session.removeAttribute("lecturer");
                    session.setAttribute("lecturer", lecturer);
                    response.sendRedirect("profile?service=viewProfile&lecturerID=" + lecturerID
                            + "&notification=" + notification);
                }
            }
        }

        //----------------------------------------------------------------------------------------------------------------------
        //for student
        if (role.equals("2")) {
            //view profile
            if (service.equals("viewProfile")) {
                String studentID = request.getParameter("studentID");
                ResultSet rs = dao.getData("select ast.*\n"
                        + "from Student s join AccountStudent ast on s.StudentID = ast.StudentID\n"
                        + "where s.StudentID = '" + studentID + "'");
                AccountStudent account = new AccountStudent();
                try {
                    if (rs.next()) {
                        account.setUserName(rs.getString(1));
                        account.setPassword(rs.getString(2));
                        account.setStudentID(rs.getString(3));
                    }
                } catch (SQLException | NullPointerException ex) {
                }
                request.setAttribute("account", account);
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            }

            //update profile
            if (service.equals("updateProfile")) {
                String studentID = request.getParameter("studentID");
                String studentName = request.getParameter("studentName");
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String checkingPassword = request.getParameter("checkingPassword");
                String email = request.getParameter("email");
                String imgage = request.getParameter("imgage");
                String className = request.getParameter("className");
                //correct checking password
                if (!password.equals(checkingPassword)) {
                    notification = "Incorrect password. Please try again.";
                    response.sendRedirect("profile?service=viewProfile&studentID=" + studentID
                            + "&notification=" + notification);
                } //correct checking password
                else {
                    //update student
                    Student student = new Student(studentID, studentName, email, imgage, className);
                    DAOStudent daoStudent = new DAOStudent();
                    int updateStudent = daoStudent.updateStudent(student);
                    //update account student
                    AccountStudent accStu = new AccountStudent(userName, password, studentID);
                    DAOAccountStudent daoAccStu = new DAOAccountStudent();
                    int updateAccStu = daoAccStu.updateAccountStudent(accStu);
                    notification = (updateStudent > 0 || updateAccStu > 0) ? "Update successfully" : "Something wrong happen. Please try later.";
                    session.removeAttribute("student");
                    session.setAttribute("student", student);
                    response.sendRedirect("profile?service=viewProfile&studentID=" + studentID
                            + "&notification=" + notification);
                }
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
