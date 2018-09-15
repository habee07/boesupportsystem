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
public class CSVREADER {

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
            String TempStudentName = "Test";
            String TempProgramCode = null;
            String TempStudentLastName = null;
            String TempStudentNumber = null;

            while ((line = br.readLine()) != null) {

                if(!(i == 1)){

                    System.out.println(i);

                String[] CsvLine = line.split(",,");
                String[] CourseCode = CsvLine[0].split(",");
                String[] NumberName = CsvLine[1].split(",");
                String[] SurName = CsvLine[2].split(",");

                String StudentNumber = NumberName[1];
                String StudentName = NumberName[2];
                String ProgramCode = CourseCode[1];
                String StudentLastName = SurName[0];


                if (!(StudentName.equals("FirstName")) && !TempStudentName.equals(StudentName)&& !TempStudentName.equals("Test")) {

                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO Students VALUES('" + TempProgramCode + "','" + TempStudentNumber + "','"
                            + TempStudentName + "','" + TempStudentLastName + "')");
                    pstmt.executeUpdate();

                }


                TempStudentNumber = NumberName[1];
                TempStudentName = NumberName[2];
                TempProgramCode = CourseCode[1];
                TempStudentLastName = SurName[0];


            }
               i = i + 1;
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

