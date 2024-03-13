/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Student {
    private String StudentID, StudentName, Email, Image, Class;

    public Student() {
    }

    public Student(String StudentID, String StudentName, String Email, String Image, String Class) {
        this.StudentID = StudentID;
        this.StudentName = StudentName;
        this.Email = Email;
        this.Image = Image;
        this.Class = Class;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
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

    public String getClassName() {
        return Class;
    }

    public void setClass(String Class) {
        this.Class = Class;
    }

    @Override
    public String toString() {
        return "Student{" + "StudentID=" + StudentID + ", StudentName=" + StudentName + ", Email=" + Email + ", Image=" + Image + ", Class=" + Class + '}';
    }
    
    
}
