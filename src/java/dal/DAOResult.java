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
import model.Result;

/**
 *
 * @author admin
 */
public class DAOResult extends DBContext {

    public int insertResult(Result result) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Result]\n"
                + "           ([StudentID]\n"
                + "           ,[TestID]\n"
                + "           ,[Grade]\n"
                + "           ,[Date])\n"
                + "     VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, result.getStudentID());
            pre.setString(2, result.getTestID());
            pre.setDouble(3, result.getGrade());
            pre.setString(4, result.getDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteResult(String studentID, String testID) {
        int n = 0;
        String sql = "delete from [Result] \n"
                + "where StudentID = '" + studentID + "' and testID = '" + testID + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteAnswer(String studentID, String testID) {
        int n = 0;
        String sql = "delete from Answer \n"
                + "where StudentID = '" + studentID + "'\n"
                + "	and QuestionID in (\n"
                + "		select QuestionID from Question where TestID = '" + testID + "'\n"
                + "	)";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
