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
    //private String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
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

            System.out.println("*********************got student numbers");
            con.close();
        } catch (Exception e) {

            String result = e.toString();
            System.out.println(result);    // getWindow(null).showNotification("Error");
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
           // NoteInfo ni = new NoteInfo("","","");

            //Class.forName("com.mysql.jdbc.Driver");
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
                //System.out.println(studentNumberList.get(i)+"done with names");
                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+studentNumberList.get(i)+"'");
                        //+ " and `Calendar_Year`=2017");

                List<Courses> allCourses = new ArrayList<>();
                //int size = rs.getFetchSize();
                while (rs.next()) {
                    String coursename = rs.getString(6);
                    double mainmark = rs.getDouble(9);
                    double supplementaryMark = rs.getDouble(8);

                    String outcomeResult = rs.getString(10);
                    int courseYear = rs.getInt(4);
                    int courseCredits = rs.getInt(7);
                    String codeOfCurse = rs.getString(5);
                    Courses newCourse = new Courses(coursename, mainmark, supplementaryMark,outcomeResult,courseYear,courseCredits,codeOfCurse);
                    allCourses.add(newCourse);

                }
                //System.out.println(allCourses.size()+"course size");

                //System.out.println("NO ERROR IN FIRST QUERY");
                List<StudentHistory> allStudentHistory = new ArrayList<>();
                rs = cs.executeQuery("select * from History where `Student_No.`='"+studentNumberList.get(i)+"'");
                while (rs.next()) {
                    int year = rs.getInt(3);
                    String yos= rs.getString(4);
                    String programCode= rs.getString(5);
                    String yearOutcome= rs.getString(8);
                    String outcomeDescription= rs.getString(9);
                    double averageMarks= rs.getDouble(10);
                    int enrolledCredits = rs.getInt(11);
                    int achievedCredits = rs.getInt(12);
                    //System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getDouble(9));
                    //System.out.println(rs.getInt(""));
                    StudentHistory newHistory = new StudentHistory(year, yos, programCode,yearOutcome, outcomeDescription, averageMarks, enrolledCredits, achievedCredits);
                    allStudentHistory.add(newHistory);
                }

                List<NoteInfo> allStudentNotes = new ArrayList<>();
                rs = cs.executeQuery("select * from Notes where `Student_No.`='"+studentNumberList.get(i)+"'");
                while (rs.next()){
                    String pri = rs.getString(3);
                    String stNote = rs.getString(4);
                    String userNote = rs.getString(5);

                    NoteInfo ni = new NoteInfo(pri, stNote,userNote);
                    allStudentNotes.add(ni);
                }

                students newStudent = new students(studentNum, surname, name, ProgramCode, allCourses, allStudentHistory, allStudentNotes);

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


    public void updateDBNotes(String stNum, String note){

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            PreparedStatement preparedStmt = con.prepareStatement("update Notes SET `Notes` = '"+note+"' WHERE `Student_No.` = '"+stNum+"'");
            preparedStmt.executeUpdate();
            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }






//this will need to be modified for multiple notes and users !
    public String getDBNotes(String stNum){
        String latestNote = "";

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Notes where `Student_No.` = '"+stNum+"'");

            while (rs.next()){
                latestNote = rs.getString(3);
            }



            con.close();
        } catch (Exception e) {
            String result = e.toString();

        }
        return latestNote;

    }



// ONLY adds new notes
    public void addNewNote(String stNum, NoteInfo note){

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            PreparedStatement preparedStmt = con.prepareStatement("insert into `Notes` (`Student_No.`, `Privacy`, `Notes`, `User`)" +
                    " values ('"+stNum+"','"+note.getPriv()+"','"+note.getNote()+"','"+note.getUser()+"')");

            preparedStmt.executeUpdate();
            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }



    //If NOT Exists in DB, then create. Otherwise Update Current Note in DB.
    public void DBNotes(String stNum, NoteInfo note){

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select 1 from Notes where `Student_No.` = '"+stNum+"'");

            if (rs.next() == false) {
                PreparedStatement newStatement = con.prepareStatement("insert into `Notes` (`Student_No.`, `Privacy`, `Notes`, `User`)" +
                        " values ('"+stNum+"','"+note.getPriv()+"','"+note.getNote()+"','"+note.getUser()+"')");
                newStatement.executeUpdate();

            } else{

                PreparedStatement preparedStmt = con.prepareStatement("update Notes SET `Notes` = '" + note.getNote() + "' WHERE" +
                        " `Student_No.` = '" + stNum + "'");
                preparedStmt.executeUpdate();
            }


            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }


}