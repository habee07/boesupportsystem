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
public class MysqlConTest {
    
    public MysqlConTest() {
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
     * Test of getStudentNumbers method, of class MysqlCon.
     */
    @Test
    public void testGetStudentNumbers() {
        System.out.println("getStudentNumbers");
        MysqlCon instance = new MysqlCon();
        instance.getStudentNumbers();
    }

    /**
     * Test of getStudentObjects method, of class MysqlCon.
     */
    @Test
    public void testGetStudentObjects() {
        System.out.println("getStudentObjects");
        MysqlCon instance = new MysqlCon();
        List<students> expResult = instance.getStudentObjects();
        List<students> result = instance.getStudentObjects();
        
    }

    /**
     * Test of updatePubDBNotes method, of class MysqlCon.
     */
    @Test
    public void testUpdatePubDBNotes() {
        System.out.println("updatePubDBNotes");
        String stNum = "";
        String userName = "";
        String notePublic = "";
        MysqlCon instance = new MysqlCon();
        instance.updatePubDBNotes(stNum, userName, notePublic);
    }

    /**
     * Test of updatePrivDBNotes method, of class MysqlCon.
     */
    @Test
    public void testUpdatePrivDBNotes() {
        System.out.println("updatePrivDBNotes");
        String stNum = "";
        String userName = "";
        String notePrivate = "";
        MysqlCon instance = new MysqlCon();
        instance.updatePrivDBNotes(stNum, userName, notePrivate);
    }

    /**
     * Test of getDBNotes method, of class MysqlCon.
     */
    @Test
    public void testGetDBNotes() {
        System.out.println("getDBNotes");
        String stNum = "";
        String userName = "";
        MysqlCon instance = new MysqlCon();
        List<String> expResult = Arrays.asList("","");
        List<String> result = instance.getDBNotes(stNum, userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsers method, of class MysqlCon.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        MysqlCon instance = new MysqlCon();
        List<Users> expResult = instance.getUsers();
        List<Users> result = instance.getUsers();
        
    }

    /**
     * Test of addNewNote method, of class MysqlCon.
     */
    @Test
    public void testAddNewNote() {
        System.out.println("addNewNote");
        String stNum = "test";
        NoteInfo note = new NoteInfo("test","test","test");
        MysqlCon instance = new MysqlCon();
        instance.addNewNote(stNum, note);
    }

    /**
     * Test of DBNotes method, of class MysqlCon.
     */
    @Test
    public void testDBNotes() {
        System.out.println("DBNotes");
        String stNum = "test";
        NoteInfo note = new NoteInfo("test","test","test");
        MysqlCon instance = new MysqlCon();
        instance.DBNotes(stNum, note);
    }
    
}
