package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

import ru.geekbrains.gu_android_hw.BuildConfig;
import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.App;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.HttpsConnection.RetrofitConnection;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CityDao;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CitySource;

public class MainActivity extends BaseActivity implements Constants, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private MenuItem cityName;
    private CitySource source;

    //проверяем введенное название города
    Pattern checkInputCity = Pattern.compile("^[A-Z,А-Я][a-z,а-я]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        toolbar = initToolbar();

        initList();

        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        cityName = menu.findItem(R.id.inputCity);
        final SearchView searchText = (SearchView) cityName.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showWeatherFromRequest(query);
                //source.addCity(new City(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
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
            new MyAlertDialogBuilder(this,"About",getResources().getString(R.string.about_developer)).build();
        }
        if (id == R.id.action_clear) {
            source.deleteAll();
            recreate();
        }
        return super.onOptionsItemSelected(item);
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

    private ListAdapter initList(){
        recyclerView = findViewById(R.id.recycler_view);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        CityDao cityDao = App.getInstance().getCityDao();
        source = new CitySource(cityDao);

        // Установим адаптер
        adapter = new ListAdapter(source,this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String name, int position) {
                showWeatherFromRequest(name);
            }
        });
        return adapter;
    }

    private void showWeatherFromRequest(String name) {

        RetrofitConnection retrofitConnection = new RetrofitConnection();
        retrofitConnection.initRetrofit();
        retrofitConnection.requestRetrofit(name, BuildConfig.WEATHER_API_KEY,MainActivity.this,source);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Snackbar.make(toolbar, "home", Snackbar.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.remove_context:
                City cityForRemove = source.getCities().get((int) adapter.getMenuPosition());
                source.removeCity(cityForRemove.id);
                adapter.notifyItemRemoved((int) adapter.getMenuPosition());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void savePreferences(SharedPreferences sharedPref) {
        String name = "Moscow";
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("1",name);
        editor.commit();
    }

    private void loadPreferences(SharedPreferences sharedPref) {
        String key = "1";


    }
}
