package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import ru.geekbrains.gu_android_hw.R;

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SwitchCompat switchDarkTheme = findViewById(R.id.switch1);
        switchDarkTheme.setChecked(isDarkTheme());
        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setIsDarkTheme(isChecked);
                recreate();
            }
        });
    }
}
