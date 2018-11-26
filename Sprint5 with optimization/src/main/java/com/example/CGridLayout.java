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
    private CourseGrid PrevCGrid;
    private BadDataGrid BDGrid;
    boolean changednotePriv;
    boolean changednotePub;
    HorizontalLayout notesDisplay;
    Button showNotes;
    int noteNum;
    Users currentUser;
    Boolean PubNotesDisp = false;


    public CGridLayout(students aStudentsss, Users curruser, List<String> filterlist,String filtertype ) {

        Button viewCourseHistory = new Button("Display Previous Courses");
        viewCourseHistory.setStyleName("friendly");
        viewCourseHistory.setIcon(VaadinIcons.CARET_DOWN);
        Label noPrevCourses = new Label("This student has no previous courses.");

        Astudent = aStudentsss;
        currentUser = curruser;
        MysqlCon conn = new MysqlCon();
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
            PrevCGrid = new CourseGrid(Astudent.getPreviouscourses());
            BDGrid = new BadDataGrid(Astudent.getBadData());


        showNotes.addClickListener(e ->{
            if(PubNotesDisp){
                notesDisplay.removeAllComponents();
                PubNotesDisp = false;
            }
            else{
                notesList = conn.getStudentNotes(Astudent.getStudentNumber());
                NotesGrid updatedNoteGrid = new NotesGrid(notesList);
                notesDisplay.removeAllComponents();
                notesDisplay.addComponent(updatedNoteGrid);
                PubNotesDisp = true;
            }

        });


        viewCourseHistory.addClickListener(e->{


            if (!Astudent.getPreviouscourses().isEmpty()) {
                if(!PrevCGrid.isVisible()){
                    PrevCGrid.setVisible(true);
                    viewCourseHistory.setIcon(VaadinIcons.CARET_UP);
                }

                else{
                    PrevCGrid.setVisible(false);
                    viewCourseHistory.setIcon(VaadinIcons.CARET_DOWN);
                }

            } else {
                if(!noPrevCourses.isVisible()){

                    noPrevCourses.setVisible(true);
                    viewCourseHistory.setIcon(VaadinIcons.CARET_UP);
                }
                else{
                    noPrevCourses.setVisible(false);
                    viewCourseHistory.setIcon(VaadinIcons.CARET_DOWN);
                }




            }



        });

        MysqlCon c = new MysqlCon();
        addPrivNotes.addClickListener(e -> {
            addComponent(tAPriv);

                List<String> notes = c.getDBNotes(Astudent.getStudentNumber(), currentUser.UserName);
                tAPriv.setValue(notes.get(0));

            hl1.addComponents(cancelNotesPriv, saveNotesPriv);
            hl1.setComponentAlignment(saveNotesPriv, Alignment.MIDDLE_CENTER);
            addComponent(hl1);

        });


        addPubNotes.addClickListener(e -> {

            addComponent(tAPub);
            List<String> notes = c.getDBNotes(Astudent.getStudentNumber(), currentUser.UserName);

            tAPub.setValue(notes.get(1));
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

            c.updatePrivDBNotes(Astudent.getStudentNumber(),currentUser.UserName,tAPriv.getValue());
            removeComponent(tAPriv);
            removeComponent(hl1);

        });


        saveNotesPub.addClickListener(e -> {

            c.updatePubDBNotes(Astudent.getStudentNumber(),currentUser.UserName,tAPub.getValue());
            removeComponent(tAPub);
            removeComponent(hl2);

        });


        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(addPrivNotes, addPubNotes);
        if (!Astudent.getBadData().isEmpty()){
            addComponent(BDGrid);
        }

        addComponent(CGrid);

        addComponent(viewCourseHistory);

        if(!Astudent.getPreviouscourses().isEmpty()){
            addComponent(PrevCGrid);
        }
        else{
            addComponent(noPrevCourses);
        }

        PrevCGrid.setVisible(false);
        noPrevCourses.setVisible(false);
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

    public BadDataGrid getBDGrid() {
        return BDGrid;
    }

    public void setBDGrid(BadDataGrid BDGrid) {
        this.BDGrid = BDGrid;
    }
}
