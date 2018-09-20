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
        
        //creating the layout
        VerticalLayout verticalLayout = new VerticalLayout(); 
        HorizontalLayout hr1 = new HorizontalLayout();
        HorizontalLayout hr2 = new HorizontalLayout();
        HorizontalLayout hr3 = new HorizontalLayout();
        FormLayout content = new FormLayout();
        Panel panel = new Panel("Students");
        
        //Filter text fields and horizontal layouts
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
       
       
       //We can use a table and use the table filtering properties when filter criteria entered. Below is just my example for testing purposes
       //https://vaadin.com/docs/v7/framework/components/components-table.html for more on tables
       //https://vaadin.com/docs/v7/framework/datamodel/datamodel-container.html#datamodel.container.filtered for filtering data in tables
       Table Student = new Table();
       Student.addContainerProperty("Student Name", String.class, null);
       Student.addContainerProperty("Student Number",  String.class, null);
       Student.setWidth("1300px");
       Object row = Student.addItem();
       Item row1 = Student.getItem(row);
       row1.getItemProperty("Student Name").setValue("Craig");
       row1.getItemProperty("Student Number").setValue("1419904");
       Student.addItem(new Object[]{"John", "13245678"}, 2);
       Student.addItem(new Object[]{"Marc", "1356562"}, 3);
       Student.addItem(new Object[]{"Jessica","1234567"}, 4);
       Student.setPageLength(Student.size());
       content.addComponent(Student);
        
       //Setting the layout with all items created above
        panel.setContent(content);
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
