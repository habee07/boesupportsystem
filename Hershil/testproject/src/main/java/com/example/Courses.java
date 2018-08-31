package com.example;

/**
 * Created by habee on 2018/08/19.
 */
public class Courses {
    private int courseMark;
    private String courseResult;
    private String name;


    public Courses(String courseName, int mark, String result) {
        name = courseName;
        courseMark = mark;
        courseResult = result;

    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        name = fullname;
    }

    public String getCourseResult() {
        return courseResult;
    }

    public int getCourseMark() {
        return courseMark;
    }


}
