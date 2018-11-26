package com.example;

import com.vaadin.ui.Grid;

import java.util.List;

/**
 * Created by Laila on 24/11/2018.
 */
public class BadDataGrid extends Grid<String> {

    private List<String> BadDataLine;

    public BadDataGrid(List<String> badDataLine) {
        BadDataLine = badDataLine;
        addColumn(String::toString).setCaption("Data with incorrect format");
        setItems(BadDataLine);
        setSizeFull();

        if (BadDataLine.size() != 0) {

            setHeightByRows(BadDataLine.size());

        }

    }
}
