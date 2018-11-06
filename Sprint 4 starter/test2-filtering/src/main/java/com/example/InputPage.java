package com.example;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/18.
 */
public class InputPage extends VerticalLayout implements View {

    public static final String NAME = "inputpage";

    List<CGridLayout> CGridLayoutList;
    private TextField courseNameFilter;
    private TextField courseCodeFilter;
    private ComboBox<String> courseOutcomeFilter;

    private TextField minSuppMarkFilter;
    private TextField maxSuppMarkFilter;

    private TextField minFinalMarkFilter;
    private TextField maxFinalMarkFilter;

    private Button SoftFilter;
    private Button RemoveSoft;
    private Button HardFilter;
    private Button RemoveHard;
    private List<String> FilterList;
    private String FilterType;
    private List<students> allStudents;

    private HorizontalLayout Filtering1;
    private HorizontalLayout Filtering2;
    private HorizontalLayout Filtering3;
    private HorizontalLayout Filtering4;

    public InputPage() {



        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponent(welcome);

        VerticalLayout filteringLayout = new VerticalLayout();
        FilterType = "NONE";
        FilterList = new ArrayList<>();
        Filtering1 = new HorizontalLayout();
        Filtering2 = new HorizontalLayout();
        Filtering3 = new HorizontalLayout();
        Filtering4 = new HorizontalLayout();
        Filtering1.setCaption("Filter Options");
        Filtering1.setSizeFull();
        //Filtering2.setSizeFull();
        //Filtering3.setSizeFull();
        Filtering4.setSizeFull();
        courseNameFilter = new TextField();
        courseNameFilter.setPlaceholder("Course Name...");
        //courseOutcomeFilter.
        courseCodeFilter = new TextField();
        courseCodeFilter.setPlaceholder("Course Code...");
        courseOutcomeFilter = new ComboBox<>();
        courseOutcomeFilter.setPlaceholder("Course Outcome...");
        courseOutcomeFilter.setEmptySelectionAllowed(true);
        courseOutcomeFilter.setTextInputAllowed(true);
        List<String> CourseCodes = new ArrayList<>();
        CourseCodes.add("PASS");
        CourseCodes.add("fal");
        CourseCodes.add("PAS");
        CourseCodes.add("FAIL");
        CourseCodes.add("PMN");
        CourseCodes.add("PMP");
        CourseCodes.add("FAB");


        courseOutcomeFilter.setItems(CourseCodes);
        //courseOutcomeFilter = new TextField();
        //courseOutcomeFilter.setPlaceholder("Course Outcome...");
        minSuppMarkFilter = new TextField();
        minSuppMarkFilter.setPlaceholder("Min Supp Mark...");
        maxSuppMarkFilter = new TextField();
        maxSuppMarkFilter.setPlaceholder("Max Supp Mark...");
        minFinalMarkFilter = new TextField();
        minFinalMarkFilter.setPlaceholder("Min Final Mark...");
        maxFinalMarkFilter = new TextField();
        maxFinalMarkFilter.setPlaceholder("Max Final Mark...");
        SoftFilter = new Button("Soft Filter");
        RemoveSoft = new Button("Remove Soft Filter");
        SoftFilter.addClickListener(this::SoftFiltering);
        RemoveSoft.addClickListener(this::RemoveSoftFilter);
        HardFilter = new Button("Hard Filter");
        RemoveHard = new Button("Remove Hard Filter");
        RemoveHard.addClickListener(this::RemoveHardFilter);
        HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");

        HardFilter.setStyleName("primary");
        SoftFilter.setStyleName("friendly");
        RemoveSoft.setStyleName("danger");
        RemoveHard.setStyleName("danger");

        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, dash ,maxFinalMarkFilter);
        Filtering4.addComponents( SoftFilter, RemoveSoft, HardFilter, RemoveHard);
        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4);


        addComponent(filteringLayout);

        //DB CONNECTION TEST:
        MysqlCon conn = new MysqlCon();

        allStudents = conn.getStudentObjects();

        //System.out.println(allStudents.size());
        Label test = new Label("test");



        CGridLayoutList = new ArrayList<>();
        for(int i=0; i<allStudents.size();i++){
            CGridLayout studentGrid = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            CGridLayoutList.add(studentGrid);
            addComponents(studentGrid);

        }


    }

    private void RemoveHardFilter(Button.ClickEvent clickEvent) {
        FilterType = "NONE";
        for(int i=0;i<CGridLayoutList.size();i++){
            if(CGridLayoutList.get(i).isVisible() == false){
               CGridLayoutList.get(i).setVisible(true);
            }
        }
        FilterList.clear();
    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {
        FilterList.clear();
        String value;
        if(courseOutcomeFilter.getValue() != null){
            value = courseOutcomeFilter.getValue();

        }
        else{
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseCodeFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseNameFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);

        FilterType = "SOFT";

        for(int i=0; i< CGridLayoutList.size();i++){
                    CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                    replaceComponent(CGridLayoutList.get(i), newReplacement);
                    CGridLayoutList.set(i,newReplacement);

        }

        //updateItemsList();
    }


    private void RemoveSoftFilter(Button.ClickEvent clickEvent) {
        FilterType = "NONE";
        for(int i=0; i< CGridLayoutList.size();i++){
                    CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                    replaceComponent(CGridLayoutList.get(i), newReplacement);
                    CGridLayoutList.set(i,newReplacement);

        }//removeAllComponents();
        //addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        //FilterList.clear();
        //updateItemsList();

    }


    private void HardFiltering(Button.ClickEvent clickEvent) {
        FilterList.clear();
        String value;
        if(courseOutcomeFilter.getValue() != null){
            value = courseOutcomeFilter.getValue();

        }
        else{
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseCodeFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseNameFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = maxFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);

        FilterType = "HARD";
        for(int i=0; i< CGridLayoutList.size();i++){
            //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
            List<Boolean> boolpercourse = new ArrayList<>();
            int j=0;
            boolean studentAns = false;
            while(studentAns == false && j < allStudents.get(i).getCourse().size() ){

                boolean Answer = true;
                int k =0;
                List<String> testing = new ArrayList<>();
                List<Double> numTesting = new ArrayList<>();
                testing.add(allStudents.get(i).getCourse().get(j).getCourseOutcome());
                testing.add(allStudents.get(i).getCourse().get(j).getCourseCode());
                testing.add(allStudents.get(i).getCourse().get(j).getCourseName());
                // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                numTesting.add(-1.0);
                numTesting.add(-1.0);
                numTesting.add(-1.0);
                numTesting.add(allStudents.get(i).getCourse().get(j).getSuppMark());
                numTesting.add(allStudents.get(i).getCourse().get(j).getSuppMark());

                numTesting.add(allStudents.get(i).getCourse().get(j).getFinalMark());
                numTesting.add(allStudents.get(i).getCourse().get(j).getFinalMark());

                // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                while(Answer == true && k < FilterList.size()-4){
                    if( FilterList.get(k).equals("EMPTY") != true){
                        if(testing.get(k).contains(FilterList.get(k))){
                            Answer = true;
                        }
                        else{
                            Answer = false;
                        }
                    }

                    k = k + 1;

                }
                // CHECKING MARKS NOW:
                while(Answer == true && k < FilterList.size()){
                    // CHECKING MIN SUPP/FINAL MARK
                    if( FilterList.get(k).equals("EMPTY") != true){
                        if(numTesting.get(k) >= Double.parseDouble(FilterList.get(k)) ){
                            Answer = true;
                        }
                        else{
                            Answer = false;
                        }
                    }

                    k = k + 1;
                    // CHECKING MAX SUPP/FINAL MARK
                    if( FilterList.get(k).equals("EMPTY") != true && Answer == true) {
                        if (numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
                            Answer = true;
                        } else {
                            Answer = false;
                        }
                    }
                    k = k + 1;
                }
                if(Answer == true){
                    studentAns = true;
                }

            j = j+1;
            }
            if(studentAns == false){
                CGridLayoutList.get(i).setVisible(false);
            }

        }

    }

}
