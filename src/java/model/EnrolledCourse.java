/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author admin
 */
public class EnrolledCourse {
    private Student student;
    private Vector<Course> listCourse;

    public EnrolledCourse() {
    }

    public EnrolledCourse(Student student, Vector<Course> listCourse) {
        this.student = student;
        this.listCourse = listCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vector<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Vector<Course> listCourse) {
        this.listCourse = listCourse;
    }

    
    
}
 