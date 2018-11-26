/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

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
public class StudentHistoryTest {
    
    public StudentHistoryTest() {
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
     * Test of getYear method, of class StudentHistory.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        int expResult = 2018;
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYear method, of class StudentHistory.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2018;
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144) ;
        instance.setYear(year);
    }

    /**
     * Test of getYos method, of class StudentHistory.
     */
    @Test
    public void testGetYos() {
        System.out.println("getYos");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        String expResult = "3";
        String result = instance.getYos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYos method, of class StudentHistory.
     */
    @Test
    public void testSetYos() {
        System.out.println("setYos");
        String yos = "3";
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setYos(yos);
    }

    /**
     * Test of getProgramCode method, of class StudentHistory.
     */
    @Test
    public void testGetProgramCode() {
        System.out.println("getProgramCode");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        String expResult = "SB00";
        String result = instance.getProgramCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProgramCode method, of class StudentHistory.
     */
    @Test
    public void testSetProgramCode() {
        System.out.println("setProgramCode");
        String programCode = "SB00";
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setProgramCode(programCode);
    }

    /**
     * Test of getYearOutcome method, of class StudentHistory.
     */
    @Test
    public void testGetYearOutcome() {
        System.out.println("getYearOutcome");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        String expResult = "Pass";
        String result = instance.getYearOutcome();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYearOutcome method, of class StudentHistory.
     */
    @Test
    public void testSetYearOutcome() {
        System.out.println("setYearOutcome");
        String yearOutcome = "Pass";
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setYearOutcome(yearOutcome);
    }

    /**
     * Test of getOutcomeDescription method, of class StudentHistory.
     */
    @Test
    public void testGetOutcomeDescription() {
        System.out.println("getOutcomeDescription");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        String expResult = "Pass";
        String result = instance.getOutcomeDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOutcomeDescription method, of class StudentHistory.
     */
    @Test
    public void testSetOutcomeDescription() {
        System.out.println("setOutcomeDescription");
        String outcomeDescription = "Pass";
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setOutcomeDescription(outcomeDescription);
    }

    /**
     * Test of getAverageMarks method, of class StudentHistory.
     */
    @Test
    public void testGetAverageMarks() {
        System.out.println("getAverageMarks");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        double expResult = 80.0;
        double result = instance.getAverageMarks();
        assertEquals(expResult, result,0.0);
    }

    /**
     * Test of setAverageMarks method, of class StudentHistory.
     */
    @Test
    public void testSetAverageMarks() {
        System.out.println("setAverageMarks");
        double averageMarks = 80.0;
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.0, 0, 144);
        instance.setAverageMarks(averageMarks);
    }

    /**
     * Test of getEnrolledCredits method, of class StudentHistory.
     */
    @Test
    public void testGetEnrolledCredits() {
        System.out.println("getEnrolledCredits");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        int expResult = 0;
        int result = instance.getEnrolledCredits();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnrolledCredits method, of class StudentHistory.
     */
    @Test
    public void testSetEnrolledCredits() {
        System.out.println("setEnrolledCredits");
        int enrolledCredits = 0;
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setEnrolledCredits(enrolledCredits);
    }

    /**
     * Test of getAchievedCredits method, of class StudentHistory.
     */
    @Test
    public void testGetAchievedCredits() {
        System.out.println("getAchievedCredits");
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        int expResult = 144;
        int result = instance.getAchievedCredits();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAchievedCredits method, of class StudentHistory.
     */
    @Test
    public void testSetAchievedCredits() {
        System.out.println("setAchievedCredits");
        int achievedCredits = 144;
        StudentHistory instance = new StudentHistory(2018, "3","SB00", "Pass", "Pass", 80.00, 0, 144);
        instance.setAchievedCredits(achievedCredits);
    }
    
}
