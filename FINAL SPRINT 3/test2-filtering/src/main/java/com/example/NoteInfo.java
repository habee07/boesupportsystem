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

    public List<String> getUserInfo(String username){
        List<String> infoList = new ArrayList<>();
        for(int i =0;i <userList.size();i++){
            if(userList.get(i).UserName.equals(username)){

                String info1 = userList.get(i).Name;
                String info2 = userList.get(i).Discipline;
                infoList.add(info1);
                infoList.add(info2);
                if(!userList.get(i).Bio.equals("-1")){
                    String info3 =  userList.get(i).Bio;
                    infoList.add(info3);
            }
            }
        }
        return infoList;

    }
}
