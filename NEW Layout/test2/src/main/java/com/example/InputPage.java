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
        //SoftFilter.addClickListener(this::SoftFiltering);
        //RemoveSoft.addClickListener(this::RemoveSoftFilter);
        HardFilter = new Button("Hard Filter");
        RemoveHard = new Button("Remove Hard Filter");
        //RemoveHard.addClickListener(this::RemoveHardFilter);
        //HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");

        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, dash ,maxFinalMarkFilter);
        Filtering4.addComponents( SoftFilter, RemoveSoft, HardFilter, RemoveHard);
        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4);


        addComponent(filteringLayout);

        //DB CONNECTION TEST:
        MysqlCon conn = new MysqlCon();
        List<students> allStudents = conn.getStudentObjects();
        //System.out.println(allStudents.size());
        Label test = new Label("test");

        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponent(welcome);


        CGridLayoutList = new ArrayList<>();
        for(int i=0; i<allStudents.size();i++){
            CGridLayout studentGrid = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            CGridLayoutList.add(studentGrid);
            addComponents(studentGrid);

        }


    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {
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
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
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
        FilterList.add(value);

        FilterType = "SOFT";
        removeAllComponents();
        addComponents(Filtering1, Filtering2, Filtering3, Filtering4);

        //updateItemsList();
    }

    private void HardFiltering(Button.ClickEvent clickEvent) {
        String value = courseOutcomeFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = courseCodeFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = courseNameFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = minFinalMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);
        value = maxSuppMarkFilter.getData().toString();
        if(value.equals("")){
            value = "EMPTY";
        }
        FilterList.add(value);

        FilterType = "HARD";

    }

}
