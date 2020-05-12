package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;
import java.util.regex.Pattern;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection.HttpsConnection;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.DataChangableSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.ChangeData;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.DataSourceBuilder;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.model.WeatherRequest;

public class MainActivity extends BaseActivity implements Constants{

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private TextInputEditText cityName;

    private HttpsConnection connection;
    private WeatherRequest weatherRequest;

    //проверяем введенное название города
    Pattern checkInputCity = Pattern.compile("^[A-Z,А-Я][a-z,а-я]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cityName = findViewById(R.id.inputCity);

        textViewOnFocusChange();

        initDataSource();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivityForResult(intent,SETTING_CODE);
        }
        if (id == R.id.action_about) {
            Snackbar.make(toolbar, R.string.about_developer, Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void textViewOnFocusChange() {
        cityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                String name = tv.getText().toString();
                validate(tv, checkInputCity, "Это не имя!");

                CityDataSource source = new DataSourceBuilder().setResources(getResources()).find(name);
                final DataChangableSource dataChangableSource = new ChangeData(source);
                final ListAdapter adapter = initList(dataChangableSource);

                showWeatherFromRequest(name);
            }
        });
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
                Snackbar.make(view,String.format("Позиция - %d", position),Snackbar.LENGTH_LONG).setAction("Action",null).show();
                showWeatherFromRequest(name);
            }
        });
        return adapter;
    }

    private void showWeatherFromRequest(String name) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                connection = new HttpsConnection(name);
                if (getResources().getConfiguration().locale.toString().contains("ru")) {
                    connection.setRusLocation(true);
                }
                connection.createConnection();
                weatherRequest = connection.getWeatherRequest();
                Intent intent = new Intent("showCityActivity");

                intent.putExtra(CREATE_CITY, weatherRequest);
                startActivity(intent);
            }
        }).start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }
}
