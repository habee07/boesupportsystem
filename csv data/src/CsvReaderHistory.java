import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 *
 * Created by Naeem Docrat on 05 Sep 2018.
 *
 *
 */
public class CsvReaderHistory {

    public static void main(String[] args) {

        Connection con = null;

        //String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";

        String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB2";
        String csvFile = "C:\\Users\\Naeem Docrat\\Desktop\\CSV FILES PROJECT\\outcome1.csv";

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

                if(i != 1 && i != 14736){

                    String[] History = line.split(",");
                    if(History.length == 11) {

                        String StudentNumber = History[1];
                        int CalenderYear = Integer.parseInt(History[0]);
                        String YearOfStudy = History[6];
                        String ProgramCode = History[3];
                        String RegStatus = History[2];
                        String AttemptStatus = History[8];
                        String ProgressOutcome = History[4];
                        String ProgressDescription = History[5];

                        if(History[7].equals("")){

                            History[7] = "-1.0";

                        }

                        double AverageMarks = Double.parseDouble(History[7]);
                        String EnrolledCreds = History[9];
                        String AchievedCreds = History[10];

                        //SQL Statement
                        String query = "INSERT INTO History ("
                                + " `Student_No.`,"
                                + " `Calendar_Year`,"
                                + " `YOS`,"
                                + " `Program_Code`,"
                                + " `Reg_Status`,"
                                + " `Attempt_Status`,"
                                + " `Progress_Outcome`,"
                                + " `Outcome_Description`,"
                                + " `Average`,"
                                + " `Enrolled_Creds`,"
                                + " `Achieved_Creds` ) VALUES ("
                                + "? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";

                        PreparedStatement st = con.prepareStatement(query);
                        st.setString(1,  StudentNumber);
                        st.setInt(2,  CalenderYear);
                        st.setString(3,  YearOfStudy);
                        st.setString(4,  ProgramCode);
                        st.setString(5,  RegStatus);
                        st.setString(6,  AttemptStatus);
                        st.setString(7,  ProgressOutcome);
                        st.setString(8,  ProgressDescription);
                        st.setDouble(9,  AverageMarks);
                        st.setString(10, EnrolledCreds );
                        st.setString(11, AchievedCreds);

                        System.out.println(st);

                        st.executeUpdate();
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
