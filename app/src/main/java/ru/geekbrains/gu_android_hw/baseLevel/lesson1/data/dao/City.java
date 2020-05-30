package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"name"})})
public class City {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
