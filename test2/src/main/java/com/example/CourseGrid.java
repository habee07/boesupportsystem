package com.example;

import com.vaadin.data.ValueProvider;
import com.vaadin.ui.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */

final class CourseGrid extends Grid<Courses> {
    private List<Courses> courses;
    public CourseGrid(List<Courses> studCourses) {
        courses = studCourses;


        //String k = courses.get(1).getName();
        //k = "<html><font color=\"red\">" + k + "</font></html>";
        addColumn(Courses::getCourseName).setCaption("Name");
        addColumn(Courses::getFinalMark).setCaption("Course Mark");
        addColumn(Courses::getCourseOutcome).setCaption("Course Result");

        setItems(courses);
        setSizeFull();
        setStyleGenerator(t -> {
            if (t.getCourseName().equals("coms")) {
                return "coms";
            }
            else if(t.getCourseName().equals("maths")) {
                return "maths";

            }
            else if(t.getCourseName().equals("cam")) {
                return "cam";

            }
            else if(t.getCourseName().equals("physics")) {
                return "physics";

            }
            else {
                return null;
            }
        });


    }



}

