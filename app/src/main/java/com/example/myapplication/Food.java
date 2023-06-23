package com.example.myapplication;

public class Food {
    int imageRes;
    String name, description;

    public Food(int imageRes, String name, String description) {
        this.imageRes = imageRes;
        this.name = name;
        this.description = description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
