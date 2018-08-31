package com.example;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by habee on 2018/08/19.
 */
final class FilteredGridLayout extends VerticalLayout {

    private final PersonGrid personGrid;
    private final TextField nameFilter;
    private final TextField yearFilter;

    public FilteredGridLayout() {
        nameFilter = new TextField();
        yearFilter = new TextField();
        nameFilter.setPlaceholder("Name...");
        yearFilter.setPlaceholder("Year...");
        nameFilter.addValueChangeListener(this::onNameFilterTextChange);
        yearFilter.addValueChangeListener(this::onYearFilterTextChange);
        addComponent(nameFilter);
        addComponent(yearFilter);

        personGrid = new PersonGrid();
        addComponentsAndExpand(personGrid);
    }

    private void onYearFilterTextChange(HasValue.ValueChangeEvent<String> stringValueChangeEvent) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getBirthYear,s->caseInsensitiveContains(s.toString(),stringValueChangeEvent.getValue()));
    }

    private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getName, s -> caseInsensitiveContains(s, event.getValue()));
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

}