/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class AccountLecturer {
    private String UserName, Password, LecturerID;

    public AccountLecturer() {
    }

    public AccountLecturer(String UserName, String Password, String LecturerID) {
        this.UserName = UserName;
        this.Password = Password;
        this.LecturerID = LecturerID;
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

    public String getLecturerID() {
        return LecturerID;
    }

    public void setLecturerID(String LecturerID) {
        this.LecturerID = LecturerID;
    }

    @Override
    public String toString() {
        return "AccountLecturer{" + "UserName=" + UserName + ", Password=" + Password + ", LecturerID=" + LecturerID + '}';
    }
    
}
