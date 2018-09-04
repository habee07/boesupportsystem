package com.mycompany.tutorial;
/**
 *
 * @author Craig
 */
public class Student {
    private String ProgramCode;
    private long StudentNumber;
    private String FirstName;
    private String LastName;
    private String RegStatus;
    private String CourseAttemptStatus;
    private int CalendarInstanceYear;
    private String CourseCode;
    private String CourseTitle;
    private int SuppMark;
    private int FinalMark;
    private String FinalGrade;
    private int NQFCredit;

    public Student(String ProgramCode, String RegStatus,String CourseAttemptStatus,  long StudentNumber, String FirstName,String LastName, int CalenderInstanceYear, String CourseCode, String CourseTitle,int NQFCredit, int SuppMark, int FinalMark, String FinalGrade){
        this.ProgramCode = ProgramCode;
        this.RegStatus = RegStatus;
        this.CourseAttemptStatus = CourseAttemptStatus;
        this.CalendarInstanceYear = CalenderInstanceYear;
        this.CourseCode = CourseCode;
        this.CourseTitle = CourseTitle;
        this.SuppMark = SuppMark;
        this.FinalMark = FinalMark;
        this.FinalGrade = FinalGrade;
        this.NQFCredit = NQFCredit;
        this.StudentNumber = StudentNumber;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public long getStudentNumber(){
        return StudentNumber;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getProgramCode() {
        return ProgramCode;
    }
}
