package com.example.databindingdemo;

import androidx.lifecycle.ViewModel;

public class Person{
    private String name = "ABCDEFG";

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
