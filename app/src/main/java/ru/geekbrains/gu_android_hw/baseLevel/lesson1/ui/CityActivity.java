package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class CityActivity extends BaseActivity implements Constants {

    private EditText weatherDescription;
    private EditText cityTemperature;
    private EditText cityWindSpeed;
    private EditText cityPressure;
    private EditText cityName;
    private EditText humidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        initFields();
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                getDataFromMainActivity();
            }
        });


    }

    private void getDataFromMainActivity() {
        WeatherRequest weatherRequest = (WeatherRequest) getIntent().getExtras().getSerializable(CREATE_CITY);

        int pressure = (int) (weatherRequest.getMain().getPressure() * 100 * 0.0075);

        cityName.setText(weatherRequest.getName());
        weatherDescription.setText(weatherRequest.getWeather()[0].getDescription());
        cityTemperature.setText(String.format("%d", (int)weatherRequest.getMain().getTemp()));
        cityPressure.setText(String.format("%d", pressure));
        humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
        cityWindSpeed.setText(String.format("%d", (int)weatherRequest.getWind().getSpeed()));

    }

    private void initFields() {
        weatherDescription = findViewById(R.id.description);
        cityTemperature = findViewById(R.id.moscowTemperature);
        cityWindSpeed = findViewById(R.id.windSpeed);
        cityPressure = findViewById(R.id.pressure);
        cityName = findViewById(R.id.city_name);
        humidity = findViewById(R.id.textHumidity);
    }


}
