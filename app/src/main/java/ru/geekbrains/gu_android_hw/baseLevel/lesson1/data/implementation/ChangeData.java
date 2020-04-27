package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.DataChangableSource;

public class ChangeData implements DataChangableSource {

    private int count;
    private CityDataSource dataSource;

    public ChangeData(CityDataSource dataSource) {
        count = dataSource.size();
        this.dataSource = dataSource;
    }


    @Override
    public City getCity(int position) {
        return dataSource.getCity(position);
    }

    @Override
    public int size() {
        return count;
    }
}
