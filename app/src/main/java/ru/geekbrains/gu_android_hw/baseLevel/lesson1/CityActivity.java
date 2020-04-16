package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;

public class CityActivity extends AppCompatActivity {

    private int todayTemperature = 16;
    private int todaySpeed = 3;
    private int todayPressure = 740;

    TextView moscowTemperature;
    TextView moscowWindSpeed;
    TextView moscowPressure;

    TableRow windspeedRow;
    TableRow pressureRow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        moscowTemperature = findViewById(R.id.moscowTemperature);
        moscowWindSpeed = findViewById(R.id.windSpeed);
        moscowPressure = findViewById(R.id.pressure);

        windspeedRow = findViewById(R.id.windspeedRow);
        pressureRow = findViewById(R.id.pressureRow);

        Intent intent = getIntent();
        boolean isWindChecked = intent.getBooleanExtra("isWindChecked",false);
        boolean isPressureChecked = intent.getBooleanExtra("isPressureChecked",false);

        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
            showToast(instanceState + " - onCreate()");
        }
        else{
            instanceState = "Повторный запуск!";
            showToast(instanceState + " - onCreate()");
        }

        moscowTemperature.setText(((Integer)todayTemperature).toString());
        if (isWindChecked) {
            moscowWindSpeed.setText(((Integer)todaySpeed).toString());
        } else {
            windspeedRow.setVisibility(View.INVISIBLE);
        }
        if (isPressureChecked) {
            moscowPressure.setText(((Integer)todayPressure).toString());
        } else {
            pressureRow.setVisibility(View.INVISIBLE);
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
