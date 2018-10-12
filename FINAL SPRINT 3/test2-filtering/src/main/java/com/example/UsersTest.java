package com.example;

import junit.framework.TestCase;

public class UsersTest extends TestCase {
    public final Users U_Test = new Users("Kenny","Male","Lecturer","1234","Coms Lecturer","Kenny.bill@gmail.com","Kenny", "3")

    public void testLogOut() {
        assertNull("Logged out", U_Test.logOut());
    }
}