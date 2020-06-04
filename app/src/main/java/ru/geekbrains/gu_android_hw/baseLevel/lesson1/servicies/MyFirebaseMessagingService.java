package ru.geekbrains.gu_android_hw.baseLevel.lesson1.servicies;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ru.geekbrains.gu_android_hw.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public int messageId = 0;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("Push message", remoteMessage.getNotification().getBody());
        String title = remoteMessage.getNotification().getTitle();
        if (title == null) {
            title = "Push message";
        }
        String text = remoteMessage.getNotification().getBody();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(messageId++,builder.build());
    }

    @Override
    public void onNewToken(String token) {
        Log.d("PushMessage", "Token " + token);
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {
    }
}
