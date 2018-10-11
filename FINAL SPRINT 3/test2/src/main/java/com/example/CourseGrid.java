package com.example;

import com.vaadin.data.ValueProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

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
        Grid.Column<Courses,?> CodeColumn = addColumn(Courses::getCourseCode).setCaption("Course Code");
        Grid.Column<Courses,?> CourseNameColumn = addColumn(Courses::getCourseName).setCaption("Name");
        Grid.Column<Courses,?> SuppColumn = addColumn(Courses::getStrSupp).setCaption("Supp Mark");
        Grid.Column<Courses,?> MarkColumn = addColumn(Courses::getFinalMark).setCaption("Final Mark");
        Grid.Column<Courses,?> OutcomeColumn = addColumn(Courses::getCourseOutcome).setCaption("Course Outcome");
        Grid.Column<Courses,?> YearColumn = addColumn(Courses::getYear).setCaption("Year");
        Grid.Column<Courses,?> CreditsColumn = addColumn(Courses::getCredits).setCaption("Course Credits");


        setItems(courses);
        setSizeFull();
        if(courses.size()!= 0){

            setHeightByRows(courses.size());

        }
        else{
            setHeightByRows(1);
        }
        /**setStyleGenerator(t -> {
            if (t.getCourseCode().equals("coms")) {
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
        **/

    }



}

