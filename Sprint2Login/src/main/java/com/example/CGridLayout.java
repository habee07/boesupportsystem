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
    //private final CourseGrid CGrid2;
    private students Astudent;
    private List<String> FilterList;
    private String FilterType;
    private StudentDetails studentDetails;
    private StudentYearInfo studentYearInfo;
    private CourseGrid CGrid;;

    public CGridLayout(students aStudentsss, List<String> filterlist,String filtertype ) {
        Astudent = aStudentsss;

        FilterList = filterlist;
        FilterType = filtertype;
        Button addNotes = new Button("ADD NOTES");
        addNotes.setIcon(VaadinIcons.COMMENT_ELLIPSIS_O);
        Button saveNotes = new Button("Save Changes");
        saveNotes.setIcon(VaadinIcons.LOCATION_ARROW_CIRCLE);
        Button cancelNotes = new Button("Cancel");
        cancelNotes.setIcon(VaadinIcons.MINUS_CIRCLE_O);
        addNotes.setStyleName("primary");
        saveNotes.setStyleName("friendly");
        cancelNotes.setStyleName("danger");
        TextArea tA = new TextArea();

        String currN = Astudent.getStNotes().getNote();
        tA.setWordWrap(true);
        tA.setHeight("150px"); // fixed size with height larger than the panel
        tA.setWidth("100%");
        tA.setValue(currN);
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeFull();

        studentDetails = new StudentDetails(Astudent);
            studentYearInfo = new StudentYearInfo(Astudent.getHistory());
            addComponents(studentDetails, studentYearInfo);
            CGrid = new CourseGrid(Astudent.getCourse());
            if (FilterType.contains("SOFT")) {
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

        MysqlCon c = new MysqlCon();
        addNotes.addClickListener(e -> {
            addComponent(tA);
            tA.setValue(c.getDBNotes(Astudent.getStudentNumber()));
            hl.addComponents(cancelNotes, saveNotes);
            hl.setComponentAlignment(saveNotes, Alignment.MIDDLE_CENTER);
            addComponent(hl);

        });




        cancelNotes.addClickListener(e -> {
                    removeComponent(hl);
                    removeComponent(tA);

                }
        );



        saveNotes.addClickListener(e -> {

            c.updateDBNotes(Astudent.getStudentNumber(), tA.getValue());
            removeComponent(tA);
            removeComponent(hl);

        });



        addComponent(CGrid);
        addComponentsAndExpand(addNotes);


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

    private void RemoveHardFilter(Button.ClickEvent clickEvent) {
        removeAllComponents();
        //addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        //FilterList.clear();
        updateItemsList();
    }


    private void updateItemsList() {


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
