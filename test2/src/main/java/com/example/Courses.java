package com.example;

/**
 * Created by habee on 2018/08/19.
 */
public class Courses {
    private int finalMark;
    private String courseOutcome;
    private String courseName;
    private int year;
    private String yos;
    private String courseCode;
    private int credits;
    private int suppMark;


    public Courses(String coursename, int mainmark, int supplementaryMark, String result, int courseYear, String yearOfStudy, int courseCredits, String codeOfCurse) {
        courseName = coursename;
        finalMark = mainmark;
        courseOutcome = result;
        year = courseYear;
        yos = yearOfStudy;
        courseCode = codeOfCurse;
        credits = courseCredits;
        suppMark = supplementaryMark;

    }

    public String getCourseOutcome() {
        return courseOutcome;
    }

    public void setCourseOutcome(String courseOutcome) {
        this.courseOutcome = courseOutcome;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(int finalMark) {
        this.finalMark = finalMark;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getYos() {
        return yos;
    }

    public void setYos(String yos) {
        this.yos = yos;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSuppMark() {
        return suppMark;
    }

    public void setSuppMark(int suppMark) {
        this.suppMark = suppMark;
    }
}
