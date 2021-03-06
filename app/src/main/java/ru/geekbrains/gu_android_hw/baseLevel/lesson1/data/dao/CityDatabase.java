package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.DateConverter;


@Database(entities = {City.class}, version = 15)
@TypeConverters(DateConverter.class)
public abstract class CityDatabase extends RoomDatabase {
    public abstract CityDao getCityDao();
}
