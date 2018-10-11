package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Laila on 11/10/2018.
 */
public class CoursesTest {

    private final Courses C_Test = new Courses("Computer Science", 70.0, 0, "PAS", 2018, 36, "coms2000");

    @Test
    public void testGetStrSupp() throws Exception {

        assertEquals(C_Test.getStrSupp(),"0");

    }

    @Test
    public void testSetStrSupp() throws Exception {

    }

    @Test
    public void testGetCourseOutcome() throws Exception {

        assertEquals(C_Test.getCourseOutcome(),"PAS");

    }

    @Test
    public void testSetCourseOutcome() throws Exception {

    }

    @Test
    public void testGetFinalMark() throws Exception {

        assertEquals(C_Test.getFinalMark(),70.0);

    }

    @Test
    public void testSetFinalMark() throws Exception {

    }

    @Test
    public void testGetCourseName() throws Exception {

        assertEquals(C_Test.getCourseName(),"Computer Science");

    }

    @Test
    public void testSetCourseName() throws Exception {

    }

    @Test
    public void testGetYear() throws Exception {

        assertEquals(C_Test.getYear(),2018);

    }

    @Test
    public void testSetYear() throws Exception {

    }

    @Test
    public void testGetCourseCode() throws Exception {

    }

    @Test
    public void testSetCourseCode() throws Exception {

    }

    @Test
    public void testGetCredits() throws Exception {

    }

    @Test
    public void testSetCredits() throws Exception {

    }

    @Test
    public void testGetSuppMark() throws Exception {

    }

    @Test
    public void testSetSuppMark() throws Exception {

    }
}