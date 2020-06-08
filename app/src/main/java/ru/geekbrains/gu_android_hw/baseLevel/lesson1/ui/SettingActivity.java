package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.Constants;

public class SettingActivity extends BaseActivity implements Constants {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SwitchCompat switchDarkTheme = findViewById(R.id.switch1);
        final SwitchCompat switchCelsium = findViewById(R.id.switch2);

        switchDarkTheme.setChecked(isDarkTheme());
        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsDarkTheme(isChecked);
                recreate();
            }
        });

        switchCelsium.setChecked(isCelsium());
        switchCelsium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsCelsiumUnit(isChecked);
                recreate();
            }
        });

    }
}
