/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOCourse extends DBContext {

    public int insertCourse(String studentID, String lecturerID) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Course]\n"
                + "           ([StudentID]\n"
                + "           ,[LecturerID])\n"
                + "     VALUES(?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, studentID);
            pre.setString(2, lecturerID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCourse(String studentID, String lecturerID) {
        int n = 0;
        String sql = "delete from Course \n"
                + "where StudentID = '" + studentID + "' and LecturerID = '" + lecturerID + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteAllStudentFromCourse(String lecturerID) {
        int n = 0;
        String sql = "delete from Course \n"
                + "where LecturerID = '" + lecturerID + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
