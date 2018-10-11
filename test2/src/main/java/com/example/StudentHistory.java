package com.example;

/**
 * Created by habee on 2018/08/30.
 */
public class StudentHistory {
    private int year;
    private String yos;
    private String programCode;
    private String yearOutcome;
    private String outcomeDescription;
    private double averageMarks;
    private int enrolledCredits;
    private int achievedCredits;


    public StudentHistory(int year, String yos, String programCode, String yearOutcome, String outcomeDescription, double averageMarks, int enrolledCredits, int achievedCredits) {
        this.year = year;
        this.yos = yos;
        this.programCode = programCode;
        this.yearOutcome = yearOutcome;
        this.outcomeDescription = outcomeDescription;
        this.averageMarks = averageMarks;
        this.enrolledCredits = enrolledCredits;
        this.achievedCredits = achievedCredits;
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

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getYearOutcome() {
        return yearOutcome;
    }

    public void setYearOutcome(String yearOutcome) {
        this.yearOutcome = yearOutcome;
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public void setOutcomeDescription(String outcomeDescription) {
        this.outcomeDescription = outcomeDescription;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(double averageMarks) {
        this.averageMarks = averageMarks;
    }

    public int getEnrolledCredits() {
        return enrolledCredits;
    }

    public void setEnrolledCredits(int enrolledCredits) {
        this.enrolledCredits = enrolledCredits;
    }

    public int getAchievedCredits() {
        return achievedCredits;
    }

    public void setAchievedCredits(int achievedCredits) {
        this.achievedCredits = achievedCredits;
    }
}

