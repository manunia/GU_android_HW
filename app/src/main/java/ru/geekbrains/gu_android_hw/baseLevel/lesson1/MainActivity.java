package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.geekbrains.gu_android_hw.R;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText textCity;
    private String enteredText;

    private CheckBox windSpeedcheck;
    private CheckBox pressureCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        submitButton = findViewById(R.id.submit_buton);
        textCity = findViewById(R.id.edit_city);

        windSpeedcheck = findViewById(R.id.windSpeedCheck);
        pressureCheck = findViewById(R.id.pressureCheck);

        showInstanceStateStatus(savedInstanceState);

        submitButtonOnClick();
    }

    private void submitButtonOnClick() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredText = textCity.getText().toString();
                if (enteredText.length() == 0) {
                    Toast.makeText(MainActivity.this, "Invalid enter", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent("showCityActivity");
                    intent.putExtra("isWindChecked", windSpeedcheck.isChecked());
                    intent.putExtra("isPressureChecked", pressureCheck.isChecked());
                    startActivity(intent);
                }

            }
        });
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
    protected void onStart() {
        super.onStart();
        showToast("onStart()");
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
        Toast.makeText(getApplicationContext(), "MainActivity " + message, Toast.LENGTH_SHORT).show();
        Log.d("Life_cicle_stack", "MainActivity " + message);
    }
}
