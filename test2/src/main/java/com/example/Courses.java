package com.example;

/**
 * Created by habee on 2018/08/19.
 */
public class Courses {
    private double finalMark;
    private String courseOutcome;
    private String courseName;
    private int year;
    private String courseCode;
    private int credits;
    private String strSupp;
    private double suppMark;


    public Courses(String coursename, double mainmark, double supplementaryMark, String result, int courseYear, int courseCredits, String codeOfCurse) {
        courseName = coursename;
        finalMark = mainmark;
        courseOutcome = result;
        year = courseYear;
        courseCode = codeOfCurse;
        credits = courseCredits;
        suppMark = supplementaryMark;

    }
    public String getStrSupp() {
        Double mark = getSuppMark();
        setStrSupp(Double.toString(mark));
        if(mark == -1.0){
            setStrSupp("");
        }

        return strSupp;
    }

    public void setStrSupp(String strSupp) {
        this.strSupp = strSupp;
    }
    public String getCourseOutcome() {
        return courseOutcome;
    }

    public void setCourseOutcome(String courseOutcome) {
        this.courseOutcome = courseOutcome;
    }

    public double getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(double finalMark) {
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

    public double getSuppMark() {
        return suppMark;
    }

    public void setSuppMark(double suppMark) {
        this.suppMark = suppMark;
    }
}
