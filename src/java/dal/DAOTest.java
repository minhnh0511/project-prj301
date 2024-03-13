/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Test;

/**
 *
 * @author admin
 */
public class DAOTest extends DBContext {

    public int insertTest(Test test) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Test]\n"
                + "           ([TestID]\n"
                + "           ,[TestName]\n"
                + "           ,[LecturerID])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, test.getTestID());
            pre.setString(2, test.getTestName());
            pre.setString(3, test.getLecturerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
