/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountLecturer;

/**
 *
 * @author admin
 */
public class DAOAccountLecturer extends DBContext {

    public int insertAccountLecturer(AccountLecturer accLec) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[AccountLecturer]\n"
                + "           ([UserName]\n"
                + "           ,[Password]\n"
                + "           ,[LecturerID])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, accLec.getUserName());
            pre.setString(2, accLec.getPassword());
            pre.setString(3, accLec.getLecturerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccountLecturer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateAccountLecturer(AccountLecturer accLec) {
        int n = 0;
        String sql = "UPDATE [dbo].[AccountLecturer]\n"
                + "   SET [UserName] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[LecturerID] = ?\n"
                + " WHERE LecturerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, accLec.getUserName());
            pre.setString(2, accLec.getPassword());
            pre.setString(3, accLec.getLecturerID());
            pre.setString(4, accLec.getLecturerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccountLecturer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
