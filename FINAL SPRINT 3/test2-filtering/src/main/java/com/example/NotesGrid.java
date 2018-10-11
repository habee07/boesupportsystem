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
            TextArea notes = new TextArea();
            notes.setValue(Noteslist.get(i).getNotePub());
            notes.setEnabled(false);
            notes.setHeight("150px"); // fixed size with height larger than the panel
            notes.setWidth("80%");
            List<String> userInfo = NotesList.get(i).getUserInfo(NotesList.get(i).getUser());
            Label Info = new Label("");
            for(int j =0;j<userInfo.size();j++){
                 Info = new Label(userInfo.get(0));
            }
            layout.addComponents(notes);
            layout.addComponent(Info);
            layout.setSizeFull();
            addComponent(layout);
        }

    }
}
