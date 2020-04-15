package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;

public class CityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

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
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
