package com.example.demo_3_3_3;

import java.io.Serializable;

public class User implements Serializable {
    private static final long seriaVersionUID = 1L;
    private String gender = "";
    private Integer height ;

    public User() {
    }

    public static long getSeriaVersionUID() {
        return seriaVersionUID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
