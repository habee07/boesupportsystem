package com.example;

import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
public class students implements java.io.Serializable {

    private int uniqueID;
    private String studentNumber;
    private String studentSurname;
    private String studentName;
    private String program;
    private List<Courses> course;
    private List<StudentHistory> history;
   // private List<NoteInfo> stNotes;
   private List<Courses> previouscourses;
    private List<String> badData;

    public students(int uniqueID, String studentNumber, String studentSurname, String studentName, String program, List<Courses> course, List<Courses> previouscourses, List<StudentHistory> history, List<String> badData) {
        this.uniqueID = uniqueID;
        this.studentNumber = studentNumber;
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.program = program;
        this.course = course;
        this.history = history;
        //this.stNotes = stNotes;
        this.previouscourses =previouscourses;
        this.badData =badData;
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

    public List<String> getBadData() {
        return badData;
    }

    public void setBadData(List<String> badData) {
        this.badData = badData;
    }

    public List<Courses> getPreviouscourses() {
        return previouscourses;
    }

    public void setPreviouscourses(List<Courses> previouscourses) {
        this.previouscourses = previouscourses;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**public List<NoteInfo> getStNotes() {
        return stNotes;
    }

    public void setStNotes(List<NoteInfo> stNotes) {
        this.stNotes = stNotes;
    }
     **/
}
