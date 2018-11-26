package com.example;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Laila on 29/10/2018.
 */
public class UploadPage extends VerticalLayout implements View {

    VerticalLayout uploadlayout = new VerticalLayout();
    VerticalLayout notificationLayout = new VerticalLayout();
    Label instruction1;
    Label instruction2;
    Label instruction3;
    Label uploadHistory;
    Label uploadCourses;
    Label sz;

    private Upload uploadC;
    private Upload uploadH;
    HorizontalLayout UpperSection = new HorizontalLayout();
    Label lblHeader;
    Button goBack;


    public UploadPage() {

        lblHeader = new Label("");
        lblHeader.setSizeUndefined();

        goBack = new Button("Dashboard");
        goBack.addStyleName("small");
        goBack.addStyleName("friendly");
        goBack.setIcon(VaadinIcons.ARROW_BACKWARD);
        goBack.setSizeUndefined();
        goBack.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                getUI().getNavigator().navigateTo("dashboard");
            }
        });

        UpperSection.addComponents(lblHeader, goBack);
        UpperSection.setComponentAlignment(goBack, Alignment.TOP_LEFT);
        UpperSection.addStyleName("borderBottom");
        addComponent(UpperSection);



        FileUploader receiverC = new FileUploader();
        FileUploader receiverH = new FileUploader();



        uploadC = new Upload("Choose File", receiverC);
        uploadC.setButtonCaption("Browse...");
        uploadC.addSucceededListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {

                PushToDbReg(receiverC.Path);
                Notification.show("Upload Successful").setDelayMsec(5000);
            }
        });
        uploadC.addFailedListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {

                Notification.show("Upload Failed, Try Again.", Notification.Type.ERROR_MESSAGE).setDelayMsec(5000);

            }
        });

        uploadH = new Upload("Choose File", receiverH);
        uploadH.setButtonCaption("Browse...");
        uploadH.addSucceededListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                PushToDbOutcome(receiverH.Path);
                Notification.show("Upload Successful").setDelayMsec(5000);
            }
        });
        uploadH.addFailedListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {

                Notification.show("Upload Failed, Try Again.", Notification.Type.ERROR_MESSAGE).setDelayMsec(5000);

            }
        });



        uploadC.setImmediateMode(true);
        uploadC.setVisible(true);

        uploadH.setImmediateMode(true);
        uploadH.setVisible(true);

        uploadHistory = new Label("Upload Academic History .csv File");
        uploadHistory.addStyleName("colored");
        uploadHistory.addStyleName("h2");
        uploadCourses = new Label("Upload Courses .csv File");
        uploadCourses.addStyleName("colored");
        uploadCourses.addStyleName("h2");
        sz = new Label("");
        sz.setWidth( null );
        sz.setHeight ( "70px" );

        File uploadedCFile = receiverC.getTempFile();
        File uploadedHFile = receiverH.getTempFile();


        instruction3 = new Label("Instructions");
        instruction3.setStyleName("h1");
        instruction1 = new Label("1) Ensure that the csv files are labeled correctly. Student History should be: outcome1.csv.       Student courses should be: reg1.csv.");
        instruction2 = new Label("2) Ensure that you upload outcome1.csv  first , followed by the reg1.csv. ");

        //instruction.setHeight("50px");
        //instruction.setWidth("100px");
        uploadlayout.addComponents(uploadHistory,uploadH,sz,uploadCourses,uploadC);
        notificationLayout.addComponents(instruction3,instruction1,instruction2,uploadlayout);
        addComponent(notificationLayout);

    }

    //Back End LOGIC FOR CSV FILE INPUTING TO DB

    public void PushToDbReg(String Reg){

        Connection con = null;
        BufferedReader br = null;
        //String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
        String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB2";
        String line = "";

        try {

            br = new BufferedReader(new FileReader(Reg));

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");

            } catch (Exception e) {

                String result = e.toString();
                System.out.println(result);

            }

            int i = 1;

            while ((line = br.readLine()) != null) {

                String[] temp = line.replaceAll("^[,]+", "").split("[,]+");
                //General Cases 13 or 12
                // Size 13 will be with supp marks Size 12 will be without supp marks
                // anything else we will get first 7 items the rest will be flagged

                //without supp mark
                if(i!=1 && temp.length == 12 ){

                    String StudentNumber = temp[3] ;
                    String AttemptStatus = temp[2] ;
                    int Year = Integer.parseInt(temp[6]);
                    String CourseCode = temp[7];
                    String CourseTitle = temp[8];
                    int Credits = Integer.parseInt(temp[9]);
                    double SuppMark = -1.0;
                    double FinalMark = Double.parseDouble(temp[10]);
                    String FinalGrade = temp[11];

                    String query = "INSERT INTO Courses ("
                            + " `Student_No.`,"
                            + " `Attempt_Status`,"
                            + " `Calendar_Year`,"
                            + " `Course_Code`,"
                            + " `Course_Title`,"
                            + " `Credits`,"
                            + " `Supp_Mark`,"
                            + " `Final_Mark`,"
                            + " `Final_Outcome` ) VALUES ("
                            + "? , ? , ? , ? , ? , ? , ? , ? , ? )";

                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1,  StudentNumber);
                    st.setString(2,  AttemptStatus);
                    st.setInt(3,  Year);
                    st.setString(4,  CourseCode);
                    st.setString(5,  CourseTitle);
                    st.setInt(6,  Credits);
                    st.setDouble(7,  SuppMark);
                    st.setDouble(8,  FinalMark);
                    st.setString(9,  FinalGrade);
                    st.executeUpdate();

                }
                else if(i!=1 && temp.length == 13 ) {

                    String StudentNumber = temp[3] ;
                    String AttemptStatus = temp[2] ;
                    int Year = Integer.parseInt(temp[6]);
                    String CourseCode = temp[7];
                    String CourseTitle = temp[8];
                    int Credits = Integer.parseInt(temp[9]);
                    double SuppMark = Double.parseDouble(temp[10]);
                    double FinalMark = Double.parseDouble(temp[11]);
                    String FinalGrade = temp[12];

                    String query = "INSERT INTO Courses ("
                            + " `Student_No.`,"
                            + " `Attempt_Status`,"
                            + " `Calendar_Year`,"
                            + " `Course_Code`,"
                            + " `Course_Title`,"
                            + " `Credits`,"
                            + " `Supp_Mark`,"
                            + " `Final_Mark`,"
                            + " `Final_Outcome` ) VALUES ("
                            + "? , ? , ? , ? , ? , ? , ? , ? , ? )";

                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1,  StudentNumber);
                    st.setString(2,  AttemptStatus);
                    st.setInt(3,  Year);
                    st.setString(4,  CourseCode);
                    st.setString(5,  CourseTitle);
                    st.setInt(6,  Credits);
                    st.setDouble(7,  SuppMark);
                    st.setDouble(8,  FinalMark);
                    st.setString(9,  FinalGrade);
                    st.executeUpdate();

                }

                /*
                else{

                    String StudentNumber = temp[3] ;
                    String AttemptStatus = temp[2] ;
                    int Year = Integer.parseInt(temp[6]);
                    String CourseCode = temp[7];
                    String CourseTitle = temp[8];
                    int Credits = Integer.parseInt(temp[9]);
                    double SuppMark = -1.0;
                    double FinalMark = Double.parseDouble(temp[10]);
                    String FinalGrade = temp[11];

                    String query = "INSERT INTO Courses ("
                            + " `Student_No.`,"
                            + " `Attempt_Status`,"
                            + " `Calendar_Year`,"
                            + " `Course_Code`,"
                            + " `Course_Title`,"
                            + " `Credits`,"
                            + " `Supp_Mark`,"
                            + " `Final_Mark`,"
                            + " `Final_Outcome` ) VALUES ("
                            + "? , ? , ? , ? , ? , ? , ? , ? , ? )";

                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1,  StudentNumber);
                    st.setString(2,  AttemptStatus);
                    st.setInt(3,  Year);
                    st.setString(4,  CourseCode);
                    st.setString(5,  CourseTitle);
                    st.setInt(6,  Credits);
                    st.setDouble(7,  SuppMark);
                    st.setDouble(8,  FinalMark);
                    st.setString(9,  FinalGrade);
                    st.executeUpdate();


                }
                */

                i = i+1;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    public void PushToDbOutcome( String OutCome ){

        Connection con = null;
        BufferedReader br = null;
        //String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
        String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB2";
        String line = "";

        try {

            br = new BufferedReader(new FileReader(OutCome));

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(dbUrl, "DevelopmentDB2", "Password");

            } catch (Exception e) {

                String result = e.toString();
                System.out.println(result);

            }

            int k = 1;
            while ((line = br.readLine()) != null) {


                String[] History = line.replaceAll("^[,]+", "").split("[,]+");
                if(History.length == 11 && k != 1){


                    String StudentNumber = History[1];
                    int CalenderYear = Integer.parseInt(History[0]);
                    String YearOfStudy = History[6];
                    String ProgramCode = History[3];
                    String RegStatus = History[2];
                    String AttemptStatus = History[8];
                    String ProgressOutcome = History[4];
                    String ProgressDescription = History[5];
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
                    st.executeUpdate();

                }
                else if (History.length == 10 && k!=1 ){

                    String StudentNumber = History[1];
                    int CalenderYear = Integer.parseInt(History[0]);
                    String YearOfStudy = History[6];
                    String ProgramCode = History[3];
                    String RegStatus = History[2];
                    String AttemptStatus = History[7];
                    String ProgressOutcome = History[4];
                    String ProgressDescription = History[5];
                    double AverageMarks = -1.0;
                    String EnrolledCreds = History[8];
                    String AchievedCreds = History[9];

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
                    st.executeUpdate();

                }
                k = k + 1;
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
