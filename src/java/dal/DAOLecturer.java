/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;

/**
 *
 * @author admin
 */
public class DAOLecturer extends DBContext {

    public int insertLecturer(Lecturer lecturer) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Lecturer]\n"
                + "           ([LecturerID]\n"
                + "           ,[LecturerName]\n"
                + "           ,[Email]\n"
                + "           ,[Image]\n"
                + "           ,[SubjectID])\n"
                + "     VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, lecturer.getLecturerID());
            pre.setString(2, lecturer.getLecturerName());
            pre.setString(3, lecturer.getEmail());
            pre.setString(4, lecturer.getImage());
            pre.setString(5, lecturer.getSubjectID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLecturer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateLecturer(Lecturer lecturer) {
        int n = 0;
        String sql = "UPDATE [dbo].[Lecturer]\n"
                + "   SET [LecturerID] = ?\n"
                + "      ,[LecturerName] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[SubjectID] = ?\n"
                + " WHERE LecturerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, lecturer.getLecturerID());
            pre.setString(2, lecturer.getLecturerName());
            pre.setString(3, lecturer.getEmail());
            pre.setString(4, lecturer.getImage());
            pre.setString(5, lecturer.getSubjectID());
            pre.setString(6, lecturer.getLecturerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLecturer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
