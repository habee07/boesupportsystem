package com.example;

/**
 * Created by habee on 2018/09/04.
 */
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//"jdbc:mysql://sdp.ms.wits.ac.za:3306/deploymentDB","username","password");
class MysqlCon{
    private Connection con;
    private PreparedStatement ps;
    private Statement cs;
    private ResultSet rs;
    //private String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
    private String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB2";
    List<List<String>> fullListofLists;
     List<String> studentNumberList;
     List<String> currStudentNumberList;
    int numberOfPages;
    int numberOfItemsPerPage = 4;


    public void getStudentNumbers() {
        fullListofLists = new ArrayList<>();
        numberOfPages = -1;
        studentNumberList = new ArrayList<>();


        /* Add a few items in the table. */
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Students");
            while (rs.next()) {
                String result = rs.getString(2);
                studentNumberList.add(result);
                //addItem(new Object[] { rs.getString(1), rs.getInt(2) }, rs.getInt(2));
            }

            System.out.println("*********************got student numbers");
            con.close();
            numberOfPages = (studentNumberList.size()/numberOfItemsPerPage)+1;
            System.out.println("number of pages:" + numberOfPages);
            for(int k=0;k<numberOfPages;k++){
                List<String> tempList = new ArrayList<>();
                fullListofLists.add(tempList);

            }

            for(int i=0;i<numberOfPages;i++){
                for(int j=0;j<numberOfItemsPerPage;j++){
                    if(j+(4*i) < studentNumberList.size()){
                        fullListofLists.get(i).add(studentNumberList.get(j+(4*i)));
                    }
                }

            }
            for(int i = 0;i < fullListofLists.size();i++){
                System.out.println("list " + i);
                for(int j=0;j<fullListofLists.get(i).size();j++){
                    System.out.println(fullListofLists.get(i).get(j));
                }
            }

        } catch (Exception e) {

            String result = e.toString();
            System.out.println(result);    // getWindow(null).showNotification("Error");
        }

    }

    public List<students> getStudentObjects(){
        int curryear = Calendar.getInstance().get(Calendar.YEAR);
        String stringcurryear = Integer.toString(curryear);
        getStudentNumbers();
        List<students> allStudents = new ArrayList<>();

        try {
            String surname = "";
            String name = "";
            String ProgramCode= "";
            String studentNum= "";
            int uniqueID = -1;
            //NoteInfo ni = new NoteInfo("","");

            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for(int i = 0; i < studentNumberList.size();i++) {
                rs = cs.executeQuery("select * from Students where `Student_No.`='"+studentNumberList.get(i)+"'");
                while (rs.next()) {
                    surname = rs.getString(4);
                    name = rs.getString(3);
                    ProgramCode =rs.getString(1) ;
                    studentNum = rs.getString(2);
                    uniqueID = rs.getInt(5);


                    //addItem(new Object[] { rs.getString(1), rs.getInt(2) }, rs.getInt(2));
                }
                //System.out.println(studentNumberList.get(i)+"done with names");
                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+studentNumberList.get(i)+"' and `Calendar_Year`=2017 and `Bad_Data` is null");
                //System.out.println("QUERY DONE");
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


                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+studentNumberList.get(i)+"' and NOT `Calendar_Year`=2017 and `Bad_Data` is null");
                List<Courses> previousCourses = new ArrayList<>();
                while (rs.next()) {
                    String prevcoursename = rs.getString(6);
                    double prevmainmark = rs.getDouble(9);
                    double prevsupplementaryMark = rs.getDouble(8);
                    String prevoutcomeResult = rs.getString(10);
                    int prevcourseYear = rs.getInt(4);
                    int prevcourseCredits = rs.getInt(7);
                    String prevcodeOfCurse = rs.getString(5);
                    Courses prevnewCourse = new Courses(prevcoursename, prevmainmark, prevsupplementaryMark,prevoutcomeResult,prevcourseYear,prevcourseCredits,prevcodeOfCurse);
                    previousCourses.add(prevnewCourse);
                }

                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+studentNumberList.get(i)+"' and `Bad_Data` is not null");
                List<String> badDataList = new ArrayList<>();
                while (rs.next()) {
                    String badDataLine = rs.getString(11);
                    badDataList.add(badDataLine);
                }

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
                    StudentHistory newHistory = new StudentHistory(year, yos, programCode,yearOutcome, outcomeDescription, averageMarks, enrolledCredits, achievedCredits);
                    allStudentHistory.add(newHistory);
                }

                students newStudent = new students(uniqueID, studentNum, surname, name, ProgramCode ,allCourses, previousCourses, allStudentHistory, badDataList);

                allStudents.add(newStudent);

            }

            con.close();
        } catch (Exception e) {

            String result = e.toString();
           //System.out.println(result);    // getWindow(null).showNotification("Error");
        }
    return allStudents;
    }


    public List<students> getStudentObjects(int pageNumber){

        currStudentNumberList = fullListofLists.get(pageNumber-1);
        List<students> allStudents = new ArrayList<>();

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            for(int i = 0; i < currStudentNumberList.size();i++) {

                rs = cs.executeQuery("select serialised_object from serialiseobjects where `student_number`='" + currStudentNumberList.get(i)+"'");
                rs.next();

                byte[] buf = rs.getBytes("serialised_object");
                ObjectInputStream objectIn = null;
                if (buf != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
                Object object = objectIn.readObject();
                students temp = (students) object;

                System.out.println(temp.getStudentNumber());

                allStudents.add(temp);


            }

            con.close();
            cs.close();
        } catch (Exception e) {

            String result = e.toString();
            System.out.println(result);    // getWindow(null).showNotification("Error");
        }
        return allStudents;
    }


    public List<students> getStudentObjects1(int pageNumber){
        int curryear = Calendar.getInstance().get(Calendar.YEAR);
        String stringcurryear = Integer.toString(curryear);

        currStudentNumberList = fullListofLists.get(pageNumber-1);


        List<students> allStudents = new ArrayList<>();

        try {
            String surname = "";
            String name = "";
            String ProgramCode= "";
            String studentNum= "";
            int uniqueID = -1;

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for(int i = 0; i < currStudentNumberList.size();i++) {
                rs = cs.executeQuery("select * from Students where `Student_No.`='"+currStudentNumberList.get(i)+"'");
                while (rs.next()) {
                    surname = rs.getString(4);
                    name = rs.getString(3);
                    ProgramCode =rs.getString(1) ;
                    studentNum = rs.getString(2);
                    uniqueID = rs.getInt(5);

                }

                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+currStudentNumberList.get(i)+"' and `Calendar_Year`=2017 and `Bad_Data` is null");

                List<Courses> allCourses = new ArrayList<>();

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

                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+currStudentNumberList.get(i)+"' and NOT `Calendar_Year`=2017 and `Bad_Data` is null");
                List<Courses> previousCourses = new ArrayList<>();
                while (rs.next()) {
                    String prevcoursename = rs.getString(6);
                    double prevmainmark = rs.getDouble(9);
                    double prevsupplementaryMark = rs.getDouble(8);
                    String prevoutcomeResult = rs.getString(10);
                    int prevcourseYear = rs.getInt(4);
                    int prevcourseCredits = rs.getInt(7);
                    String prevcodeOfCurse = rs.getString(5);
                    Courses prevnewCourse = new Courses(prevcoursename, prevmainmark, prevsupplementaryMark,prevoutcomeResult,prevcourseYear,prevcourseCredits,prevcodeOfCurse);
                    previousCourses.add(prevnewCourse);
                }

                rs = cs.executeQuery("select * from Courses where `Student_No.`='"+currStudentNumberList.get(i)+"' and `Bad_Data` is not null");
                List<String> badDataList = new ArrayList<>();
                while (rs.next()) {
                    String badDataLine = rs.getString(11);
                    badDataList.add(badDataLine);
                }

                List<StudentHistory> allStudentHistory = new ArrayList<>();
                rs = cs.executeQuery("select * from History where `Student_No.`='"+currStudentNumberList.get(i)+"'");
                while (rs.next()) {
                    int year = rs.getInt(3);
                    String yos= rs.getString(4);
                    String programCode= rs.getString(5);
                    String yearOutcome= rs.getString(8);
                    String outcomeDescription= rs.getString(9);
                    double averageMarks= rs.getDouble(10);
                    int enrolledCredits = rs.getInt(11);
                    int achievedCredits = rs.getInt(12);
                    StudentHistory newHistory = new StudentHistory(year, yos, programCode,yearOutcome, outcomeDescription, averageMarks, enrolledCredits, achievedCredits);
                    allStudentHistory.add(newHistory);
                }


                students newStudent = new students(uniqueID,studentNum, surname, name, ProgramCode ,allCourses, previousCourses, allStudentHistory, badDataList);

                allStudents.add(newStudent);

            }

            System.out.println("*********************student objeccts done");
            con.close();
        } catch (Exception e) {

            String result = e.toString();
            // System.out.println(result);    // getWindow(null).showNotification("Error");
        }
        return allStudents;
    }


    public void updatePubDBNotes(String stNum, String userName, String notePublic){

        try {
            //con = DriverManager.getConnection(dbUrl, "username", "password");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select 1 from Notes where `Student_No.` = '"+stNum+"' AND `User` = '" +userName+"'");

            if (rs.next() == false) {
                PreparedStatement newStatement = con.prepareStatement("insert into `Notes` (`Student_No.`, `PrivNotes`, `PubNotes`, `User`)" +
                        " values ('"+stNum+"','','"+notePublic+"','"+userName+"')");
                newStatement.executeUpdate();

            } else {
                PreparedStatement preparedStmt = con.prepareStatement("update Notes SET `PubNotes` = '" + notePublic + "' WHERE `Student_No.` = '" + stNum + "' AND `User` = '" + userName + "'");
                preparedStmt.executeUpdate();
            }
            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }

    public void updatePrivDBNotes(String stNum,  String userName, String notePrivate){

        try {
                con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
                cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = cs.executeQuery("select 1 from Notes where `Student_No.` = '"+stNum+"' AND `User` = '" +userName+"'");

                if (rs.next() == false) {
                    PreparedStatement newStatement = con.prepareStatement("insert into `Notes` (`Student_No.`, `PrivNotes`, `PubNotes`, `User`)" +
                            " values ('"+stNum+"','"+notePrivate+"','','"+userName+"')");
                    newStatement.executeUpdate();

                } else {
                    //con = DriverManager.getConnection(dbUrl, "username", "password");
                    PreparedStatement preparedStmt = con.prepareStatement("update Notes SET `PrivNotes` = '" + notePrivate + "' WHERE `Student_No.` = '" + stNum + "' AND `User` = '" + userName + "'");
                    preparedStmt.executeUpdate();

                }
            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }

    public List<String> getDBNotes(String stNum , String userName){
        String PrivNote = "";
        String PubNote = "";
        List<String> notes = new ArrayList<>();

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Notes where `Student_No.` = '"+stNum+"' AND `User` = '"+userName+"'");

            while (rs.next()){
                PubNote = rs.getString(4);
                PrivNote = rs.getString(3);
            }




            con.close();
        } catch (Exception e) {
            String result = e.toString();
            System.out.println("catch ");

        }
        notes.add(PrivNote);
        notes.add(PubNote);
        return notes;

    }

    public List<Users> getUsers() {

        List<Users> userList = new ArrayList<>();
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");

            Statement cs;
            ResultSet rs;

            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Users");

            while (rs.next()) {
                String Name = rs.getString(3);
                String userName = rs.getString(2);
                String Bio = rs.getString(5);
                String Discipline = rs.getString(7);

                Users temp = new Users(userName, Name, Discipline, Bio);
                userList.add(temp);


            }

            con.close();
        }
        catch (Exception e) {
            String result = e.toString();

        }
        return userList;
    }

    //If NOT Exists in DB, then create. Otherwise Update Current Note in DB.
    public void DBNotes(String stNum, NoteInfo note){

        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select 1 from Notes where `Student_No.` = '"+stNum+"'");

            if (rs.next() == false) {
                PreparedStatement newStatement = con.prepareStatement("insert into `Notes` (`Student_No.`, `PrivNotes`, `PubNotes`, `User`)" +
                        " values ('"+stNum+"','"+note.getNotePriv()+"','"+note.getNotePub()+"','"+note.getUser()+"')");
                newStatement.executeUpdate();

            } else{

                PreparedStatement preparedStmt = con.prepareStatement("update Notes SET `PubNotes` = '"+note.getNotePub()+"' , `PrivNotes` = '" +note.getNotePriv()+ "' WHERE" +
                        " `Student_No.` = '" + stNum + "' AND `User` = '"+note.getUser()+"'");
                preparedStmt.executeUpdate();
            }


            con.close();

        } catch (Exception e) {
            String result = e.toString();

        }


    }


    public List<NoteInfo> getStudentNotes(String studentNumber) {
        List<NoteInfo> allStudentNotes = new ArrayList<>();
        try {

            con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Notes where `Student_No.`='" +studentNumber+ "'");
            while (rs.next()) {
                System.out.println("note db: " +rs.getString(4));
                String priNote = rs.getString(3);
                String pubNote = rs.getString(4);
                String userNote = rs.getString(5);

                NoteInfo ni = new NoteInfo(priNote, pubNote, userNote);
                allStudentNotes.add(ni);
            }
            con.close();
        }
        catch (Exception e) {
            String result = e.toString();

        }
        return allStudentNotes;
    }
}