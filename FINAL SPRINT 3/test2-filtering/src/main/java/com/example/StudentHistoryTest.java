package com.example;

import junit.framework.TestCase;

public class StudentHistoryTest extends TestCase {
    public final StudentHistory SH_Test = new StudentHistory(2017, "3rd","SB000","Pass", "Graduate",75,144,144);
    public void testGetYear() {
        assertEquals(SH_Test.getYear(),"2017");
    }

    public void testGetYos() {
        assertEquals(SH_Test.getYos(),"3rd");
    }

    public void testGetEnrolledCredits() {
        assertNotNull("Object not null",SH_Test);
        assertEquals(SH_Test.getEnrolledCredits(),144);
    }

    public void testGetAchievedCredits() {
        assertEquals(SH_Test.getAchievedCredits(),144);
    }
}