package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;


import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection.HttpsConnection;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class WeatherDataLoader {

    private DataLoadListener dataLoadListener;
    private Gson gson;
    private WeatherRequest weatherRequest;
    private HttpsConnection connection;

    public WeatherDataLoader(DataLoadListener dataLoadListener) {
        this.dataLoadListener = dataLoadListener;
    }

    public void loadData(String name, Context context) {
        final Handler handler = new Handler(Looper.myLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                connection = new HttpsConnection(name);
                if (context.getResources().getConfiguration().locale.toString().contains("ru")) {
                    connection.setRusLocation(true);
                }

                connection.createConnection(context);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dataLoadListener.onFinish(connection.getWeatherRequest());
                    }
                });
            }
        }).start();
    }

    public void parse(String result) {
        gson = new Gson();
        weatherRequest = gson.fromJson(result, WeatherRequest.class);
    }

    public interface DataLoadListener {
        void onFinish(WeatherRequest param);
    }

}
