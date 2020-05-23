package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data;

import com.google.gson.Gson;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class JsonParser {
    Gson gson;
    WeatherRequest weatherRequest;

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
}
