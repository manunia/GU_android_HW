package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.squareup.picasso.Picasso;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.servicies.MyNotificationChannel;

public class CityActivity extends BaseActivity implements Constants {

    private EditText weatherDescription;
    private ImageView weatherIcon;
    private EditText cityTemperature;
    private EditText cityWindSpeed;
    private EditText cityPressure;
    private EditText cityName;
    private EditText humidity;
    private TextView celcium;

    public static final String CHANNEL_ID = "3";
    public static final String CHANNEL_NAME = "shtorm";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        initFields();
        getDataFromMainActivity();
    }

    private void initGetToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("PushMessage", "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        System.out.println("token = " + token);
                    }
                });
    }

    private void initNotificationChannel() {
        new MyNotificationChannel().init(CityActivity.this, CHANNEL_ID, CHANNEL_NAME);
    }

    private void getDataFromMainActivity() {
        celcium.setText(units);
        WeatherRequest weatherRequest = (WeatherRequest) getIntent().getExtras().getSerializable(CREATE_CITY);

        if (weatherRequest != null) {
            int pressure = (int) (weatherRequest.getMain().getPressure() * 100 * 0.0075);
            int temperature = (int)weatherRequest.getMain().getTemp();
            int windSpeed = (int)weatherRequest.getWind().getSpeed();

            cityName.setText(weatherRequest.getName());
            weatherDescription.setText(weatherRequest.getWeather()[0].getDescription());
            cityTemperature.setText(String.format("%d", temperature));
            cityPressure.setText(String.format("%d", pressure));
            humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
            cityWindSpeed.setText(String.format("%d", windSpeed));
            Picasso.get()
                    .load("http://openweathermap.org/img/wn/" + weatherRequest.getWeather()[0].getIcon() + "@4x.png").into(weatherIcon);

            if (windSpeed >= 6) {
                initGetToken();
                initNotificationChannel();
            }

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

        celcium = findViewById(R.id.celsium);
    }


}
