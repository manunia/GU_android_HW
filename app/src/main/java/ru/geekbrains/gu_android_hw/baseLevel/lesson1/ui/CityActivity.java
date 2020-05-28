package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class CityActivity extends BaseActivity implements Constants {

    private EditText weatherDescription;
    private ImageView weatherIcon;
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
        getDataFromMainActivity();

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
            Picasso.get()
                    .load("http://openweathermap.org/img/wn/" + weatherRequest.getWeather()[0].getIcon() + "@2x.png").into(weatherIcon);
        } else {
            new MyAlertDialogBuilder(CityActivity.this,"Exception",getResources().getText(R.string.incorrect_name).toString()).build();
        }
    }

    private void initFields() {
        weatherDescription = findViewById(R.id.description);
        weatherIcon = findViewById(R.id.weather_icon);
        cityTemperature = findViewById(R.id.moscowTemperature);
        cityWindSpeed = findViewById(R.id.windSpeed);
        cityPressure = findViewById(R.id.pressure);
        cityName = findViewById(R.id.city_name);
        humidity = findViewById(R.id.textHumidity);

    }


}
