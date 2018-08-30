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
        addColumn(Courses::getName).setCaption("Name");
        addColumn(Courses::getCourseMark).setCaption("Course Mark");
        addColumn(Courses::getCourseResult).setCaption("Course Result");

        setItems(courses);
        setSizeFull();
        setStyleGenerator(t -> {
            if (t.getName().equals("coms")) {
                return "coms";
            }
            else if(t.getName().equals("maths")) {
                return "maths";

            }
            else if(t.getName().equals("cam")) {
                return "cam";

            }
            else if(t.getName().equals("physics")) {
                return "physics";

            }
            else {
                return null;
            }
        });


    }



}

