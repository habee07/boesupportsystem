package com.example;


import com.vaadin.annotations.Push;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/09/14.
 */
@Push
public class Dashboard extends VerticalLayout implements View, Broadcaster.BroadcastListener {

    //VerticalLayout messages = new VerticalLayout();
    HorizontalLayout upperSection = new HorizontalLayout();
    HorizontalLayout innerUpperSection = new HorizontalLayout();
    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    VerticalLayout menuLayout = new VerticalLayout();
    HorizontalLayout menuTitle = new HorizontalLayout();
    VerticalLayout contentLayout1 = new VerticalLayout();
    VerticalLayout profileLayout = new VerticalLayout();

    VerticalLayout absLayout = new VerticalLayout();


    HorizontalLayout liveSessionInfo = new HorizontalLayout();
    Button nextStudent;
    Button prevStudent;
    Label sessionInfo;
    Label studentInfo = new Label();
    Button update;
    //Button joinSession;
    Button startSession;
    String currStudent = "-1";
    int currIndexstudent = -1;
    int currLivePage = -1;
    Boolean liveOrNot = false;
    //List<String> joinedUsers = new ArrayList<>();
    Boolean joinOrNot = false;


    Label lblHeader;
    Label lblMenu;
    Button btnLogout;
    Button btnProfile;
    Button btnUpload;

    //Pages
    int numberOfPages;
    HorizontalLayout pageButtons;
    Button nextPage;
    Button prevPage;
    Button goToPage;

    int currPage;
    ComboBox<String> pageNumbers;
    List<List<students>> ListofStudentLists;

    List<CGridLayout> CGridLayoutList;
    List<List<CGridLayout>> listOfLayoutList;

    List<CGridLayout> CGridLayoutList1;
    List<CGridLayout> CGridLayoutList2;
    List<CGridLayout> CGridLayoutList3;
    List<CGridLayout> CGridLayoutList4;
    List<CGridLayout> CGridLayoutList5;

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
    private List<String> FilterList1;
    private List<String> FilterList2;
    private List<String> FilterList3;

    private List<String> CurrSOFT;
    private List<String> FilterListSOFT1;
    private List<String> FilterListSOFT2;
    private List<String> FilterListSOFT3;

    private String FilterType;
    private List<students> allStudents;

    private VerticalLayout AllFilterStuff;
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
    private CheckBox filterBadData;


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

    private List<CGridLayout> tempLayoutlist;

    Users user = new Users();

    public Dashboard(){
        //setSizeUndefined();

        //absLayout.setSizeFull();

        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnProfile = new Button("My Profile");
        btnProfile.addStyleName("small");
        btnProfile.addStyleName("friendly");
        btnProfile.setSizeUndefined();
        btnProfile.setIcon(VaadinIcons.USER_CARD);



        btnUpload = new Button("Upload Files");
        btnUpload.addStyleName("small");
        btnUpload.addStyleName("friendly");
        btnUpload.setIcon(VaadinIcons.ARROW_CIRCLE_UP);
        btnUpload.setSizeUndefined();
        btnUpload.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                getUI().getNavigator().navigateTo("uploadpage/"+user.currUser.UserName);
            }
        });




        innerUpperSection.addComponent(btnUpload);
        innerUpperSection.setComponentAlignment(btnUpload, Alignment.MIDDLE_RIGHT);

        btnLogout = new Button("Sign Out");
        btnLogout.addStyleName("small");
        btnLogout.addStyleName("friendly");
        btnLogout.setSizeUndefined();
        btnLogout.setIcon(VaadinIcons.SIGN_OUT);
        btnLogout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                System.out.println(user.currUser.Role);
                if(user.currUser.Role.equals("special user")){
                    if(liveOrNot == true){
                        liveOrNot = false;

                        Broadcaster.broadcast("offsession");

                    }
                }

                unregister();
                user.logOut();
                Notification.show("Sign out Successful!").setDelayMsec(2000);
                /**
                else{
                    if(joinedUsers.contains(user.CurrensatUser.Name)){
                        joinedUsers.remove(user.CurrsaentUser.Name);
                        joinOrNot = false;
                    }
                }
                 **/
                getUI().getNavigator().navigateTo("login");
            }
        });

        lblMenu =  new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");

        innerUpperSection.addComponent(lblHeader);

        innerUpperSection.addComponent(btnProfile);
        innerUpperSection.addComponent(btnLogout);
        innerUpperSection.addComponent(btnUpload);
        innerUpperSection.setComponentAlignment(btnUpload, Alignment.MIDDLE_LEFT);



        //innerUpperSection.setExpandRatio(filler,2f);
        innerUpperSection.setExpandRatio(lblHeader,1f);
        //innerUpperSection.setExpandRatio(btnLogout,1.5f);
        innerUpperSection.setSpacing(true);
        //innerUpperSection.setComponentAlignment(lblHeader,Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(btnProfile, Alignment.MIDDLE_CENTER);
        btnUpload.setVisible(false);


        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, true));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        //upperSection.setHeight(4, UNITS_EM);

        //absLayout.addComponent(upperSection);
        contentLayout1.setSizeFull();

        profileLayout.setSizeUndefined();
        //addWelcomeText();
        profileLayout.setWidth("100%");
        contentLayout1.setWidth("100%");

        //contentLayout.setSizeFull();
        //absLayout.addComponent( upperSection , profileLayout);

        pageButtons = new HorizontalLayout();
        //pageButtons.setSizeFull();


        AllFilterStuff = new VerticalLayout();
        absLayout.setSizeFull();
        absLayout.addComponents(upperSection , profileLayout, pageButtons, liveSessionInfo, AllFilterStuff);

        /** semi working split panel


        VerticalSplitPanel sample = new VerticalSplitPanel();
        sample.setSizeFull();
        sample.setSplitPosition(500, Unit.PIXELS);
        sample.setFirstComponent(absLayout);
        sample.setSecondComponent(contentLayout1);
        //addComponent(sample);
         **/
        //absLayout.addComponent(AllFilterStuff);
        //absLayout.addComponent(messages);

        addComponent(absLayout);
        addComponent(contentLayout1);



        /**
        Panel menuPanel = new Panel();
        menuPanel.setContent(absLayout);
        menuPanel.setStyleName(ValoTheme.PANEL_WELL);
        menuPanel.setSizeFull();
        setExpandRatio(menuPanel, 1);
        addComponent(menuPanel);
        **/



       // addComponent(AllFilterStuff);


        profileLayout.setVisible(false);


        Broadcaster.register(this);

        final TextField input = new TextField();
        Button send = new Button("Send");
        //messages.addComponent(input);
        //messages.addComponent(send);
        send.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Broadcast the message
                Broadcaster.broadcast(input.getValue());

                input.setValue("");
            }
        });


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

        liveSessionInfo.setSizeFull();
        nextStudent = new Button("Next Student");
        prevStudent = new Button("Previous Student");
        sessionInfo = new Label("No Current Live Session");
        update = new Button("Sync with Session");
        startSession = new Button("Start Live Session");


        liveSessionInfo.addComponents(sessionInfo, studentInfo, update, startSession, nextStudent, prevStudent);

        nextStudent.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(liveOrNot == true){
                    if( currIndexstudent < ListofStudentLists.get(currPage - 1).size()-1 ){
                        currStudent = ListofStudentLists.get(currPage - 1).get(currIndexstudent +1).getStudentNumber();
                        currIndexstudent = currIndexstudent +1;
                        studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                        Broadcaster.broadcast("!cs "+ currStudent + " !cp "+ currLivePage + " !ci " + currIndexstudent);
                        scrollToResult(CGridLayoutList.get(currIndexstudent));
                    }
                }
            }
        });

        prevStudent.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(liveOrNot == true){
                    if(currIndexstudent > 0 ){
                        currStudent = ListofStudentLists.get(currPage - 1).get(currIndexstudent-1).getStudentNumber();
                        currIndexstudent = currIndexstudent -1;
                        studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                        Broadcaster.broadcast("!cs "+ currStudent + " !cp "+ currLivePage + " !ci " + currIndexstudent);
                        scrollToResult(CGridLayoutList.get(currIndexstudent));
                    }
                }
            }
        });

        update.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(liveOrNot == true){
                    if(currPage == currLivePage ){
                        scrollToResult(CGridLayoutList.get(currIndexstudent));
                    }
                    else{
                        Notification.show("Go to Page "+ currLivePage).setDelayMsec(2000);
                    }
                }
            }
        });


        startSession.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (liveOrNot == false) {
                    liveOrNot = true;
                    joinOrNot = true;
                    Broadcaster.broadcast("livesession");
                    //joinedUsers.add(user.CurrentasUser.Name);
                    currLivePage = currPage;
                    currStudent = ListofStudentLists.get(currPage - 1).get(0).getStudentNumber();
                    currIndexstudent = 0;
                    studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                    Broadcaster.broadcast("!cs "+ currStudent + " !cp "+ currLivePage + " !ci " + currIndexstudent);

                    //addLiveUserList();
                }
                else{
                    Notification.show("A Session is Already Live!").setDelayMsec(2000);

                }
            }
        });

    }


    public void scrollToResult(VerticalLayout layout) {
        UI.getCurrent().scrollIntoView(layout);
    }

    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }

    public void unregister(){
        Broadcaster.unregister(this);
    }
    @Override
    public void receiveBroadcast(final String message) {
        // Must lock the session to execute logic safely
        getUI().access(new Runnable() {
            @Override
            public void run() {
                // Show it somehow

                if(message.contains("!")){
                    System.out.println(message);
                    String[] msg = message.split(" ");
                    String update = "";
                    if(msg[0].equals("!cs")){
                        update  = update + "Current Student: " + msg[1];
                    }
                    if(msg[2].equals("!cp")){
                        update = update + "   Current Page: " + msg[3];
                        currLivePage = Integer.parseInt(msg[3]);
                    }
                    if(msg[4].equals("!ci")){
                        currIndexstudent = Integer.parseInt(msg[5]);
                    }
                    studentInfo.setValue(update);


                }
                if(message.equals("offsession")){
                    sessionInfo.setValue("No Current Live Session");
                    studentInfo.setValue("");
                    liveOrNot = false;
                }
                if(message.equals("student")){
                        CGridLayoutList.get(0).getStudentDetails().markAsDirty();
                       ListofStudentLists.get(0).get(0).setStudentName("blah blah");

                    }
                    if(message.equals("livesession")){
                        System.out.println(message);
                        liveOrNot =true;
                        sessionInfo.setValue("Session is now LIVE");


                    }
                    if(message.equals("jump")){
                        System.out.println(message);
                        scrollToResult(CGridLayoutList.get(3));


                    }
                    /**else {
                        messages.addComponent(new Label(message));

                        System.out.println(message);
                    }
                     **/


            }
        });

        System.out.println("out");
    }


    public void addLiveSessionInfo(){
        /**String currStudent = "-1";
         int currLivePage = -1;
         Boolean liveOrNot = false;
**/




    }

    public void addLiveUserList(){
        // info = new Label("Current Users in Session");
        //messages.addComponent(info);
       // Label username = new Label(user.currUser.Name);
        //messages.addComponent(username);

    }
    /**public void setMenuTitle(){
     menuTitle.addComponent(lblMenu);
     menuLayout.addComponent(menuTitle);
     menuLayout.setWidth("100%");
     menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
     }

     public void addWelcomeText(){
     VerticalLayout data = new VerticalLayout();
     if( user.CurrentsaUser != null) {
     Label lblTitle = new Label("WELCOME " + user.CurrenastUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD !!");
     lblTitle.addStyleName("h1");
     lblTitle.addStyleName("colored");

     lblHeader.setValue("" + user.CurreasntUser.Name);

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
        tfID.setValue(user.currUser.UserName);
        tfID.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfID);

        tfEmail = new TextField("Email Address:");
        tfEmail.setValue(user.currUser.Email);
        tfEmail.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfEmail);

        tfName = new TextField("Full Name:");
        tfName.setValue(user.currUser.Name);
        tfName.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfName);

        tfPassword = new PasswordField("Password:");
        tfPassword.setValue(user.currUser.Password);
        tfPassword.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfPassword);

        tfDiscipline = new TextField("Discipline:");
        tfDiscipline.setValue(user.currUser.Discipline);
        tfDiscipline.setRequiredIndicatorVisible(true);
        formLayout.addComponent(tfDiscipline);


        taBio = new TextArea("Bio:");
        taBio.setValue(user.currUser.Bio);
        formLayout.addComponent(taBio);

        opGender = new RadioButtonGroup("Gender");
        opGender.setItems("Male", "Female", "Other");
        if(user.currUser.Gender.equals("Male")){
         opGender.setSelectedItem("Male");

         }
         if(user.currUser.Gender.equals("Female")){
         opGender.setSelectedItem("Female");

         }
         if(user.currUser.Gender.equals("Other")){
         opGender.setSelectedItem("Other");

         }

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

                boolean Auth = user.updateUser(user.currUser.UserName, tfID.getValue(), opGender.getValue().toString(), tfDiscipline.getValue(), tfPassword.getValue(), Bio, tfEmail.getValue(), tfName.getValue());



                if (Auth){
                    user.currUser.UserName = tfID.getValue();
                    user.currUser.Gender = opGender.getValue().toString();
                    user.currUser.Discipline = tfDiscipline.getValue();
                    user.currUser.Password = tfPassword.getValue();
                    user.currUser.Bio = Bio;
                    user.currUser.Email = tfEmail.getValue();
                    user.currUser.Name =tfName.getValue();

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


        if (user.currUser.Role.equals("special user")){
            btnUpload.setVisible(true);
        }
        else{
            btnUpload.setVisible(false);
        }


        if(user.currUser.Role.equals("special user")){
            update.setVisible(false);

            nextStudent.setVisible(true);
            prevStudent.setVisible(true);
            startSession.setVisible(true);


        }
        else{
            update.setVisible(true);

            nextStudent.setVisible(false);
            prevStudent.setVisible(false);
            startSession.setVisible(false);

        }

        Label lblTitle = new Label("WELCOME " + user.currUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD !!");
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        lblHeader.setValue("" + user.currUser.Name);

        AllFilterStuff.addComponent(lblTitle);




        layoutMainButtons = new HorizontalLayout();
        layoutMainButtons.setSizeFull();
        question = new Label("What would you like to do?");

        mainHbutton = new CheckBox("Filter");
        mainSbutton = new CheckBox("Highlight");
        filterBadData = new CheckBox("Filter Unprocessed Data");

        layoutMainButtons.addComponents(question, filterBadData, mainHbutton, mainSbutton);


        AllFilterStuff.addComponent(layoutMainButtons);




        VerticalLayout filteringLayout = new VerticalLayout();
        FilterType = "NONE";
        FilterList = new ArrayList<>();
        FilterList1 = new ArrayList<>();
        FilterList2 = new ArrayList<>();
        FilterList3 = new ArrayList<>();
        Filtering1 = new HorizontalLayout();
        Filtering2 = new HorizontalLayout();
        Filtering3 = new HorizontalLayout();
        Filtering4 = new HorizontalLayout();
        Filtering5 = new HorizontalLayout();
        Filtering6 = new VerticalLayout();
        Filtering1.setCaption("Filter Options");
        Filtering1.setSizeFull();
        Filtering6.setCaption("Current Filter Criteria:");
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
        //CourseCodes.add("PASS");
        CourseCodes.add("FAL");
        CourseCodes.add("PAS");
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

        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        Filtering5.setSizeFull();

        AllFilterStuff.addComponent(Filtering5);



        mainHbutton.addValueChangeListener(e -> {
            if(mainHbutton.getValue()) {
                Filtering5.addComponent(filteringLayout);
                AllFilterStuff.addComponent(Filtering6);
            }

            else {
                RemoveHardFilter();
                Filtering5.removeComponent(filteringLayout);
                Filtering6.removeAllComponents();
            AllFilterStuff.removeComponent(Filtering6)  ;}
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
        Filtering6SOFT.setCaption("Current Highlight Criteria:");
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
        filteringLayoutSOFT.addComponents(Filtering1SOFT, Filtering2SOFT, Filtering3SOFT, Filtering4SOFT);

        filterBadData.addValueChangeListener(e -> {

            if(filterBadData.getValue()) {
                BadFilter();
            }
            else{

                RemoveBadFilter();

            }
        });

        mainSbutton.addValueChangeListener(e -> {

            if(mainSbutton.getValue()) {
                Filtering5.addComponent(filteringLayoutSOFT);
                AllFilterStuff.addComponent(Filtering6SOFT);
            }
        else{
                RemoveSoftFilter();
                Filtering5.removeComponent(filteringLayoutSOFT);
                Filtering6SOFT.removeAllComponents();
            AllFilterStuff.removeComponent(Filtering6SOFT);}
        }
           );


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

        UserViewSOFT1.addStyleName("orange1");
        UserViewSOFT2.addStyleName("purple1");
        UserViewSOFT3.addStyleName("green1");


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
        conn.getStudentNumbers();
        numberOfPages = conn.numberOfPages;
        System.out.println(numberOfPages + " no of pages");
        //allStudents = conn.getStudentObjects();
        List<students> studentList1 = conn.getStudentObjects(1);
        ListofStudentLists = new ArrayList<>();
        ListofStudentLists.add(studentList1);
        // Pages:
        pageNumbers = new ComboBox<>();
        pageNumbers.setPlaceholder("Go to Page...");
        pageNumbers.setTextInputAllowed(true);
        List<String> listOfPageNumbers = new ArrayList<>();
        for(int i=1;i<=numberOfPages;i++) {
            listOfPageNumbers.add(Integer.toString(i));
        }
        pageNumbers.setItems(listOfPageNumbers);
        nextPage = new Button("Next Page");
        prevPage = new Button("Previous Page");
        nextPage.setStyleName("friendly");
        nextPage.setIcon(VaadinIcons.CHEVRON_CIRCLE_RIGHT_O);
        prevPage.setStyleName("friendly");
        prevPage.setIcon(VaadinIcons.CHEVRON_CIRCLE_LEFT_O);
        goToPage = new Button("Go");
        goToPage.setStyleName("primary");
        goToPage.setIcon(VaadinIcons.FORWARD);


        pageButtons.addComponents(prevPage,pageNumbers, goToPage,nextPage);
        currPage = 1;
        CGridLayoutList = new ArrayList<>();
        listOfLayoutList = new ArrayList<>();
        Label currpg = new Label("Current Page: " + currPage);
       //contentLayout1.addComponent(currpg);
       contentLayout1.addComponent(currpg);

        tempLayoutlist = new ArrayList<>();
        for(int i=0; i<ListofStudentLists.get(0).size();i++){
            HardList.add(1);
            CGridLayout studentGrid = new CGridLayout(ListofStudentLists.get(0).get(i), user.currUser, FilterList, FilterType);

            tempLayoutlist.add(studentGrid);
            contentLayout1.addComponent(studentGrid);

        }
        listOfLayoutList.add(tempLayoutlist);

        //making cgridlayoutlist = cgridlayoutlist1
        CGridLayoutList = listOfLayoutList.get(0);
        goToPage.addClickListener(e->{
            int oldpage = currPage;
            String gotoNum  = pageNumbers.getValue();
            System.out.println(gotoNum+" GO TO ");
            if (pageNumbers.getValue() != null) {
                currPage = Integer.parseInt(gotoNum);


                contentLayout1.removeAllComponents();


                HardList.clear();
                HardList1.clear();
                HardList2.clear();
                HardList3.clear();


                Label curr = new Label("Current Page: " + currPage);
                contentLayout1.addComponent(curr);

                if (listOfLayoutList.size() < currPage) {
                    for (int j = listOfLayoutList.size(); j < currPage; j++) {
                        ListofStudentLists.add(conn.getStudentObjects(j + 1));
                        List<CGridLayout> tempLayout = new ArrayList<>();

                        for (int i = 0; i < ListofStudentLists.get(j).size(); i++) {

                            HardList.add(1);
                            CGridLayout studentGrid = new CGridLayout(ListofStudentLists.get(j).get(i), user.currUser, FilterList, FilterType);
                            tempLayout.add(studentGrid);
                            if (j == currPage - 1) {
                                contentLayout1.addComponent(studentGrid);
                            }

                        }
                        listOfLayoutList.add(tempLayout);
                    }
                } else {

                    for (int i = 0; i < listOfLayoutList.get(currPage - 1).size(); i++) {
                        contentLayout1.addComponent(listOfLayoutList.get(currPage - 1).get(i));
                    }

                }

                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = listOfLayoutList.get(currPage - 1);
                if (mainSbutton.getValue()){
                    NewPageHighlight();
                }
                if (mainHbutton.getValue()){
                    NewPageFilter();
                }
                if (filterBadData.getValue()){
                    BadFilter();
                }
                if (user.currUser.Role.equals("special user")) {
                    currIndexstudent = 0;

                    currStudent = ListofStudentLists.get(currPage - 1).get(currIndexstudent).getStudentNumber();
                    currLivePage = currPage;
                    studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                    Broadcaster.broadcast("!cs " + currStudent + " !cp " + currLivePage + " !ci " + currIndexstudent);
                }

            }


        });
        nextPage.addClickListener(e->{
            if(currPage < numberOfPages){

                contentLayout1.removeAllComponents();


                //clear prev filtering

                HardList.clear();
                HardList1.clear();
                HardList2.clear();
                HardList3.clear();


                currPage = currPage +1;
                Label curr = new Label("Current Page: " + currPage);
                contentLayout1.addComponent(curr);

                if(listOfLayoutList.size() < currPage) {
                    ListofStudentLists.add(conn.getStudentObjects(currPage));
                    List<CGridLayout> tempLayout = new ArrayList<>();
                    for (int i = 0; i < ListofStudentLists.get(currPage-1).size(); i++) {
                        HardList.add(1);
                        CGridLayout studentGrid = new CGridLayout(ListofStudentLists.get(currPage-1).get(i), user.currUser, FilterList, FilterType);
                        tempLayout.add(studentGrid);
                        contentLayout1.addComponent(studentGrid);

                    }
                    listOfLayoutList.add(tempLayout);
                }
                else{

                    for(int i=0; i<listOfLayoutList.get(currPage-1).size();i++){
                        contentLayout1.addComponent(listOfLayoutList.get(currPage-1).get(i));
                    }

                }

                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = listOfLayoutList.get(currPage-1);
                if (mainSbutton.getValue()){
                    NewPageHighlight();
                }
                if (mainHbutton.getValue()){
                    NewPageFilter();
                }
                if (filterBadData.getValue()){
                    BadFilter();
                }

                if(user.currUser.Role.equals("special user")) {
                    currIndexstudent = 0;

                    currStudent = ListofStudentLists.get(currPage - 1).get(currIndexstudent).getStudentNumber();
                    currLivePage = currPage;
                    studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                    Broadcaster.broadcast("!cs " + currStudent + " !cp " + currLivePage + " !ci " + currIndexstudent);
                }
            }
        });
        prevPage.addClickListener(e->{
            if(currPage > 1){
                contentLayout1.removeAllComponents();


                //clear prev filtering
                HardList.clear();
                HardList1.clear();
                HardList2.clear();
                HardList3.clear();


                currPage = currPage -1;
                Label curr = new Label("Current Page: " + currPage);
                contentLayout1.addComponent(curr);
                for(int i=0; i<listOfLayoutList.get(currPage-1).size();i++){
                    contentLayout1.addComponent(listOfLayoutList.get(currPage-1).get(i));
                    HardList.add(1);
                }
                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = listOfLayoutList.get(currPage-1);
                if (mainSbutton.getValue()){
                    NewPageHighlight();
                }
                if (mainHbutton.getValue()){
                    NewPageFilter();
                }
                if (filterBadData.getValue()){
                    BadFilter();
                }
                if(user.currUser.Role.equals("special user")) {
                    currIndexstudent = 0;

                    currStudent = ListofStudentLists.get(currPage - 1).get(currIndexstudent).getStudentNumber();
                    currLivePage = currPage;
                    studentInfo.setValue("Current Student: " + currStudent + "   Current Page: " + currLivePage);
                    Broadcaster.broadcast("!cs " + currStudent + " !cp " + currLivePage + " !ci " + currIndexstudent);
                }

            }
        });

        /**btnPage1.addClickListener(e->{
            if(currPage!=1){

                contentLayout1.setVisible(true);


                if(contentLayout2.isVisible() == true){
                    contentLayout2.setVisible(false);
                }
                if(contentLayout3.isVisible() == true){
                    contentLayout3.setVisible(false);
                }
                if(contentLayout4.isVisible() == true){
                    contentLayout4.setVisible(false);
                }
                if(contentLayout5.isVisible() == true){
                    contentLayout5.setVisible(false);
                }

                currPage=1;
            }
        });
        btnPage2.addClickListener(e->{
            if(currPage!=2){
                if(studentLists.studentList2.isEmpty() == true) {
                    studentLists.studentList2 = conn.getStudentObjects(2);

                    CGridLayoutList2 = new ArrayList<>();
                    for (int i = 0; i < studentLists.studentList2.size(); i++) {
                        HardList.add(1);
                        CGridLayout studentGrid = new CGridLayout(studentLists.studentList2.get(i), FilterList, FilterType);
                        CGridLayoutList2.add(studentGrid);
                        contentLayout2.addComponent(studentGrid);

                    }
                }
                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = CGridLayoutList2;

                contentLayout2.setVisible(true);


                if(contentLayout3.isVisible() == true){
                    contentLayout3.setVisible(false);
                }
                if(contentLayout4.isVisible() == true){
                    contentLayout4.setVisible(false);
                }
                if(contentLayout5.isVisible() == true){
                    contentLayout5.setVisible(false);
                }
                if(contentLayout1.isVisible() == true){
                    contentLayout1.setVisible(false);
                }

                currPage=2;

            }
        });
        btnPage3.addClickListener(e->{
            if(currPage!=3){
                if(studentLists.studentList3.isEmpty() == true) {
                    studentLists.studentList3 = conn.getStudentObjects(3);

                    CGridLayoutList3 = new ArrayList<>();
                    for (int i = 0; i < studentLists.studentList3.size(); i++) {
                        HardList.add(1);
                        CGridLayout studentGrid = new CGridLayout(studentLists.studentList3.get(i), FilterList, FilterType);
                        CGridLayoutList3.add(studentGrid);
                        contentLayout3.addComponent(studentGrid);

                    }
                }
                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = CGridLayoutList3;

                contentLayout3.setVisible(true);


                if(contentLayout1.isVisible() == true){
                    contentLayout1.setVisible(false);
                }
                if(contentLayout2.isVisible() == true){
                    contentLayout2.setVisible(false);
                }
                if(contentLayout4.isVisible() == true){
                    contentLayout4.setVisible(false);
                }
                if(contentLayout5.isVisible() == true){
                    contentLayout5.setVisible(false);
                }

                currPage=3;

            }
        });
        btnPage4.addClickListener(e->{
            if(currPage!=4){
                if(studentLists.studentList4.isEmpty() == true) {
                    studentLists.studentList4 = conn.getStudentObjects(4);


                    CGridLayoutList4 = new ArrayList<>();
                    for (int i = 0; i < studentLists.studentList4.size(); i++) {
                        HardList.add(1);
                        CGridLayout studentGrid = new CGridLayout(studentLists.studentList4.get(i), FilterList, FilterType);
                        CGridLayoutList4.add(studentGrid);
                        contentLayout4.addComponent(studentGrid);

                    }
                }
                //making cgridlayoutlist = cgridlayoutlist1
                CGridLayoutList = CGridLayoutList4;

                contentLayout4.setVisible(true);


                if(contentLayout2.isVisible() == true){
                    contentLayout2.setVisible(false);
                }
                if(contentLayout3.isVisible() == true){
                    contentLayout3.setVisible(false);
                }
                if(contentLayout1.isVisible() == true){
                    contentLayout1.setVisible(false);
                }
                if(contentLayout5.isVisible() == true){
                    contentLayout5.setVisible(false);
                }

                currPage=4;
            }
        });
        btnPage5.addClickListener(e->{
            if(currPage!=5) {
                if (studentLists.studentList5.isEmpty() == true) {
                    studentLists.studentList5 = conn.getStudentObjects(5);

                    CGridLayoutList5 = new ArrayList<>();
                    for (int i = 0; i < studentLists.studentList5.size(); i++) {
                        HardList.add(1);
                        CGridLayout studentGrid = new CGridLayout(studentLists.studentList5.get(i), FilterList, FilterType);
                        CGridLayoutList5.add(studentGrid);
                        contentLayout5.addComponent(studentGrid);

                    }
                }
                //making cgridlayoutlist = cgridlayoutlist1
                    CGridLayoutList = CGridLayoutList5;

                contentLayout5.setVisible(true);


                if(contentLayout2.isVisible() == true){
                    contentLayout2.setVisible(false);
                }
                if(contentLayout3.isVisible() == true){
                    contentLayout3.setVisible(false);
                }
                if(contentLayout4.isVisible() == true){
                    contentLayout4.setVisible(false);
                }
                if(contentLayout1.isVisible() == true){
                    contentLayout1.setVisible(false);
                }

                currPage=5;
            }
        });

        **/



        //System.out.println(allStudents.size());
        Label test = new Label("test");


        // old all data
        /**CGridLayoutList = new ArrayList<>();
        for(int i=0; i<allStudents.size();i++){
            HardList.add(1);
            CGridLayout studentGrid = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            CGridLayoutList.add(studentGrid);
            contentLayout.addComponent(studentGrid);

        }
        **/

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
                int val = 1;
                HardList.set(i,val);

            }
        }
        // p.setVisible(false);

            FilterList.clear();
            FilterList1.clear();
            FilterList2.clear();
            FilterList3.clear();




        HardList1.clear();
        HardList2.clear();
        HardList3.clear();

    }


    private void RemoveSoftFilter() {
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



        FilterListSOFT1.clear();
        FilterListSOFT2.clear();
        FilterListSOFT3.clear();




    }



    private void HardFiltering(Button.ClickEvent clickEvent) {

        if(filterBadData.getValue()){
            RemoveBadFilter();
            filterBadData.setValue(false);
        }

        int tobefiltered  = -1;
        boolean ensurefieldsnotblank= false;

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
            ensurefieldsnotblank = true;

        } else {
            value = "EMPTY";
        }
        System.out.println(value);

        FilterList.add(value.toUpperCase());
        value = courseCodeFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        } else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        FilterList.add(value.toUpperCase());
        value = courseNameFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value.toUpperCase());
        FilterList.add(value);
        value = minSuppMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        } else{

            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }

            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        FilterList.add(value);
        value = maxSuppMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        FilterList.add(value);
        value = minFinalMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        FilterList.add(value);
        value = maxFinalMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        FilterList.add(value);

        if (!ensurefieldsnotblank){
            Notification.show("All Fields Can Not Be Blank!").setDelayMsec(3000);
            return;
        }





        FilterType = "HARD";

        if (tobefiltered == -1){
            Notification.show("More than 3 Filters can not be applied!").setDelayMsec(3000);
            return;
        }

        if (tobefiltered == 0) {

            FilterList1.addAll(FilterList);
            FilterList.clear();

            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList1.get(k).equals("EMPTY")){
                    Display1 = Display1  + DisplayFields.get(k) + FilterList1.get(k)+ " . ";
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
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList1.size() - 4) {
                        if (FilterList1.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList1.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList1.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList1.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList1.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList1.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList1.get(k))) {
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

            System.out.println("hard filter list 1 :"+ FilterList1);
        }




        if (tobefiltered == 1 ){

            FilterList2.addAll(FilterList);
            FilterList.clear();


            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList2.get(k).equals("EMPTY")){
                    Display1 = Display1 + DisplayFields.get(k) + FilterList2.get(k) + " . ";
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
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList2.size() - 4) {
                        if (FilterList2.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList2.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList2.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList2.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList2.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList2.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList2.get(k))) {
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

            System.out.println("hard filter list 2: " + FilterList2);
        }

        if (tobefiltered == 2){

            FilterList3.addAll(FilterList);
            FilterList.clear();

            String Display1 = "";

            for (int k = 0; k < DisplayFields.size();k++){
                if (!FilterList3.get(k).equals("EMPTY")){
                    Display1 = Display1 + DisplayFields.get(k) + FilterList3.get(k) + " . ";
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
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList3.size() - 4) {
                        if (FilterList3.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList3.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList3.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList3.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList3.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList3.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList3.get(k))) {
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
            System.out.println("hard filter list 3: " + FilterList3);
        }

        //p.setVisible(false);
        System.out.println("hl: " + HardList);
        System.out.println("hlist1: " + HardList1);
        System.out.println("hlist2" + HardList2);
        System.out.println("hl3: " + HardList3);



        courseCodeFilter.clear();
        courseNameFilter.clear();
        courseOutcomeFilter.clear();
        minSuppMarkFilter.clear();
        maxSuppMarkFilter.clear();
        minFinalMarkFilter.clear();
        maxFinalMarkFilter.clear();

    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {

        if(filterBadData.getValue()){
            RemoveBadFilter();
            filterBadData.setValue(false);
        }


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
            value = courseOutcomeFilterSOFT.getValue().toUpperCase();
            ensurefieldsnotblank = true;

        } else {
            value = "EMPTY";
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = courseCodeFilterSOFT.getValue().toUpperCase();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = courseNameFilterSOFT.getValue().toUpperCase();
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
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);
        value = maxSuppMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);
        value = minFinalMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);

        CurrSOFT.add(value);
        value = maxFinalMarkFilterSOFT.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        else{
            if (isNumeric(value) == false){
                Notification.show("You may only enter numbers as marks!").setDelayMsec(3000);
                return ;
            }
            ensurefieldsnotblank = true;
        }
        System.out.println(value);
        CurrSOFT.add(value);

        FilterType = "SOFT";


        if (!ensurefieldsnotblank){
            Notification.show("All Fields Can Not Be Blank!", Notification.Type.ERROR_MESSAGE).setDelayMsec(1000);
            return;
        }


        for (int j = 0; j < 3; j++){
            if(buttonOnClickCountSOFT.get(j) == 0){
                // buttonOnClickCountSOFT.set(j, 1);
                tobefiltered = j;
                break;
            }
        }


        System.out.println("To be filtered: " + tobefiltered);
        System.out.println("on click list:  " +buttonOnClickCountSOFT);
        System.out.println("Current filter: " + CurrSOFT);


        if (tobefiltered == 0){

            FilterListSOFT1.addAll(CurrSOFT);

            if (buttonOnClickCountSOFT.get(1) == 0){
                if(buttonOnClickCountSOFT.get(2) == 1){
                    //001 becomes 110

                    Filtering6SOFT.removeComponent(SoftDisplay3);

                    buttonOnClickCountSOFT.set(0,1);
                    buttonOnClickCountSOFT.set(1,1);
                    buttonOnClickCountSOFT.set(2,0);
                    System.out.println("new button list: " + buttonOnClickCountSOFT);

                    List <String> temp = new ArrayList<String>(FilterListSOFT1);
                    FilterListSOFT1.clear();
                    FilterListSOFT1.addAll(FilterListSOFT3);
                    FilterListSOFT2.addAll(temp);
                    FilterListSOFT3.clear();


                    String Label1 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT1.get(k).equals("EMPTY")){
                            Label1 = Label1 + DisplayFields.get(k) + FilterListSOFT1.get(k) + " . ";
                        }

                    }
                    UserViewSOFT1.setValue(Label1);
                    Filtering6SOFT.addComponent(SoftDisplay1);


                    String Label2 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT2.get(k).equals("EMPTY")){
                            Label2 = Label2 + DisplayFields.get(k) + FilterListSOFT2.get(k) + " . ";
                        }

                    }
                    UserViewSOFT2.setValue(Label2);
                    Filtering6SOFT.addComponent(SoftDisplay2);





                }

                if (buttonOnClickCountSOFT.get(2) == 0){
                    //000 becomes 100
                    buttonOnClickCountSOFT.set(0,1);

                    String Label1 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT1.get(k).equals("EMPTY")){
                            Label1 = Label1 + DisplayFields.get(k) + FilterListSOFT1.get(k) + " . ";
                        }

                    }
                    UserViewSOFT1.setValue(Label1);
                    Filtering6SOFT.addComponent(SoftDisplay1);
                }
            }

            if (buttonOnClickCountSOFT.get(1) == 1){
                if (buttonOnClickCountSOFT.get(2) == 0){
                    //010 should be 110
                    Filtering6SOFT.removeComponent(SoftDisplay2);
                    buttonOnClickCountSOFT.set(0,1);
                    buttonOnClickCountSOFT.set(1,1);
                    buttonOnClickCountSOFT.set(2,0);

                    List <String> temp = new ArrayList<>();
                    temp.add(FilterListSOFT1.get(0));
                    temp.add(FilterListSOFT1.get(1));
                    temp.add(FilterListSOFT1.get(2));
                    temp.add(FilterListSOFT1.get(3));
                    temp.add(FilterListSOFT1.get(4));
                    temp.add(FilterListSOFT1.get(5));
                    temp.add(FilterListSOFT1.get(6));
                    FilterListSOFT1.clear();

                    FilterListSOFT1.add(FilterListSOFT2.get(0));
                    FilterListSOFT1.add(FilterListSOFT2.get(1));
                    FilterListSOFT1.add(FilterListSOFT2.get(2));
                    FilterListSOFT1.add(FilterListSOFT2.get(3));
                    FilterListSOFT1.add(FilterListSOFT2.get(4));
                    FilterListSOFT1.add(FilterListSOFT2.get(5));
                    FilterListSOFT1.add(FilterListSOFT2.get(6));
                    FilterListSOFT2.clear();
                    FilterListSOFT2.addAll(temp);

                    String Label1 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT1.get(k).equals("EMPTY")){
                            Label1 = Label1 + DisplayFields.get(k) + FilterListSOFT1.get(k) + " . ";
                        }

                    }
                    UserViewSOFT1.setValue(Label1);
                    Filtering6SOFT.addComponent(SoftDisplay1);


                    String Label2 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT2.get(k).equals("EMPTY")){
                            Label2 = Label2 + DisplayFields.get(k) + FilterListSOFT2.get(k) + " . ";
                        }

                    }
                    UserViewSOFT2.setValue(Label2);
                    Filtering6SOFT.addComponent(SoftDisplay2);

                }
                if(buttonOnClickCountSOFT.get(2) == 1){
                    //011 should be 111

                    Filtering6SOFT.removeComponent(SoftDisplay2);
                    Filtering6SOFT.removeComponent(SoftDisplay3);

                    List<String> temp1 = new ArrayList<>(FilterListSOFT1);
                    List<String> temp2 = new ArrayList<>(FilterListSOFT2);
                    List<String> temp3 = new ArrayList<>(FilterListSOFT3);


                    FilterListSOFT1.clear();
                    FilterListSOFT2.clear();
                    FilterListSOFT3.clear();

                    FilterListSOFT1.addAll(temp2);
                    FilterListSOFT2.addAll(temp3);
                    FilterListSOFT3.addAll(temp1);

                    buttonOnClickCountSOFT.set(0,1);
                    buttonOnClickCountSOFT.set(1,1);
                    buttonOnClickCountSOFT.set(2,1);


                    String Label1 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT1.get(k).equals("EMPTY")){
                            Label1 = Label1 + DisplayFields.get(k) + FilterListSOFT1.get(k) + " . ";
                        }

                    }
                    UserViewSOFT1.setValue(Label1);
                    Filtering6SOFT.addComponent(SoftDisplay1);


                    String Label2 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT2.get(k).equals("EMPTY")){
                            Label2 = Label2 + DisplayFields.get(k) + FilterListSOFT2.get(k) + " . ";
                        }

                    }
                    UserViewSOFT2.setValue(Label2);
                    Filtering6SOFT.addComponent(SoftDisplay2);

                    String Label3 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT3.get(k).equals("EMPTY")){
                            Label3 = Label3 + DisplayFields.get(k) + FilterListSOFT3.get(k) + " . ";
                        }

                    }
                    UserViewSOFT3.setValue(Label3);
                    Filtering6SOFT.addComponent(SoftDisplay3);


                }
            }


        }

        if(tobefiltered == 1){

            FilterListSOFT2.addAll(CurrSOFT);


            if (buttonOnClickCountSOFT.get(0) == 1){
                if (buttonOnClickCountSOFT.get(2) == 0){
                    //100 will become 110


                    buttonOnClickCountSOFT.set(1,1);
                    String Label2 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT2.get(k).equals("EMPTY")){
                            Label2 = Label2 + DisplayFields.get(k) + FilterListSOFT2.get(k) + " . ";
                        }

                    }
                    UserViewSOFT2.setValue(Label2);
                    Filtering6SOFT.addComponent(SoftDisplay2);
                }
                if (buttonOnClickCountSOFT.get(2) == 1){
                    //101 should become 111

                    Filtering6SOFT.removeComponent(SoftDisplay2);
                    Filtering6SOFT.removeComponent(SoftDisplay3);

                    List<String> temp2 = new ArrayList<String>(FilterListSOFT2);
                    List<String> temp3 = new ArrayList<String>(FilterListSOFT3);

                    FilterListSOFT2.clear();
                    FilterListSOFT3.clear();

                    FilterListSOFT2.addAll(temp3);
                    FilterListSOFT3.addAll(temp2);

                    buttonOnClickCountSOFT.set(0,1);
                    buttonOnClickCountSOFT.set(1,1);
                    buttonOnClickCountSOFT.set(2,1);

                    String Label2 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT2.get(k).equals("EMPTY")){
                            Label2 = Label2 + DisplayFields.get(k) + FilterListSOFT2.get(k) + " . ";
                        }

                    }
                    UserViewSOFT2.setValue(Label2);
                    Filtering6SOFT.addComponent(SoftDisplay2);

                    String Label3 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT3.get(k).equals("EMPTY")){
                            Label3 = Label3 + DisplayFields.get(k) + FilterListSOFT3.get(k) + " . ";
                        }

                    }
                    UserViewSOFT3.setValue(Label3);
                    Filtering6SOFT.addComponent(SoftDisplay3);

                }
            }


        }

        if (tobefiltered == 2){

            FilterListSOFT3.addAll(CurrSOFT);

            if (buttonOnClickCountSOFT.get(0) == 1){
                if (buttonOnClickCountSOFT.get(1) == 1){
                    //110 becomes 111
                    buttonOnClickCountSOFT.set(2,1);


                    String Label3 = "";
                    for (int k = 0; k < DisplayFields.size();k++){
                        if (!FilterListSOFT3.get(k).equals("EMPTY")){
                            Label3 = Label3 + DisplayFields.get(k) + FilterListSOFT3.get(k) + " . ";
                        }

                    }
                    UserViewSOFT3.setValue(Label3);
                    Filtering6SOFT.addComponent(SoftDisplay3);
                }
            }
        }

        if (tobefiltered == -1){
            Notification.show("Only 3 Highlights can be applied!", Notification.Type.ERROR_MESSAGE).setDelayMsec(3000);
            return;
        }


        for (int i = 0; i < ListofStudentLists.get(currPage-1).size(); i++) {

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (!FilterListSOFT1.get(0).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(1).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(2).equals("EMPTY")  && firstcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT1.get(2))) {
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


                    if (!FilterListSOFT2.get(0).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(1).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(2).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT2.get(2))) {
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


                    if (!FilterListSOFT3.get(0).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(1).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(2).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT3.get(2))) {
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

                if (!rowcolour.equals("")) {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });

            CurrSOFT.clear();


        }

        //updateItemsList();
        //System.out.print(SoftList);

        courseCodeFilterSOFT.clear();
        courseNameFilterSOFT.clear();
        courseOutcomeFilterSOFT.clear();
        minSuppMarkFilterSOFT.clear();
        maxSuppMarkFilterSOFT.clear();
        minFinalMarkFilterSOFT.clear();
        maxFinalMarkFilterSOFT.clear();



    }

    private void RemoveHardFilter1(Button.ClickEvent clickEvent) {
        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);

        FilterType = "NONE";


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
        FilterList1.clear();
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
        FilterList2.clear();
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
        FilterList3.clear();
        Filtering6.removeComponent(HardDisplay3);

        System.out.println("hardList: " + HardList);
        System.out.println("hardList1: " + HardList1);
        System.out.println("hardList2: " + HardList2);
        System.out.println("hardList3: " + HardList3);
    }


    private void RemoveSoftFilter1(Button.ClickEvent clickEvent) {

        FilterType = "NONE";


        FilterListSOFT1.clear();
        buttonOnClickCountSOFT.set(0,0);

        for(int i=0; i< ListofStudentLists.get(currPage-1).size();i++){

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {
                String rowcolour = "";

                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT2.isEmpty()) {

                    if (!FilterListSOFT2.get(0).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(1).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(2).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT2.get(2))) {
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


                    if (!FilterListSOFT3.get(0).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(1).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(2).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT3.get(2))) {
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

                if (!rowcolour.equals("")) {
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



        FilterListSOFT2.clear();
        buttonOnClickCountSOFT.set(1,0);

        for(int i=0; i< ListofStudentLists.get(currPage-1).size(); i++){
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (!FilterListSOFT1.get(0).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(1).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(2).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT1.get(2))) {
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


                    if (!FilterListSOFT3.get(0).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(1).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(2).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT3.get(2))) {
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

                if (!rowcolour.equals("")) {
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


        FilterListSOFT3.clear();
        buttonOnClickCountSOFT.set(2,0);

        for(int i=0; i< CGridLayoutList.size();i++){
            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (!FilterListSOFT1.get(0).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(1).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(2).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT1.get(2))) {
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


                    if (!FilterListSOFT2.get(0).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(1).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(2).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT2.get(2))) {
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


                if (!rowcolour.equals("")) {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });

        }

        Filtering6SOFT.removeComponent(SoftDisplay3);


    }


    private void NewPageHighlight(){

        for (int i = 0; i < ListofStudentLists.get(currPage-1).size(); i++) {

            CGridLayoutList.get(i).getCGrid().setStyleGenerator(t -> {

                String rowcolour = "";

                boolean firstcolour = true;
                boolean secondcolour = true;
                boolean thirdcolour = true;


                if (!FilterListSOFT1.isEmpty()) {

                    if (!FilterListSOFT1.get(0).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT1.get(0))) {
                            System.out.println("OUTcome filtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }

                    if (!FilterListSOFT1.get(1).equals("EMPTY") && firstcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT1.get(1))) {
                            System.out.println("FILtering");
                            firstcolour = true;
                        }
                        else{
                            firstcolour = false;
                        }
                    }
                    if (!FilterListSOFT1.get(2).equals("EMPTY")  && firstcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT1.get(2))) {
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


                    if (!FilterListSOFT2.get(0).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT2.get(0))) {
                            System.out.println("OUTcome filtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }

                    if (!FilterListSOFT2.get(1).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT2.get(1))) {
                            System.out.println("FILtering");
                            secondcolour = true;
                        }
                        else{
                            secondcolour = false;
                        }
                    }
                    if (!FilterListSOFT2.get(2).equals("EMPTY") && secondcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT2.get(2))) {
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


                    if (!FilterListSOFT3.get(0).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseOutcome().toUpperCase().contains(FilterListSOFT3.get(0))) {
                            System.out.println("OUTcome filtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }

                    if (!FilterListSOFT3.get(1).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseCode().toUpperCase().contains(FilterListSOFT3.get(1))) {
                            System.out.println("FILtering");
                            thirdcolour = true;
                        }
                        else{
                            thirdcolour = false;
                        }
                    }
                    if (!FilterListSOFT3.get(2).equals("EMPTY") && thirdcolour == true) {

                        if (t.getCourseName().toUpperCase().contains(FilterListSOFT3.get(2))) {
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

                if (!rowcolour.equals("")) {
                    return rowcolour;
                }

                else {
                    return null;
                }


            });



        }



    }

    private void NewPageFilter(){

        if (!FilterList1.isEmpty()) {

            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList1.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();
                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList1.size() - 4) {
                        if (FilterList1.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList1.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList1.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList1.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList1.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList1.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList1.get(k))) {
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

            System.out.println("hard filter list 1 :"+ FilterList1);
        }




        if (!FilterList2.isEmpty()){

            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList2.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();

                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList2.size() - 4) {
                        if (FilterList2.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList2.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList2.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList2.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList2.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList2.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList2.get(k))) {
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

            System.out.println("hard filter list 2: " + FilterList2);
        }

        if (!FilterList3.isEmpty()){


            for (int i = 0; i < CGridLayoutList.size(); i++) {
                HardList3.add(0);
                //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
                //List<Boolean> boolpercourse = new ArrayList<>();

                int j = 0;
                boolean studentAns = false;
                while (studentAns == false && j < ListofStudentLists.get(currPage-1).get(i).getCourse().size()) {

                    boolean Answer = true;
                    int k = 0;
                    List<String> testing = new ArrayList<>();
                    List<Double> numTesting = new ArrayList<>();
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseOutcome());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseCode());
                    testing.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getCourseName());
                    // 3 BLANK ENTRIES TO KEEP INDEX SAME WITH TESTING INDEX:
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(-1.0);
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getSuppMark());

                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());
                    numTesting.add(ListofStudentLists.get(currPage-1).get(i).getCourse().get(j).getFinalMark());

                    // ONLY CHECKING FIRST 3 FILTER LIST VALUES (NAME CODE AND OUTCOME)
                    while (Answer == true && k < FilterList3.size() - 4) {
                        if (FilterList3.get(k).equals("EMPTY") != true) {
                            if (testing.get(k).toUpperCase().contains(FilterList3.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;

                    }
                    // CHECKING MARKS NOW:
                    while (Answer == true && k < FilterList3.size()) {
                        // CHECKING MIN SUPP/FINAL MARK
                        if (FilterList3.get(k).equals("EMPTY") != true) {
                            if (numTesting.get(k) >= Double.parseDouble(FilterList3.get(k))) {
                                Answer = true;
                            } else {
                                Answer = false;
                            }
                        }

                        k = k + 1;
                        // CHECKING MAX SUPP/FINAL MARK
                        if (FilterList3.get(k).equals("EMPTY") != true && Answer == true) {
                            //Remember supp marks = -1 should not be considered
                            if (numTesting.get(k) >= 0 && numTesting.get(k) <= Double.parseDouble(FilterList3.get(k))) {
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
            System.out.println("hard filter list 3: " + FilterList3);
        }

        //p.setVisible(false);
        System.out.println("hl: " + HardList);
        System.out.println("hlist1: " + HardList1);
        System.out.println("hlist2" + HardList2);
        System.out.println("hl3: " + HardList3);


    }

    private void BadFilter(){

        if (mainSbutton.getValue()){

            for (int i = 0; i < 3; i ++){
                if (buttonOnClickCountSOFT.get(i) == 1){
                    RemoveSoftFilter();
                    break;
                }
            }

            mainSbutton.setValue(false);
        }

        if(mainHbutton.getValue()){

            for (int i = 0; i < 3; i ++){
                if (buttonOnClickCountHARD.get(i) == 1){
                    RemoveHardFilter();
                    break;
                }
            }

            mainHbutton.setValue(false);
        }

        for (int i = 0; i < CGridLayoutList.size(); i++) {

            if(ListofStudentLists.get(currPage-1).get(i).getBadData().isEmpty()){

                CGridLayoutList.get(i).setVisible(false);

            }
            else{
                CGridLayoutList.get(i).getBDGrid().setStyleGenerator(t -> {
                    return "rf";
                });
            }


        }

    }

    private void RemoveBadFilter(){

        for (int i = 0; i < CGridLayoutList.size(); i++) {

            if(!CGridLayoutList.get(i).isVisible()){

                CGridLayoutList.get(i).setVisible(true);

            }
            else{
                CGridLayoutList.get(i).getBDGrid().setStyleGenerator(t -> {return null;});
            }


        }

    }

    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
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

        String username = event.getParameters();
        System.out.println(username);
        user.getUserInfo(username);
        System.out.println("Made user: " + user.currUser.Name +" "+user.currUser.Discipline + " " +user.currUser.UserName);
        //user.currUser = new Users()
        menuLayout.removeAllComponents();
        contentLayout1.removeAllComponents();
        profileLayout.removeAllComponents();
        pageButtons.removeAllComponents();
        AllFilterStuff.removeAllComponents();
        System.out.println(user.currUser.Role);
        Broadcaster.register(this);

        addDataView();
        addProfilePage();
        addLiveSessionInfo();
        getUI().setPollInterval(1000);

    }

}
