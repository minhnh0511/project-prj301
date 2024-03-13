/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;

/**
 *
 * @author admin
 */
public class DAOQuestion extends DBContext {

    public int insertQuestion(Question question) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Question]\n"
                + "           ([QuestionID]\n"
                + "           ,[Content]\n"
                + "           ,[Choice1]\n"
                + "           ,[Choice2]\n"
                + "           ,[Choice3]\n"
                + "           ,[Choice4]\n"
                + "           ,[QuestionKey]\n"
                + "           ,[TestID])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, question.getQuestionID());
            pre.setString(2, question.getContent());
            pre.setString(3, question.getChoice1());
            pre.setString(4, question.getChoice2());
            pre.setString(5, question.getChoice3());
            pre.setString(6, question.getChoice4());
            pre.setInt(7, question.getQuestionKey());
            pre.setString(8, question.getTestID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
