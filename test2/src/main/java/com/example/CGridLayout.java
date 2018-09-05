package com.example;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
final class CGridLayout extends VerticalLayout {

     List<CourseGrid> CGridList;
    //private final CourseGrid CGrid2;
    private final TextField courseNameFilter;
    private final TextField courseCodeFilter;
    private final ComboBox<String> courseOutcomeFilter;

    private final TextField minSuppMarkFilter;
    private final TextField maxSuppMarkFilter;

    private final TextField minFinalMarkFilter;
    private final TextField maxFinalMarkFilter;

    private final Button SoftFilter;
    private final Button HardFilter;
    private List<String> FilterList;
    private String FilterType;
    private List<students> allStudents;

    private HorizontalLayout Filtering1;
    private HorizontalLayout Filtering2;
    private HorizontalLayout Filtering3;
    private HorizontalLayout Filtering4;


    public CGridLayout(List<students> allStudentsss) {
        allStudents = allStudentsss;
        // FILTERING :
        FilterType = "NONE";
        FilterList = new ArrayList<>();
        Filtering1 = new HorizontalLayout();
        Filtering2 = new HorizontalLayout();
        Filtering3 = new HorizontalLayout();
        Filtering4 = new HorizontalLayout();
        Filtering1.setCaption("Filter Options");
        Filtering1.setSizeFull();
        //Filtering2.setSizeFull();
        //Filtering3.setSizeFull();
        Filtering4.setSizeFull();
        courseNameFilter = new TextField();
        courseNameFilter.setPlaceholder("Course Name...");
        //courseOutcomeFilter.
        courseCodeFilter = new TextField();
        courseCodeFilter.setPlaceholder("Course Code...");
        courseOutcomeFilter = new ComboBox<>();
        courseOutcomeFilter.setPlaceholder("Course Outcome...");
        courseOutcomeFilter.setEmptySelectionAllowed(true);
        courseOutcomeFilter.setTextInputAllowed(true);
        List<String> CourseCodes = new ArrayList<>();
        CourseCodes.add("PASS");
        CourseCodes.add("FAIL");
        CourseCodes.add("PMN");
        CourseCodes.add("PMP");
        CourseCodes.add("FAB");


        courseOutcomeFilter.setItems(CourseCodes);
        //courseOutcomeFilter = new TextField();
        //courseOutcomeFilter.setPlaceholder("Course Outcome...");
        minSuppMarkFilter = new TextField();
        minSuppMarkFilter.setPlaceholder("Min Supp Mark...");
        maxSuppMarkFilter = new TextField();
        maxSuppMarkFilter.setPlaceholder("Max Supp Mark...");
        minFinalMarkFilter = new TextField();
        minFinalMarkFilter.setPlaceholder("Min Final Mark...");
        maxFinalMarkFilter = new TextField();
        maxFinalMarkFilter.setPlaceholder("Max Final Mark...");
        SoftFilter = new Button("Soft Filter");
        SoftFilter.addClickListener(this::SoftFiltering);
        HardFilter = new Button("Hard Filter");
        HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");


        //nameFilter.addValueChangeListener(this::onNameFilterTextChange);
        //yearFilter.addValueChangeListener(this::onYearFilterTextChange);

        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, dash ,maxFinalMarkFilter);
        Filtering4.addComponents( SoftFilter, HardFilter);
        addComponents(Filtering1, Filtering2, Filtering3, Filtering4);


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
        updateItemsList();

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

    private void updateItemsList() {
        CourseGrid CGrid;
        for(int i=0;i<allStudents.size();i++) {
            StudentDetails studentDetails = new StudentDetails(allStudents.get(i));
            StudentYearInfo studentYearInfo = new StudentYearInfo(allStudents.get(i).getHistory());
            addComponents(studentDetails, studentYearInfo);
            CGrid = new CourseGrid(allStudents.get(i).getCourse());
            if (FilterType.contains("SOFT")) {
                CGrid.setStyleGenerator(t -> {
                    /**if (t.getCourseOutcome().contains(FilterList.get(0))) {
                        return "filters";
                    }
                     **/
                    if (t.getCourseCode().contains(FilterList.get(1))) {
                        return "filters";
                    }
                    /**if (t.getCourseName().contains(FilterList.get(2))) {
                        return "filters";
                    }
                    if(FilterList.get(3).equals("EMPTY")){
                        FilterList.set(3,"0");
                    }
                    if(FilterList.get(4).equals("EMPTY")){
                        FilterList.set(4,"100");
                    }
                    if(FilterList.get(5).equals("EMPTY")){
                        FilterList.set(5,"0");
                    }
                    if(FilterList.get(6).equals("EMPTY")){
                        FilterList.set(6,"100");
                    }
                    if (t.getFinalMark() >= Double.parseDouble(FilterList.get(3)) && t.getFinalMark() <= Double.parseDouble(FilterList.get(4))) {
                        return "filters";
                    }
                    if (t.getSuppMark() >= Double.parseDouble(FilterList.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterList.get(4))) {
                        return "filters";
                    }
                    **/
                    else {
                        return null;
                    }
                });
            }

            addComponentsAndExpand(CGrid);

        }



    }


    private void SoftFiltering(Button.ClickEvent clickEvent) {
        String value;
        if(courseOutcomeFilter.getValue() != null){
             value = courseOutcomeFilter.getValue();

        }
        else{
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseCodeFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseNameFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);

        FilterType = "SOFT";
        removeAllComponents();
        addComponents(Filtering1, Filtering2, Filtering3, Filtering4);

        updateItemsList();
    }

    private void HardFiltering(Button.ClickEvent clickEvent) {
        String value = courseOutcomeFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = courseCodeFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = courseNameFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);

        FilterType = "HARD";

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

}
