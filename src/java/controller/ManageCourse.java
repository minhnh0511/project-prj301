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
import java.util.HashSet;
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
@WebServlet(name = "ManageCourse", urlPatterns = {"/manageCourse"})
public class ManageCourse extends HttpServlet {

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
        Student student = (Student) session.getAttribute("student");
        EnrolledCourse enrolledCourses = (EnrolledCourse) session.getAttribute("enrolledCourses");
        String service = request.getParameter("service");

        //search course
        if (service.equals("searchCourse")) {
            String searchedCourse = request.getParameter("course");
            ResultSet rs = dao.getData("select l.*, s.SubjectName\n"
                    + "from Lecturer l join Subject s on l.SubjectID = s.SubjectID\n"
                    + "where l.LecturerID = '" + searchedCourse + "'\n"
                    + "	or l.LecturerName = '" + searchedCourse + "'\n"
                    + "	or s.SubjectID = '" + searchedCourse + "'\n"
                    + "	or s.SubjectName like '%" + searchedCourse + "%'");
            Vector<Course> searchedCourses = new Vector<>();
            try {
                while (rs.next()) {
                    Lecturer lecturer = new Lecturer(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                    Subject subject = new Subject(rs.getString(5), rs.getString(6));
                    Course course = new Course(lecturer, subject);
                    searchedCourses.add(course);
                }
            } catch (SQLException ex) {
            }
            request.setAttribute("searchedCourses", searchedCourses);
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        }

        //view all enrolled courses
        if (service.equals("viewAllEnrolledCourses")) {
            request.setAttribute("request", "viewAllEnrolledCourses");
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        }

        //view course detail
        if (service.equals("viewCourseDetail")) {
            Vector<Course> listEnrolleds = enrolledCourses.getListCourse();
            String lecturerID = request.getParameter("lecturerID");
            //Check if the course is enrolled
            boolean isEnrolled = false;
            for (Course course : listEnrolleds) {
                if (course.getLecturer().getLecturerID().equals(lecturerID)) {
                    isEnrolled = true;
                    break;
                }
            }
            //get the data
            ResultSet rs = dao.getData("select s.*, t.*, l.LecturerName\n"
                    + "from Lecturer l join Subject s on l.SubjectID = s.SubjectID\n"
                    + "	join Test t on l.LecturerID = t.LecturerID\n"
                    + "where l.LecturerID = '" + lecturerID + "'");
            Subject subject = new Subject();
            String lecturerName = "";
            Vector<Test> listTestOfCourse = new Vector<>();
            try {
                while (rs.next()) {
                    //set subject
                    subject.setSubjectID(rs.getString(1));
                    subject.setSubjectName(rs.getString(2));
                    //set list test
                    Test test = new Test(rs.getString(3), rs.getString(4), rs.getString(5));
                    listTestOfCourse.add(test);
                    //set lecturerName
                    lecturerName = rs.getString(6);
                }
            } catch (SQLException ex) {
            }
            //check test is done or not
            ResultSet rsRes = dao.getData("select TestID from Result where StudentID = '" + student.getStudentID() + "'");
            HashSet<String> testIsDone = new HashSet<>();
            try {
                while (rsRes.next()) {
                    testIsDone.add(rsRes.getString(1));
                }
            } catch (SQLException ex) {
            }
            //set request
            try {
                request.setAttribute("subject", subject);
                request.setAttribute("lecturerName", lecturerName);
                request.setAttribute("listTestOfCourse", listTestOfCourse);
                request.setAttribute("testIsDone", testIsDone);
                request.setAttribute("isEnrolled", isEnrolled);
                request.getRequestDispatcher("/view/home.jsp").forward(request, response);
            } catch (Exception e) {
                PrintWriter out = response.getWriter();
                out.print("This course does not contain any content. Wait for lecturer add content to view");
            }

        }

        //enroll course
        if (service.equals("enroll")) {
            String lecturerID = request.getParameter("course");
            DAOCourse daoCourse = new DAOCourse();
            daoCourse.insertCourse(student.getStudentID(), lecturerID);
            Vector<Course> listEnrolleds = enrolledCourses.getListCourse();
            //set course info
            ResultSet rs = dao.getData("select l.*, s.SubjectName \n"
                    + "from Lecturer l join Subject s on l.SubjectID = s.SubjectID\n"
                    + "where LecturerID = '" + lecturerID + "'");
            Lecturer lecturer = new Lecturer();
            Subject subject = new Subject();
            try {
                if (rs.next()) {
                    //set lecturer info
                    lecturer.setLecturerID(rs.getString(1));
                    lecturer.setLecturerName(rs.getString(2));
                    lecturer.setEmail(rs.getString(3));
                    lecturer.setImage(rs.getString(4));
                    lecturer.setSubjectID(rs.getString(5));
                    //set subject info
                    subject.setSubjectID(rs.getString(5));
                    subject.setSubjectName(rs.getString(6));
                }
            } catch (SQLException ex) {
            }
            Course newCourse = new Course(lecturer, subject);
            //add newCOurse to list
            listEnrolleds.add(newCourse);
            //set new session
            session.removeAttribute("enrolledCourses");
            session.setAttribute("enrolledCourses", new EnrolledCourse(student, listEnrolleds));
            request.getRequestDispatcher("manageCourse?service=viewCourseDetail&lecturerID=" + lecturerID).forward(request, response);
        }

        //unenroll course
        if (service.equals("unenroll")) {
            String lecturerID = request.getParameter("course");
            DAOCourse daoCourse = new DAOCourse();
            daoCourse.deleteCourse(student.getStudentID(), lecturerID);
            Vector<Course> listEnrolleds = enrolledCourses.getListCourse();
            //set course info
            for (int i = 0; i < listEnrolleds.size(); i++) {
                if (listEnrolleds.get(i).getLecturer().getLecturerID().equals(lecturerID)) {
                    listEnrolleds.remove(i);
                    break;
                }
            }
            session.removeAttribute("enrolledCourses");
            session.setAttribute("enrolledCourses", new EnrolledCourse(student, listEnrolleds));
            request.getRequestDispatcher("manageCourse?service=viewCourseDetail&lecturerID=" + lecturerID).forward(request, response);
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
