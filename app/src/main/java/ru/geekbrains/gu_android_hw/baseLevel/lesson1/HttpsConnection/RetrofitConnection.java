package ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui.MyAlertDialogBuilder;

public class RetrofitConnection {

    private static final String TAG = "WEATHER";
    private OpenWeather openWeather;

    public void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeather = retrofit.create(OpenWeather.class);
    }

    public void requestRetrofit(String city, String keyApi, Context context) {
        String[] lang = String.valueOf(Locale.getDefault()).split("_");
        openWeather.loadWeather(city, "metric", keyApi, lang[0])
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        Intent intent = new Intent("showCityActivity");

                        intent.putExtra(Constants.CREATE_CITY, response.body());
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        new MyAlertDialogBuilder(context,"Exception!","Fail connection").build();
                        Log.e(TAG, "Fail connection");
                    }
                });
    }
}
