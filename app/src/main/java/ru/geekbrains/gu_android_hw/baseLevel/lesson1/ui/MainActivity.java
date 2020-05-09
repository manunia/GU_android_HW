package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.gu_android_hw.BuildConfig;
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

    private RecyclerView recyclerView;

    private TextInputEditText cityName;

    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=Moscow,RU&appid=";

    private WeatherRequest weatherRequest;

    //проверяем введенное название города
    Pattern checkInputCity = Pattern.compile("^[A-Z][a-z]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Button setting = findViewById(R.id.settingButton);

        settingOnClick(setting);

        cityName = findViewById(R.id.inputCity);

        textViewOnFocusChange();

        initDataSource();
    }

    private void textViewOnFocusChange() {
        cityName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                validate(tv, checkInputCity, "Это не имя!");

                CityDataSource source = new DataSourceBuilder().setResources(getResources()).find(tv.getText().toString());
                final DataChangableSource dataChangableSource = new ChangeData(source);
                final ListAdapter adapter = initList(dataChangableSource);

            }
        });
    }

    private void settingOnClick(Button setting) {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent,SETTING_CODE);
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

                try {
                    final URL uri = new URL(WEATHER_URL + BuildConfig.WEATHER_API_KEY);

                    new Thread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            HttpsURLConnection urlConnection = null;
                            try {
                                urlConnection = (HttpsURLConnection) uri.openConnection();
                                urlConnection.setRequestMethod("GET");
                                urlConnection.setReadTimeout(10000);
                                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                                String result = in.lines().collect(Collectors.joining("\n"));
                                Gson gson = new Gson();
                                weatherRequest = gson.fromJson(result,WeatherRequest.class);

                                Intent intent = new Intent("showCityActivity");

                                intent.putExtra(CREATE_CITY, weatherRequest);
                                startActivity(intent);


                            } catch (Exception e) {
                                Log.e(TAG,"Fail connection",e);
                                e.printStackTrace();
                            } finally {
                                if (null != urlConnection) {
                                    urlConnection.disconnect();
                                }
                            }
                        }
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                        private String getLines(BufferedReader in) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                return in.lines().collect(Collectors.joining("\n"));
                            }
                            return null;
                        }
                    }).start();
                } catch (MalformedURLException e) {
                    Log.e(TAG,"Fail URI",e);
                    e.printStackTrace();
                }


            }
        });
        return adapter;
    }

    private City createCity(String name, int position) {
        City city = new City(name,position);
        return city;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }
}
