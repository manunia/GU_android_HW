package ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CitySource;
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

    public void requestRetrofit(String city, String keyApi, String units, Context context, CitySource source) {
        String[] lang = String.valueOf(Locale.getDefault()).split("_");
        openWeather.loadWeather(city, units, keyApi, lang[0])
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            saveResponseIntoDataBase(response, source);
                            Intent intent = new Intent("showCityActivity");
                            intent.putExtra(Constants.CREATE_CITY, response.body());
                            context.startActivity(intent);
                        }
                        if (!response.isSuccessful() && response.errorBody() != null) {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                String error = jsonError.getString("message");
                                new MyAlertDialogBuilder(context,"Error!",error).build();
                                Log.e(TAG, error);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        new MyAlertDialogBuilder(context,"Exception!","Fail connection").build();
                        Log.e(TAG, "Fail connection");
                    }
                });
    }

    private void saveResponseIntoDataBase(Response<WeatherRequest> response, CitySource source) {
        City newCity = new City(response.body().getName(),(int)response.body().getMain().getTemp());
        Calendar cal = new GregorianCalendar();
        newCity.weatherDate = cal.getTime();
        if (source.isCityExists(response.body().getName())) {
            source.updateCity(newCity);
        } else {
            source.addCity(newCity);
        }
    }
}
