package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {City.class}, version = 2)
public abstract class CityDatabase extends RoomDatabase {
    public abstract CityDao getCityDao();
}
