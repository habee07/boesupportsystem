package com.example;

/**
 * Created by habee on 2018/09/04.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//"jdbc:mysql://sdp.ms.wits.ac.za:3306/deploymentDB","username","password");
class MysqlCon{
    private Connection con;
    private PreparedStatement ps;
    private Statement cs;
    private ResultSet rs;
    //private String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/deploymentDB";
    private String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB";
    private List<String> studentNumberList;

    public void getStudentNumbers() {
        studentNumberList = new ArrayList<>();


        /* Add a few items in the table. */
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Students");
            while (rs.next()) {
                String result = rs.getString(2);
                studentNumberList.add(result);
                //addItem(new Object[] { rs.getString(1), rs.getInt(2) }, rs.getInt(2));
            }

           // System.out.println("*********************got student numbers");
            con.close();
        } catch (Exception e) {

            String result = e.toString();
            //System.out.println(result);    // getWindow(null).showNotification("Error");
        }

    }

    public List<students> getStudentObjects(){
        getStudentNumbers();
        List<students> allStudents = new ArrayList<>();

        try {
            String surname = "";
            String name = "";
            String ProgramCode= "";
            String studentNum= "";

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for(int i = 0; i < studentNumberList.size();i++) {
                rs = cs.executeQuery("select * from Students where `Student_No.`='"+studentNumberList.get(i)+"'");
                while (rs.next()) {
                    surname = rs.getString(4);
                    name = rs.getString(3);
                    ProgramCode =rs.getString(1) ;
                    studentNum = rs.getString(2);


                    //addItem(new Object[] { rs.getString(1), rs.getInt(2) }, rs.getInt(2));
                }
                rs = cs.executeQuery("select * from "+studentNumberList.get(i)+"Courses");
                List<Courses> allCourses = new ArrayList<>();
                while (rs.next()) {
                    String coursename = rs.getString(5);
                    double mainmark = rs.getDouble(8);
                    double supplementaryMark = rs.getDouble(7);
                    String outcomeResult = rs.getString(9);
                    int courseYear = rs.getInt(3);
                    int courseCredits = rs.getInt(6);
                    String codeOfCurse = rs.getString(4);
                    Courses newCourse = new Courses(coursename, mainmark, supplementaryMark,outcomeResult,courseYear,courseCredits,codeOfCurse);
                    allCourses.add(newCourse);
                }
                //System.out.println("NO ERROR IN FIRST QUERY");
                List<StudentHistory> allStudentHistory = new ArrayList<>();
                rs = cs.executeQuery("select * from "+studentNumberList.get(i)+"History");
                while (rs.next()) {
                    int year = rs.getInt(2);
                    String yos= rs.getString(3);
                    String programCode= rs.getString(4);
                    String yearOutcome= rs.getString(7);
                    String outcomeDescription= rs.getString(8);
                    double averageMarks= rs.getDouble(9);
                    int enrolledCredits = rs.getInt(10);
                    int achievedCredits = rs.getInt(11);
                    //System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getDouble(9));
                    //System.out.println(rs.getInt(""));
                    StudentHistory newHistory = new StudentHistory(year, yos, programCode,yearOutcome, outcomeDescription, averageMarks, enrolledCredits, achievedCredits);
                    allStudentHistory.add(newHistory);
                }

                students newStudent = new students(studentNum, surname, name, ProgramCode, allCourses, allStudentHistory );
                allStudents.add(newStudent);

            }

            //System.out.println("*********************student objeccts done");
            con.close();
        } catch (Exception e) {

            String result = e.toString();
           // System.out.println(result);    // getWindow(null).showNotification("Error");
        }
    return allStudents;
    }
}