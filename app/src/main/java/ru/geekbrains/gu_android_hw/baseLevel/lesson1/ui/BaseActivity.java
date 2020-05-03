package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.gu_android_hw.R;

public class BaseActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "MAIN";

    private static final String IsDarkTheme = "IS_DARK_THEME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

    protected boolean isDarkTheme() {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getBoolean(IsDarkTheme, true);
    }

    protected void setIsDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme,isDarkTheme);
        editor.apply();
    }
}
