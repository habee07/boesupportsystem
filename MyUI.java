package com.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
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

        Slider sliderFinalmin = new Slider("Final Mark Min value:");
        Label Finalmin = new Label();
        sliderFinalmin.addValueChangeListener((HasValue.ValueChangeEvent<Double> event) -> {
            Double val1 = event.getValue();
            Finalmin.setValue(String.valueOf(val1));
        });
        
        Slider sliderFinalmax = new Slider("Final Mark Max value:");
        Label Finalmax = new Label();
        sliderFinalmax.addValueChangeListener((HasValue.ValueChangeEvent<Double> event) -> {
            Double val2 = event.getValue();
            Finalmax.setValue(String.valueOf(val2));
        });
        
        Slider sliderSuppmin = new Slider("Supp Mark Min value:");
        Label Suppmin = new Label();
        sliderSuppmin.addValueChangeListener((HasValue.ValueChangeEvent<Double> event) -> {
            Double val3 = event.getValue();
            Suppmin.setValue(String.valueOf(val3));
        });
        
        Slider sliderSuppmax = new Slider("Supp Mark Max value:");
        Label Suppmax = new Label();
        sliderSuppmax.addValueChangeListener((HasValue.ValueChangeEvent<Double> event) -> {
            Double val4 = event.getValue();
            Suppmax.setValue(String.valueOf(val4));
        });
        
        TextField coursename = new TextField("Course Name");
        TextField coursecode = new TextField("Course Code");
        TextField outcome = new TextField("Outcome");
        hr1.addComponents(coursename, coursecode, outcome);
        hr2.addComponents(sliderFinalmin, Finalmin , sliderFinalmax, Finalmax);
        hr3.addComponents(sliderSuppmin, Suppmin , sliderSuppmax, Suppmax);
        Button Filter = new Button("Filter", VaadinIcons.CHECK);
        verticalLayout.addComponents(hr1,hr2,hr3, Filter);
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(panel);
        setContent(verticalLayout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
