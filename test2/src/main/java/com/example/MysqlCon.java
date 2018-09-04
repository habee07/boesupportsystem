package com.example;

/**
 * Created by habee on 2018/09/04.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//"jdbc:mysql://sdp.ms.wits.ac.za:3306/deploymentDB","username","password");
class MysqlCon{
    Connection con;
    PreparedStatement ps;
    Statement cs;
    ResultSet rs;
    String dbUrl = "jdbc:mysql://sdp.ms.wits.ac.za:3306/deploymentDB";
    List<String> studentNumberList;

    public List<String> getStudentNumbers() {
        studentNumberList = new ArrayList<>();


        /* Add a few items in the table. */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "username", "password");
            cs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = cs.executeQuery("select * from Students");
            while (rs.next()) {
                String result = rs.getString(2);
                studentNumberList.add(result);
                //addItem(new Object[] { rs.getString(1), rs.getInt(2) }, rs.getInt(2));
            }
            con.close();
        } catch (Exception e) {

            String result = e.toString();
            studentNumberList.add(result);    // getWindow(null).showNotification("Error");
        }
    return studentNumberList;
    }

    public List<students> getStudentObjects(List<String> studentNumbers){


    }
}