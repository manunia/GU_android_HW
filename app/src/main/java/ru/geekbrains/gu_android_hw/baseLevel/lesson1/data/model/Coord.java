package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model;

import java.io.Serializable;

public class Coord implements Serializable {
    private float lon;
    private float lat;


    // Getter Methods

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    // Setter Methods

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
