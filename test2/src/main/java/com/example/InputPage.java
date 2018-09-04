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

        //DB CONNECTION TEST:
        MysqlCon conn = new MysqlCon();
        List<String> data = conn.getData();
        String list = "";
        for(int i = 0; i< data.size(); i++) {
            list = list + "\n"+ data.get(i);
        }
        Label dataPrint = new Label(list);



        // making students with courses;
        List<students> allStudents = new ArrayList<>();


        List<StudentHistory> history1 = new ArrayList<>();
        List<StudentHistory> history2 = new ArrayList<>();
        history1.add(new StudentHistory(2018,"YOS3", "SB000", "RTC", "Repeat Current Year", 34.5, 144, 144));
        history2.add(new StudentHistory(2018,"YOS3", "SB000", "PCD", "Permitted to Proceed", 65.5, 144, 144));

        List<Courses> course1 = new ArrayList<>();
        course1.add(new Courses("maths", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        course1.add(new Courses("physics", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        course1.add(new Courses("chemistry", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        students stud1 = new students("1322634","docrat" , "jack", "SB000", course1, history1);
        List<Courses> course2 = new ArrayList<>();
        course2.add(new Courses("coms", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        course2.add(new Courses("cam", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        course2.add(new Courses("maths", 35, 50, "PMIN", 2018, "YOS3", 44, "MATH3009"));
        students stud2 = new students("1424988", "manack", "jill", "SB000", course2, history2);

        allStudents.add(stud1);
        allStudents.add(stud2);

        CGridLayout courseGrid = new CGridLayout(allStudents);
        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponents(welcome, dataPrint);

        addComponents(courseGrid);



    }
}
