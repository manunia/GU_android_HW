package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data;

import java.io.Serializable;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;

public interface CityDataSource {
    City getCity(int position);
    int size();
}
