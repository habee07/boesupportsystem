package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laila on 06/09/2018.
 */
public class NoteInfo {

    private String notePub;
    private String notePriv;
    private String userID;
    List<Users> userList;


    public NoteInfo(String notepriv,String notepub,String user) {
        userList = new ArrayList<>();
        MysqlCon c = new MysqlCon();
        userList = c.getUsers();

        this.notePriv = notepriv;
        this.notePub = notepub;
        this.userID = user;

    }


    public String getNotePriv() {
        return notePriv;
    }

    public void setNotePriv(String notePriv) {
        this.notePriv = notePriv;
    }

    public String getNotePub() {
        return notePub;
    }

    public void setNotePub(String notePub) {
        this.notePub = notePub;
    }

    public String getUser() {
        return userID;
    }

    public void setUser(String user) {
        this.userID = user;
    }

    public String getUserInfo(String username){
        String info = "";
        for(int i =0;i <userList.size();i++){
            if(userList.get(i).UserName.equals(username)){

                info = info + userList.get(i).Name + "\n ";
                info = info + "Discipline: " +userList.get(i).Discipline;

                if(!userList.get(i).Bio.equals("-1")){
                    info = info + "\n Bio: " + userList.get(i).Bio;

            }
            }
        }
        return info;

    }
}
