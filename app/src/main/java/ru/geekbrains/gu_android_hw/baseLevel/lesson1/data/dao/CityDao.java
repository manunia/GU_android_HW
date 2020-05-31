package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(City city);

    @Update
    void updateCity(City city);

    @Delete
    void deleteCity(City city);

    @Query("DELETE FROM city")
    void dropTable();

    @Query("DELETE FROM city WHERE id = :id")
    void deleteCityById(long id);

    @Query("SELECT DISTINCT * FROM city DESK")//выводим все уникальные значения из таблицы и в обратном порядке
    List<City> getAllCities();

    @Query("SELECT * FROM city WHERE id = :id")
    City getCityById(long id);

    @Query("SELECT COUNT() FROM city")
    long getCountCities();
}
