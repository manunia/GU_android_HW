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
    private static final String POST_BODY = ",RU&appid=";
    private String name;

    private WeatherRequest weatherRequest;

    public HttpsConnection(String name) {
        this.name = name;
    }

    public void setWeatherRequest(WeatherRequest weatherRequest) {
        this.weatherRequest = weatherRequest;
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public void createConnection() {
        try {
            final URL uri = new URL(WEATHER_URL + name + POST_BODY + BuildConfig.WEATHER_API_KEY);

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        //final WeatherRequest weatherRequest = gson.fromJson(result,WeatherRequest.class);
                        setWeatherRequest(gson.fromJson(result,WeatherRequest.class));

                    } catch (Exception e) {
                        Log.e(TAG,"Fail connection",e);
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                private String getLines(BufferedReader in) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }
                    return null;
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG,"Fail URI",e);
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}
