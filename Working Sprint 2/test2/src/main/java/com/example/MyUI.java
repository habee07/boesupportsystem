package com.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

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

        Navigator nav = new Navigator(this,this);

        //login
        nav.addView("login", new Login());

        //sign up
        nav.addView("signup", new SignUpForm());

        //dashboard
        nav.addView("dashboard", new Dashboard());


        //Initial view
        nav.navigateTo("login");


        /**final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);


        Panel contentPanel = new Panel("Main Panel");
        //contentPanel.setSizeUndefined();
        contentPanel.setSizeFull();

        new Navigator(this, contentPanel);
        getNavigator().addView(WelcomePage.NAME, WelcomePage.class);
        getNavigator().addView(InputPage.NAME, InputPage.class);

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

        MenuBar mainMenu = new MenuBar();
        mainMenu.addItem("Welcome", FontAwesome.ARROW_CIRCLE_LEFT, welcome);
        mainMenu.addItem("Input", FontAwesome.WEIXIN, input);

        layout.addComponent(mainMenu);
        layout.addComponent(contentPanel);
        getNavigator().navigateTo(WelcomePage.NAME);
        setContent(layout);
        inal VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        
        setContent(layout);**/
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
