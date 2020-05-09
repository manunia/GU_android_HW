package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model;

import java.io.Serializable;

public class Weather implements Serializable {
    private String main;
    private String description;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
