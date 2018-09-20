package com.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Range;
import com.vaadin.ui.*;
import javafx.scene.chart.PieChart.Data;

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
        
        VerticalLayout verticalLayout = new VerticalLayout(); 
        HorizontalLayout hr1 = new HorizontalLayout();
        HorizontalLayout hr2 = new HorizontalLayout();
        HorizontalLayout hr3 = new HorizontalLayout();
        FormLayout content = new FormLayout();
        Panel panel = new Panel("Students");
        panel.setContent(content);
        
        TextField coursename = new TextField("Course Name");
        coursename.focus();
        TextField coursecode = new TextField("Course Code");
        TextField outcome = new TextField("Outcome");
        TextField SuppMin = new TextField("Supp Mark Min value");
        TextField SuppMax = new TextField("Supp Mark Max value");
        TextField FinalMin = new TextField("Final Mark Min value");
        TextField FinalMax = new TextField("Final Mark Max value");
        hr1.addComponents(coursename, coursecode, outcome);
        hr2.addComponents(SuppMin, SuppMax);
        hr3.addComponents(FinalMin , FinalMax);
        Button Filter = new Button("Filter", VaadinIcons.CHECK);
        verticalLayout.addComponents(hr1,hr2,hr3, Filter);
        verticalLayout.setComponentAlignment(hr1, Alignment.TOP_CENTER);
        verticalLayout.setComponentAlignment(hr2, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(hr3, Alignment.BOTTOM_CENTER);
        verticalLayout.setComponentAlignment(Filter, Alignment.BOTTOM_CENTER);
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(panel);
        setContent(verticalLayout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
