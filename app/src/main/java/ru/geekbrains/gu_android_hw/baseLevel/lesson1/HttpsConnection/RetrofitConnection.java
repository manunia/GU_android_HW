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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        openWeather = retrofit.create(OpenWeather.class);
    }

    public void requestRetrofit(String city, String keyApi, String units, Context context, CitySource source) {
        String[] lang = String.valueOf(Locale.getDefault()).split("_");
        openWeather.loadWeather(city,units,keyApi,lang[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<WeatherRequest>() {
                    @Override
                    public void onSuccess(WeatherRequest weatherRequest) {
                        if (weatherRequest != null) {
                            saveResponseIntoDataBase(weatherRequest, source);
                            Intent intent = new Intent("showCityActivity");
                            intent.putExtra(Constants.CREATE_CITY, weatherRequest);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        new MyAlertDialogBuilder(context,"Error!",e.getMessage()).build();
                    }
                });
    }

    private void saveResponseIntoDataBase(WeatherRequest response, CitySource source) {
        City newCity = new City(response.getName(),(int)response.getMain().getTemp());
        Calendar cal = new GregorianCalendar();
        newCity.weatherDate = cal.getTime();
        if (source.isCityExists(response.getName())) {
            source.updateCity(newCity);
        } else {
            source.addCity(newCity);
        }
    }
}
