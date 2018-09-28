package com.example;

import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */
public class students {

    private String name;
    private List<Courses> course;
    private String yearOutCome;

    public students(String fullName, String yearOC , List<Courses> coursesList) {
        name = fullName;
        yearOutCome = yearOC;
        course = coursesList;

    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        fullname = name;
    }

    public List<Courses> getCourse(){
        return course;
    }
}
