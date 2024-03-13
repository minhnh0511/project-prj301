/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author admin
 */
public class DAOStudent extends DBContext {

    public int insertStudent(Student student) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([StudentID]\n"
                + "           ,[StudentName]\n"
                + "           ,[Email]\n"
                + "           ,[Image]\n"
                + "           ,[Class])\n"
                + "     VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, student.getStudentID());
            pre.setString(2, student.getStudentName());
            pre.setString(3, student.getEmail());
            pre.setString(4, student.getImage());
            pre.setString(5, student.getClassName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStudent(Student student) {
        int n = 0;
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [StudentID] = ?\n"
                + "      ,[StudentName] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[SubjectID] = ?\n"
                + " WHERE StudentID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, student.getStudentID());
            pre.setString(2, student.getStudentName());
            pre.setString(3, student.getEmail());
            pre.setString(4, student.getImage());
            pre.setString(5, student.getClassName());
            pre.setString(6, student.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
