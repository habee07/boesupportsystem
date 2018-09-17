package com.example;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

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

    Label lblHeader;
    Label lblMenu;
    Button btnLogout;
    Button btnProfile;

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


    Users user = new Users();

    public Dashboard() {
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

                Notification.show("Sign out Successful!").setDelayMsec(2000);
                getUI().getNavigator().navigateTo("login");
                user.logOut();

            }
        });

        lblMenu = new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");

        innerUpperSection.addComponent(lblHeader);
        innerUpperSection.addComponent(btnProfile);
        innerUpperSection.addComponent(btnLogout);
        //innerUpperSection.setExpandRatio(filler,2f);
        innerUpperSection.setExpandRatio(lblHeader, 1f);
        //innerUpperSection.setExpandRatio(btnLogout,1.5f);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(lblHeader, Alignment.MIDDLE_RIGHT);
        innerUpperSection.setComponentAlignment(btnProfile, Alignment.MIDDLE_CENTER);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.MIDDLE_LEFT);

        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, true));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");


        addComponent(upperSection);
        contentLayout.setSizeUndefined();


        contentLayout.setWidth("100%");

        addComponent(contentLayout);

    }

    public void setMenuTitle() {
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
    }


    public void addDataView() {

        System.out.println(user.CurrentUser.Name);
        Label lblTitle = new Label("WELCOME " + user.CurrentUser.Name + " TO THE WITS BOARD OF EXAMINATIONS SYSTEM DASHBOARD !!");
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        lblHeader.setValue("" + user.CurrentUser.Name);

        contentLayout.addComponent(lblTitle);


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
        SoftFilter.setIcon(VaadinIcons.FLAG_O);
        RemoveSoft = new Button("Remove Soft Filter");
        RemoveSoft.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        SoftFilter.addClickListener(this::SoftFiltering);
        RemoveSoft.addClickListener(this::RemoveSoftFilter);
        HardFilter = new Button("Hard Filter");
        HardFilter.setIcon(VaadinIcons.FILTER);
        RemoveHard = new Button("Remove Hard Filter");
        RemoveHard.setIcon(VaadinIcons.CLOSE_CIRCLE_O);
        RemoveHard.addClickListener(this::RemoveHardFilter);
        HardFilter.addClickListener(this::HardFiltering);
        Label dash = new Label("-");

        HardFilter.setStyleName("primary");
        SoftFilter.setStyleName("friendly");
        RemoveSoft.setStyleName("danger");
        RemoveHard.setStyleName("danger");

        Filtering1.addComponents(courseCodeFilter, courseNameFilter, courseOutcomeFilter);
        Filtering2.addComponents(minSuppMarkFilter, new Label("-"), maxSuppMarkFilter);
        Filtering3.addComponents(minFinalMarkFilter, dash, maxFinalMarkFilter);
        Filtering4.addComponents(SoftFilter, RemoveSoft, HardFilter, RemoveHard);
        filteringLayout.addComponents(Filtering1, Filtering2, Filtering3, Filtering4);


        contentLayout.addComponent(filteringLayout);

        //DB CONNECTION TEST:
        MysqlCon conn = new MysqlCon();

        allStudents = conn.getStudentObjects();

        //System.out.println(allStudents.size());
        Label test = new Label("test");


        CGridLayoutList = new ArrayList<>();
        for (int i = 0; i < allStudents.size(); i++) {
            CGridLayout studentGrid = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            CGridLayoutList.add(studentGrid);
            contentLayout.addComponent(studentGrid);

        }


    }

    private void RemoveHardFilter(Button.ClickEvent clickEvent) {
        FilterType = "NONE";
        for (int i = 0; i < CGridLayoutList.size(); i++) {
            if (CGridLayoutList.get(i).isVisible() == false) {
                CGridLayoutList.get(i).setVisible(true);
            }
        }
        FilterList.clear();
    }


    private void RemoveSoftFilter(Button.ClickEvent clickEvent) {
        FilterType = "NONE";
        for (int i = 0; i < CGridLayoutList.size(); i++) {
            CGridLayout newReplacement = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            replaceComponent(CGridLayoutList.get(i), newReplacement);
            CGridLayoutList.set(i, newReplacement);

        }//removeAllComponents();
        //addComponents(Filtering1, Filtering2, Filtering3, Filtering4);
        //FilterList.clear();
        //updateItemsList();

    }


    private void HardFiltering(Button.ClickEvent clickEvent) {
        FilterList.clear();
        String value;
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
        for (int i = 0; i < CGridLayoutList.size(); i++) {
            //CGridLayout newReplacement = new CGridLayout(allStudents.get(i),FilterList,FilterType);
            List<Boolean> boolpercourse = new ArrayList<>();
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
                        if (numTesting.get(k) <= Double.parseDouble(FilterList.get(k))) {
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
                CGridLayoutList.get(i).setVisible(false);
            }

        }

    }

    private void SoftFiltering(Button.ClickEvent clickEvent) {
        FilterList.clear();
        String value;
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
        value = minFinalMarkFilter.getValue();
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
        value = maxSuppMarkFilter.getValue();
        if (value.equals("")) {
            value = "EMPTY";
        }
        System.out.println(value);
        FilterList.add(value);

        FilterType = "SOFT";

        for (int i = 0; i < CGridLayoutList.size(); i++) {
            CGridLayout newReplacement = new CGridLayout(allStudents.get(i), FilterList, FilterType);
            replaceComponent(CGridLayoutList.get(i), newReplacement);
            CGridLayoutList.set(i, newReplacement);

        }

        //updateItemsList();
    }

    /**
     * public void addDashboardOption(String caption){
     * Button button = new Button(caption);
     * button.setWidth("100%");
     * button.setStyleName("borderless");
     * menuLayout.addComponentsAndExpand(button);
     * <p/>
     * button.addClickListener(new Button.ClickListener() {
     *
     * @Override public void buttonClick(Button.ClickEvent clickEvent) {
     * contentLayout.removeAllComponents();
     * addWelcomeText();
     * }
     * });
     * }
     * <p/>
     * <p/>
     * public void addMenuOption(String caption, String componentName){
     * Button butt = new Button(caption);
     * butt.setWidth("100%");
     * butt.setStyleName("borderless");
     * menuLayout.addComponent(butt);
     * butt.addClickListener(new Button.ClickListener() {
     * @Override public void buttonClick(Button.ClickEvent clickEvent) {
     * contentLayout.removeAllComponents();
     * contentLayout.addComponent(getComponent(Integer.parseInt(componentName)));
     * }
     * });
     * <p/>
     * <p/>
     * <p/>
     * }
     */

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        addDataView();

    }
}
