package ru.geekbrains.gu_android_hw.baseLevel.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.geekbrains.gu_android_hw.R;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText textCity;
    String enteredText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        submitButton = findViewById(R.id.submit_buton);
        textCity = findViewById(R.id.edit_city);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredText = textCity.getText().toString();
                if (enteredText.length() == 0) {
                    Toast.makeText(MainActivity.this, "Invalid enter", Toast.LENGTH_SHORT).show();
                } else {
                    setContentView(R.layout.activity_main);
                }
            }
        });
    }
}
