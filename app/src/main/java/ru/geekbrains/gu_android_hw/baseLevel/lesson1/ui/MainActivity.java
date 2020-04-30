package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.DataChangableSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.ChangeData;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.DataSourceBuilder;

public class MainActivity extends AppCompatActivity implements Constants {

    private RecyclerView recyclerView;

    TextInputEditText cityName;
    //проверяем введенное название города
    Pattern checkInputCity = Pattern.compile("^[A-Z][a-z]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.inputCity);

        cityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                validate(tv, checkInputCity, "Это не имя!");
            }
        });

        initDataSource();
    }

    private void validate(TextView tv, Pattern check, String s) {
        String value = tv.getText().toString();
        if (check.matcher(value).matches()) {
            hideError(tv);
        } else {
            showError(tv, s);
        }
    }

    private void showError(TextView tv, String s) {
        tv.setError(s);
    }

    private void hideError(TextView tv) {
        tv.setError(null);
    }

    private void initDataSource() {
        CityDataSource source = new DataSourceBuilder().setResources(getResources()).build();

        final DataChangableSource dataChangableSource = new ChangeData(source);
        final ListAdapter adapter = initList(dataChangableSource);

    }

    private ListAdapter initList(final CityDataSource data){
        recyclerView = findViewById(R.id.recycler_view);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        ListAdapter adapter = new ListAdapter(data);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name, int position) {
                Toast.makeText(MainActivity.this, String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("showCityActivity");

                intent.putExtra(CREATE_CITY, createCity(name,position));
                startActivity(intent);
            }
        });
        return adapter;
    }

    private City createCity(String name, int position) {
        City city = new City(name,position);

        return city;
    }
}
