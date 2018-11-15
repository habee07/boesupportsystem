package com.example;

import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
public class students {

    private String studentNumber;
    private String studentSurname;
    private String studentName;
    private String program;
    private List<Courses> course;
    private List<StudentHistory> history;
    private List<NoteInfo> stNotes;

    public students(String studentNumber, String studentSurname, String studentName, String program, List<Courses> course, List<StudentHistory> history, List<NoteInfo> stNotes) {
        this.studentNumber = studentNumber;
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.program = program;
        this.course = course;
        this.history = history;
        this.stNotes = stNotes;
    }

    public List<StudentHistory> getHistory() {
        return history;
    }

    public void setHistory(List<StudentHistory> history) {
        this.history = history;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
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

    public List<NoteInfo> getStNotes() {
        return stNotes;
    }

    public void setStNotes(List<NoteInfo> stNotes) {
        this.stNotes = stNotes;
    }
}
