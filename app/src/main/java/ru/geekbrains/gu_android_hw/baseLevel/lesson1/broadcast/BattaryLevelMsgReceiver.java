package ru.geekbrains.gu_android_hw.baseLevel.lesson1.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui.MainActivity;

public class BattaryLevelMsgReceiver extends BroadcastReceiver {

    private static final String NAME_MSG = "MSG";
    private static final String TAG = "MessageBroadcastReceiver";
    private int messageId = 10;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Broadcast Receiver")
                .setContentText("Small battary level");
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(messageId++,builder.build());
    }
}
