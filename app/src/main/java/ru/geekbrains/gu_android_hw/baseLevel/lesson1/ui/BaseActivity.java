package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;

public class BaseActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    private static final String NameSharedPreference = "MAIN";

    private static final String IsDarkTheme = "IS_DARK_THEME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);

        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

    }

    protected boolean isDarkTheme() {
        return sharedPref.getBoolean(IsDarkTheme, true);
    }

    protected void setIsDarkTheme(boolean isDarkTheme) {
        sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme,isDarkTheme);
        editor.apply();
    }
}
