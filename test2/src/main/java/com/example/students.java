package com.example;

import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
public class students {

    private float studentNumber;
    private String studentSurname;
    private String studentName;
    private String program;
    private List<Courses> course;
    private String yearOutCome;
    private List<StudentHistory> history;

    public students(float studentNumber, String studentSurname, String studentName, String program, List<Courses> course, String yearOutCome, List<StudentHistory> history) {
        this.studentNumber = studentNumber;
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.program = program;
        this.course = course;
        this.yearOutCome = yearOutCome;
        this.history = history;
    }

    public List<StudentHistory> getHistory() {
        return history;
    }

    public void setHistory(List<StudentHistory> history) {
        this.history = history;
    }

    public float getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(float studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<Courses> getCourse() {
        return course;
    }

    public void setCourse(List<Courses> course) {
        this.course = course;
    }

    public String getYearOutCome() {
        return yearOutCome;
    }

    public void setYearOutCome(String yearOutCome) {
        this.yearOutCome = yearOutCome;
    }
}
