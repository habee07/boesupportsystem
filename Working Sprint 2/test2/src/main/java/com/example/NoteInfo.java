package com.example;
/**
 * Created by Laila on 06/09/2018.
 */
public class NoteInfo {

    private String priv;
    private String note;


    public NoteInfo(String priv,String note) {
        this.priv = priv;
        this.note = note;

    }


    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
