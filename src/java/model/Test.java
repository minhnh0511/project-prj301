/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Test {
    private String TestID, TestName, LecturerID;

    public Test() {
    }

    public Test(String TestID, String TestName, String LecturerID) {
        this.TestID = TestID;
        this.TestName = TestName;
        this.LecturerID = LecturerID;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String TestID) {
        this.TestID = TestID;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String TestName) {
        this.TestName = TestName;
    }

    public String getLecturerID() {
        return LecturerID;
    }

    public void setLecturerID(String LecturerID) {
        this.LecturerID = LecturerID;
    }
    
}
