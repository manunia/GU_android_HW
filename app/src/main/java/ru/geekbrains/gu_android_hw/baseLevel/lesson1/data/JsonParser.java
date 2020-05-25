package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection.HttpsConnection;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui.MainActivity;

public class JsonParser {
    private Gson gson;
    private WeatherRequest weatherRequest;
    private HttpsConnection connection;

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public void setWeatherRequest(WeatherRequest weatherRequest) {
        this.weatherRequest = weatherRequest;
    }

    public void parse(String result) {
        gson = new Gson();
        setWeatherRequest(gson.fromJson(result, WeatherRequest.class));
    }

    public WeatherRequest getWeatherFromRequest(String name, Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                connection = new HttpsConnection(name);
                if (context.getResources().getConfiguration().locale.toString().contains("ru")) {
                    connection.setRusLocation(true);
                }

                connection.createConnection(context, JsonParser.this);

                Intent intent = new Intent("showCityActivity");

                intent.putExtra(Constants.CREATE_CITY, weatherRequest);
                context.startActivity(intent);
            }
        }).start();

        return weatherRequest;
    }
}
