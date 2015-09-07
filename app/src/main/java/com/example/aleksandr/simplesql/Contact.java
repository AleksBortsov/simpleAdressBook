package com.example.aleksandr.simplesql;

/**
 * Created by aleksandr on 07.09.2015.
 */
public class Contact {
    String name;
    String lastName;

    public Contact(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
