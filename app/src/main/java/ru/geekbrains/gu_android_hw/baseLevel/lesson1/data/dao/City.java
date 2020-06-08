package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(indices = {@Index(value = {"name"})})
public class City {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "temperature")
    public int temperature;

    public Date weatherDate;


    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public City(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }
}
