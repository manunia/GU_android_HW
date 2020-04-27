package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation;

import java.io.Serializable;

public class City implements Serializable {

    private String name;
    private int picture;

    private int todayTemperature;
    private int todaySpeed;
    private int todayPressure;

    public City(String name, int picture) {
        this.name = name;
        this.picture = picture;

        this.todayTemperature = +18;
        this.todaySpeed = 5;
        this.todayPressure = 720;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public int getTodayTemperature() {
        return todayTemperature;
    }

    public void setTodayTemperature(int todayTemperature) {
        this.todayTemperature = todayTemperature;
    }

    public int getTodaySpeed() {
        return todaySpeed;
    }

    public void setTodaySpeed(int todaySpeed) {
        this.todaySpeed = todaySpeed;
    }

    public int getTodayPressure() {
        return todayPressure;
    }

    public void setTodayPressure(int todayPressure) {
        this.todayPressure = todayPressure;
    }
}
