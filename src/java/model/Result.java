/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author admin
 */
public class Result {
    private String StudentID, TestID;
    private double grade;
    private String date;

    public Result() {
    }

    public Result(String StudentID, String TestID, double grade, String date) {
        this.StudentID = StudentID;
        this.TestID = TestID;
        this.grade = grade;
        this.date = date;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String TestID) {
        this.TestID = TestID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
}
