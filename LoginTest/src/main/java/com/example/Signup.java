package com.example;

import com.vaadin.ui.*;

/**
 * Created by habee on 2018/09/15.
 */
public class Signup {

    public static String currUser;
    String ID;
    String Name;
    String Email;
    String Bio;
    String Password;
    String Discipline;
    String Gender;

    public Signup(String ID, String name, String email, String bio, String password, String discipline, String gender) {
        this.ID = ID;
        Name = name;
        Email = email;
        Bio = bio;
        Password = password;
        Discipline = discipline;
        Gender = gender;
        System.out.println(ID + " " + Gender);
        //System.out.println();
    }
    public Signup(){
        ID = "Habee";
        Name = "Habeebullah";
        Email = "email";
        Bio = "bio";
        Password = "password";
        Discipline = "discipline";
        Gender = "Male";
        currUser = "Habee";


    }

    public boolean LoginCheck(String userName, String sentPassword){
        if(ID.equals(userName) && Password.equals(sentPassword)){
            currUser = userName;
            return true;
        }
        else{
            return  false;
        }
    }

    public void logOut() {
        currUser = null;
    }

    public static String getCurrUser() {
        return currUser;
    }
}
