package com.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import java.util.Iterator;
import java.util.Random;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")

public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);


        Panel contentPanel = new Panel("Main Panel");
        //contentPanel.setSizeUndefined();
        contentPanel.setSizeFull();

        new Navigator(this, contentPanel);
        getNavigator().addView(InputPage.NAME, InputPage.class);
        getNavigator().addView(WelcomePage.NAME, WelcomePage.class);
        getNavigator().addView(DataPage.NAME, DataPage.class);

        MenuBar.Command welcome = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getNavigator().navigateTo(WelcomePage.NAME);
            }
        };
        MenuBar.Command input = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getNavigator().navigateTo(InputPage.NAME);
            }
        };
        MenuBar.Command data = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getNavigator().navigateTo(DataPage.NAME);
            }
        };

        MenuBar mainMenu = new MenuBar();
        mainMenu.addItem("Welcome", FontAwesome.ARROW_CIRCLE_LEFT, welcome);
        mainMenu.addItem("Input", FontAwesome.WEIXIN, input);
        mainMenu.addItem("Data", FontAwesome.LIST, data);


        layout.addComponent(mainMenu);
        layout.addComponent(contentPanel);
        getNavigator().navigateTo(WelcomePage.NAME);

        /**final TextField name = new TextField();
        name.setCaption("Type your name here:");
        name.setWidth("400px");

        VaadinArchPersistence vap = new VaadinArchPersistence();
        Label lItems = new Label("");
        lItems.addStyleName("mylabelstyle");
        lItems.setWidth("400px");

        Button bAddItem = new Button("Add Item");
        bAddItem.setWidth("200px");
        bAddItem.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                vap.addItem(name.getValue());
                name.clear();
            }
        });

        Button showItems = new Button("Show All Items");
        showItems.setWidth("200px");
        showItems.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
              lItems.setValue(vap.getItems());
            }
        });

         Button button = new Button("Click Me");
        /button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        **/

        //layout.addComponents(name, bAddItem, showItems, lItems);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
