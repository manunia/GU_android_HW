package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import android.app.Application;

import androidx.room.Room;

import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CityDao;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CityDatabase;

public class App extends Application {

    private static App instance;

    private CityDatabase db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Сохраняем объект приложения (для Singleton’а)
        instance = this;

        // Строим базу
        db = Room.databaseBuilder(
                getApplicationContext(),
                CityDatabase.class,
                "city_database"
        ).allowMainThreadQueries() //Только для примеров и тестирования.
         .build();
    }

    public CityDao getCityDao() {
        return db.getCityDao();
    }
}
