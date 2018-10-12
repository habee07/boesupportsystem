package com.example;

import junit.framework.TestCase;
import java.util.*;

public class studentsTest extends TestCase {
    List<Courses> courses=new List<Courses>();
    List<StudentHistory> history=new List<StudentHistory>();
    List<NoteInfo> notes=new List<NoteInfo>();
    public final students S_TEST = new students("1419904", "Dean", "James", "BSC",courses, history, notes);

    public void testGetStudentNumber() {
        assertEquals(S_TEST.getStudentNumber(),"1419904");
    }

    public void testGetStudentSurname() {
        assertEquals(S_TEST.getStudentSurname(),"Dean");
    }

    public void testGetStudentName() {
        assertEquals(S_TEST.getStudentName(),"James");
    }
}