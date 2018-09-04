package com.mycompany.tutorial;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Craig
 */
class CSVReader {
private static final String COMMA_DELIMITER = ",";
private static int STUDENT_NUMBER_IDX = 3;
private static int STUDENT_FNAME_IDX = 4;
private static int STUDENT_LNAME_IDX = 5;
private static int STUDENT_ProgramCode = 0;
private static int STUDENT_RegStatus = 1;
private static int STUDENT_CourseAttemptStatus = 2;
private static int STUDENT_CalenderInstanceYear = 6;
private static int STUDENT_CourseCode = 7;
private static int STUDENT_CourseTitle = 8;
private static int STUDENT_NQFCredit = 9;
private static int STUDENT_SuppMark = 10;
private static int STUDENT_FinalMark = 11;
private static int STUDENT_FinalGrade = 12;

public static void readCsvFile(String fileName) {
BufferedReader fileReader = null;
try {
    List students = new ArrayList();
    String line;
    fileReader = new BufferedReader(new FileReader(fileName));
    fileReader.readLine();
        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                Student student = new Student(tokens[STUDENT_ProgramCode], tokens[STUDENT_RegStatus], tokens[STUDENT_CourseAttemptStatus], Long.parseLong(tokens[STUDENT_NUMBER_IDX]), tokens[STUDENT_FNAME_IDX],tokens[STUDENT_LNAME_IDX], Integer.parseInt(tokens[STUDENT_CalenderInstanceYear]), tokens[STUDENT_CourseCode], tokens[STUDENT_CourseTitle],Integer.parseInt(tokens[STUDENT_NQFCredit]), Integer.parseInt(tokens[STUDENT_SuppMark]), Integer.parseInt(tokens[STUDENT_FinalMark]), tokens[STUDENT_FinalGrade]);
                        students.add(student);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }
}

