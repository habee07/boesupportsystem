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
public class UsersTest {
    
    public UsersTest() {
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
     * Test of MakeUser method, of class Users.
     */
    @Test
    public void testMakeUser() throws Exception {
        System.out.println("MakeUser");
        Users instance = new Users("James","James Bond", "Tutor", "1st year COMS tutor");
        Boolean expResult = false;
        Boolean result = instance.MakeUser();
        assertEquals(expResult, result);

    }

    /**
     * Test of Login method, of class Users.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("Login");
        Users instance = new Users();
        Boolean expResult = false;
        Boolean result = instance.Login();
        assertEquals(expResult, result);
    }

    /**
     * Test of logOut method, of class Users.
     */
    @Test
    public void testLogOut() {
        System.out.println("logOut");
        Users instance = new Users("James","James Bond", "Tutor", "1st year COMS tutor");
        instance.logOut();
    }
    
}
