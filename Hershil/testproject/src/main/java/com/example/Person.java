package com.example;

/**
 * Created by habee on 2018/08/19.
 */
public class Person {
    private String name;
    private int birthDate;

    public Person(String fullName, int year) {
       name = fullName;
        birthDate = year;

    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        fullname = name;
    }

    public int getBirthYear() {
        return birthDate;
    }

    public void setBirthYear(int date) {
        date = birthDate;
    }

}
