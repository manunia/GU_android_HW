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

public class MainActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

//        submitButton = findViewById(R.id.submit_buton);
//        textCity = findViewById(R.id.edit_city);
//
//        windSpeedcheck = findViewById(R.id.windSpeedCheck);
//        pressureCheck = findViewById(R.id.pressureCheck);
//
//
//        submitButtonOnClick();
    }

//    private void submitButtonOnClick() {
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enteredText = textCity.getText().toString();
//                if (enteredText.length() == 0) {
//                    Toast.makeText(MainActivity.this, "Invalid enter", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent("showCityActivity");
//                    intent.putExtra(WIND_SPEED_CHECK, windSpeedcheck.isChecked());
//                    intent.putExtra(PRESSURE_CHECK, pressureCheck.isChecked());
//                    intent.putExtra(CREATE_CITY, createCity());
//                    startActivity(intent);
//                }
//
//            }
//        });
//    }
//
//    private CityEntity createCity() {
//        CityEntity city = new CityEntity();
//        city.setName(textCity.getText().toString());
//        city.setTodayTemperature(-14);
//        city.setTodayPressure(760);
//        city.setTodaySpeed(3);
//        return city;
//    }

}
