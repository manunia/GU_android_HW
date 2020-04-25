package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import java.io.Serializable;

public class CityEntity implements Serializable {

    private String name;

    private int todayTemperature;
    private int todaySpeed;
    private int todayPressure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
