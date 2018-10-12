package com.example;
/**
 * Created by Naeem Docrat on 15 Sep 2018.
 */
import java.sql.*;
import java.util.ArrayList;

public class Users {

    public static Users CurrentUser;


    String UserName;
    String Name;
    String Email;
    String Bio;
    String Password;
    String Discipline;
    String Gender;

    //String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/developmentDB?useSSL=false";
    String dbUrl = "jdbc:mysql://docselectrical.co.za:3306/DevelopmentDB";



    public Users(String userName, String gender, String discipline, String password, String bio, String email, String name ) {
        UserName = userName;
        Gender = gender;
        Discipline = discipline;
        Password = password;
        Bio = bio;
        Email = email;
        Name = name;

    }

    public Users(String password, String userName) throws ClassNotFoundException, SQLException {
        UserName = userName;
        Password = password;
        //Do MORE HERE
    }

    public Users(String userName,String name, String discipline, String bio){
        UserName = userName;
        Name = name;
        Discipline = discipline;
        Bio = bio;

    }

    public Users(){


    }



    public Boolean MakeUser() throws ClassNotFoundException, SQLException {

        Connection con = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");

        Statement cs;
        ResultSet rs;

        ArrayList<String> Usernames = new ArrayList<String>();

        cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = cs.executeQuery("select Username from Users");

        while (rs.next()) {

            Usernames.add(rs.getString(1));

        }

        if (!(Usernames.contains(UserName))){

            String query = "INSERT INTO Users ("
                    + " `Username`,"
                    + " `Name`,"
                    + " `Email`,"
                    + " `Bio`,"
                    + " `Password`,"
                    + " `Discipline`,"
                    + " `Gender`) VALUES ("
                    + "? , ? , ? , ? , ? , ? , ? )";


            PreparedStatement PT = con.prepareStatement(query);

            PT.setString(1, UserName);
            PT.setString(2, Name);
            PT.setString(3, Email);
            PT.setString(4, Bio);
            PT.setString(5, Password);
            PT.setString(6, Discipline);
            PT.setString(7, Gender);

            PT.executeUpdate();

            return true;
        }
        else{

            return false;

        }
    }

    public Boolean Login() throws ClassNotFoundException, SQLException {


        Connection con = null;

        Class.forName("com.mysql.jdbc.Driver");
        //con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");
        con = DriverManager.getConnection(dbUrl, "DevelopmentDB", "Password");

        Statement cs;
        ResultSet rs;

        cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = cs.executeQuery("select * from Users where `Username`='"+ UserName + "'");

        if(!rs.first()){

            return false;

        }

        else if(rs.getString(6).equals(Password)){

            Name = rs.getString(3);
            Email = rs.getString(4);
            Bio = rs.getString(5);
            Discipline = rs.getString(7);
            Gender = rs.getString(8);


            CurrentUser = new Users(UserName,Gender,Discipline,Password,Bio,Email,Name);

            return true;

        }
        else{

            return false;

        }
    }

    public void logOut() {

        CurrentUser = null;

    }

}


