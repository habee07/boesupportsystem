package com.example;

import com.vaadin.ui.Grid;

import java.util.List;

/**
 * Created by habee on 2018/09/02.
 */
public class StudentYearInfo extends Grid<StudentHistory> {
    private List<StudentHistory> YearsHistory;


    public StudentYearInfo(List<StudentHistory> studentInfo) {
        YearsHistory = studentInfo;
        addColumn(StudentHistory::getYear).setCaption("Year");
        addColumn(StudentHistory::getYos).setCaption("Year of Study");
        addColumn(StudentHistory::getProgramCode).setCaption("Program Code");
        addColumn(StudentHistory::getYearOutcome).setCaption("Year Outcome");
        addColumn(StudentHistory::getOutcomeDescription);
        addColumn(StudentHistory::getAverageMarks).setCaption("Average Year Mark");
        addColumn(StudentHistory::getAchievedCredits).setCaption("Achieved Credits");
        addColumn(StudentHistory::getEnrolledCredits).setCaption("Enrolled Credits");

        setItems(YearsHistory);
        setSizeFull();
        if(studentInfo.size()!=0){
            setHeightByRows(studentInfo.size());
        }
        else {
            setHeightByRows(1);
        }

    }


}
