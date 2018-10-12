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
    //private Button RemoveSoft;
    private Button HardFilter;
    // private Button RemoveHard;


    private List<String> FilterList;
    private List<String> FilterList2;
    private List<String> FilterList3;

    private List<String> CurrSOFT;
    private List<String> FilterListSOFT1;
    private List<String> FilterListSOFT2;
    private List<String> FilterListSOFT3;

    private String FilterType;
    private List<students> allStudents;


    private HorizontalLayout Filtering1;
    private HorizontalLayout Filtering2;
    private HorizontalLayout Filtering3;
    private HorizontalLayout Filtering4;
    private HorizontalLayout Filtering5;
    private VerticalLayout Filtering6;



    private HorizontalLayout Filtering1SOFT;
    private HorizontalLayout Filtering2SOFT;
    private HorizontalLayout Filtering3SOFT;
    private HorizontalLayout Filtering4SOFT;
    private VerticalLayout Filtering6SOFT;


    private HorizontalLayout layoutMainButtons;
    private Label question;


    private CheckBox mainHbutton;
    private CheckBox mainSbutton;


    private List<Integer> HardList; //1 if on, 0 if off
    private List<Integer> SoftList; //0 if nothing, 1, 2 , 3

    private List<Integer> buttonOnClickCountSOFT;

    private List<Integer> buttonOnClickCountHARD;

    private List<Integer> HardList1;
    private List<Integer> HardList2;
    private List<Integer> HardList3;

    private Label UserViewHard1;
    private Label UserViewHard2;
    private Label UserViewHard3;

    private Label UserViewSOFT1;
    private Label UserViewSOFT2;
    private Label UserViewSOFT3;

    private Button UserViewRemoveHard1;
    private Button UserViewRemoveHard2;
    private Button UserViewRemoveHard3;

    private Button UserViewRemoveSoft1;
    private Button UserViewRemoveSoft2;
    private Button UserViewRemoveSoft3;


    private HorizontalLayout HardDisplay1;
    private HorizontalLayout HardDisplay2;
    private HorizontalLayout HardDisplay3;

    private HorizontalLayout SoftDisplay1;
    private HorizontalLayout SoftDisplay2;
    private HorizontalLayout SoftDisplay3;

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

        Label lblTitle = new Label("WELCOME " + user.CurrentUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD !!");
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
        Filtering6 = new VerticalLayout();
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
        CourseCodes.add("FAL");
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
        SoftFilter = new Button("Highlight");
        SoftFilter.setIcon(VaadinIcons.FLAG_O);
        // RemoveSoft = new Button("Remove Soft Filter");
        // RemoveSoft.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        //SoftFilter.addClickListener(this::SoftFiltering);
        //RemoveSoft.addClickListener(this::RemoveSoftFilter);
        HardFilter = new Button("Hard Filter");
        HardFilter.setIcon(VaadinIcons.FILTER);
        // RemoveHard = new Button("Remove Hard Filter");
        // RemoveHard.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        //RemoveHard.addClickListener(this::RemoveHardFilter);
        //HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");

        HardFilter.setStyleName("primary");
        SoftFilter.setStyleName("friendly");
        //  RemoveSoft.setStyleName("danger");
        // RemoveHard.setStyleName("danger");

        HardFilter.addClickListener(this::HardFiltering);
        // RemoveHard.addClickListener(this::RemoveHardFilter);
        SoftFilter.addClickListener(this::SoftFiltering);
        //  RemoveSoft.addClickListener(this::RemoveSoftFilter);

        Filtering1.addComponents(courseCodeFilter,courseNameFilter,courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-") ,maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, new Label("-") ,maxFinalMarkFilter);
        Filtering4.addComponents(HardFilter); //removehard

        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4,Filtering6);
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

        CurrSOFT = new ArrayList<>();
        FilterListSOFT1 = new ArrayList<>();
        FilterListSOFT2 = new ArrayList<>();
        FilterListSOFT3 = new ArrayList<>();

        Filtering1SOFT = new HorizontalLayout();
        Filtering2SOFT = new HorizontalLayout();
        Filtering3SOFT = new HorizontalLayout();
        Filtering4SOFT = new HorizontalLayout();
        Filtering6SOFT = new VerticalLayout();
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
        Filtering4SOFT.addComponents(SoftFilter);
        filteringLayoutSOFT.addComponents(Filtering1SOFT, Filtering2SOFT, Filtering3SOFT, Filtering4SOFT, Filtering6SOFT);

        mainSbutton.addValueChangeListener(e -> {

            if(mainSbutton.getValue()) {
                mainHbutton.setEnabled(false);
                Filtering5.addComponent(filteringLayoutSOFT);
            }
            else {mainHbutton.setEnabled(true);
                Filtering5.removeComponent(filteringLayoutSOFT);}
        });


        buttonOnClickCountSOFT = new ArrayList<>();
        buttonOnClickCountSOFT.add(0);
        buttonOnClickCountSOFT.add(0);
        buttonOnClickCountSOFT.add(0);

        buttonOnClickCountHARD = new ArrayList<>();
        buttonOnClickCountHARD.add(0);
        buttonOnClickCountHARD.add(0);
        buttonOnClickCountHARD.add(0);

        HardList = new ArrayList<>();  // visible or not
        HardList1 = new ArrayList<>(); //first filter
        HardList2 = new ArrayList<>();
        HardList3 = new ArrayList<>();

        HardDisplay1 = new HorizontalLayout();
        HardDisplay1.setSizeFull();
        HardDisplay2 = new HorizontalLayout();
        HardDisplay2.setSizeFull();
        HardDisplay3 = new HorizontalLayout();
        HardDisplay3.setSizeFull();

        SoftDisplay1 = new HorizontalLayout();
        SoftDisplay1.setSizeFull();
        SoftDisplay2 = new HorizontalLayout();
        SoftDisplay2.setSizeFull();
        SoftDisplay3 = new HorizontalLayout();
        SoftDisplay3.setSizeFull();

        UserViewHard1 = new Label();
        UserViewHard2 = new Label();
        UserViewHard3 = new Label();

        UserViewSOFT1 = new Label();
        UserViewSOFT2 = new Label();
        UserViewSOFT3 = new Label();

        UserViewRemoveHard1 = new Button("Remove");
        UserViewRemoveHard1.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveHard1.addStyleName("danger");
        UserViewRemoveHard1.addClickListener(this::RemoveHardFilter1);

        UserViewRemoveHard2 = new Button("Remove");
        UserViewRemoveHard2.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveHard2.addStyleName("danger");
        UserViewRemoveHard2.addClickListener(this::RemoveHardFilter2);

        UserViewRemoveHard3 = new Button("Remove");
        UserViewRemoveHard3.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveHard3.addStyleName("danger");
        UserViewRemoveHard3.addClickListener(this::RemoveHardFilter3);

        //soft

        UserViewRemoveSoft1 = new Button("Remove");
        UserViewRemoveSoft1.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveSoft1.addStyleName("danger");
        UserViewRemoveSoft1.addClickListener(this::RemoveSoftFilter1);

        UserViewRemoveSoft2 = new Button("Remove");
        UserViewRemoveSoft2.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveSoft2.addStyleName("danger");
        UserViewRemoveSoft2.addClickListener(this::RemoveSoftFilter2);

        UserViewRemoveSoft3 = new Button("Remove");
        UserViewRemoveSoft3.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        UserViewRemoveSoft3.addStyleName("danger");
        UserViewRemoveSoft3.addClickListener(this::RemoveSoftFilter3);


        HardDisplay1.addComponents(UserViewHard1,UserViewRemoveHard1);
        HardDisplay1.setComponentAlignment(UserViewRemoveHard1, Alignment.MIDDLE_RIGHT);
        HardDisplay2.addComponents(UserViewHard2,UserViewRemoveHard2);
        HardDisplay2.setComponentAlignment(UserViewRemoveHard2, Alignment.MIDDLE_RIGHT);
        HardDisplay3.addComponents(UserViewHard3,UserViewRemoveHard3);
        HardDisplay3.setComponentAlignment(UserViewRemoveHard3, Alignment.MIDDLE_RIGHT);

        SoftDisplay1.addComponents(UserViewSOFT1,UserViewRemoveSoft1);
        SoftDisplay1.setComponentAlignment(UserViewRemoveSoft1, Alignment.MIDDLE_RIGHT);
        SoftDisplay2.addComponents(UserViewSOFT2,UserViewRemoveSoft2);
        SoftDisplay2.setComponentAlignment(UserViewRemoveSoft2, Alignment.MIDDLE_RIGHT);
        SoftDisplay3.addComponents(UserViewSOFT3,UserViewRemoveSoft3);
        SoftDisplay3.setComponentAlignment(UserViewRemoveSoft3, Alignment.MIDDLE_RIGHT);




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


    private void RemoveHardFilter() {
        //ProgressBar p = new ProgressBar();
        // Filtering4.addComponent(p);
        // p.setIndeterminate(true);
        //  p.setVisible(true);

        FilterType = "NONE";

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();



        if (HardList.isEmpty()){
            //p.setVisible(false);
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
        HardList1.clear();
        HardList2.clear();
        HardList3.clear();

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

        Integer tobefiltered  = -1;

        for (int j = 0; j < 3; j++){
            if(buttonOnClickCountHARD.get(j) == 0){
                buttonOnClickCountHARD.set(j, 1);
                tobefiltered = j;
                break;
            }
        }

        FilterList.clear();
        String value;
        List<String> DisplayFields = new ArrayList<>();
        DisplayFields.add("Course Outcome: ");
        DisplayFields.add("Course Code: ");
        DisplayFields.add("Course Title: ");
        DisplayFields.add("Min Supp Mark: ");
        DisplayFields.add("Max Supp Mark: ");
        DisplayFields.add("Min Final Mark: ");
        DisplayFields.add("Max Final Mark: ");


        if (courseOutcomeFilter.getValue() != null) {
            value = courseOutcomeFilter.getValue();

        } else {
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseCodeFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = courseNameFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minSuppMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value);
        value = maxFinalMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);


        FilterType = "HARD";

        if (tobefiltered == 0) {

            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList.get(k).equals("EMPTY")){
                    Display1 = Display1  + DisplayFields.get(k) + FilterList.get(k)+ "*****";
                }

            }

            UserViewHard1.setValue(Display1);
            Filtering6.addComponent(HardDisplay1);


            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList1.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();
                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < allStudents.get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
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
                    while (Answer == true && k < FilterList.size() - 4) {
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).contains(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }
                        k = k + 1;
                    }
                    if (Answer == true) {
                        studentAns = true;
                    }

                    j = j + 1;
                }
                if (studentAns == false) {

                    Boolean currstatus = false;

                    HardList1.set(i,0);

                    if (!HardList2.isEmpty()){
                        if (HardList2.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (!HardList3.isEmpty()){
                        if (HardList3.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (currstatus){
                        HardList.set(i,1);
                    }
                    else{
                        HardList.set(i,0);
                    }

                    CGridLayoutList.get(i).setVisible(currstatus);
                }


                else {

                    HardList.set(i,1);
                    HardList1.set(i,1);
                    CGridLayoutList.get(i).setVisible(true);

                }

            }
        }




        if (tobefiltered == 1 ){


            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList.get(k).equals("EMPTY")){
                    Display1 = Display1 + DisplayFields.get(k) + FilterList.get(k) + "*****";
                }

            }

            UserViewHard2.setValue(Display1);
            Filtering6.addComponent(HardDisplay2);


            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList2.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();

                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < allStudents.get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
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
                    while (Answer == true && k < FilterList.size() - 4) {
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).contains(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }
                        k = k + 1;
                    }
                    if (Answer == true) {
                        studentAns = true;
                    }

                    j = j + 1;
                }
                if (studentAns == false) {

                    Boolean currstatus = false;


                    HardList2.set(i,0); //take out

                    if (!HardList1.isEmpty()){
                        if (HardList1.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (!HardList3.isEmpty()){
                        if (HardList3.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (currstatus){
                        HardList.set(i,1);
                    }
                    else{
                        HardList.set(i,0);
                    }

                    CGridLayoutList.get(i).setVisible(currstatus);


                }
                else {

                    HardList.set(i,1);
                    HardList2.set(i,1);
                    CGridLayoutList.get(i).setVisible(true);
                }

            }
        }

        if (tobefiltered == 2){

            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList.get(k).equals("EMPTY")){
                    Display1 = Display1 + DisplayFields.get(k) + FilterList.get(k) + "*****";
                }

            }

            UserViewHard3.setValue(Display1);
            Filtering6.addComponent(HardDisplay3);

            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList3.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();

                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < allStudents.get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
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
                    while (Answer == true && k < FilterList.size() - 4) {
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).contains(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }
                        k = k + 1;
                    }
                    if (Answer == true) {
                        studentAns = true;
                    }

                    j = j + 1;
                }
                if (studentAns == false) {
                    Boolean currstatus = false;

                    HardList3.set(i,0); //take out

                    if (!HardList1.isEmpty()){
                        if (HardList1.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (!HardList2.isEmpty()){
                        if (HardList2.get(i) == 1){
                            currstatus = true;
                        }
                    }

                    if (currstatus){
                        HardList.set(i,1);
                    }
                    else{
                        HardList.set(i,0);
                    }

                    CGridLayoutList.get(i).setVisible(currstatus);

                } else {

                    HardList.set(i,1);
                    HardList3.set(i,1);
                    CGridLayoutList.get(i).setVisible(true);


                }


            }
        }

        //p.setVisible(false);
        System.out.println("hl: " + HardList);
        System.out.println("hlist1: " + HardList1);
        System.out.println("hlist2" + HardList2);
        System.out.println("hl3: " + HardList3);

    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {


        boolean ensurefieldsnotblank = false;


        Integer tobefiltered  = -1;

        List<String> DisplayFields = new ArrayList<>();
        DisplayFields.add("Course Outcome: ");
        DisplayFields.add("Course Code: ");
        DisplayFields.add("Course Title: ");
        DisplayFields.add("Min Supp Mark: ");
        DisplayFields.add("Max Supp Mark: ");
        DisplayFields.add("Min Final Mark: ");
        DisplayFields.add("Max Final Mark: ");


        CurrSOFT.clear();

        String value;
        if (courseOutcomeFilterSOFT.getValue() != null) {
            value = courseOutcomeFilterSOFT.getValue();
            ensurefieldsnotblank = true;

        } else {
            value = "EMPTY";
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = courseCodeFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = courseNameFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);
        value = minSuppMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);
        value = maxSuppMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);
        value = minFinalMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = maxFinalMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);

        FilterType = "SOFT";


        if (!ensurefieldsnotblank){
            Notification.show("All Fields Can Not Be Blank!").setDelayMsec(1000);
            return;
        }


        for (int j = 0; j < 3; j++){
            if(buttonOnClickCountSOFT.get(j) == 0){
                buttonOnClickCountSOFT.set(j, 1);
                tobefiltered = j;
                break;
            }
        }

        String Display = "";

        for (int k = 0; k < DisplayFields.size();k++){
            if (!CurrSOFT.get(k).equals("EMPTY")){
                Display = Display + DisplayFields.get(k) + CurrSOFT.get(k) + "*****";
            }

        }



        if (tobefiltered == 0){

            FilterListSOFT1.add(CurrSOFT.get(0));
            FilterListSOFT1.add(CurrSOFT.get(1));
            FilterListSOFT1.add(CurrSOFT.get(2));
            FilterListSOFT1.add(CurrSOFT.get(3));
            FilterListSOFT1.add(CurrSOFT.get(4));
            FilterListSOFT1.add(CurrSOFT.get(5));
            FilterListSOFT1.add(CurrSOFT.get(6));


            UserViewSOFT1.setValue(Display);
            Filtering6SOFT.addComponent(SoftDisplay1);

        }

        if(tobefiltered == 1){

            FilterListSOFT2.add(CurrSOFT.get(0));
            FilterListSOFT2.add(CurrSOFT.get(1));
            FilterListSOFT2.add(CurrSOFT.get(2));
            FilterListSOFT2.add(CurrSOFT.get(3));
            FilterListSOFT2.add(CurrSOFT.get(4));
            FilterListSOFT2.add(CurrSOFT.get(5));
            FilterListSOFT2.add(CurrSOFT.get(6));

            UserViewSOFT2.setValue(Display);
            Filtering6SOFT.addComponent(SoftDisplay2);

        }

        if (tobefiltered == 2){

            FilterListSOFT3.add(CurrSOFT.get(0));
            FilterListSOFT3.add(CurrSOFT.get(1));
            FilterListSOFT3.add(CurrSOFT.get(2));
            FilterListSOFT3.add(CurrSOFT.get(3));
            FilterListSOFT3.add(CurrSOFT.get(4));
            FilterListSOFT3.add(CurrSOFT.get(5));
            FilterListSOFT3.add(CurrSOFT.get(6));

            UserViewSOFT3.setValue(Display);
            Filtering6SOFT.addComponent(SoftDisplay3);
        }

        if (tobefiltered == -1){
            return;
        }


        for (int i = 0; i < CGridLayoutList.size(); i++) {

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (FilterListSOFT1.get(0) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(1) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(2) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT1.get(2))) {
                            System.out.println("name filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour ==true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour ==true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(3).equals("EMPTY") && FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (firstcolour) {
                        rowcolour = "filters";
                    }

                }




                if (!FilterListSOFT2.isEmpty()) {


                    if (FilterListSOFT2.get(0) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(1) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(2) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT2.get(2))) {
                            System.out.println("name filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(3).equals("EMPTY") && FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (secondcolour) {
                        System.out.print(secondcolour);
                        rowcolour = "bluefilters";
                    }

                }


                if (!FilterListSOFT3.isEmpty()) {


                    if (FilterListSOFT3.get(0) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(1) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(2) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT3.get(2))) {
                            System.out.println("name filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true ) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(3).equals("EMPTY") && FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (thirdcolour) {
                        System.out.print(thirdcolour);
                        rowcolour = "greenfilters";
                    }

                }

                if (rowcolour != "") {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });


        }

        //updateItemsList();
        //System.out.print(SoftList);


    }


    private void RemoveHardFilter1(Button.ClickEvent clickEvent) {
        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);

        FilterType = "NONE";

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();

        buttonOnClickCountHARD.set(0,0);

        if (HardList1.isEmpty()){
            return;
        }


        if (HardList2.isEmpty() && HardList3.isEmpty()) {

            for (int i = 0; i < HardList1.size(); i++) {
                if (HardList1.get(i) == 0) {
                    CGridLayoutList.get(i).setVisible(true);
                    HardList.set(i,1);

                }

            }

        }


        else {


            for (int i = 0; i < HardList1.size(); i++) {
                if (HardList1.get(i) == 1) {

                    Boolean currvisible = false; //we dont wanna see it

                    if (!HardList2.isEmpty()) {
                        if (HardList2.get(i) == 1) {
                            currvisible = true;

                        }
                    }

                    if (!HardList3.isEmpty()) {
                        if (HardList3.get(i) == 1) {
                            currvisible = true;
                        }
                    }        //if the other lists have it we must keep it

                    CGridLayoutList.get(i).setVisible(currvisible);

                    if (currvisible == false) {
                        HardList.set(i, 0);
                    }

                }

            }

        }

        HardList1.clear();
        FilterList.clear();
        Filtering6.removeComponent(HardDisplay1);


        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);
    }

    private void RemoveHardFilter2(Button.ClickEvent clickEvent) {

        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);

        FilterType = "NONE";

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();

        buttonOnClickCountHARD.set(1,0);


        if (HardList2.isEmpty()){
            return;
        }


        if (HardList1.isEmpty() && HardList3.isEmpty()) {
            for (int i = 0; i < HardList2.size(); i++) {

                if (HardList2.get(i) == 0) {
                    CGridLayoutList.get(i).setVisible(true);
                    HardList.set(i,1);
                }

            }
        }



        else {

            for (int i = 0; i < HardList2.size(); i++) {

                if (HardList2.get(i) == 1) {
                    Boolean currvisible = false; //we dont wanna see it

                    if (!HardList1.isEmpty()) {
                        if (HardList1.get(i) == 1) {
                            currvisible = true;
                        }
                    }

                    if (!HardList3.isEmpty()) {
                        if (HardList3.get(i) == 1) {
                            currvisible = true;
                        }
                    }        //if the other lists have it we must keep it


                    CGridLayoutList.get(i).setVisible(currvisible);

                    if (currvisible == false) {
                        HardList.set(i, 0);
                    }

                }

            }
        }

        HardList2.clear();
        FilterList.clear();
        Filtering6.removeComponent(HardDisplay2);


        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);

    }

    private void RemoveHardFilter3(Button.ClickEvent clickEvent) {


        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);
        FilterType = "NONE";

        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();

        buttonOnClickCountHARD.set(2,0);


        if (HardList3.isEmpty()){
            return;
        }



        if (HardList1.isEmpty() && HardList2.isEmpty()) {
            for (int i = 0; i < HardList3.size(); i++) {
                if (HardList3.get(i) == 0) {
                    CGridLayoutList.get(i).setVisible(true);
                    HardList.set(i,1);
                }

            }
        }


        else {

            for (int i = 0; i < HardList3.size(); i++) {


                if (HardList3.get(i) == 1) {

                    Boolean currvisible = false; //we dont wanna see it

                    if (!HardList1.isEmpty()) {
                        if (HardList1.get(i) == 1) {
                            currvisible = true;
                        }
                    }

                    if (!HardList2.isEmpty()) {
                        if (HardList2.get(i) == 1) {
                            currvisible = true;
                        }
                    }        //if the other lists have it we must keep it


                    CGridLayoutList.get(i).setVisible(currvisible);

                    if (currvisible == false) {
                        HardList.set(i, 0);
                    }

                }

            }
        }

        HardList3.clear();
        FilterList.clear();
        Filtering6.removeComponent(HardDisplay3);

        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);
    }


    private void RemoveSoftFilter1(Button.ClickEvent clickEvent) {

        FilterType = "NONE";

        courseCodeFilterSOFT.clear();
        courseNameFilterSOFT.clear();
        courseOutcomeFilterSOFT.clear();
        minSuppMarkFilterSOFT.clear();
        maxSuppMarkFilterSOFT.clear();
        minFinalMarkFilterSOFT.clear();
        maxFinalMarkFilterSOFT.clear();

        FilterListSOFT1.clear();
        buttonOnClickCountSOFT.set(0,0);

        for(int i=0; i< CGridLayoutList.size();i++){

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {
                String rowcolour = "";

                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT2.isEmpty()) {

                    if (FilterListSOFT2.get(0) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(1) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(2) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT2.get(2))) {
                            System.out.println("name filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(3).equals("EMPTY") && FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (secondcolour) {
                        System.out.print(secondcolour);
                        rowcolour = "bluefilters";
                    }

                }


                if (!FilterListSOFT3.isEmpty()) {


                    if (FilterListSOFT3.get(0) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(1) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(2) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT3.get(2))) {
                            System.out.println("name filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true ) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(3).equals("EMPTY") && FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (thirdcolour) {
                        System.out.print(thirdcolour);
                        rowcolour = "greenfilters";
                    }

                }

                if (rowcolour != "") {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });
        }

        Filtering6SOFT.removeComponent(SoftDisplay1);



    }

    private void RemoveSoftFilter2(Button.ClickEvent clickEvent) {
        FilterType = "NONE";

        courseCodeFilterSOFT.clear();
        courseNameFilterSOFT.clear();
        courseOutcomeFilterSOFT.clear();
        minSuppMarkFilterSOFT.clear();
        maxSuppMarkFilterSOFT.clear();
        minFinalMarkFilterSOFT.clear();
        maxFinalMarkFilterSOFT.clear();


        FilterListSOFT2.clear();
        buttonOnClickCountSOFT.set(1,0);

        for(int i=0; i< CGridLayoutList.size();i++){
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (FilterListSOFT1.get(0) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(1) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(2) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT1.get(2))) {
                            System.out.println("name filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour ==true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour ==true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(3).equals("EMPTY") && FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (firstcolour) {
                        rowcolour = "filters";
                    }

                }


                if (!FilterListSOFT3.isEmpty()) {


                    if (FilterListSOFT3.get(0) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(1) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(2) != "EMPTY" && thirdcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT3.get(2))) {
                            System.out.println("name filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(5).equals("EMPTY") && FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true ) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT3.get(5))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (FilterListSOFT3.get(5).equals("EMPTY") && !FilterListSOFT3.get(6).equals("EMPTY") && thirdcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT3.get(6))) {
                            System.out.println("mark filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (FilterListSOFT3.get(3).equals("EMPTY") && !FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT3.get(4))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(3).equals("EMPTY") && FilterListSOFT3.get(4).equals("EMPTY") && thirdcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT3.get(3))) {
                            System.out.println("supp filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (thirdcolour) {
                        System.out.print(thirdcolour);
                        rowcolour = "greenfilters";
                    }

                }

                if (rowcolour != "") {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });

        }

        Filtering6SOFT.removeComponent(SoftDisplay2);


    }

    private void RemoveSoftFilter3(Button.ClickEvent clickEvent) {
        FilterType = "NONE";

        courseCodeFilterSOFT.clear();
        courseNameFilterSOFT.clear();
        courseOutcomeFilterSOFT.clear();
        minSuppMarkFilterSOFT.clear();
        maxSuppMarkFilterSOFT.clear();
        minFinalMarkFilterSOFT.clear();
        maxFinalMarkFilterSOFT.clear();

        FilterListSOFT3.clear();
        buttonOnClickCountSOFT.set(2,0);

        for(int i=0; i< CGridLayoutList.size();i++){
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (FilterListSOFT1.get(0) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(1) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(2) != "EMPTY" && firstcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT1.get(2))) {
                            System.out.println("name filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour ==true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(5).equals("EMPTY") && FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT1.get(5))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (FilterListSOFT1.get(5).equals("EMPTY") && !FilterListSOFT1.get(6).equals("EMPTY") && firstcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT1.get(6))) {
                            System.out.println("mark filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (FilterListSOFT1.get(3).equals("EMPTY") && !FilterListSOFT1.get(4).equals("EMPTY") && firstcolour ==true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT1.get(4))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(3).equals("EMPTY") && FilterListSOFT1.get(4).equals("EMPTY") && firstcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT1.get(3))) {
                            System.out.println("supp filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (firstcolour) {
                        rowcolour = "filters";
                    }

                }




                if (!FilterListSOFT2.isEmpty()) {


                    if (FilterListSOFT2.get(0) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseOutcome().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(1) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseCode().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(2) != "EMPTY" && secondcolour == true) {

                        if (t.getCourseName().contains(FilterListSOFT2.get(2))) {
                            System.out.println("name filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5)) && t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(5).equals("EMPTY") && FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() >= Double.parseDouble(FilterListSOFT2.get(5))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (FilterListSOFT2.get(5).equals("EMPTY") && !FilterListSOFT2.get(6).equals("EMPTY") && secondcolour == true) {
                        if (t.getFinalMark() <= Double.parseDouble(FilterListSOFT2.get(6))) {
                            System.out.println("mark filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3)) && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (FilterListSOFT2.get(3).equals("EMPTY") && !FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= 0 && t.getSuppMark() <= Double.parseDouble(FilterListSOFT2.get(4))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(3).equals("EMPTY") && FilterListSOFT2.get(4).equals("EMPTY") && secondcolour == true) {
                        if (t.getSuppMark() >= Double.parseDouble(FilterListSOFT2.get(3))) {
                            System.out.println("supp filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (secondcolour) {
                        System.out.print(secondcolour);
                        rowcolour = "bluefilters";
                    }

                }


                if (rowcolour != "") {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });

        }

        Filtering6SOFT.removeComponent(SoftDisplay3);


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
