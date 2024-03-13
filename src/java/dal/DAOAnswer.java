/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;

/**
 *
 * @author admin
 */
public class DAOAnswer extends DBContext {

    public int insertAnswer(Answer answer) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Answer]\n"
                + "           ([QuestionID]\n"
                + "           ,[StudentID]\n"
                + "           ,[Answer])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, answer.getQuestionID());
            pre.setString(2, answer.getStudentID());
            pre.setInt(3, answer.getAnswer());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
