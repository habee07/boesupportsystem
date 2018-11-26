/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Craig
 */
public class studentsTest {
        Courses c = new Courses("Machine Learning", 89.00, 00.00, "Pass", 2018, 72, "COMS3000");
        List<Courses> course = Arrays.asList(c);
        StudentHistory h = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        List<StudentHistory> history = Arrays.asList(h);
        NoteInfo n = new NoteInfo("test","testing","tested");
        List<NoteInfo> stNotes = Arrays.asList(n);
        students instance = new students("1419904", "Bond","James", "SB00",course, history,stNotes);
    public studentsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHistory method, of class students.
     */
    @Test
    public void testGetHistory() {
        System.out.println("getHistory");
        StudentHistory e = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        List<StudentHistory> StudentResult = Arrays.asList(e);
        List<StudentHistory> result = instance.getHistory();
        
    }

    /**
     * Test of setHistory method, of class students.
     */
    @Test
    public void testSetHistory() {
        System.out.println("setHistory");
        instance.setHistory(history);
    }

    /**
     * Test of getStudentNumber method, of class students.
     */
    @Test
    public void testGetStudentNumber() {
        System.out.println("getStudentNumber");
        String expResult = "1419904";
        String result = instance.getStudentNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStudentNumber method, of class students.
     */
    @Test
    public void testSetStudentNumber() {
        System.out.println("setStudentNumber");
        String studentNumber = "1419904";
        instance.setStudentNumber(studentNumber);
    }

    /**
     * Test of getStudentSurname method, of class students.
     */
    @Test
    public void testGetStudentSurname() {
        System.out.println("getStudentSurname");
        String expResult = "Bond";
        String result = instance.getStudentSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStudentSurname method, of class students.
     */
    @Test
    public void testSetStudentSurname() {
        System.out.println("setStudentSurname");
        String studentSurname = "Bond";
        instance.setStudentSurname(studentSurname);
    }

    /**
     * Test of getStudentName method, of class students.
     */
    @Test
    public void testGetStudentName() {
        System.out.println("getStudentName");
        String expResult = "James";
        String result = instance.getStudentName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStudentName method, of class students.
     */
    @Test
    public void testSetStudentName() {
        System.out.println("setStudentName");
        String studentName = "James";
        instance.setStudentName(studentName);
    }

    /**
     * Test of getProgram method, of class students.
     */
    @Test
    public void testGetProgram() {
        System.out.println("getProgram");
        String expResult = "SB00";
        String result = instance.getProgram();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProgram method, of class students.
     */
    @Test
    public void testSetProgram() {
        System.out.println("setProgram");
        String program = "SB00";
        instance.setProgram(program);
    }

    /**
     * Test of getCourse method, of class students.
     */
    @Test
    public void testGetCourse() {
        System.out.println("getCourse");
        Courses e = new Courses("Machine Learning", 89.00, 00.00, "Pass", 2018, 72, "COMS3000");
        List<Courses> CourseResult = Arrays.asList(e);
        List<Courses> result = instance.getCourse();
        
    }

    /**
     * Test of setCourse method, of class students.
     */
    @Test
    public void testSetCourse() {
        System.out.println("setCourse");
        instance.setCourse(course);
    }

    /**
     * Test of getStNotes method, of class students.
     */
    @Test
    public void testGetStNotes() {
        System.out.println("getStNotes");
        NoteInfo e = new NoteInfo("test","testing","tested");
        List<NoteInfo> NoteResult = Arrays.asList(e);
        List<NoteInfo> result = instance.getStNotes();
        
    }

    /**
     * Test of setStNotes method, of class students.
     */
    @Test
    public void testSetStNotes() {
        System.out.println("setStNotes");
        instance.setStNotes(stNotes);
    }
    
}
