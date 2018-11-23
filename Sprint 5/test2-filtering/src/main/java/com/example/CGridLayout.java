package com.example;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
final class CGridLayout extends VerticalLayout {

     List<CourseGrid> CGridList;
    List<NoteInfo> notesList;
     //Users user = new Users();
    //private final CourseGrid CGrid2;
    private students Astudent;
    private List<String> FilterList;
    private String FilterType;
    private StudentDetails studentDetails;
    private StudentYearInfo studentYearInfo;
    private CourseGrid CGrid;
    boolean changednotePriv;
    boolean changednotePub;
    HorizontalLayout notesDisplay;
    Button showNotes;
    int noteNum;
    Users currentUser;


    public CGridLayout(students aStudentsss, Users curruser, List<String> filterlist,String filtertype ) {
        Astudent = aStudentsss;
        currentUser = curruser;
        System.out.println("Cgrid user"+ curruser.UserName);
        MysqlCon conn = new MysqlCon();
        notesList = conn.getStudentNotes(Astudent.getStudentNumber());
        System.out.println("notes size:" + notesList.size());
        changednotePriv = false;
        changednotePub = false;
        FilterList = filterlist;
        FilterType = filtertype;
        Button addPubNotes = new Button("ADD PUBLIC NOTES");
        Button addPrivNotes = new Button("ADD PRIVATE NOTES");
        addPubNotes.setIcon(VaadinIcons.COMMENT_ELLIPSIS_O);
        addPrivNotes.setIcon(VaadinIcons.COMMENT_ELLIPSIS_O);
        Button saveNotesPriv = new Button("Save Changes");
        Button saveNotesPub = new Button("Save Changes");
        saveNotesPriv.setIcon(VaadinIcons.LOCATION_ARROW_CIRCLE);
        saveNotesPub.setIcon(VaadinIcons.LOCATION_ARROW_CIRCLE);
        Button cancelNotesPriv = new Button("Cancel");
        Button cancelNotesPub = new Button("Cancel");
        cancelNotesPriv.setIcon(VaadinIcons.MINUS_CIRCLE_O);
        cancelNotesPub.setIcon(VaadinIcons.MINUS_CIRCLE_O);
        addPrivNotes.setStyleName("primary");
        addPubNotes.setStyleName("primary");
        saveNotesPriv.setStyleName("friendly");
        saveNotesPub.setStyleName("friendly");
        cancelNotesPriv.setStyleName("danger");
        cancelNotesPub.setStyleName("danger");
        TextArea tAPriv = new TextArea();
        TextArea tAPub = new TextArea();
        notesDisplay = new HorizontalLayout();
        notesDisplay.setSizeFull();
        showNotes = new Button("Display Notes");
        showNotes.setStyleName("friendly");
        showNotes.setIcon(VaadinIcons.NOTEBOOK);
        String currNPriv = "";
        String currNPub = "";
        noteNum = -1;
        for(int i=0; i< notesList.size();i++){
            if(currentUser.UserName.equals(notesList.get(i).getUser())){
                currNPriv = notesList.get(i).getNotePriv();
                currNPub = notesList.get(i).getNotePub();
                noteNum = i;

            }
        }
        updateNoteNum();
        tAPriv.setWordWrap(true);
        tAPriv.setHeight("150px"); // fixed size with height larger than the panel
        tAPriv.setWidth("100%");
        tAPriv.setValue(currNPriv);
        tAPriv.setCaption("ADD PRIVATE NOTE:");


        tAPub.setWordWrap(true);
        tAPub.setHeight("150px"); // fixed size with height larger than the panel
        tAPub.setWidth("100%");
        tAPub.setValue(currNPub);
        tAPub.setCaption("ADD PUBLIC NOTE:");
        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setSizeFull();
        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setSizeFull();

        studentDetails = new StudentDetails(Astudent);
            studentYearInfo = new StudentYearInfo(Astudent.getHistory());
            addComponents(studentDetails, studentYearInfo);
            CGrid = new CourseGrid(Astudent.getCourse());

        NotesGrid NoteGrid = new NotesGrid(notesList);
            notesDisplay.addComponent(NoteGrid);
            notesDisplay.setVisible(false);

        showNotes.addClickListener(e ->{
            if(notesDisplay.isVisible() == true){
                notesDisplay.setVisible(false);
            }
            else{
                notesList = conn.getStudentNotes(Astudent.getStudentNumber());
                NotesGrid updatedNoteGrid = new NotesGrid(notesList);
                notesDisplay.removeAllComponents();
                notesDisplay.addComponent(updatedNoteGrid);
                notesDisplay.setVisible(true);
            }

        });
            /**if (FilterType.contains("SOFT")) {
                System.out.println("SOFT");

                CGrid.setStyleGenerator(t -> {
                    boolean yesorno = false;
                    if (FilterList.get(0)!= "EMPTY") {

                        if (t.getCourseOutcome().contains(FilterList.get(0))) {
                            System.out.println("OUTcome filtering");
                            yesorno = true;
                        } else {
                            return null;
                        }
                    }

                    if (FilterList.get(1)!= "EMPTY") {

                        if (t.getCourseCode().contains(FilterList.get(1))) {
                            System.out.println("FILtering");
                            yesorno = true;
                        } else {
                            return null;
                        }
                    }
                    if (FilterList.get(2)!= "EMPTY") {

                        if (t.getCourseName().contains(FilterList.get(2))) {
                            System.out.println("name filtering");
                            yesorno = true;
                        } else {
                            return null;
                        }
                    }
                     if(!FilterList.get(5).equals("EMPTY") && !FilterList.get(6).equals("EMPTY")){
                         if (t.getFinalMark() >= Double.parseDouble(FilterList.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterList.get(6))) {
                             System.out.println("mark filtering");
                             yesorno = true;
                         }
                         else{
                             return null;
                         }
                     }
                    if(!FilterList.get(5).equals("EMPTY") && FilterList.get(6).equals("EMPTY")){
                        if (t.getFinalMark() >= Double.parseDouble(FilterList.get(5))) {
                            System.out.println("mark filtering");
                            yesorno = true;
                        }
                        else{
                            return null;
                        }
                    }
                    if(FilterList.get(5).equals("EMPTY") && !FilterList.get(6).equals("EMPTY")){
                        if (t.getFinalMark() <= Double.parseDouble(FilterList.get(6))) {
                            System.out.println("mark filtering");
                            yesorno = true;
                        }
                        else{
                            return null;
                        }
                    }
                     if(!FilterList.get(3).equals("EMPTY") && !FilterList.get(4).equals("EMPTY")){
                         if (t.getSuppMark() >= Double.parseDouble(FilterList.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterList.get(4))) {
                             System.out.println("supp filtering");
                             yesorno = true;
                         }else{
                            return null;
                        }
                     }

                    if(FilterList.get(3).equals("EMPTY") && !FilterList.get(4).equals("EMPTY")){
                        if (t.getSuppMark() <= Double.parseDouble(FilterList.get(4))) {
                            System.out.println("supp filtering");
                            yesorno = true;
                        }else{
                            return null;
                        }
                    }

                    if(!FilterList.get(3).equals("EMPTY") && FilterList.get(4).equals("EMPTY")){
                        if (t.getSuppMark() >= Double.parseDouble(FilterList.get(3))) {
                            System.out.println("supp filtering");
                            yesorno = true;
                        }else{
                            return null;
                        }
                    }
                    if(yesorno == false){
                        return null;
                    }
                    else {
                        return "filters";
                    }
                });


            }


            if (FilterType.contains("NONE")) {
                CGrid.setStyleGenerator(t -> {
                    return null;
                });

            }
            **/
        MysqlCon c = new MysqlCon();
        addPrivNotes.addClickListener(e -> {
            addComponent(tAPriv);

                List<String> notes = c.getDBNotes(Astudent.getStudentNumber(), currentUser.UserName);
                tAPriv.setValue(notes.get(0));
            if(noteNum != -1 ) {
                notesList.get(noteNum).setNotePriv(notes.get(0));
            }
                changednotePriv = false;

            hl1.addComponents(cancelNotesPriv, saveNotesPriv);
            hl1.setComponentAlignment(saveNotesPriv, Alignment.MIDDLE_CENTER);
            addComponent(hl1);

        });


        addPubNotes.addClickListener(e -> {

            addComponent(tAPub);
            List<String> notes = c.getDBNotes(Astudent.getStudentNumber(), currentUser.UserName);

            tAPub.setValue(notes.get(1));
            if(noteNum != -1 ) {
                notesList.get(noteNum).setNotePub(notes.get(1));
            }
            changednotePub = false;

            hl2.addComponents(cancelNotesPub, saveNotesPub);
            hl2.setComponentAlignment(saveNotesPub, Alignment.MIDDLE_CENTER);
            addComponent(hl2);

        });



        cancelNotesPriv.addClickListener(e -> {
                    removeComponent(hl1);
                    removeComponent(tAPriv);


                }
        );

        cancelNotesPub.addClickListener(e -> {
                    removeComponent(hl2);

                    removeComponent(tAPub);

                }
        );


        saveNotesPriv.addClickListener(e -> {
            changednotePriv = true;
            if(noteNum != -1 ) {
                notesList.get(noteNum).setNotePriv(tAPriv.getValue());
            }
            else{
                NoteInfo newnote = new NoteInfo(tAPriv.getValue(),"",currentUser.UserName);
                notesList.add(newnote);
                updateNoteNum();
            }

            c.updatePrivDBNotes(Astudent.getStudentNumber(),currentUser.UserName,tAPriv.getValue());
            removeComponent(tAPriv);
            removeComponent(hl1);

        });


        saveNotesPub.addClickListener(e -> {
            changednotePriv = true;
            if(noteNum != -1 ) {
                notesList.get(noteNum).setNotePub(tAPub.getValue());
            }
            else{
                NoteInfo newnote = new NoteInfo("",tAPub.getValue(),currentUser.UserName);
                notesList.add(newnote);
                updateNoteNum();
            }

            c.updatePubDBNotes(Astudent.getStudentNumber(),currentUser.UserName,tAPub.getValue());
            NotesGrid newNoteGrid = new NotesGrid(notesList);
            notesDisplay.removeAllComponents();
            notesDisplay.addComponent(newNoteGrid);
            removeComponent(tAPub);
            removeComponent(hl2);

        });


        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(addPrivNotes, addPubNotes);
        addComponent(CGrid);
        addComponent(showNotes);
        addComponent(notesDisplay);
        addComponentsAndExpand(buttonsLayout);


        FilterType = "NONE";

        //nameFilter.addValueChangeListener(this::onNameFilterTextChange);
        //yearFilter.addValueChangeListener(this::onYearFilterTextChange);



        //Label studentInfo = ;

        //Label studentDetails0 = new Label(allStudents.get(0).getStudentNumber()+ "  " + allStudents.get(0).getStudentName() + " " + allStudents.get(0).getStudentSurname());
        //studentDetails0.addStyleName("h1");
        //Label studentDetails1 = new Label(allStudents.get(1).getStudentNumber()+ "  " + allStudents.get(1).getStudentName() + " " + allStudents.get(1).getStudentSurname());
        //studentDetails1.addStyleName("h1");
        //Label blank = new Label(" ");
        //blank.setStyleName("h2");
        List<Courses> blankCourse = new ArrayList<>();
        List<StudentHistory> blankHistory = new ArrayList<>();
        CGridList = new ArrayList<>();
        //students blankStudent = new students("", "" , "" , "", blankCourse, blankHistory );
        //CGrid = new CourseGrid(blankStudent.getCourse());
        //updateItemsList();

                //StudentDetails studentDetails0 = new StudentDetails(allStudents.get(0));
        //StudentDetails studentDetails1 = new StudentDetails(allStudents.get(1));

        //StudentYearInfo studentYearInfo0 = new StudentYearInfo(allStudents.get(0).getHistory());
        //StudentYearInfo studentYearInfo1 = new StudentYearInfo(allStudents.get(1).getHistory());


        //studentSubHeader.setSizeUndefined();
        //addComponents(studentDetails0,studentYearInfo0);
        //CGrid = new CourseGrid(allStudents.get(0).getCourse());
        //addComponentsAndExpand(CGrid);

        //setSizeFull();
        //CGrid2 = new CourseGrid(allStudents.get(1).getCourse());
        //addComponents(studentDetails1, studentYearInfo1);
        //addComponentsAndExpand(CGrid2);
        //setSizeFull();
    }

    private void updateNoteNum() {
        for(int i=0; i< notesList.size();i++){
            if(currentUser.UserName.equals(notesList.get(i).getUser())){
                noteNum = i;

            }
        }
    }

    private void RemoveHardFilter(Button.ClickEvent clickEvent) {
        removeAllComponents();
        //addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        //FilterList.clear();
       // updateItemsList();
    }







    private void onYearFilterTextChange(HasValue.ValueChangeEvent<String> stringValueChangeEvent) {
        for(int i=0;i<CGridList.size();i++){

            ListDataProvider<Courses> dataProvider = (ListDataProvider<Courses>) CGridList.get(i).getDataProvider();

        }
        //ListDataProvider<Courses> dataProvider2 = (ListDataProvider<Courses>) CGrid2.getDataProvider();
        //dataProvider.setFilter(Courses::getCourseMark, s -> caseInsensitiveContains(s.toString(), stringValueChangeEvent.getValue()));
        //dataProvider2.setFilter(Courses::getCourseMark, s -> caseInsensitiveContains(s.toString(), stringValueChangeEvent.getValue()));

    }

    private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        // NAmes
        //ListDataProvider<Courses> dataProvider = (ListDataProvider<Courses>) CGrid.getDataProvider();
        //dataProvider.setFilter(Courses::getName, s -> caseInsensitiveContains(s, event.getValue()));

        //ListDataProvider<Courses> dataProvider2 = (ListDataProvider<Courses>) CGrid2.getDataProvider();
        //dataProvider2.setFilter(Courses::getName, s -> caseInsensitiveContains(s, event.getValue()));

        // Marks
        //ListDataProvider<Courses> dataProvider3 = (ListDataProvider<Courses>) CGrid.getDataProvider();

        //ListDataProvider<Courses> dataProvider4 = (ListDataProvider<Courses>) CGrid2.getDataProvider();
        //dataProvider3.setFilter(Courses::getCourseMark, p -> caseInsensitiveContains(p.toString(), event.getValue()));
        //dataProvider4.setFilter(Courses::getCourseMark, p -> caseInsensitiveContains(p.toString(), event.getValue()));
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

    public CourseGrid getCGrid() {
        return CGrid;
    }

    public void setCGrid(CourseGrid CGrid) {
        this.CGrid = CGrid;
    }

    public students getAstudent() {
        return Astudent;
    }

    public void setAstudent(students astudent) {
        Astudent = astudent;
    }

    public List<String> getFilterList() {
        return FilterList;
    }

    public void setFilterList(List<String> filterList) {
        FilterList = filterList;
    }

    public String getFilterType() {
        return FilterType;
    }

    public void setFilterType(String filterType) {
        FilterType = filterType;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    public StudentYearInfo getStudentYearInfo() {
        return studentYearInfo;
    }

    public void setStudentYearInfo(StudentYearInfo studentYearInfo) {
        this.studentYearInfo = studentYearInfo;
    }
}
