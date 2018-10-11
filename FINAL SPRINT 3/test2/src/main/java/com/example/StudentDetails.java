package com.example;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by habee on 2018/09/02.
 */
public class StudentDetails extends Grid<students> {
    private students studentObject;
    public StudentDetails(students aStudent) {
        studentObject = aStudent;
        addColumn(students::getStudentNumber).setCaption("Student Number");
        addColumn(students::getStudentName).setCaption("Student Name");
        addColumn(students::getStudentSurname).setCaption("Student Surname");
        setItems(studentObject);
        setSizeFull();
        setHeightByRows(1);

    }
}
