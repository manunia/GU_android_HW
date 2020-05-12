package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model;

import java.io.Serializable;

public class Wind implements Serializable {
    private float speed;
    private float deg;


    // Getter Methods

    public float getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }

    // Setter Methods

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}
