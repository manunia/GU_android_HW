package ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.gu_android_hw.BuildConfig;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;


public class HttpsConnection {

    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String POST_BODY = ",RU&units=metric&appid=";
    private static final String RUS_LANG = "&lang=ru";

    private URL uri;
    private boolean isRusLocation;
    private String name;
    private WeatherRequest weatherRequest;

    public HttpsConnection(String name) {
        this.name = name;
    }

    public void setWeatherRequest(WeatherRequest weatherRequest) {
        this.weatherRequest = weatherRequest;
    }

    public boolean isRusLocation() {
        return isRusLocation;
    }

    public void setRusLocation(boolean rusLocation) {
        isRusLocation = rusLocation;
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public void createConnection() {
        try {
            String path = WEATHER_URL + name + POST_BODY + BuildConfig.WEATHER_API_KEY;
            if (isRusLocation()) {
                path+=RUS_LANG;
            }
            uri = new URL(path);

            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(100000);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = getLines(in);
                Gson gson = new Gson();
                setWeatherRequest(gson.fromJson(result, WeatherRequest.class));
            } catch (Exception e) {
                Log.e(TAG, "Fail connection", e);
                e.printStackTrace();
            } finally {
                if (null != urlConnection) {
                    urlConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}
