package com.example;


import com.sun.org.apache.xalan.internal.xsltc.dom.Filter;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/09/14.
 */
public class Dashboard extends VerticalLayout implements View {
    HorizontalLayout upperSection = new HorizontalLayout();
    HorizontalLayout innerUpperSection = new HorizontalLayout();
    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    VerticalLayout menuLayout = new VerticalLayout();
    HorizontalLayout menuTitle = new HorizontalLayout();
    VerticalLayout contentLayout = new VerticalLayout();
    VerticalLayout profileLayout = new VerticalLayout();


    Label lblHeader;
    Label lblMenu;
    Button btnLogout;
    Button btnProfile;

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



    private List<String> FilterListSOFT;


    private String FilterType;
    private List<students> allStudents;


    private HorizontalLayout Filtering1;
    private HorizontalLayout Filtering2;
    private HorizontalLayout Filtering3;
    private HorizontalLayout Filtering4;
    private HorizontalLayout Filtering5;




    private HorizontalLayout Filtering1SOFT;
    private HorizontalLayout Filtering2SOFT;
    private HorizontalLayout Filtering3SOFT;
    private HorizontalLayout Filtering4SOFT;



    private HorizontalLayout layoutMainButtons;
    private Label question;


    private CheckBox mainHbutton;
    private CheckBox mainSbutton;


    private List<Integer> HardList; //1 if on, 0 if off
    private List<Integer> SoftList; //0 if nothing, 1, 2 , 3





    Users user = new Users();

    public Dashboard(){
        //setSizeUndefined();
        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnProfile = new Button("My Profile");
        btnProfile.addStyleName("small");
        btnProfile.addStyleName("friendly");
        btnProfile.setSizeUndefined();
        btnProfile.setIcon(VaadinIcons.USER_CARD);



        btnLogout = new Button("Sign Out");
        btnLogout.addStyleName("small");
        btnLogout.addStyleName("friendly");
        btnLogout.setSizeUndefined();
        btnLogout.setIcon(VaadinIcons.SIGN_OUT);
        btnLogout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                user.logOut();
                Notification.show("Sign out Successful!").setDelayMsec(2000);

                getUI().getNavigator().navigateTo("login");
            }
        });

        lblMenu =  new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");

        innerUpperSection.addComponent(lblHeader);
        innerUpperSection.addComponent(btnProfile);
        innerUpperSection.addComponent(btnLogout);
        //innerUpperSection.setExpandRatio(filler,2f);
        innerUpperSection.setExpandRatio(lblHeader,1f);
        //innerUpperSection.setExpandRatio(btnLogout,1.5f);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(lblHeader,Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(btnProfile, Alignment.MIDDLE_CENTER);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.MIDDLE_LEFT);

        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, true));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        //upperSection.setHeight(4, UNITS_EM);

        addComponent(upperSection);
        contentLayout.setSizeUndefined();
        profileLayout.setSizeUndefined();
        //addWelcomeText();
        profileLayout.setWidth("100%");
        contentLayout.setWidth("100%");
        //contentLayout.setSizeFull();
        addComponent(profileLayout);
        addComponent(contentLayout);
        profileLayout.setVisible(false);









        /**menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(15);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection,1);
         **/
        //addComponent(new Label("WELCOME TO BOARD OF EXAMINATIONS SYSTEM DASHBOARD ! "));
    }

    /**public void setMenuTitle(){
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
    }

    public void addWelcomeText(){
        VerticalLayout data = new VerticalLayout();
        if( user.CurrentUser != null) {
            Label lblTitle = new Label("WELCOME " + user.CurrentUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD !!");
            lblTitle.addStyleName("h1");
            lblTitle.addStyleName("colored");

            lblHeader.setValue("" + user.CurrentUser.Name);

            data.addComponentsAndExpand(lblTitle);
        }
        //contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }**/
    public void addProfilePage(){
        btnProfile.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                profileLayout.setVisible(true);

            }
        });
        Label lblTitle;
        Label lblHeader;
        TextField tfID;
        TextField tfName;
        TextField tfEmail;
        TextArea taBio;
        PasswordField tfPassword;
        TextField tfDiscipline;
        Button btnConfirm;
        Button btnCancel;
        RadioButtonGroup opGender;


        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("My Profile");
        lblTitle.addStyleName("h1");


        FormLayout formLayout= new FormLayout();
        formLayout.setMargin(false);
        formLayout.addStyleName("light");


        lblHeader = new Label("");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("coloured");
        lblHeader.setCaption("Personal Information");
        lblHeader.setIcon(VaadinIcons.USER_CARD);
        formLayout.addComponent(lblHeader);

        tfID = new TextField("Username:");
        System.out.println(user.Discipline);
        //tfID.setValue(user.UserName);
        tfID.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfID);

        tfEmail = new TextField("Email Address:");
        //tfEmail.setValue(user.Email);
        tfEmail.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfEmail);

        tfName = new TextField("Full Name:");
        //tfName.setValue(user.Name);
        tfName.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfName);

        tfPassword = new PasswordField("Password:");
        //tfPassword.setValue(user.Password);
        tfPassword.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfPassword);

        tfDiscipline = new TextField("Discipline:");
        //tfDiscipline.setValue(user.Discipline);
        tfDiscipline.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfDiscipline);


        taBio = new TextArea("Bio:");
        //taBio.setValue(user.Bio);
        formLayout.addComponent(taBio);

        opGender = new RadioButtonGroup("Gender");
        opGender.setItems("Male", "Female", "Other");
        /**if(user.Gender == "Male"){
            opGender.setValue("Male");

        }
        if(user.Gender == "Female"){
            opGender.setValue("Female");

        }
        if(user.Gender == "Other"){
            opGender.setValue("Other");

        }
        **/
        opGender.setRequiredIndicatorVisible(true);
        opGender.addStyleName("horizontal");
        formLayout.addComponent(opGender);


        btnConfirm = new Button("Save Changes");
        btnCancel =  new Button("Cancel");
        btnConfirm.setIcon(VaadinIcons.USER_CHECK);
        btnConfirm.addStyleName("primary");
        btnCancel.addStyleName("danger");
        btnCancel.setIcon(VaadinIcons.CLOSE_CIRCLE);


        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        formLayout.addComponent(footer);
        footer.addComponent(btnConfirm);
        footer.addComponent(btnCancel);

        profileLayout.addComponents(lblTitle,lblHeader,formLayout,footer);



        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                String Bio = taBio.getValue();

                if(Bio.equals("")){

                    Bio = "-1";

                }

                //User = updateUser(tfID.getValue(), opGender.getValue().toString(), tfDiscipline.getValue(), tfPassword.getValue(), Bio, tfEmail.getValue(), tfName.getValue());
                boolean Auth = true;
                tfID.setValue("");
                tfDiscipline.setValue("");
                tfPassword.setValue("");
                tfEmail.setValue("");
                tfName.setValue("");
                taBio.setValue("");

                if (Auth){

                    Notification.show("You profile has been updated successfully!").setDelayMsec(2000);

                    profileLayout.setVisible(false);

                }
                else{

                    Notification.show("Username already in use, Please try again!",Notification.Type.ERROR_MESSAGE);


                }

            }
        });

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                profileLayout.setVisible(false);

            }
        });

    }

    public void addDataView(){

        Label lblTitle = new Label("WELCOME " + user.CurrentUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD");
            lblTitle.addStyleName("h1");
            lblTitle.addStyleName("colored");

            lblHeader.setValue("" + user.CurrentUser.Name);

            contentLayout.addComponent(lblTitle);



        layoutMainButtons = new HorizontalLayout();
        layoutMainButtons.setSizeFull();
        question = new Label("What would you like to do?");


        mainHbutton = new CheckBox("Filter");
        mainSbutton = new CheckBox("Highlight");

        layoutMainButtons.addComponents(question, mainHbutton, mainSbutton);


        contentLayout.addComponent(layoutMainButtons);



        VerticalLayout filteringLayout = new VerticalLayout();
        FilterType = "NONE";
        FilterList = new ArrayList<>();
        Filtering1 = new HorizontalLayout();
        Filtering2 = new HorizontalLayout();
        Filtering3 = new HorizontalLayout();
        Filtering4 = new HorizontalLayout();
        Filtering5 = new HorizontalLayout();
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
        CourseCodes.add("PAS");
        CourseCodes.add("FAL");
        //CourseCodes.add("PAS");
        //CourseCodes.add("FAIL");
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
        SoftFilter = new Button("Highlight");
        SoftFilter.setIcon(VaadinIcons.FLAG_O);
       RemoveSoft = new Button("Remove Highlight");
       RemoveSoft.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        SoftFilter.addClickListener(this::SoftFiltering);
        RemoveSoft.addClickListener(this::RemoveSoftFilter);
        HardFilter = new Button("Filter");
        HardFilter.setIcon(VaadinIcons.FILTER);
       RemoveHard = new Button("Remove Filter");
       RemoveHard.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        RemoveHard.addClickListener(this::RemoveHardFilter);
        HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");

        HardFilter.setStyleName("primary");
        SoftFilter.setStyleName("friendly");
      RemoveSoft.setStyleName("danger");
       RemoveHard.setStyleName("danger");

        //HardFilter.addClickListener(this::HardFiltering);
       // RemoveHard.addClickListener(this::RemoveHardFilter);
       // SoftFilter.addClickListener(this::SoftFiltering);
      //  RemoveSoft.addClickListener(this::RemoveSoftFilter);

        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, dash ,maxFinalMarkFilter);
        Filtering4.addComponents(HardFilter, RemoveHard);

        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        Filtering5.setSizeFull();

        contentLayout.addComponent(Filtering5);

        mainHbutton.addValueChangeListener(e -> {
            if(mainHbutton.getValue()) {
                mainSbutton.setEnabled(false);
                Filtering5.addComponent(filteringLayout);
            }

            else {mainSbutton.setEnabled(true);
            Filtering5.removeComponent(filteringLayout);}
        });

//highlighting ui
        VerticalLayout filteringLayoutSOFT = new VerticalLayout();
        // FilterType = "NONE";


        FilterListSOFT = new ArrayList<>();


        Filtering1SOFT = new HorizontalLayout();
        Filtering2SOFT = new HorizontalLayout();
        Filtering3SOFT = new HorizontalLayout();
        Filtering4SOFT = new HorizontalLayout();

        Filtering1SOFT.setCaption("Highlight Options");
        Filtering1SOFT.setSizeFull();
        //Filtering2.setSizeFull();
        //Filtering3.setSizeFull();
        Filtering4SOFT.setSizeFull();


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


        Filtering1SOFT.addComponents(courseCodeFilterSOFT,courseNameFilterSOFT,courseOutcomeFilterSOFT);
        Filtering2SOFT.addComponents(minSuppMarkFilterSOFT, new Label("-") ,maxSuppMarkFilterSOFT);
        Filtering3SOFT.addComponents(minFinalMarkFilterSOFT, dash ,maxFinalMarkFilterSOFT);
        Filtering4SOFT.addComponents(SoftFilter, RemoveSoft);
        filteringLayoutSOFT.addComponents(Filtering1SOFT, Filtering2SOFT, Filtering3SOFT, Filtering4SOFT);

        mainSbutton.addValueChangeListener(e -> {

            if(mainSbutton.getValue()) {
                mainHbutton.setEnabled(false);
                Filtering5.addComponent(filteringLayoutSOFT);
            }
            else {mainHbutton.setEnabled(true);
                Filtering5.removeComponent(filteringLayoutSOFT);}
        });




        HardList = new ArrayList<>();  // visible or not






        //DB CONNECTION TEST:
        MysqlCon conn = new MysqlCon();

        allStudents = conn.getStudentObjects();

        //System.out.println(allStudents.size());
        Label test = new Label("test");


        CGridLayoutList = new ArrayList<>();
        for(int i=0; i<allStudents.size();i++){
            HardList.add(1);
            CGridLayout studentGrid = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            CGridLayoutList.add(studentGrid);
            contentLayout.addComponent(studentGrid);

        }


    }


    private void RemoveHardFilter(Button.ClickEvent clickEvent) {
       //ProgressBar p = new ProgressBar();
      // Filtering4.addComponent(p);
      // p.setIndeterminate(true);
    // p.setVisible(true);

        FilterType = "NONE";

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();



        if (HardList.isEmpty()){
           // p.setVisible(false);
            return;
        }

        for(int i=0;i<HardList.size();i++){
            if(HardList.get(i) == 0){
                CGridLayoutList.get(i).setVisible(true);

            }
        }
       // p.setVisible(false);
        FilterList.clear();
        HardList.clear();


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
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {return null;});
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


                    return "filters";


                }
            });


        }


        //updateItemsList();
        System.out.print(SoftList);
    }





    /**public void addDashboardOption(String caption){
        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponentsAndExpand(button);

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                contentLayout.removeAllComponents();
                addWelcomeText();
            }
        });
    }


    public void addMenuOption(String caption, String componentName){
        Button butt = new Button(caption);
        butt.setWidth("100%");
        butt.setStyleName("borderless");
        menuLayout.addComponent(butt);
        butt.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                contentLayout.removeAllComponents();
                contentLayout.addComponent(getComponent(Integer.parseInt(componentName)));
            }
        });



    }
        **/

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();
        profileLayout.removeAllComponents();
        addDataView();
        addProfilePage();

    }

}
