package com.example;

import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by habee on 2018/09/19.
 */
public class NotesGrid extends VerticalLayout{
    private List<NoteInfo> NotesList;


    public NotesGrid(List<NoteInfo> Noteslist){
        NotesList = Noteslist;
        setSizeFull();
        for(int i=0;i<NotesList.size();i++){
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSizeFull();
            String note = Noteslist.get(i).getNotePub();
            String userInfo = NotesList.get(i).getUserInfo(NotesList.get(i).getUser());
            String text = note + "\n\n - Note by: " + userInfo;
            TextArea notes = new TextArea();
            notes.setValue(text);
            notes.setEnabled(false);

            notes.setHeight("150px"); // fixed size with height larger than the panel
            notes.setWidth("100%");
            layout.addComponents(notes);
            /**String userInfo = NotesList.get(i).getUserInfo(NotesList.get(i).getUser());
            Label lblInfo = new Label(userInfo);
            lblInfo.setStyleName("h1");
            lblInfo.setWidth("100px");
            layout.addComponent(lblInfo);
             layout.setSizeFull();

            **/
            addComponent(layout);

        }

    }
}
