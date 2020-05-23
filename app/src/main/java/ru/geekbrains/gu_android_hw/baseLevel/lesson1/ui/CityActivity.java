package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.annotation.Nullable;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class CityActivity extends BaseActivity implements Constants {

    private EditText weatherDescription;
    private EditText cityTemperature;
    private EditText cityWindSpeed;
    private EditText cityPressure;
    private EditText cityName;
    private EditText humidity;

    private TermometrView termometr;

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

        if (weatherRequest != null) {
            int pressure = (int) (weatherRequest.getMain().getPressure() * 100 * 0.0075);
            int temperature = (int)weatherRequest.getMain().getTemp();

            cityName.setText(weatherRequest.getName());
            weatherDescription.setText(weatherRequest.getWeather()[0].getDescription());
            cityTemperature.setText(String.format("%d", temperature));
            cityPressure.setText(String.format("%d", pressure));
            humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
            cityWindSpeed.setText(String.format("%d", (int)weatherRequest.getWind().getSpeed()));
            termometr.setLevel(temperature);
        } else {
            new MyAlertDialogBuilder(CityActivity.this,"Exception",getResources().getText(R.string.incorrect_name).toString()).build();
        }
    }

    private void initFields() {
        weatherDescription = findViewById(R.id.description);
        cityTemperature = findViewById(R.id.moscowTemperature);
        cityWindSpeed = findViewById(R.id.windSpeed);
        cityPressure = findViewById(R.id.pressure);
        cityName = findViewById(R.id.city_name);
        humidity = findViewById(R.id.textHumidity);
        termometr = findViewById(R.id.termometr);
    }


}
