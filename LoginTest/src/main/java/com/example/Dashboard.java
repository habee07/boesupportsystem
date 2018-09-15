package com.example;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

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

    Signup user = new Signup();
    public Dashboard(){
        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnLogout = new Button("Sign Out");
        btnLogout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                user.logOut();

                getUI().getNavigator().navigateTo("login");
            }
        });

        lblMenu =  new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");

        innerUpperSection.addComponent(lblHeader);
        innerUpperSection.addComponent(btnLogout);
        innerUpperSection.setExpandRatio(btnLogout,1);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.TOP_RIGHT);

        upperSection.setSizeFull();
        upperSection.addComponentsAndExpand(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, true));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        upperSection.setHeight(4, UNITS_EM);

        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        contentLayout.setSizeFull();
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(15);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection,1);

        //addComponent(new Label("WELCOME TO BOARD OF EXAMINATIONS SYSTEM DASHBOARD ! "));
    }

    public void setMenuTitle(){
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
    }

    public void addWelcomeText(){
        Label lblTitle = new Label("Welcome " +user.currUser + " !" );
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        lblHeader.setValue("" + user.currUser);

        contentLayout.addComponentsAndExpand(lblTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }

    public void addDashboardOption(String caption){
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


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        setMenuTitle();
        this.addDashboardOption("Dashboard");
        // not needed
        this.addMenuOption("add Class", "Classform");
        this.addMenuOption("show all", "teachers table");
        addWelcomeText();


    }
}
