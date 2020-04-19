package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;

public class CityActivity extends AppCompatActivity implements Constants {

    private TextView cityTemperature;
    private TextView cityWindSpeed;
    private TextView cityPressure;
    private TextView cityName;

    private TableRow windspeedRow;
    private TableRow pressureRow;

    private Button openBrouserButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        initFields();

        showInstanceStateStatus(savedInstanceState);

        getDataFromMainActivity();

        openBrouser();
    }

    private void getDataFromMainActivity() {
        CityEntity city = (CityEntity) getIntent().getExtras().getSerializable(CREATE_CITY);

        Intent intent = getIntent();
        boolean isWindChecked = intent.getBooleanExtra(WIND_SPEED_CHECK,false);
        boolean isPressureChecked = intent.getBooleanExtra(PRESSURE_CHECK,false);

        cityName.setText(city.getName());
        cityTemperature.setText(((Integer)city.getTodayTemperature()).toString());
        showParametr(isWindChecked,city.getTodaySpeed(), cityWindSpeed,windspeedRow);
        showParametr(isPressureChecked,city.getTodayPressure(), cityPressure,pressureRow);
    }

    private void initFields() {
        cityTemperature = findViewById(R.id.moscowTemperature);
        cityWindSpeed = findViewById(R.id.windSpeed);
        cityPressure = findViewById(R.id.pressure);
        cityName = findViewById(R.id.cityName);

        windspeedRow = findViewById(R.id.windspeedRow);
        pressureRow = findViewById(R.id.pressureRow);

        openBrouserButton = findViewById(R.id.openBrouserButton);
    }

    private void openBrouser() {
        openBrouserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://yandex.ru/pogoda";
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(browser);

            }
        });
    }

    //show or hide selected param
    private void showParametr(boolean isParametrChecked, int todayParam, TextView parametrText, TableRow parametrRow) {
        if (isParametrChecked) {
            parametrText.setText(((Integer)todayParam).toString());
        } else {
            parametrRow.setVisibility(View.INVISIBLE);
        }
    }

    private void showInstanceStateStatus(@Nullable Bundle savedInstanceState) {
        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
            showToast(instanceState + " - onCreate()");
        }
        else{
            instanceState = "Повторный запуск!";
            showToast(instanceState + " - onCreate()");
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        showToast("Повторный запуск!! - onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        showToast("onSaveInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy()");
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), "CityActivity " + message, Toast.LENGTH_SHORT).show();
        Log.d("Life_cicle_stack", "CityActivity " + message);
    }
}
