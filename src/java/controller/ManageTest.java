/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOQuestion;
import dal.DAOTest;
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
import model.Question;
import model.Subject;
import model.Test;
import model.Lecturer;

/**
 *
 * @author admin
 */
@WebServlet(name = "ManageTest", urlPatterns = {"/manageTest"})
public class ManageTest extends HttpServlet {

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
        String service = request.getParameter("service");
        Lecturer lecturer = (Lecturer)session.getAttribute("lecturer");
        if (service == null) {
            service = "viewAllTest";
        }
        //view all tests
        if (service.equals("viewAllTest")) {
            ResultSet rs = dao.getData("select t.*, s.*\n"
                    + "from Test t join Lecturer l on l.LecturerID = t.LecturerID\n"
                    + "	join Subject s on s.SubjectID = l.SubjectID\n"
                    + "where l.LecturerID = '" + lecturer.getLecturerID() + "'");
            Vector<Test> testList = new Vector<>();
            Subject subject = new Subject();
            try {
                while (rs.next()) {
                    testList.add(new Test(rs.getString(1), rs.getString(2), rs.getString(3)));
                    subject.setSubjectID(rs.getString(4));
                    subject.setSubjectName(rs.getString(5));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("subject", subject);
            request.setAttribute("testList", testList);
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        }
        
        //go to add test form
        if(service.equals("addTest")) {
            Integer numberOfQuestion = 0; 
            try {
               numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
            } catch(NumberFormatException e){}
            //get the order of the last test in all tests
            ResultSet rs = dao.getData("select * from Test where TestID like '" + lecturer.getSubjectID()+ "%'");
            Vector<Test> listTest = new Vector<>();
            try {
                while(rs.next()) {
                    Test test = new Test(rs.getString(1), rs.getString(2), rs.getString(3));
                    listTest.add(test);
                }
            } catch (SQLException ex) {
            }
            String preTestID = "";
            if(!listTest.isEmpty()) {
                preTestID = listTest.get(listTest.size() - 1).getTestID();
            } 
            String newOrder; // initialize the new Order for the test ID
            //If the test list already had test(s)
            if(preTestID != null && !preTestID.equals("")) {
                int preOrder = Integer.parseInt(preTestID.substring(preTestID.length() - 2));
                newOrder = String.format("%02d", preOrder + 1);
            }
            //If the test list does not have any test yet
            else {
                newOrder = "01";
            }
            newOrder = "_T" + newOrder;
            //set request
            request.setAttribute("numberOfQuestion", numberOfQuestion);
            request.setAttribute("newOrder", newOrder);
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
        }
        
        //add test with list of questions
        if(service.equals("addTestWithQuestions")) {
            //add test
            String testID = request.getParameter("testID");
            String testName =request.getParameter("testName");
            Test newTest = new Test(testID, testName, lecturer.getLecturerID());
            DAOTest daoTest = new DAOTest();
            int insertTest = daoTest.insertTest(newTest);
            //insert test successfully
            if(insertTest > 0) {
                //add questions
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                DAOQuestion daoQuestion = new DAOQuestion();
                for(int i = 0; i < numberOfQuestion; i++) {
                    String newOrder = String.format("%02d", i + 1);
                    String questionID = request.getParameter("questionID" + newOrder);
                    String content = request.getParameter("content" + newOrder);
                    String choice1 = request.getParameter("choice1" + newOrder);
                    String choice2 = request.getParameter("choice2" + newOrder);
                    String choice3 = request.getParameter("choice3" + newOrder);
                    String choice4 = request.getParameter("choice4" + newOrder);
                    int questionKey = Integer.parseInt(request.getParameter("questionKey" + newOrder));
                    Question question = new Question(questionID, content, choice1, choice2, choice3, choice4, questionKey, testID);
                    daoQuestion.insertQuestion(question);
                }
                //reset the listTest in session
                Vector<Test> listTest = (Vector<Test>)session.getAttribute("listTest");
                listTest.add(newTest);
                session.removeAttribute("listTest");
                session.setAttribute("listTest", listTest);
                response.sendRedirect("manageTest");
            }
            //fail to insert test
            else {
                request.setAttribute("notification", "Fail to insert. Please try again.");
                request.getRequestDispatcher("manageTest").forward(request, response);
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
