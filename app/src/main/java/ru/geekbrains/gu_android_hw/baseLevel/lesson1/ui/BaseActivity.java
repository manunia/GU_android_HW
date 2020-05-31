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

    private static final String IsCelsiumUnit = "IS_CELSIUM_UNIT";

    public String units, unitsForRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);

        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        if (isCelsium()) {
            units = getString(R.string.unit,"\u2103");
            unitsForRequest = "metric";
        } else {
            units = getString(R.string.unit,"\u2109");
            unitsForRequest = "imperial";
        }

    }

    protected boolean isDarkTheme() {
        return sharedPref.getBoolean(IsDarkTheme, true);
    }

    protected boolean isCelsium() {
        return sharedPref.getBoolean(IsCelsiumUnit,true);
    }

    protected void setIsDarkTheme(boolean isDarkTheme) {
        sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsDarkTheme,isDarkTheme);
        editor.apply();
    }

    protected void  setIsCelsiumUnit(boolean isCelsiumUnit) {
        sharedPref = getSharedPreferences(NameSharedPreference,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IsCelsiumUnit,isCelsiumUnit);
        editor.apply();
    }
}
