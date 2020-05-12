package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;

public class DataSource implements CityDataSource {

    private List<City> dataSource;
    private Resources resources;

    public DataSource(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public DataSource init(){
        // строки описаний из ресурсов
        String[] descriptions = resources.getStringArray(R.array.items);
        // изображения
        int[] pictures = getImageArray();
        // заполнение источника данных
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new City(descriptions[i], pictures[i]));
        }
        return this;
    }

    public DataSource find(String name) {
        // строки описаний из ресурсов
        String[] descriptions = resources.getStringArray(R.array.items);
        int[] pictures = getImageArray();
        // заполнение источника данных
        for (int i = 0; i < descriptions.length; i++) {
            if(descriptions[i].equals(name)) {
                dataSource.add(0,new City(descriptions[i], pictures[i]));
            }
        }
        return this;
    }

    public City getCity(int position) {
        return dataSource.get(position);
    }

    public int size() {
        return dataSource.size();
    }

    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = pictures.getResourceId(i,0);
        }
        return answer;
    }


}
