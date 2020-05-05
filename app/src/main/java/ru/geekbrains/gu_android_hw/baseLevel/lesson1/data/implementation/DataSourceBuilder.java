package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation;

import android.content.res.Resources;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;

public class DataSourceBuilder {

    private Resources resources;

    public DataSourceBuilder setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    public CityDataSource build() {
        DataSource dataSource = new DataSource(resources);
        dataSource.init();
        return dataSource;
    }

    public CityDataSource find(String name) {
        DataSource dataSource = new DataSource(resources);
        dataSource.find(name);
        return dataSource;
    }
}
