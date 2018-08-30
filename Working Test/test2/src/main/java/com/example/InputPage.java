package com.example;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/18.
 */
public class InputPage extends VerticalLayout implements View {

    public static final String NAME = "inputpage";
    public InputPage() {
        Panel inputPanel = new Panel("Input data");
         inputPanel.setSizeUndefined();
         addComponent(inputPanel);


        // making students with courses;
        List<students> allStudents = new ArrayList<>();

        List<Courses> course1 = new ArrayList<>();
        course1.add(new Courses("maths", 35, "PMIN"));
        course1.add(new Courses("physics", 20, "FAIL"));
        course1.add(new Courses("coms", 70, "PASS"));
        students stud1 = new students("jack", "RPT", course1);
        List<Courses> course2 = new ArrayList<>();
        course2.add(new Courses("maths", 70, "PASS"));
        course2.add(new Courses("cam", 60, "PASS"));
        course2.add(new Courses("coms", 80, "PASS"));
        students stud2 = new students("jill", "PCD", course2);

        allStudents.add(stud1);
        allStudents.add(stud2);

        CGridLayout courseGrid = new CGridLayout(allStudents);
        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponent(welcome);

        addComponents(courseGrid);



    }
}
