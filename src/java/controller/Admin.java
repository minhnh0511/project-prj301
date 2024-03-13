/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCourse;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;

/**
 *
 * @author admin
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

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
        HttpSession session = request.getSession();
        DBContext dao = new DBContext();
        String service = request.getParameter("service");

        //admin page
        if (service == null) {
            service = "";
            request.getRequestDispatcher("/view/adminLayout.jsp").forward(request, response);
        }

        //manage course
        if (service.equals("manageCourse")) {
            String lecturerID = request.getParameter("lecturerID");
            //student list
            ResultSet rs = dao.getData("select s.*\n"
                    + "from Course c join Student s on c.StudentID = s.StudentID\n"
                    + "where LecturerID = '" + lecturerID + "'\n"
                    + "order by s.Class, s.StudentID");
            Vector<Student> studentList = new Vector<>();
            try {
                while (rs.next()) {
                    Student student = new Student(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                    studentList.add(student);
                }
            } catch (SQLException ex) {
            }
            //course info
            ResultSet rsLec = dao.getData("select * from Lecturer where LecturerID = '" + lecturerID + "'");
            Lecturer lecturer = new Lecturer();
            try {
                if (rsLec.next()) {
                    lecturer.setLecturerID(rsLec.getString(1));
                    lecturer.setLecturerName(rsLec.getString(2));
                    lecturer.setEmail(rsLec.getString(3));
                    lecturer.setImage(rsLec.getString(4));
                    lecturer.setSubjectID(rsLec.getString(5));
                }
            } catch (SQLException ex) {
            }
            //set request
            request.setAttribute("studentsOfCourse", studentList);
            request.setAttribute("course", lecturer);
            request.getRequestDispatcher("/view/adminLayout.jsp").forward(request, response);
        }

        //unenroll a single student course
        if (service.equals("unenroll")) {
            String lecturerID = request.getParameter("lecturerID");
            String studentID = request.getParameter("studentID");
            DAOCourse daoCourse = new DAOCourse();
            daoCourse.deleteCourse(studentID, lecturerID);
            request.getRequestDispatcher("admin?service=manageCourse&lecturerID" + lecturerID).forward(request, response);
        }
        
        //unenroll all student from course
        if(service.equals("unenrollAll")) {
            String lecturerID = request.getParameter("lecturerID");
            DAOCourse daoCourse = new DAOCourse();
            daoCourse.deleteAllStudentFromCourse(lecturerID);
            request.getRequestDispatcher("admin?service=manageCourse&lecturerID" + lecturerID).forward(request, response); 
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
