import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *
 * Created by Naeem Docrat on 04 Sep 2018.
 *
 *
 */
public class CsvReaderCourses {

    // READS IN AND PROCESSES THH DATA AND PUTS IT ON THE DATABASE


    public static void main(String[] args) {

        Connection con = null;

        //String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
        String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB2";


        String csvFile = "C:\\Users\\Naeem Docrat\\Desktop\\CSV FILES PROJECT\\reg1.csv";
        BufferedReader br = null;
        String line = "";

        try {

            br = new BufferedReader(new FileReader(csvFile));

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");


            } catch (Exception e) {

                String result = e.toString();
                System.out.println(result);

            }

            int i = 1;

            while ((line = br.readLine()) != null) {

                    if(i != 1) {
                        String[] CsvLine = line.split(",,,");

                        String[] CsvLine1 = CsvLine[0].split(",,");
                        String[] c2 = CsvLine1[1].split(",");
                        String[] c3 = CsvLine1[2].split(",");

                        String AttemptStatus = c2[0];
                        String StudentNumber = c2[1];
                        int Year = Integer.parseInt(c3[1]);

                        String[] SubjectAndMarks = CsvLine[1].split(",,");

                        if (SubjectAndMarks.length == 3) {

                            String[] c4 = SubjectAndMarks[0].split(",");
                            String[] c5 = SubjectAndMarks[2].split(",");



                            String CourseCode = c4[0];
                            String CourseTitle = c4[1];
                            double FinalMark = Double.parseDouble(c5[0]);
                            String FinalGrade = c5[1];
                            int Credits = Integer.parseInt(SubjectAndMarks[1]);
                            double SuppMark = -1.0;

                            if(CourseTitle.contains("\'")){

                                CourseTitle = CourseTitle.replace("\'","%");

                            }

                            //Insert in to DB

                            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Courses (`Student_No.`,`Attempt_Status`,`Calendar_Year`,`Course_Code`,`Course_Title`,`Credits`,`Supp_Mark`,`Final_Mark`,`Final_Outcome`) VALUES('" + StudentNumber + "','" + AttemptStatus + "','"
                                    + Year + "','" + CourseCode + "','" + CourseTitle + "','"
                                    + Credits + "','" + SuppMark + "','" + FinalMark + "','" +
                                    FinalGrade + "')");
                            pstmt.executeUpdate();


                        }

                       else if (SubjectAndMarks.length == 1){

                            String[] c4 = SubjectAndMarks[0].split(",,,,");

                            String[] c5 = c4[0].split(",");
                            String[] c6 = CsvLine[2].split(",");

                            System.out.println(c6[2]);

                                String CourseCode = c5[0];
                                String CourseTitle = c5[1];
                                int Credits = 0;
                                double SuppMark = 0.0;
                                double FinalMark = Double.parseDouble(c6[1]);
                                String FinalGrade = c6[2];


                                if (CourseTitle.contains("'")) {

                                    CourseTitle.replace("'", "%");

                                }


                                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Courses (`Student_No.`,`Attempt_Status`,`Calendar_Year`,`Course_Code`,`Course_Title`,`Credits`,`Supp_Mark`,`Final_Mark`,`Final_Outcome`) VALUES('" + StudentNumber + "','" + AttemptStatus + "','"
                                        + Year + "','" + CourseCode + "','" + CourseTitle + "','"
                                        + Credits + "','" + SuppMark + "','" + FinalMark + "','" +
                                        FinalGrade + "')");
                                pstmt.executeUpdate();

                        }
                        else {

                            String[] c4 = SubjectAndMarks[0].split(",");
                            String[] c5 = SubjectAndMarks[1].split(",");

                            if((c5.length != 1)) {
                                String CourseCode = c4[0];
                                String CourseTitle = c4[1];
                                int Credits = Integer.parseInt(c5[0]);
                                double SuppMark = Double.parseDouble(c5[1]);
                                double FinalMark = Double.parseDouble(c5[2]);
                                String FinalGrade = c5[3];




                                if (CourseTitle.contains("'")) {

                                    CourseTitle.replace("'", "%");

                                }


                                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Courses (`Student_No.`,`Attempt_Status`,`Calendar_Year`,`Course_Code`,`Course_Title`,`Credits`,`Supp_Mark`,`Final_Mark`,`Final_Outcome`) VALUES('" + StudentNumber + "','" + AttemptStatus + "','"
                                        + Year + "','" + CourseCode + "','" + CourseTitle + "','"
                                        + Credits + "','" + SuppMark + "','" + FinalMark + "','" +
                                        FinalGrade + "')");
                                pstmt.executeUpdate();
                            }

                            else{

                                String CourseCode = c4[0];
                                String CourseTitle = c4[1];
                                int Credits = Integer.parseInt(c5[0]);
                                double SuppMark = -1.0;
                                double FinalMark = -1.0;
                                String FinalGrade = "N/A";


                                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Courses (`Student_No.`,`Attempt_Status`,`Calendar_Year`,`Course_Code`,`Course_Title`,`Credits`,`Supp_Mark`,`Final_Mark`,`Final_Outcome`) VALUES('" + StudentNumber + "','" + AttemptStatus + "','"
                                        + Year + "','" + CourseCode + "','" + CourseTitle + "','"
                                        + Credits + "','" + SuppMark + "','" + FinalMark + "','" +
                                        FinalGrade + "')");
                                pstmt.executeUpdate();



                            }



                        }
                    }

                i = i+1;


                System.out.println(i);


            }

            con.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

