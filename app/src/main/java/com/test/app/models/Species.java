package com.test.app.models;

public class Species {

    public Species(String name)
    {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
