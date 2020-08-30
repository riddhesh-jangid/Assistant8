package com.example.root.renametest;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by root on 16/1/18.
 */

public class homeworkservice extends IntentService {
    private NotificationManager alarmNotificationManager;

    public homeworkservice() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Pending homework");
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        homeworkbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (homeworkbroadcast.alarmUri == null) {
            homeworkbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        homeworkbroadcast.ringtone = RingtoneManager.getRingtone(this, homeworkbroadcast.alarmUri);
        homeworkbroadcast.ringtone.play();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, homeworkstop.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }

}
