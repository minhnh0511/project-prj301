/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountStudent;

/**
 *
 * @author admin
 */
public class DAOAccountStudent extends DBContext {

    public int insertAccountStudent(AccountStudent accStu) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[AccountStudent]\n"
                + "           ([UserName]\n"
                + "           ,[Password]\n"
                + "           ,[StudentID])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, accStu.getUserName());
            pre.setString(2, accStu.getPassword());
            pre.setString(3, accStu.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccountStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateAccountStudent(AccountStudent accStu) {
        int n = 0;
        String sql = "UPDATE [dbo].[AccountStudent]\n"
                + "   SET [UserName] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[StudentID] = ?\n"
                + " WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, accStu.getUserName());
            pre.setString(2, accStu.getPassword());
            pre.setString(3, accStu.getStudentID());
            pre.setString(4, accStu.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccountStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
