/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class AccountStudent {
    private String UserName, Password, StudentID;

    public AccountStudent() {
    }

    public AccountStudent(String UserName, String Password, String StudentID) {
        this.UserName = UserName;
        this.Password = Password;
        this.StudentID = StudentID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    @Override
    public String toString() {
        return "AccountStudent{" + "UserName=" + UserName + ", Password=" + Password + ", StudentID=" + StudentID + '}';
    }
    
}
