package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;

public class CityActivity extends BaseActivity implements Constants {

    private TextView cityTemperature;
    private TextView cityWindSpeed;
    private TextView cityPressure;
    private TextView cityName;


    private TableRow windspeedRow;
    private TableRow pressureRow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        initFields();
        getDataFromMainActivity();
    }

    private void getDataFromMainActivity() {
        City city = (City) getIntent().getExtras().getSerializable(CREATE_CITY);

        cityName.setText(city.getName());
        cityTemperature.setText(((Integer)city.getTodayTemperature()).toString());
    }

    private void initFields() {
        cityTemperature = findViewById(R.id.moscowTemperature);
        cityWindSpeed = findViewById(R.id.windSpeed);
        cityPressure = findViewById(R.id.pressure);
        cityName = findViewById(R.id.itemCityName);
        windspeedRow = findViewById(R.id.windspeedRow);
        pressureRow = findViewById(R.id.pressureRow);
    }


}
