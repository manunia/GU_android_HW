package ru.geekbrains.gu_android_hw.baseLevel.lesson1.servicies;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui.MainActivity;

public class BroadcastMsgReceiver extends BroadcastReceiver {

    private static int messageId = 10;

    private String message;

    public BroadcastMsgReceiver(String message) {
        this.message = message;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Broadcast Receiver")
                .setContentText(message);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(messageId++,builder.build());
    }


}
