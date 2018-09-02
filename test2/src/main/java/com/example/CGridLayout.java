package com.example;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
final class CGridLayout extends VerticalLayout {

    private final CourseGrid CGrid;
    private final CourseGrid CGrid2;
    //private final TextField nameFilter;
    //private final TextField yearFilter;

    public CGridLayout(List<students> allStudents) {
        //nameFilter = new TextField();
        //yearFilter = new TextField();
        //nameFilter.setPlaceholder("Name...");
        //yearFilter.setPlaceholder("mark...");
        //nameFilter.addValueChangeListener(this::onNameFilterTextChange);
        //yearFilter.addValueChangeListener(this::onYearFilterTextChange);
        //addComponent(nameFilter);
        //addComponent(yearFilter);


        //Label studentInfo = ;

        //Label studentDetails0 = new Label(allStudents.get(0).getStudentNumber()+ "  " + allStudents.get(0).getStudentName() + " " + allStudents.get(0).getStudentSurname());
        //studentDetails0.addStyleName("h1");
        //Label studentDetails1 = new Label(allStudents.get(1).getStudentNumber()+ "  " + allStudents.get(1).getStudentName() + " " + allStudents.get(1).getStudentSurname());
        //studentDetails1.addStyleName("h1");
        Label blank = new Label(" ");
        blank.setStyleName("h2");
        StudentDetails studentDetails0 = new StudentDetails(allStudents.get(0));
        StudentDetails studentDetails1 = new StudentDetails(allStudents.get(1));

        StudentYearInfo studentYearInfo0 = new StudentYearInfo(allStudents.get(0).getHistory());
        StudentYearInfo studentYearInfo1 = new StudentYearInfo(allStudents.get(1).getHistory());


        //studentSubHeader.setSizeUndefined();
        addComponents(studentDetails0,studentYearInfo0);
        CGrid = new CourseGrid(allStudents.get(0).getCourse());
        addComponentsAndExpand(CGrid);

        //setSizeFull();
        CGrid2 = new CourseGrid(allStudents.get(1).getCourse());
        addComponents(studentDetails1, studentYearInfo1);
        addComponentsAndExpand(CGrid2);
        //setSizeFull();
    }

    private void onYearFilterTextChange(HasValue.ValueChangeEvent<String> stringValueChangeEvent) {
        ListDataProvider<Courses> dataProvider = (ListDataProvider<Courses>) CGrid.getDataProvider();

        ListDataProvider<Courses> dataProvider2 = (ListDataProvider<Courses>) CGrid2.getDataProvider();
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
