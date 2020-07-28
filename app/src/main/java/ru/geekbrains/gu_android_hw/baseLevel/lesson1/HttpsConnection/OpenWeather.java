package ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public interface OpenWeather {

    @GET("data/2.5/weather")
    Single<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("units") String metric, @Query("appid") String keyApi, @Query("lang") String lang);

}
