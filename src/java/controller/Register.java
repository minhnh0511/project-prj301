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
import java.sql.ResultSet;
import model.AccountLecturer;
import model.AccountStudent;
import model.Lecturer;
import model.Student;

/**
 *
 * @author admin
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
        String submit = request.getParameter("submit");
        //get to register form
        if (submit == null) {
            String role = request.getParameter("role");
            request.setAttribute("role", role);
            ResultSet rs = dao.getData("select * from Subject");
            request.setAttribute("data", rs);
            request.setAttribute("notification", "");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        } //get the data from register form
        else {
            String role = request.getParameter("role");
            //sign up as lecturer
            if (role.equals("1")) {
                //get data
                String lecturerID = request.getParameter("lecturerID");
                String lecturerName = request.getParameter("lecturerName");
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String checkingPassword = request.getParameter("checkingPassword");
                String email = request.getParameter("email");
                String image = request.getParameter("image");
                String subjectID = request.getParameter("subjectID");
                //sign up
                signUpLecturer(dao, request, response, lecturerID, lecturerName, 
                        userName, password, checkingPassword, email, image, subjectID);
            }
            //sign up as student
            else if(role.equals("2")) {
                String studentID = request.getParameter("studentID");
                String studentName = request.getParameter("studentName");
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String checkingPassword = request.getParameter("checkingPassword");
                String email = request.getParameter("email");
                String image = request.getParameter("image");
                String classname = request.getParameter("class");
                //sign up
                signUpStudent(dao, request, response, studentID, studentName, 
                        userName, password, checkingPassword, email, image, classname);
            }
        }
    }

    /**
     * Sign up as Lecturer
     * @param dao
     * @param request
     * @param response
     * @param lecturerID
     * @param lecturerName
     * @param userName
     * @param password
     * @param checkingPassword
     * @param email
     * @param image
     * @param subjectID
     * @throws ServletException
     * @throws IOException 
     */
    private void signUpLecturer(DBContext dao, HttpServletRequest request, HttpServletResponse response, 
            String lecturerID, String lecturerName, String userName, String password,
            String checkingPassword, String email, String image, String subjectID) 
            throws ServletException, IOException {
        //InCorrect password
        if (!password.equals(checkingPassword)) {
            request.setAttribute("role", "1");
            ResultSet rs = dao.getData("select * from Subject");
            request.setAttribute("data", rs);
            request.setAttribute("notification", "Incorrect password. Enter password again.");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
        //Correct Password
        else {
            try {
                //insert lecturer
                Lecturer lecturer = new Lecturer(lecturerID, lecturerName, email, image, subjectID);
                DAOLecturer daoLecturer = new DAOLecturer();
                int insertLecturer = daoLecturer.insertLecturer(lecturer);
                //insert account lecturer
                AccountLecturer accLec = new AccountLecturer(userName, password, lecturerID);
                DAOAccountLecturer daoAccLec = new DAOAccountLecturer();
                int insertAccLec = daoAccLec.insertAccountLecturer(accLec);
                String notification = (insertLecturer > 0 && insertAccLec > 0) ? "Sign up successfully" : "This account already exist.";
                //go to login
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                request.setAttribute("role", "1");
                ResultSet rs = dao.getData("select * from Subject");
                request.setAttribute("data", rs);
                request.setAttribute("notification", "Something wrong happen. Please try later.");
                request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            }
        }
    }
    
    /**
     * Sign up as Student
     * @param dao
     * @param request
     * @param response
     * @param studentID
     * @param studentName
     * @param userName
     * @param password
     * @param checkingPassword
     * @param email
     * @param image
     * @param classname
     * @throws ServletException
     * @throws IOException 
     */
    private void signUpStudent(DBContext dao, HttpServletRequest request, HttpServletResponse response, 
            String studentID, String studentName, String userName, String password,
            String checkingPassword, String email, String image, String classname) 
            throws ServletException, IOException {
        //InCorrect password
        if (!password.equals(checkingPassword)) {
            request.setAttribute("role", "2");
            ResultSet rs = dao.getData("select * from Subject");
            request.setAttribute("data", rs);
            request.setAttribute("notification", "Incorrect password. Enter password again.");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
        //Correct Password
        else {
            try {
                //insert student
                Student student = new Student(studentID, studentName, email, image, classname);
                DAOStudent daoStudent = new DAOStudent();
                int insertStudent = daoStudent.insertStudent(student);
                //insert account student
                AccountStudent accStu = new AccountStudent(userName, password, studentID);
                DAOAccountStudent daoAccStu = new DAOAccountStudent();
                int insertAccStu = daoAccStu.insertAccountStudent(accStu);
                String notification = (insertStudent > 0 && insertAccStu > 0) ? "Sign up successfully." : "This account already exist.";
                //go to login
                request.setAttribute("notification", notification);
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                request.setAttribute("role", "2");
                request.setAttribute("notification", "Something wrong happen. Please try later");
                request.getRequestDispatcher("/view/register.jsp").forward(request, response);
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
