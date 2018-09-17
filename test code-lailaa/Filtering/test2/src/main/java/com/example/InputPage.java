package com.example;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Collections;
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

    //SOFT
    private TextField courseNameFilterSOFT;
    private TextField courseCodeFilterSOFT;
    private ComboBox<String> courseOutcomeFilterSOFT;


    private TextField minSuppMarkFilter;
    private TextField maxSuppMarkFilter;

    //SOFT
    private TextField minSuppMarkFilterSOFT;
    private TextField maxSuppMarkFilterSOFT;


    private TextField minFinalMarkFilter;
    private TextField maxFinalMarkFilter;


    //SOFT
    private TextField minFinalMarkFilterSOFT;
    private TextField maxFinalMarkFilterSOFT;


    private Button SoftFilter;
    private Button RemoveSoft;


    private Button HardFilter;
    private Button RemoveHard;


    private List<String> FilterList;

    //soft
    private List<String> FilterListSOFT;


    private String FilterType;
    private List<students> allStudents;



    //LABELS
    private Label labelHARD;
    private Label labelSOFT;


    private HorizontalLayout Filtering0;
    private HorizontalLayout Filtering1;
    private HorizontalLayout Filtering2;
    private HorizontalLayout Filtering3;
    private HorizontalLayout Filtering4;


    private HorizontalLayout Filtering0SOFT;
    private HorizontalLayout Filtering1SOFT;
    private HorizontalLayout Filtering2SOFT;
    private HorizontalLayout Filtering3SOFT;
    private HorizontalLayout Filtering4SOFT;


    private Button mainHbutton;
    private Button mainSbutton;


    private List<Integer> HardList; //1 if on, 0 if off
    private List<Integer> SoftList; //0 if nothing, 1, 2 , 3





    public InputPage() {

        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponent(welcome);


        HorizontalLayout twoMains = new HorizontalLayout();
        twoMains.setSizeFull();
        mainHbutton = new Button("Display Filter Options");
        mainSbutton = new Button("Display Highlight Options");
        twoMains.addComponents(mainHbutton,mainSbutton);

        //addComponent(twoMains);
        FilterType = "NONE";
        List<String> CourseCodes = new ArrayList<>();
        // CourseCodes.add("PASS");
        CourseCodes.add("FAL");
        CourseCodes.add("PAS");
        //CourseCodes.add("FAIL");
        CourseCodes.add("PMN");
        CourseCodes.add("PMP");
        CourseCodes.add("FAB");

        VerticalLayout filteringLayout = new VerticalLayout();

        FilterList = new ArrayList<>();
        Filtering0 = new HorizontalLayout();
        Filtering1 = new HorizontalLayout();
        Filtering2 = new HorizontalLayout();
        Filtering3 = new HorizontalLayout();
        Filtering4 = new HorizontalLayout();
        Filtering1.setCaption("Filter Options");
        Filtering1.setSizeFull();
        //Filtering2.setSizeFull();
        //Filtering3.setSizeFull();
        Filtering4.setSizeFull();

        labelHARD = new Label("HARD FILTER");
        courseNameFilter = new TextField();
        courseNameFilter.setPlaceholder("Course Name...");
        //courseOutcomeFilter.
        courseCodeFilter = new TextField();
        courseCodeFilter.setPlaceholder("Course Code...");
        courseOutcomeFilter = new ComboBox<>();
        courseOutcomeFilter.setPlaceholder("Course Outcome...");
        courseOutcomeFilter.setEmptySelectionAllowed(true);
        courseOutcomeFilter.setTextInputAllowed(true);



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

        SoftFilter = new Button("Highlight");
        RemoveSoft = new Button("Remove Highlight");
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

        Filtering0.addComponent(labelHARD);
        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, new Label("-"), maxFinalMarkFilter);
        Filtering4.addComponents(HardFilter, RemoveHard);
        filteringLayout.addComponents(Filtering0,Filtering1, Filtering2, Filtering3, Filtering4);


        addComponent(filteringLayout);
        //mainHbutton.addClickListener(e -> {twoMains.addComponent(filteringLayout);});


        // SOFT UI

        VerticalLayout filteringLayoutSOFT = new VerticalLayout();
       // FilterType = "NONE";

        FilterListSOFT = new ArrayList<>();
        Filtering0SOFT = new HorizontalLayout();
        Filtering1SOFT = new HorizontalLayout();
        Filtering2SOFT = new HorizontalLayout();
        Filtering3SOFT = new HorizontalLayout();
        Filtering4SOFT = new HorizontalLayout();
        Filtering1SOFT.setCaption("Highlight Options");
        Filtering1SOFT.setSizeFull();
        //Filtering2.setSizeFull();
        //Filtering3.setSizeFull();
        Filtering4SOFT.setSizeFull();

        labelSOFT = new Label("HIGHLIGHTING");
        courseNameFilterSOFT = new TextField();
        courseNameFilterSOFT.setPlaceholder("Course Name...");
        //courseOutcomeFilter.
        courseCodeFilterSOFT = new TextField();
        courseCodeFilterSOFT.setPlaceholder("Course Code...");
        courseOutcomeFilterSOFT = new ComboBox<>();
        courseOutcomeFilterSOFT.setPlaceholder("Course Outcome...");
        courseOutcomeFilterSOFT.setEmptySelectionAllowed(true);
        courseOutcomeFilterSOFT.setTextInputAllowed(true);
        courseOutcomeFilterSOFT.setItems(CourseCodes);
        //courseOutcomeFilter = new TextField();
        //courseOutcomeFilter.setPlaceholder("Course Outcome...");
        minSuppMarkFilterSOFT = new TextField();
        minSuppMarkFilterSOFT.setPlaceholder("Min Supp Mark...");
        maxSuppMarkFilterSOFT = new TextField();
        maxSuppMarkFilterSOFT.setPlaceholder("Max Supp Mark...");
        minFinalMarkFilterSOFT = new TextField();
        minFinalMarkFilterSOFT.setPlaceholder("Min Final Mark...");
        maxFinalMarkFilterSOFT = new TextField();
        maxFinalMarkFilterSOFT.setPlaceholder("Max Final Mark...");

        Filtering0SOFT.addComponent(labelSOFT);
        Filtering1SOFT.addComponents(courseCodeFilterSOFT,courseNameFilterSOFT,courseOutcomeFilterSOFT);
        Filtering2SOFT.addComponents(minSuppMarkFilterSOFT, new Label("-") ,maxSuppMarkFilterSOFT);
        Filtering3SOFT.addComponents(minFinalMarkFilterSOFT, dash ,maxFinalMarkFilterSOFT);
        Filtering4SOFT.addComponents( SoftFilter, RemoveSoft);
        filteringLayoutSOFT.addComponents(Filtering0SOFT,Filtering1SOFT, Filtering2SOFT, Filtering3SOFT, Filtering4SOFT);

        //addComponent(filteringLayoutSOFT);

        //mainSbutton.addClickListener(e -> {;});
        addComponent(filteringLayoutSOFT);






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

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();

        if (HardList.isEmpty()){
            return;
        }

        for(int i=0;i<HardList.size();i++){
            if(HardList.get(i) == 0){
               CGridLayoutList.get(i).setVisible(true);
            }
        }
        FilterList.clear();
    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {


        FilterListSOFT.clear();
        String value;
        if(courseOutcomeFilterSOFT.getValue() != null){
            value = courseOutcomeFilterSOFT.getValue();

        }
        else{
            value = "EMPTY";
        }
        System.out.println(value);

        FilterListSOFT.add(value);
        value = courseCodeFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterListSOFT.add(value);
        value = courseNameFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterListSOFT.add(value);
        value = minSuppMarkFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterListSOFT.add(value);
        value = maxSuppMarkFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterListSOFT.add(value);
        value = minFinalMarkFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);

        FilterListSOFT.add(value);
        value = maxFinalMarkFilterSOFT.getValue();
        if(value.equals("")){
            value = "EMPTY";
        }
        System.out.println(value);
        FilterListSOFT.add(value);

        FilterType = "SOFT";
        SoftList = new ArrayList<>();

        for(int i=0; i< CGridLayoutList.size();i++){
            SoftList.add(0);
            int currplace = i;

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {
                boolean yesorno = false;
                if (FilterListSOFT.get(0)!= "EMPTY") {

                    if (t.getCourseOutcome().contains(FilterListSOFT.get(0))) {
                        System.out.println("OUTcome filtering");
                        yesorno = true;
                    } else {
                        return null;
                    }
                }

                if (FilterListSOFT.get(1)!= "EMPTY") {

                    if (t.getCourseCode().contains(FilterListSOFT.get(1))) {
                        System.out.println("FILtering");
                        yesorno = true;
                    } else {
                        return null;
                    }
                }
                if (FilterListSOFT.get(2)!= "EMPTY") {

                    if (t.getCourseName().contains(FilterListSOFT.get(2))) {
                        System.out.println("name filtering");
                        yesorno = true;
                    } else {
                        return null;
                    }
                }
                if(!FilterListSOFT.get(5).equals("EMPTY") && !FilterListSOFT.get(6).equals("EMPTY")){
                    if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT.get(6))) {
                        System.out.println("mark filtering");
                        yesorno = true;
                    }
                    else{
                        return null;
                    }
                }
                if(!FilterListSOFT.get(5).equals("EMPTY") && FilterListSOFT.get(6).equals("EMPTY")){
                    if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT.get(5))) {
                        System.out.println("mark filtering");
                        yesorno = true;
                    }
                    else{
                        return null;
                    }
                }
                if(FilterListSOFT.get(5).equals("EMPTY") && !FilterListSOFT.get(6).equals("EMPTY")){
                    if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT.get(6))) {
                        System.out.println("mark filtering");
                        yesorno = true;
                    }
                    else{
                        return null;
                    }
                }
                if(!FilterListSOFT.get(3).equals("EMPTY") && !FilterListSOFT.get(4).equals("EMPTY")){
                    if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT.get(4))) {
                        System.out.println("supp filtering");
                        yesorno = true;
                    }else{
                        return null;
                    }
                }

                if(FilterListSOFT.get(3).equals("EMPTY") && !FilterListSOFT.get(4).equals("EMPTY")){
                    if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT.get(4))) {
                        System.out.println("supp filtering");
                        yesorno = true;
                    }else{
                        return null;
                    }
                }

                if(!FilterListSOFT.get(3).equals("EMPTY") && FilterListSOFT.get(4).equals("EMPTY")){
                    if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT.get(3))) {
                        System.out.println("supp filtering");
                        yesorno = true;
                    }else{
                        return null;
                    }
                }
                if(yesorno == false){
                    return null;
                }
                else {

                    SoftList.set(currplace, 1);
                    return "filters";


                }
            });


        }


        //updateItemsList();
        System.out.print(SoftList);
    }


    private void RemoveSoftFilter(Button.ClickEvent clickEvent) {
        FilterType = "NONE";

        courseCodeFilterSOFT.clear();
        courseNameFilterSOFT.clear();
        courseOutcomeFilterSOFT.clear();
        minSuppMarkFilterSOFT.clear();
        maxSuppMarkFilterSOFT.clear();
        minFinalMarkFilterSOFT.clear();
        maxFinalMarkFilterSOFT.clear();


        for(int i=0; i< CGridLayoutList.size();i++){
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> "default");
        }

    }


    private void HardFiltering(Button.ClickEvent clickEvent) {
        HardList = new ArrayList<>();
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
            //List<Boolean> boolpercourse = new ArrayList<>();
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
                        //Remember supp marks = -1 should not be considered
                        if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
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
                HardList.add(0);

            }
            else {
                HardList.add(1);
            }

        }

    }

}
