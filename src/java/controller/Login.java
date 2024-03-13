/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.util.Vector;
import model.Course;
import model.EnrolledCourse;
import model.Subject;
import model.Test;
import model.Lecturer;
import model.Student;

/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
        if (submit == null) {
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            switch (role) {
                case "1":
                    loginLecturer(request, response, session, dao, userName, password);
                    break;
                case "2":
                    loginStudent(request, response, session, dao, userName, password);
                    break;
                case "3":
                    loginAdmin(request, response, session, dao, userName, password);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Login as Lecturer
     *
     * @param request
     * @param response
     * @param session
     * @param dao
     * @param userName
     * @param password
     * @throws IOException
     * @throws ServletException
     */
    private void loginLecturer(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            DBContext dao, String userName, String password) throws IOException, ServletException {
        ResultSet rs = dao.getData("select l.*\n"
                + "from AccountLecturer al join Lecturer l on al.LecturerID = l.LecturerID\n"
                + "where al.UserName = '" + userName + "' and al.Password = '" + password + "'");
        Lecturer lecturer = new Lecturer();
        try {
            if (rs.next()) {
                lecturer.setLecturerID(rs.getString(1));
                lecturer.setLecturerName(rs.getString(2));
                lecturer.setEmail(rs.getString(3));
                lecturer.setImage(rs.getString(4));
                lecturer.setSubjectID(rs.getString(5));
            }
        } catch (SQLException ex) {
        }
        if (lecturer.getLecturerID() != null) {
            session.setAttribute("role", 1);
            session.setAttribute("lecturer", lecturer);
            ResultSet rsTest = dao.getData("select t.*\n"
                    + "from Test t join Lecturer l on t.LecturerID = l.LecturerID\n"
                    + "where t.LecturerID = '" + lecturer.getLecturerID() + "'");
            Vector<Test> listTest = new Vector<>();
            try {
                while (rsTest.next()) {
                    listTest.add(new Test(rsTest.getString(1), rsTest.getString(2), rsTest.getString(3)));
                }
            } catch (SQLException ex) {
            }
            session.setAttribute("listTest", listTest);
            response.sendRedirect("home");
        } else {
            request.setAttribute("notification", "Incorrect UserName or Password. Please try again.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }

    /**
     * Login as Student
     *
     * @param request
     * @param response
     * @param session
     * @param dao
     * @param userName
     * @param password
     * @throws IOException
     * @throws ServletException
     */
    private void loginStudent(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            DBContext dao, String userName, String password) throws IOException, ServletException {
        ResultSet rs = dao.getData("select s.*\n"
                + "from AccountStudent ast join Student s on ast.StudentID = s.StudentID\n"
                + "where ast.UserName = '" + userName + "' and ast.Password = '" + password + "'");
        Student student = new Student();
        try {
            if (rs.next()) {
                student.setStudentID(rs.getString(1));
                student.setStudentName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setImage(rs.getString(4));
                student.setClass(rs.getString(5));
            }
        } catch (SQLException ex) {
        }
        if (student.getStudentID() != null) {
            session.setAttribute("role", 2);
            session.setAttribute("student", student);
            ResultSet courseList = dao.getData("select l.*, s.SubjectName\n"
                    + "from Course c join Lecturer l on c.LecturerID = l.LecturerID\n"
                    + "	join Subject s on l.SubjectID = s.SubjectID\n"
                    + "where StudentID = '" + student.getStudentID() + "'");
            Vector<Course> listCourse = new Vector<>();
            try {
                while(courseList.next()) {
                    Lecturer lecturer = new Lecturer(courseList.getString(1), courseList.getString(2), 
                            courseList.getString(3), courseList.getString(4), courseList.getString(5));
                    Subject subject = new Subject(courseList.getString(5), courseList.getString(6));
                    Course course = new Course(lecturer, subject);
                    listCourse.add(course);
                }
            } catch (SQLException ex) {
            }
            session.setAttribute("enrolledCourses", new EnrolledCourse(student, listCourse));
            response.sendRedirect("home");
        } else {
            request.setAttribute("notification", "Incorrect UserName or Password. Please try again.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }
    
    /**
     * Login as admin
     * 
     * @param request
     * @param response
     * @param session
     * @param dao
     * @param userName
     * @param password
     * @throws IOException
     * @throws ServletException 
     */
    private void loginAdmin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            DBContext dao, String userName, String password) throws IOException, ServletException {
        ResultSet rs = dao.getData("select * from Admin where UserName = '" + userName + "' and Password = '" + password + "'");
        boolean isAdmin = false;
        try {
            if(rs.next()) {
                isAdmin = true;
            }
        } catch (SQLException ex) {
        }
        if(isAdmin) {
            //set courses
            ResultSet rsCourse = dao.getData("select * from Lecturer");
            Vector<Lecturer> courseList = new Vector<>();
            try {
                while(rsCourse.next()) {
                    Lecturer lecturer = new Lecturer(rsCourse.getString(1), rsCourse.getString(2),
                            rsCourse.getString(3), rsCourse.getString(4), rsCourse.getString(5));
                    courseList.add(lecturer);
                }
            } catch (SQLException ex) {
            }
            //set subjects
            ResultSet rsSubject = dao.getData("select * from Subject");
            Vector<Subject> subjectList = new Vector<>();
            try {
                while(rsSubject.next()) {
                    subjectList.add(new Subject(rsSubject.getString(1), rsSubject.getString(2)));
                }
            } catch (SQLException ex) {
            }
            //set session
            session.setAttribute("role", 3);
            session.setAttribute("courseList", courseList);
            session.setAttribute("subjectList", subjectList);
            response.sendRedirect("admin");
        } else {
            request.setAttribute("notification", "Incorrect UserName or Password. Please try again.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
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
