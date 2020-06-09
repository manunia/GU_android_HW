package ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao;

import java.util.List;

// Вспомогательный класс, развязывающий зависимость между Room и RecyclerView
public class CitySource {
    private final CityDao cityDao;

    private List<City> cities;

    public CitySource(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public List<City> getCities() {
        if (cities == null) {
            loadCities();
        }
        return cities;
    }

    public boolean isCityExists(String name) {
        if (cities != null) {
            for (City c : cities) {
                if (c.name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void loadCities() {
        cities = cityDao.getAllCities();
    }

    public long getCountCities() {
        return cityDao.getCountCities();
    }

    public void addCity(City city) {
        cityDao.insertCity(city);
        loadCities(); // После изменения БД надо повторно прочесть данные из буфера
    }

    public void updateCity(City city) {
        cityDao.updateCity(city);
        loadCities();
    }

    public void removeCity(long id) {
        cityDao.deleteCityById(id);
    }

    public void deleteAll() {
        cityDao.dropTable();
    }

}
