package com.example;

import com.vaadin.ui.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by habee on 2018/08/19.
 */final class PersonGrid extends Grid<Person> {

    public PersonGrid() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Nicolaus Copernicus", 1543));
        persons.add(new Person("Galileo Galilei", 1564));
        persons.add(new Person("Johannes Kepler", 1571));


        addColumn(Person::getName).setCaption("Name");
        addColumn(Person::getBirthYear).setCaption("Year of birth");
        setItems(persons);
        setSizeFull();
    }

}
