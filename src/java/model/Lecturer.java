/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Lecturer {
    private String LecturerID, LecturerName, Email, Image, SubjectID;

    public Lecturer() {
    }

    public Lecturer(String LecturerID, String LecturerName, String Email, String Image, String SubjectID) {
        this.LecturerID = LecturerID;
        this.LecturerName = LecturerName;
        this.Email = Email;
        this.Image = Image;
        this.SubjectID = SubjectID;
    }

    public String getLecturerID() {
        return LecturerID;
    }

    public void setLecturerID(String LecturerID) {
        this.LecturerID = LecturerID;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String LecturerName) {
        this.LecturerName = LecturerName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String SubjectID) {
        this.SubjectID = SubjectID;
    }

    @Override
    public String toString() {
        return "Lecturer{" + "LecturerID=" + LecturerID + ", LecturerName=" + LecturerName + ", Email=" + Email + ", Image=" + Image + ", SubjectID=" + SubjectID + '}';
    }
    
}
