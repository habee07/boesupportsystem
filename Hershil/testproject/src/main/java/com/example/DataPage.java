package com.example;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import com.vaadin.data.provider.DataProvider;

/**
 * Created by habee on 2018/08/18.
 */

public class DataPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "datapage";


    public DataPage() {
        FilteredGridLayout personGrid = new FilteredGridLayout();
        /**TextField filterTextField = new TextField("Filter by name");
        filterTextField.setPlaceholder("name filter");

        filterTextField.addValueChangeListener(event -> {
            dataProvider.setFilter(Person::getName, name -> {
                String nameLower = name == null ? ""
                        : name.toLowerCase(Locale.ENGLISH);
                String filterLower = event.getValue()
                        .toLowerCase(Locale.ENGLISH);
                return nameLower.contains(filterLower);
            });
        **/
        Label welcome = new Label("Welcome");
        welcome.addStyleName("h1");
        addComponent(welcome);

// Create a grid bound to the list
        addComponent(personGrid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
