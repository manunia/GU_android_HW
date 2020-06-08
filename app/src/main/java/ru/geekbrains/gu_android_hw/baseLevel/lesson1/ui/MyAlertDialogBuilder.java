package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import ru.geekbrains.gu_android_hw.R;

public class MyAlertDialogBuilder {
    private Context context;
    private String title;
    private String messege;

    public MyAlertDialogBuilder(Context context, String title, String messege) {
        this.context = context;
        this.title = title;
        this.messege = messege;
    }

    public void build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(messege)
                .setIcon(R.mipmap.ic_launcher_round)
                .setCancelable(false)
                .setPositiveButton(R.string.button,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
